package org.xtick;

import org.xtick.api.*;
import org.xtick.bean.Bid;
import org.xtick.bean.Minute;
import org.xtick.bean.QuantPacket;
import org.xtick.bean.base.XTickStockCalendar;
import org.xtick.bean.base.XTickStockInfo;
import org.xtick.bean.core.*;
import org.xtick.bean.finance.*;
import org.xtick.bean.hot.XTickBKInfo;
import org.xtick.bean.hot.XTickEmotion;
import org.xtick.bean.hot.XTickNewsInfo;
import org.xtick.bean.hot.XTickRelationBK;
import org.xtick.constant.MethodType;
import org.xtick.constant.XTickConst;
import org.xtick.util.JsonUtil;
import org.xtick.util.XTickUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * 行情实时数据、财务报表数据获取API接口。
 * 官网：http://www.xtick.top/
 */
public class XTickStockApiClient {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取财务报表代码示例
     *
     * @param code
     * @throws IOException
     */
    /**
     * 获取行情数据代码示例
     *
     * @param code
     * @throws IOException
     */
    public static void demoForMarketApi(String code) throws IOException {
        XTickMarketApi xTickMarketApi = new XTickMarketApi();
        String dataStr = xTickMarketApi.getCalendar(code, LocalDate.now().minusDays(3).toString(), LocalDate.now().minusDays(3).toString(), XTickConst.token, MethodType.POST);//获取所有股票代码，包括沪深京股票、ETF、港股、指数
        List<XTickStockCalendar> calendars = JsonUtil.jsonToList(dataStr, XTickStockCalendar.class);
        System.out.println(String.format("[calendar]code=%s,size=%s", code, calendars == null ? 0 : calendars.size()));
        String symbol = "sz";
        dataStr = xTickMarketApi.getStockInfo(symbol, XTickConst.token, MethodType.POST);//获取所有股票代码，包括沪深京股票、ETF、港股、指数
        List<XTickStockInfo> stockInfos = JsonUtil.jsonToList(dataStr, XTickStockInfo.class);
        System.out.println(String.format("[stockInfo]symbol=%s,size=%s", symbol, stockInfos == null ? 0 : stockInfos.size()));

        int type = 1;//沪深京A股Type=1，港股Type=3
        String startDate = LocalDate.now().minusDays(30).toString();
        String endDate = LocalDate.now().toString();
        for (String period : XTickConst.historyKlinePeriods) {//获取K线数据
            for (String fq : XTickConst.dividends) {
                String result = xTickMarketApi.getKlineMarket(type, code, period, fq, startDate, endDate, XTickConst.token, MethodType.POST);
                List<XTickKlineData> klines = JsonUtil.jsonToList(result, XTickKlineData.class);
                System.out.println(String.format("[kline.market]time=%s,code=%s,period=%s,fq=%s,startDate=%s,endDate=%s,size=%s", LocalDateTime.now().format(formatter), code, period, fq, startDate, endDate, klines == null ? 0 : klines.size()));
            }
        }
        //当天实时分钟数据
        for (String fq : XTickConst.dividends) {
            String result = xTickMarketApi.getKlineMinute(type, code, fq, XTickConst.token, MethodType.POST);
            List<XTickKlineData> klines = JsonUtil.jsonToList(result, XTickKlineData.class);
            System.out.println(String.format("[kline.minute]time=%s,code=%s,fq=%s,startDate=%s,endDate=%s,size=%s", LocalDateTime.now().format(formatter), code, fq, startDate, endDate, klines == null ? 0 : klines.size()));
        }
    }

