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
 * XTick API接口调用示例代码。
 * 官网：http://www.xtick.top/
 */
public class XTickStockApiClient {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取行情数据代码示例
     */
    public static void demoForMarketApi(String code) throws IOException {
        XTickMarketApi xTickMarketApi = new XTickMarketApi();
        String dataStr = xTickMarketApi.getCalendar(code, LocalDate.now().minusDays(3).toString(), LocalDate.now().minusDays(3).toString(), XTickConst.token, MethodType.POST);
        List<XTickStockCalendar> calendars = JsonUtil.jsonToList(dataStr, XTickStockCalendar.class);
        System.out.println(String.format("[calendar]code=%s,size=%s", code, calendars == null ? 0 : calendars.size()));

        String symbol = "sz";
        dataStr = xTickMarketApi.getStockInfo(symbol, XTickConst.token, MethodType.POST);
        List<XTickStockInfo> stockInfos = JsonUtil.jsonToList(dataStr, XTickStockInfo.class);
        System.out.println(String.format("[stockInfo]symbol=%s,size=%s", symbol, stockInfos == null ? 0 : stockInfos.size()));

        int type = 1;//沪深京A股Type=1，港股Type=3
        String startDate = LocalDate.now().minusDays(30).toString();
        String endDate = LocalDate.now().toString();
        for (String period : XTickConst.historyKlinePeriods) {
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
            System.out.println(String.format("[kline.minute]time=%s,code=%s,fq=%s,size=%s", LocalDateTime.now().format(formatter), code, fq, klines == null ? 0 : klines.size()));
        }
    }

    /**
     * 获取金融指标数据代码示例
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
        System.out.println(String.format("[indicator.stoch]time=%s,code=%s,period=%s,fq=%s,size=%s", LocalDateTime.now().format(formatter), code, period, fq, datas == null ? 0 : datas.size()));
        //MACD指标
        result = xTickIndicatorApi.macd(type, code, period, fq, startDate, endDate, XTickConst.token, 2, 12, 26, 9, MethodType.POST);
        datas = JsonUtil.jsonToList(result, Map.class);
        System.out.println(String.format("[indicator.macd]time=%s,code=%s,period=%s,fq=%s,size=%s", LocalDateTime.now().format(formatter), code, period, fq, datas == null ? 0 : datas.size()));
    }

    /**
     * 获取盯盘数据代码示例
     */
    public static void demoForWatchApi(String code) throws IOException {
        XTickWatchApi xTickWatchApi = new XTickWatchApi();
        int type = 1;//沪深京A股type=1，港股type=3，沪深指数type=10，沪深ETF type=20
        LocalDate tradeDate = LocalDate.now();

        //日K线-实时数据
        String result = xTickWatchApi.getOrderDay(type, code, XTickConst.token, MethodType.POST);
        List<XTickKlineData> orderDays = JsonUtil.jsonToList(result, XTickKlineData.class);
        System.out.println(String.format("[watch.order.day]time=%s,code=%s,size=%s", LocalDateTime.now().format(formatter), code, orderDays == null ? 0 : orderDays.size()));

        //分钟K线-实时数据
        result = xTickWatchApi.getOrderMinute(type, code, XTickConst.token, MethodType.POST);
        List<XTickKlineData> orderMinutes = JsonUtil.jsonToList(result, XTickKlineData.class);
        System.out.println(String.format("[watch.order.minute]time=%s,code=%s,size=%s", LocalDateTime.now().format(formatter), code, orderMinutes == null ? 0 : orderMinutes.size()));

        //深度行情-实时数据
        result = xTickWatchApi.getDeep(type, code, XTickConst.token, MethodType.POST);
        List<XTickLevelOne> deepDatas = JsonUtil.jsonToList(result, XTickLevelOne.class);
        System.out.println(String.format("[watch.deep]time=%s,code=%s,size=%s", LocalDateTime.now().format(formatter), code, deepDatas == null ? 0 : deepDatas.size()));

        //深度行情-历史数据
        result = xTickWatchApi.getTickHistory(type, code, tradeDate.toString(), XTickConst.token, MethodType.POST);
        List<XTickLevelOne> levelOneOfDay = JsonUtil.jsonToList(result, XTickLevelOne.class);
        System.out.println(String.format("[watch.history]time=%s,code=%s,tradeDate=%s,size=%s", LocalDateTime.now().format(formatter), code, tradeDate, levelOneOfDay == null ? 0 : levelOneOfDay.size()));

        //成交统计-实时接口
        result = xTickWatchApi.getAmount(tradeDate.toString(), XTickConst.token, MethodType.POST);
        XTickMarketCount xTickMarketCount = JsonUtil.jsonToObj(result, XTickMarketCount.class);
        System.out.println(String.format("[watch.amount]time=%s,tradeDate=%s,size=%s", LocalDateTime.now().format(formatter), tradeDate, xTickMarketCount == null ? 0 : 1));

        //日K线复权-更新接口
        result = xTickWatchApi.getFqKline(type, "1", tradeDate.toString(), XTickConst.token, MethodType.POST);
        List<XTickKlineData> fqKlines = JsonUtil.jsonToList(result, XTickKlineData.class);
        System.out.println(String.format("[watch.fqkline]time=%s,type=%s,tradeDate=%s,size=%s", LocalDateTime.now().format(formatter), type, tradeDate, fqKlines == null ? 0 : fqKlines.size()));
    }

