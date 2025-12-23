package org.xtick;

import com.fasterxml.jackson.core.type.TypeReference;
import org.xtick.api.*;
import org.xtick.bean.*;
import org.xtick.bean.base.XTickStockCalendar;
import org.xtick.bean.base.XTickStockInfo;
import org.xtick.bean.finance.*;
import org.xtick.constant.MethodType;
import org.xtick.constant.XTickConst;
import org.xtick.util.JsonUtil;

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
    public static void demoForFinancialApi(String code) throws IOException {
        XTickMarketApi xTickMarketApi = new XTickMarketApi();
        int type = 1;//沪深京A股type=1，港股type=3，沪深指数type=10，沪深ETF type=20
        String startDate = LocalDate.now().minusDays(365).toString();
        String endDate = LocalDate.now().toString();
        //获取财务指标数据
        String report = "Pershareindex";
        String result = xTickMarketApi.getFinancial(type, code, report, startDate, endDate, XTickConst.token, MethodType.POST);
        List<XTickFinancePershareIndex> financePershareIndexDatas = JsonUtil.jsonToList(result, XTickFinancePershareIndex.class);
        System.out.println(String.format("[financial]time=%s,code=%s,report=%s,date=%s,size=%s", LocalDateTime.now().format(formatter), code, report, startDate, financePershareIndexDatas == null ? 0 : financePershareIndexDatas.size()));

        report = "Balance";
        result = xTickMarketApi.getFinancial(type, code, report, startDate, endDate, XTickConst.token, MethodType.POST);
        List<XTickFinanceBalance> financeBalanceDatas = JsonUtil.jsonToList(result, XTickFinanceBalance.class);
        System.out.println(String.format("[financial]time=%s,code=%s,report=%s,date=%s,size=%s", LocalDateTime.now().format(formatter), code, report, startDate, financeBalanceDatas == null ? 0 : financeBalanceDatas.size()));

        report = "CashFlow";
        result = xTickMarketApi.getFinancial(type, code, report, startDate, endDate, XTickConst.token, MethodType.POST);
        List<XTickFinanceCashFlow> financeCashFlowDatas = JsonUtil.jsonToList(result, XTickFinanceCashFlow.class);
        System.out.println(String.format("[financial]time=%s,code=%s,report=%s,date=%s,size=%s", LocalDateTime.now().format(formatter), code, report, startDate, financeCashFlowDatas == null ? 0 : financeCashFlowDatas.size()));

        report = "Capital";
        result = xTickMarketApi.getFinancial(type, code, report, startDate, endDate, XTickConst.token, MethodType.POST);
        List<XTickFinanceCapital> financeCapitalDatas = JsonUtil.jsonToList(result, XTickFinanceCapital.class);
        System.out.println(String.format("[financial]time=%s,code=%s,report=%s,date=%s,size=%s", LocalDateTime.now().format(formatter), code, report, startDate, financeCapitalDatas == null ? 0 : financeCapitalDatas.size()));

        report = "Holdernum";
        result = xTickMarketApi.getFinancial(type, code, report, startDate, endDate, XTickConst.token, MethodType.POST);
        List<XTickFinanceHoldernum> financeHoldernumDatas = JsonUtil.jsonToList(result, XTickFinanceHoldernum.class);
        System.out.println(String.format("[financial]time=%s,code=%s,report=%s,date=%s,size=%s", LocalDateTime.now().format(formatter), code, report, startDate, financeHoldernumDatas == null ? 0 : financeHoldernumDatas.size()));

        report = "Top10holder";
        result = xTickMarketApi.getFinancial(type, code, report, startDate, endDate, XTickConst.token, MethodType.POST);
        List<XTickFinanceTop10holder> financeTop10holderDatas = JsonUtil.jsonToList(result, XTickFinanceTop10holder.class);
        System.out.println(String.format("[financial]time=%s,code=%s,report=%s,date=%s,size=%s", LocalDateTime.now().format(formatter), code, report, startDate, financeTop10holderDatas == null ? 0 : financeTop10holderDatas.size()));

        report = "Top10flowholder";
        result = xTickMarketApi.getFinancial(type, code, report, startDate, endDate, XTickConst.token, MethodType.POST);
        List<XTickFinanceTop10flowholder> financeTop10flowholderDatas = JsonUtil.jsonToList(result, XTickFinanceTop10flowholder.class);
        System.out.println(String.format("[financial]time=%s,code=%s,report=%s,date=%s,size=%s", LocalDateTime.now().format(formatter), code, report, startDate, financeTop10flowholderDatas == null ? 0 : financeTop10flowholderDatas.size()));
    }

    /**
     * 获取行情数据代码示例
     *
     * @param code
     * @throws IOException
     */
    public static void demoForMarketApi(String code) throws IOException {
        XTickMarketApi xTickMarketApi = new XTickMarketApi();
        int type = 1;//沪深京A股Type=1，港股Type=3
        String startDate = LocalDate.now().minusDays(30).toString();
        String endDate = LocalDate.now().toString();
        for (String period : XTickConst.historyKlinePeriods) {//获取K线数据
            for (String fq : XTickConst.dividends) {
                String result = xTickMarketApi.getMarket(type, code, period, fq, startDate, endDate, XTickConst.token, MethodType.POST);
                List<Minute> klines = JsonUtil.jsonToList(result, Minute.class);
                System.out.println(String.format("[market]time=%s,code=%s,period=%s,fq=%s,startDate=%s,endDate=%s,size=%s", LocalDateTime.now().format(formatter), code, period, fq, startDate, endDate, klines == null ? 0 : klines.size()));
            }
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
        String period = "tick";
        //tick实时数据，多个票批量获取，单次最多50个票
        String batchCodes = "000001,000002,600000";
        String result = xTickWatchApi.getTickTime(type, batchCodes, period, XTickConst.token, MethodType.POST);
        Map<String, Tick> ticks = JsonUtil.jsonToObj(result, new TypeReference<Map<String, Tick>>() {
        });
        System.out.println(String.format("[tick.time]time=%s,batchCodes=%s,period=%s,size=%s", LocalDateTime.now().format(formatter), batchCodes, period, ticks == null ? 0 : ticks.size()));

        //Tick 全市场的Tick实时数据
        result = xTickWatchApi.getTickTime(type, "all", period, XTickConst.token, MethodType.POST);
        ticks = JsonUtil.jsonToObj(result, new TypeReference<Map<String, Tick>>() {
        });
        System.out.println(String.format("[tick.time]time=%s,code=%s,period=%s,size=%s", LocalDateTime.now().format(formatter), "all", period, ticks == null ? 0 : ticks.size()));

        //Tick 某个交易日的Tick历史数据
        result = xTickWatchApi.getTickHistory(type, code, tradeDate.toString(), XTickConst.token, MethodType.POST);
        List<Tick> tickOfDay = JsonUtil.jsonToList(result, Tick.class);
        System.out.println(String.format("[tick.history]time=%s,code=%s,tradeDate=%s,size=%s", LocalDateTime.now().minusDays(1).format(formatter), code, tradeDate, tickOfDay == null ? 0 : tickOfDay.size()));
        //获取竞价实时数据，多个票批量获取，单次最多50个票
        result = xTickWatchApi.getBidTime(type, batchCodes, XTickConst.token, MethodType.POST);
        List<Bid> bids = JsonUtil.jsonToList(result, Bid.class);
        System.out.println(String.format("[bid.time]time=%s,batchCodes=%s,period=%s,size=%s", LocalDateTime.now().format(formatter), batchCodes, period, bids == null ? 0 : bids.size()));
        //获取竞价实时数据，全市场所有票的实时竞价数据
        result = xTickWatchApi.getBidTime(type, "all", XTickConst.token, MethodType.POST);
        bids = JsonUtil.jsonToList(result, Bid.class);
        System.out.println(String.format("[bid.time]time=%s,code=%s,size=%s", LocalDateTime.now().format(formatter), "all", bids == null ? 0 : bids.size()));
        //获取竞价实时数据，全市场所有票的实时竞价数据，支持option参数，按需排序和过滤股票
        //当天全市场股票竞价，按未成交额排序，从大到小，取前100条。
        Option option = Option.builder().asc(0).sort("noe").limit(100).build();
        result = xTickWatchApi.getBidTime(type, "all", option, XTickConst.token, MethodType.POST);
        bids = JsonUtil.jsonToList(result, Bid.class);
        System.out.println(String.format("[bid.time]time=%s,code=%s,size=%s", LocalDateTime.now().format(formatter), "all", bids == null ? 0 : bids.size()));
        //获取竞价实时数据，全市场所有票的实时竞价数据，支持option参数，按需排序和过滤股票
        //当天全市场股票竞价，过滤出来当天竞价涨幅5个点以上且竞价额大于等于1000万的个股，结果数据按未成交额排序，从大到小，取前100条。
        option = Option.builder().filter("jjzf>5;jje>=10000000").asc(0).sort("noe").limit(100).build();
        result = xTickWatchApi.getBidTime(type, "all", option, XTickConst.token, MethodType.POST);
        bids = JsonUtil.jsonToList(result, Bid.class);
        System.out.println(String.format("[bid.time]time=%s,code=%s,size=%s", LocalDateTime.now().format(formatter), "all", bids == null ? 0 : bids.size()));


        //获取竞价历史数据，按单个票（code不支持批量，支持all参数）
        result = xTickWatchApi.getBidHistory(type, code, tradeDate.toString(), tradeDate.plusDays(10).toString(), XTickConst.token, MethodType.POST);
        bids = JsonUtil.jsonToList(result, Bid.class);
        System.out.println(String.format("[bid.history]time=%s,code=%s,startDate=%s,endDate=%s,size=%s", LocalDateTime.now().format(formatter), code, tradeDate, tradeDate.plusDays(10).toString(), bids == null ? 0 : bids.size()));
        //获取竞价历史数据，全市场某天的竞价数据（code不支持批量，支持all参数），开始日期和结束日期必须相同
        result = xTickWatchApi.getBidHistory(type, "all", tradeDate.toString(), tradeDate.toString(), XTickConst.token, MethodType.POST);
        bids = JsonUtil.jsonToList(result, Bid.class);
        System.out.println(String.format("[bid.history]time=%s,code=%s,startDate=%s,endDate=%s,size=%s", LocalDateTime.now().format(formatter), code, tradeDate, tradeDate.toString(), bids == null ? 0 : bids.size()));


        //获取竞价阶段所有明细数据
        result = xTickWatchApi.getBidDetail(type, code, tradeDate.toString(), XTickConst.token, MethodType.POST);
        bids = JsonUtil.jsonToList(result, Bid.class);
        System.out.println(String.format("[bid.detail]time=%s,code=%s,tradeDate=%s,size=%s", LocalDateTime.now().format(formatter), code, tradeDate, bids == null ? 0 : bids.size()));
    }

    /**
     * 获取基础API接口数据
     *
     * @param code
     * @throws IOException
     */
    public static void demoForBaseApi(String code) throws IOException {
        XTickBaseApi xTickBaseApi = new XTickBaseApi();
        String dataStr = xTickBaseApi.getCalendar(code, LocalDate.now().minusDays(3).toString(), LocalDate.now().minusDays(3).toString(), XTickConst.token, MethodType.POST);//获取所有股票代码，包括沪深京股票、ETF、港股、指数
        List<XTickStockCalendar> calendars = JsonUtil.jsonToList(dataStr, XTickStockCalendar.class);
        System.out.println(String.format("[calendar]code=%s,size=%s", code, calendars == null ? 0 : calendars.size()));
        String symbol = "sz";
        dataStr = xTickBaseApi.getStockInfo(symbol, XTickConst.token, MethodType.POST);//获取所有股票代码，包括沪深京股票、ETF、港股、指数
        List<XTickStockInfo> stockInfos = JsonUtil.jsonToList(dataStr, XTickStockInfo.class);
        System.out.println(String.format("[stockInfo]symbol=%s,size=%s", symbol, stockInfos == null ? 0 : stockInfos.size()));
    }

    public static void demoForWebSocketApi() throws IOException {
        XTickWebSocketApi xTickWebSocketApi = new XTickWebSocketApi();
        String dataStr = xTickWebSocketApi.querySubscribe(XTickConst.token, MethodType.POST);
        System.out.println("[websocket]查询已订阅服务结果：" + dataStr);
        dataStr = xTickWebSocketApi.unsubscribe(XTickConst.token, MethodType.POST);
        System.out.println("[websocket]取消已订阅服务结果：" + dataStr);
    }

    /**
     * 获取量化API接口数据
     *
     * @param code
     * @throws IOException
     */
    public static void demoForQuantApi(String code) throws IOException {
        XTickQuantApi xTickQuantApi = new XTickQuantApi();
        String field = "x001,x002,x003,x004,x005";
        String dataStr = xTickQuantApi.getCoreTime(1, code, field, XTickConst.token, MethodType.POST);
        Map<String, Object> datas = JsonUtil.jsonToObj(dataStr, Map.class);
        System.out.println(String.format("[core.time]code=%s,size=%s", code, datas == null ? 0 : datas.size()));

        int day = 3;
        dataStr = xTickQuantApi.getCoreChange(1, day, XTickConst.token, MethodType.POST);
        List<XTickStockInfo> stockInfos = JsonUtil.jsonToList(dataStr, XTickStockInfo.class);
        System.out.println(String.format("[core.change]day=%s,size=%s", day, stockInfos == null ? 0 : stockInfos.size()));

        dataStr = xTickQuantApi.getQunatData(1, "all", XTickConst.token, MethodType.POST);
        QuantPacket quantPacket = JsonUtil.jsonToObj(dataStr, QuantPacket.class);
        System.out.println(String.format("[quant.data]size=%s", quantPacket == null ? 0 : quantPacket.toMap().size()));
    }


    /**
     * 所有API接口的Demo示例
     * 会调用所有接口，因此调用API接口次数多，请按需调用
     *
     * @throws IOException
     */
    public static void allDemo() throws IOException {
        String code = "000001";
        demoForWebSocketApi();//获取已订阅服务
        demoForBaseApi(code);//获取基础数据
        demoForMarketApi(code);//获取行情数据
        demoForFinancialApi(code);//获取财务数据
        demoForIndicatorApi(code);//获取指标数据
        demoForWatchApi(code);//获取盯盘数据
        demoForQuantApi(code);//获取量化数据
    }


    public static void main(String[] args) throws IOException {
        String batchCodes = "000001,000002,600000";
        String period = "tick";
        String result = new XTickWatchApi().getTickTime(1, batchCodes, period, XTickConst.token, MethodType.POST);
        Map<String, Tick> ticks = JsonUtil.jsonToObj(result, new TypeReference<Map<String, Tick>>() {
        });
        System.out.println(String.format("[tick.time]time=%s,batchCodes=%s,period=%s,size=%s", LocalDateTime.now().format(formatter), batchCodes, period, ticks == null ? 0 : ticks.size()));
    }
}