    /**
     * 获取金融指标数据代码示例
     *
     * @param code
     * @throws IOException
     */
    public static void demoForIndicatorApi(String code) throws IOException {
        XTickIndicatorApi xTickIndicatorApi = new XTickIndicatorApi();
        int type = 1;//沪深京A股type=1，港股type=3，沪深指数type=10，沪深ETF type=20
        String period = "1d";
        String fq = "front";
        String startDate = LocalDate.now().minusDays(180).toString();
        String endDate = LocalDate.now().toString();
        //KDJ指标，若计算J值，则J值=3×K值-2×D值
        String result = xTickIndicatorApi.stoch(type, code, period, fq, startDate, endDate, XTickConst.token, 9, 5, 2, 5, 2, MethodType.POST);
        List<Map> datas = JsonUtil.jsonToList(result, Map.class);
        System.out.println(String.format("[indicator]time=%s,code=%s,period=%s,fq=%s,startDate=%s,endDate=%s,size=%s", LocalDateTime.now().format(formatter), code, period, fq, startDate, endDate, datas == null ? 0 : datas.size()));
        //MACD指标，DIF=EMA(C,N1)-EMA(C,N2)，DEA=EMA(DIF,M)，MACD=DIF-DEA
        result = xTickIndicatorApi.macd(type, code, period, fq, startDate, endDate, XTickConst.token, 2, 12, 26, 9, MethodType.POST);
        datas = JsonUtil.jsonToList(result, Map.class);
        System.out.println(String.format("[indicator]time=%s,code=%s,period=%s,fq=%s,startDate=%s,endDate=%s,size=%s", LocalDateTime.now().format(formatter), code, period, fq, startDate, endDate, datas == null ? 0 : datas.size()));
    }

    /**
     * 获取盯盘数据代码示例
     *
     * @param code
     * @throws IOException
     */
    public static void demoForWatchApi(String code) throws IOException {
        XTickWatchApi xTickWatchApi = new XTickWatchApi();
        int type = 1;//沪深京A股type=1，港股type=3，沪深指数type=10，沪深ETF type=20
        LocalDate tradeDate = LocalDate.now();

        //某个交易日的买卖五档历史数据
        String result = xTickWatchApi.getTickHistory(type, code, tradeDate.toString(), XTickConst.token, MethodType.POST);
        List<XTickLevelOne> levelOneOfDay = JsonUtil.jsonToList(result, XTickLevelOne.class);
        System.out.println(String.format("[watch.lv1.history]time=%s,code=%s,tradeDate=%s,size=%s", LocalDateTime.now().minusDays(1).format(formatter), code, tradeDate, levelOneOfDay == null ? 0 : levelOneOfDay.size()));

        //获取市场成交额实时信息
        result = xTickWatchApi.getAmount(tradeDate.toString(), XTickConst.token, MethodType.POST);
        XTickMarketCount xTickMarketCount = JsonUtil.jsonToObj(result, XTickMarketCount.class);
        System.out.println(String.format("[watch.amount]time=%s,tradeDate=%s,size=%s", LocalDateTime.now().format(formatter), tradeDate, xTickMarketCount == null ? 0 : 1));

        //获取龙虎榜详情历史数据
        result = xTickWatchApi.getLonghubang(tradeDate.toString(), XTickConst.token, MethodType.POST);
        List<XTickLonghubang> xTickLonghubangs = JsonUtil.jsonToList(result, XTickLonghubang.class);
        System.out.println(String.format("[watch.longhubang]time=%s,tradeDate=%s,size=%s", LocalDateTime.now().format(formatter), tradeDate, xTickLonghubangs == null ? 0 : xTickLonghubangs.size()));

        //买卖五档-实时数据
        result = xTickWatchApi.getFiveLevel(type, code, XTickConst.token, MethodType.POST);
        List<XTickLevelOne> fiveLevels = JsonUtil.jsonToList(result, XTickLevelOne.class);
        System.out.println(String.format("[watch.five.level]time=%s,code=%s,size=%s", LocalDateTime.now().format(formatter), code, fiveLevels == null ? 0 : fiveLevels.size()));

        //分钟K线-实时数据
        result = xTickWatchApi.getOrderMinute(type, code, "1", XTickConst.token, MethodType.POST);
        List<Minute> orderMinutes = JsonUtil.jsonToList(result, Minute.class);
        System.out.println(String.format("[watch.order.minute]time=%s,code=%s,size=%s", LocalDateTime.now().format(formatter), code, orderMinutes == null ? 0 : orderMinutes.size()));

        //日K线-实时数据
        result = xTickWatchApi.getOrderDay(type, code, "1", XTickConst.token, MethodType.POST);
        List<Minute> orderDays = JsonUtil.jsonToList(result, Minute.class);
        System.out.println(String.format("[watch.order.day]time=%s,code=%s,size=%s", LocalDateTime.now().format(formatter), code, orderDays == null ? 0 : orderDays.size()));

    }