    /**
     * 获取财务数据代码示例
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
        System.out.println(String.format("[financial.core]code=%s,result=%s", code, coreFinancials != null ? coreFinancials.size() : 0));

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
     */
    public static void demoForQuantApi(String code) throws IOException {
        XTickQuantApi xTickQuantApi = new XTickQuantApi();
        String dataStr = xTickQuantApi.getQunatData(1, "all", XTickConst.token, MethodType.POST);
        QuantPacket quantDataPacket = JsonUtil.jsonToObj(dataStr, QuantPacket.class);
        System.out.println(String.format("[quant.data]size=%s", quantDataPacket == null ? 0 : quantDataPacket.toFieldMap().size()));

        XTickUtil.toFile("F://quantByField.json", JsonUtil.toJsonWithStrFormat(quantDataPacket.toFieldMap()));
        XTickUtil.toFile("F://quantByStock.json", JsonUtil.toJsonWithStrFormat(quantDataPacket.toStockMap()));

        //量化因子-历史接口
        String tradeDate = LocalDate.now().minusDays(1).toString();
        dataStr = xTickQuantApi.getQuantHistory(tradeDate, XTickConst.token, MethodType.POST);
        quantDataPacket = JsonUtil.jsonToObj(dataStr, QuantPacket.class);
        System.out.println(String.format("[quant.history]size=%s", quantDataPacket == null ? 0 : quantDataPacket.toFieldMap().size()));
    }