    /**
     * 获取财务数据代码示例
     *
     * @param code
     * @throws IOException
     */
    public static void demoForFinancialApi(String code) throws IOException {
        XTickMarketApi xTickMarketApi = new XTickMarketApi();
        String startDate = LocalDate.now().minusYears(1).toString();
        String endDate = LocalDate.now().toString();

        // 股东数
        String dataStr = xTickMarketApi.getHolderNum(code, startDate, endDate, XTickConst.token, MethodType.POST);
        List<XTickFinanceHoldernum> holderNums = JsonUtil.jsonToList(dataStr, XTickFinanceHoldernum.class);
        System.out.println(String.format("[financial.holdernum]code=%s,startDate=%s,endDate=%s,size=%s", code, startDate, endDate, holderNums == null ? 0 : holderNums.size()));

        // 财务指标
        dataStr = xTickMarketApi.getCoreFinancial(code, startDate, endDate, XTickConst.token, MethodType.POST);
        List<XTickFinancialCore> coreFinancials = JsonUtil.jsonToList(dataStr, XTickFinancialCore.class);
        System.out.println(String.format("[financial.core]code=%s,result=%s", code, coreFinancials != null  ? coreFinancials.size() : 0));

        // 十大股东
        dataStr = xTickMarketApi.getTopHolder(code, startDate, endDate, XTickConst.token, MethodType.POST);
        List<XTickFinanceTop10holder> topHolders = JsonUtil.jsonToList(dataStr, XTickFinanceTop10holder.class);
        System.out.println(String.format("[financial.topholder]code=%s,startDate=%s,endDate=%s,size=%s", code, startDate, endDate, topHolders == null ? 0 : topHolders.size()));

        // 十大流通股东
        dataStr = xTickMarketApi.getTopFlowHolder(code, startDate, endDate, XTickConst.token, MethodType.POST);
        List<XTickFinanceTop10flowholder> topFlowHolders = JsonUtil.jsonToList(dataStr, XTickFinanceTop10flowholder.class);
        System.out.println(String.format("[financial.topflowholder]code=%s,startDate=%s,endDate=%s,size=%s", code, startDate, endDate, topFlowHolders == null ? 0 : topFlowHolders.size()));
    }

    /**
     * 获取量化API接口数据
     *
     * @param code
     * @throws IOException
     */
    public static void demoForQuantApi(String code) throws IOException {
        XTickQuantApi xTickQuantApi = new XTickQuantApi();
        String dataStr = xTickQuantApi.getQunatData(1, "all", XTickConst.token, MethodType.POST);
        QuantPacket quantDataPacket = JsonUtil.jsonToObj(dataStr, QuantPacket.class);
        System.out.println(String.format("[quant.data]size=%s", quantDataPacket == null ? 0 : quantDataPacket.toFieldMap().size()));

//        dataStr = xTickQuantApi.getQunatTime(1, "all", XTickConst.token, MethodType.POST);
//        QuantPacket quantTimePacket = JsonUtil.jsonToObj(dataStr, QuantPacket.class);
//        System.out.println(String.format("[quant.time]size=%s", quantTimePacket == null ? 0 : quantTimePacket.toFieldMap().size()));

        XTickUtil.toFile("F://quantByField.json", JsonUtil.toJsonWithStrFormat(quantDataPacket.toFieldMap()));//保存数据到文件,按照指标字段组织数据，方便按字段查找所有股票数据
        XTickUtil.toFile("F://quantByStock.json", JsonUtil.toJsonWithStrFormat(quantDataPacket.toStockMap()));//保存数据到文件,按照股票code组织数据，方便按个股查找所有字段数据

        //量化因子-历史接口
        String tradeDate = LocalDate.now().toString();
        dataStr = xTickQuantApi.getQuantHistory(tradeDate, XTickConst.token, MethodType.POST);
        quantDataPacket = JsonUtil.jsonToObj(dataStr, QuantPacket.class);
        System.out.println(String.format("[quant.history]size=%s", quantDataPacket == null ? 0 : quantDataPacket.toFieldMap().size()));
    }

    public static void demoForCoreApi(String code) throws IOException {
        XTickCoreApi xTickCoreApi = new XTickCoreApi();
        String startDate =LocalDate.now().minusYears(1).toString();
        String endDate = LocalDate.now().toString();
        String tradeDate = "2026-04-03";
        String field = "x001,x002,x003,x004,x005";
        String dataStr = xTickCoreApi.getCoreTime(1, code, field, XTickConst.token, MethodType.POST);
        XTickCoreTime  coreTime = JsonUtil.jsonToObj(dataStr, XTickCoreTime.class);
        System.out.println(String.format("[core.time]code=%s,indicators=%s", code, coreTime != null ? 1 : 0));

        dataStr = xTickCoreApi.getCoreChuQuan(1, code, startDate, endDate, XTickConst.token, MethodType.POST);
        List<XTickStockInfo> stockInfos = JsonUtil.jsonToList(dataStr, XTickStockInfo.class);
        System.out.println(String.format("[core.chuquan]startDate=%s,endDate=%s,size=%s", startDate, endDate, stockInfos == null ? 0 : stockInfos.size()));

        dataStr = xTickCoreApi.getCoreTingpai(1, "603311", startDate, endDate, XTickConst.token, MethodType.POST);
        List<XTickTingPaiStock> tingPaiStocks = JsonUtil.jsonToList(dataStr, XTickTingPaiStock.class);
        System.out.println(String.format("[core.tingpai]startDate=%s,endDate=%s,size=%s", startDate, endDate, tingPaiStocks == null ? 0 : tingPaiStocks.size()));

        dataStr = xTickCoreApi.getCoreSt(1, "600636", startDate, endDate, XTickConst.token, MethodType.POST);
        List<XTickStHistory> stStocks = JsonUtil.jsonToList(dataStr, XTickStHistory.class);
        System.out.println(String.format("[core.st]startDate=%s,endDate=%s,size=%s", startDate, endDate, stStocks == null ? 0 : stStocks.size()));

        dataStr = xTickCoreApi.getCorePrice(1, code, 1, startDate, endDate, XTickConst.token, MethodType.POST);
        List<XTickStockHistoryPrice> historyPriceInfos = JsonUtil.jsonToList(dataStr, XTickStockHistoryPrice.class);
        System.out.println(String.format("[core.historyprice]startDate=%s,endDate=%s,size=%s", startDate, endDate, historyPriceInfos == null ? 0 : historyPriceInfos.size()));

        dataStr = xTickCoreApi.getCoreFenbi(1, code, tradeDate, XTickConst.token, MethodType.POST);
        List<XTickTimeDeal> fenbiDatas = JsonUtil.jsonToList(dataStr, XTickTimeDeal.class);
        System.out.println(String.format("[core.fenbi]tradeDate=%s,size=%s", tradeDate, fenbiDatas == null ? 0 : fenbiDatas.size()));

        dataStr = xTickCoreApi.getCoreFenjia(1, code, tradeDate, XTickConst.token, MethodType.POST);
        List<XTickTimePrice> fenjiaDatas = JsonUtil.jsonToList(dataStr, XTickTimePrice.class);
        System.out.println(String.format("[core.fenjia]tradeDate=%s,size=%s", tradeDate, fenjiaDatas == null ? 0 : fenjiaDatas.size()));

        //竞价数据-实时接口
        dataStr = xTickCoreApi.getCoreBidTime(1, code, "", XTickConst.token, MethodType.POST);
        List<XTickBidTime> bidTimes = JsonUtil.jsonToList(dataStr, XTickBidTime.class);
        System.out.println(String.format("[core.bidtime]code=%s,size=%s", code, bidTimes == null ? 0 : bidTimes.size()));

    }