    /**
     * 获取核心数据代码示例
     */
    public static void demoForCoreApi(String code) throws IOException {
        XTickCoreApi xTickCoreApi = new XTickCoreApi();
        String startDate = LocalDate.now().minusYears(1).toString();
        String endDate = LocalDate.now().toString();
        String tradeDate = "2026-04-03";
        String field = "x001,x002,x003,x004,x005";

        //核心指标-实时接口
        String dataStr = xTickCoreApi.getCoreTime(1, code, field, XTickConst.token, MethodType.POST);
        XTickCoreTime coreTime = JsonUtil.jsonToObj(dataStr, XTickCoreTime.class);
        System.out.println(String.format("[core.time]code=%s,indicators=%s", code, coreTime != null ? 1 : 0));

        //除权变更数据
        dataStr = xTickCoreApi.getCoreChuQuan(1, code, startDate, endDate, XTickConst.token, MethodType.POST);
        List<XTickStockInfo> stockInfos = JsonUtil.jsonToList(dataStr, XTickStockInfo.class);
        System.out.println(String.format("[core.chuquan]startDate=%s,endDate=%s,size=%s", startDate, endDate, stockInfos == null ? 0 : stockInfos.size()));

        //停牌数据
        dataStr = xTickCoreApi.getCoreTingpai(1, "603311", startDate, endDate, XTickConst.token, MethodType.POST);
        List<XTickTingPaiStock> tingPaiStocks = JsonUtil.jsonToList(dataStr, XTickTingPaiStock.class);
        System.out.println(String.format("[core.tingpai]startDate=%s,endDate=%s,size=%s", startDate, endDate, tingPaiStocks == null ? 0 : tingPaiStocks.size()));

        //ST数据
        dataStr = xTickCoreApi.getCoreSt(1, "600636", startDate, endDate, XTickConst.token, MethodType.POST);
        List<XTickStHistory> stStocks = JsonUtil.jsonToList(dataStr, XTickStHistory.class);
        System.out.println(String.format("[core.st]startDate=%s,endDate=%s,size=%s", startDate, endDate, stStocks == null ? 0 : stStocks.size()));

        //涨跌停价格
        dataStr = xTickCoreApi.getCorePrice(1, code, 1, startDate, endDate, XTickConst.token, MethodType.POST);
        List<XTickStockHistoryPrice> historyPriceInfos = JsonUtil.jsonToList(dataStr, XTickStockHistoryPrice.class);
        System.out.println(String.format("[core.price]startDate=%s,endDate=%s,size=%s", startDate, endDate, historyPriceInfos == null ? 0 : historyPriceInfos.size()));

        //分笔数据-历史数据
        dataStr = xTickCoreApi.getCoreFenbi(1, code, tradeDate, XTickConst.token, MethodType.POST);
        List<XTickTimeDeal> fenbiDatas = JsonUtil.jsonToList(dataStr, XTickTimeDeal.class);
        System.out.println(String.format("[core.fenbi]tradeDate=%s,size=%s", tradeDate, fenbiDatas == null ? 0 : fenbiDatas.size()));

        //分价数据
        dataStr = xTickCoreApi.getCoreFenjia(1, code, tradeDate, XTickConst.token, MethodType.POST);
        List<XTickTimePrice> fenjiaDatas = JsonUtil.jsonToList(dataStr, XTickTimePrice.class);
        System.out.println(String.format("[core.fenjia]tradeDate=%s,size=%s", tradeDate, fenjiaDatas == null ? 0 : fenjiaDatas.size()));

        //竞价数据-实时接口
        dataStr = xTickCoreApi.getCoreBidTime(1, code, "", XTickConst.token, MethodType.POST);
        List<XTickBidTime> bidTimes = JsonUtil.jsonToList(dataStr, XTickBidTime.class);
        System.out.println(String.format("[core.bidtime]code=%s,size=%s", code, bidTimes == null ? 0 : bidTimes.size()));
    }