    public static void demoForHotApi(String code) throws IOException {
        XTickHotApi xTickHotApi = new XTickHotApi();
        String tradeDate = LocalDate.now().toString();
        String dataStr = xTickHotApi.getHotMoney(1, code, tradeDate, LocalDate.now().toString(), XTickConst.token, MethodType.POST);
        List<XTickTransactionCount> transactionDatas = JsonUtil.jsonToList(dataStr, XTickTransactionCount.class);
        System.out.println(String.format("[hot.money]tradeDate=%s,size=%s", tradeDate, transactionDatas == null ? 0 : transactionDatas.size()));

        dataStr = xTickHotApi.getHotBoard(1, 1, LocalDate.now().toString(), XTickConst.token, MethodType.POST);
        List<XTickStockBoard> xTickStockBoards = JsonUtil.jsonToList(dataStr, XTickStockBoard.class);
        System.out.println(String.format("[hot.board]tradeDate=%s,size=%s", tradeDate, xTickStockBoards == null ? 0 : xTickStockBoards.size()));

        dataStr = xTickHotApi.getHotNews(30, LocalDate.now().toString(), XTickConst.token, MethodType.POST);
        List<XTickNewsInfo> xTickNewsInfos = JsonUtil.jsonToList(dataStr, XTickNewsInfo.class);
        System.out.println(String.format("[hot.news]tradeDate=%s,size=%s", tradeDate, xTickNewsInfos == null ? 0 : xTickNewsInfos.size()));

        dataStr = xTickHotApi.getHotTimekline(1, "000001", XTickConst.token, MethodType.POST);
        List<Minute> xTickMinutes = JsonUtil.jsonToList(dataStr, Minute.class);
        System.out.println(String.format("[hot.timekline]tradeDate=%s,size=%s", tradeDate, xTickMinutes == null ? 0 : xTickMinutes.size()));

        dataStr = xTickHotApi.getHotBk("sw3", XTickConst.token, MethodType.POST);
        List<XTickBKInfo> xTickBKInfos = JsonUtil.jsonToList(dataStr, XTickBKInfo.class);
        System.out.println(String.format("[hot.bk]symbol=%s,size=%s", "sw3", xTickBKInfos == null ? 0 : xTickBKInfos.size()));

        dataStr = xTickHotApi.getHotGainian("000001", XTickConst.token, MethodType.POST);
        List<XTickRelationBK> xTickRelationBKs = JsonUtil.jsonToList(dataStr, XTickRelationBK.class);
        System.out.println(String.format("[hot.gainian]code=%s,size=%s", "000001", xTickRelationBKs == null ? 0 : xTickRelationBKs.size()));

        //市场情绪-实时接口
        dataStr = xTickHotApi.getHotEmotion(1, LocalDate.now().toString(), XTickConst.token, MethodType.POST);
        List<XTickEmotion> emotions = JsonUtil.jsonToList(dataStr, XTickEmotion.class);
        System.out.println(String.format("[hot.emotion]tradeDate=%s,size=%d", LocalDate.now(), emotions != null ? emotions.size() : 0));

        //竞价数据-历史接口
        dataStr = xTickHotApi.getHotBidHistory(1, code, tradeDate, LocalDate.now().toString(), XTickConst.token, MethodType.POST);
        List<Bid> hotBids = JsonUtil.jsonToList(dataStr, Bid.class);
        System.out.println(String.format("[hot.bid.history]code=%s,startDate=%s,endDate=%s,size=%s", code, tradeDate, LocalDate.now(), hotBids == null ? 0 : hotBids.size()));

        //竞价详情-实时接口
        dataStr = xTickHotApi.getHotBidDetail(1, code, tradeDate, XTickConst.token, MethodType.POST);
        List<Bid> bidDetails = JsonUtil.jsonToList(dataStr, Bid.class);
        System.out.println(String.format("[hot.bid.detail]code=%s,tradeDate=%s,size=%s", code, LocalDate.now(), bidDetails == null ? 0 : bidDetails.size()));
    }


    /**
     * 所有API接口的Demo示例
     * 会调用所有接口，因此调用API接口次数多，请按需调用
     *
     * @throws IOException
     */
    public static void allDemo() throws IOException {
        String code = "000001";
        demoForMarketApi(code);//获取行情数据
        demoForFinancialApi(code);//获取财务数据
        demoForIndicatorApi(code);//获取指标数据
        demoForWatchApi(code);//获取盯盘数据
        demoForCoreApi(code);//获取核心接口数据
        demoForHotApi(code);//获取短线热点数据
        demoForQuantApi(code);//获取量化数据
    }


    public static void main(String[] args) throws IOException {
        allDemo();//所有API接口的Demo示例,会调用所有接口，因此调用API接口次数多，请按需调用
        //demoForQuantApi("000001");//获取核心接口数据
    }
}