    /**
     * 获取短线热点数据代码示例
     */
    public static void demoForHotApi(String code) throws IOException {
        XTickHotApi xTickHotApi = new XTickHotApi();
        String tradeDate = LocalDate.now().toString();

        //连板天梯-实时接口
        String dataStr = xTickHotApi.getHotBoard(1, 1, tradeDate, XTickConst.token, MethodType.POST);
        List<XTickStockBoard> xTickStockBoards = JsonUtil.jsonToList(dataStr, XTickStockBoard.class);
        System.out.println(String.format("[hot.board]tradeDate=%s,size=%s", tradeDate, xTickStockBoards == null ? 0 : xTickStockBoards.size()));

        //市场情绪-实时接口
        dataStr = xTickHotApi.getHotEmotion(1, tradeDate, XTickConst.token, MethodType.POST);
        List<XTickEmotion> emotions = JsonUtil.jsonToList(dataStr, XTickEmotion.class);
        System.out.println(String.format("[hot.emotion]tradeDate=%s,size=%d", tradeDate, emotions != null ? emotions.size() : 0));

        //资金流向-实时接口
        dataStr = xTickHotApi.getHotTimeMoney(1, code, XTickConst.token, MethodType.POST);
        List<XTickTransactionCount> timeMoneyDatas = JsonUtil.jsonToList(dataStr, XTickTransactionCount.class);
        System.out.println(String.format("[hot.timemoney]code=%s,size=%s", code, timeMoneyDatas == null ? 0 : timeMoneyDatas.size()));

        //资金流向-历史接口
        dataStr = xTickHotApi.getHotHistoryMoney(1, code, tradeDate, tradeDate, XTickConst.token, MethodType.POST);
        List<XTickTransactionCount> historyMoneyDatas = JsonUtil.jsonToList(dataStr, XTickTransactionCount.class);
        System.out.println(String.format("[hot.historymoney]code=%s,tradeDate=%s,size=%s", code, tradeDate, historyMoneyDatas == null ? 0 : historyMoneyDatas.size()));

        //竞价数据-历史接口
        dataStr = xTickHotApi.getHotBidHistory(1, code, tradeDate, tradeDate, XTickConst.token, MethodType.POST);
        List<Bid> hotBids = JsonUtil.jsonToList(dataStr, Bid.class);
        System.out.println(String.format("[hot.bid.history]code=%s,tradeDate=%s,size=%s", code, tradeDate, hotBids == null ? 0 : hotBids.size()));

        //竞价详情-实时接口
        dataStr = xTickHotApi.getHotBidDetail(1, code, tradeDate, XTickConst.token, MethodType.POST);
        List<Bid> bidDetails = JsonUtil.jsonToList(dataStr, Bid.class);
        System.out.println(String.format("[hot.bid.detail]code=%s,tradeDate=%s,size=%s", code, tradeDate, bidDetails == null ? 0 : bidDetails.size()));

        //新闻资讯-实时接口
        dataStr = xTickHotApi.getHotNews(30, tradeDate, XTickConst.token, MethodType.POST);
        List<XTickNewsInfo> xTickNewsInfos = JsonUtil.jsonToList(dataStr, XTickNewsInfo.class);
        System.out.println(String.format("[hot.news]tradeDate=%s,size=%s", tradeDate, xTickNewsInfos == null ? 0 : xTickNewsInfos.size()));

        //日内分时-实时接口
        dataStr = xTickHotApi.getHotTimekline(1, code, XTickConst.token, MethodType.POST);
        List<Minute> xTickMinutes = JsonUtil.jsonToList(dataStr, Minute.class);
        System.out.println(String.format("[hot.timekline]code=%s,size=%s", code, xTickMinutes == null ? 0 : xTickMinutes.size()));

        //概念板块成分股数据
        dataStr = xTickHotApi.getHotBk("sw3", XTickConst.token, MethodType.POST);
        List<XTickBKInfo> xTickBKInfos = JsonUtil.jsonToList(dataStr, XTickBKInfo.class);
        System.out.println(String.format("[hot.bk]symbol=%s,size=%s", "sw3", xTickBKInfos == null ? 0 : xTickBKInfos.size()));

        //股票关联概念板块数据
        dataStr = xTickHotApi.getHotGainian(code, XTickConst.token, MethodType.POST);
        List<XTickRelationBK> xTickRelationBKs = JsonUtil.jsonToList(dataStr, XTickRelationBK.class);
        System.out.println(String.format("[hot.gainian]code=%s,size=%s", code, xTickRelationBKs == null ? 0 : xTickRelationBKs.size()));

        //增量更新
        dataStr = xTickHotApi.getHotDayUpdate("bid", "bj", tradeDate, XTickConst.token, MethodType.POST);
        System.out.println(String.format("[hot.dayupdate]tradeDate=%s,result=%s", tradeDate, dataStr != null ? dataStr.length() : 0));
    }

    /**
     * 所有API接口的Demo示例
     * 会调用所有接口，因此调用API接口次数多，请按需调用
     */
    public static void allDemo() throws IOException {
        String code = "000001";
        demoForMarketApi(code);//获取行情数据
        demoForFinancialApi(code);//获取财务数据
        demoForIndicatorApi(code);//获取指标数据
        demoForWatchApi(code);//获取盯盘数据
        demoForCoreApi(code);//获取核心数据
        demoForHotApi(code);//获取短线热点数据
        demoForQuantApi(code);//获取量化数据
    }

    public static void main(String[] args) throws IOException {
        allDemo();//所有API接口的Demo示例
    }
}
