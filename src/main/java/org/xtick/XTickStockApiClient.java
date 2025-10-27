package org.xtick;

import com.google.common.collect.ImmutableMap;
import org.xtick.api.XTickIndicatorApi;
import org.xtick.api.XTickMarketApi;
import org.xtick.bean.Minute;
import org.xtick.bean.Tick;
import org.xtick.bean.finance.*;
import org.xtick.constant.MethodType;
import org.xtick.constant.XTickConst;
import org.xtick.http.HttpClientRest;
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
    public static void demoForFinancialData(String code) throws IOException {
        XTickMarketApi xTickMarketApi = new XTickMarketApi();
        int type = 1;//沪深京A股Type=1，港股Type=3，ETF Type=20
        String startDate = LocalDate.now().minusDays(365).toString();
        String endDate = LocalDate.now().toString();
        //获取财务指标数据
        String report = "Pershareindex";
        String result = xTickMarketApi.getFinancial(type, code, report, startDate, endDate, XTickConst.token, MethodType.GET);
        List<XTickFinancePershareIndex> financePershareIndexDatas = JsonUtil.jsonToList(result, XTickFinancePershareIndex.class);
        System.out.println(String.format("[financial]time=%s,code=%s,report=%s,date=%s,size=%s", LocalDateTime.now().format(formatter), code, report, startDate, financePershareIndexDatas == null ? 0 : financePershareIndexDatas.size()));

        report = "Balance";
        result = xTickMarketApi.getFinancial(type, code, report, startDate, endDate, XTickConst.token, MethodType.GET);
        List<XTickFinanceBalance> financeBalanceDatas = JsonUtil.jsonToList(result, XTickFinanceBalance.class);
        System.out.println(String.format("[financial]time=%s,code=%s,report=%s,date=%s,size=%s", LocalDateTime.now().format(formatter), code, report, startDate, financeBalanceDatas == null ? 0 : financeBalanceDatas.size()));

        report = "CashFlow";
        result = xTickMarketApi.getFinancial(type, code, report, startDate, endDate, XTickConst.token, MethodType.GET);
        List<XTickFinanceCashFlow> financeCashFlowDatas = JsonUtil.jsonToList(result, XTickFinanceCashFlow.class);
        System.out.println(String.format("[financial]time=%s,code=%s,report=%s,date=%s,size=%s", LocalDateTime.now().format(formatter), code, report, startDate, financeCashFlowDatas == null ? 0 : financeCashFlowDatas.size()));

        report = "Capital";
        result = xTickMarketApi.getFinancial(type, code, report, startDate, endDate, XTickConst.token, MethodType.GET);
        List<XTickFinanceCapital> financeCapitalDatas = JsonUtil.jsonToList(result, XTickFinanceCapital.class);
        System.out.println(String.format("[financial]time=%s,code=%s,report=%s,date=%s,size=%s", LocalDateTime.now().format(formatter), code, report, startDate, financeCapitalDatas == null ? 0 : financeCapitalDatas.size()));

        report = "Holdernum";
        result = xTickMarketApi.getFinancial(type, code, report, startDate, endDate, XTickConst.token, MethodType.GET);
        List<XTickFinanceHoldernum> financeHoldernumDatas = JsonUtil.jsonToList(result, XTickFinanceHoldernum.class);
        System.out.println(String.format("[financial]time=%s,code=%s,report=%s,date=%s,size=%s", LocalDateTime.now().format(formatter), code, report, startDate, financeHoldernumDatas == null ? 0 : financeHoldernumDatas.size()));

        report = "Top10holder";
        result = xTickMarketApi.getFinancial(type, code, report, startDate, endDate, XTickConst.token, MethodType.GET);
        List<XTickFinanceTop10holder> financeTop10holderDatas = JsonUtil.jsonToList(result, XTickFinanceTop10holder.class);
        System.out.println(String.format("[financial]time=%s,code=%s,report=%s,date=%s,size=%s", LocalDateTime.now().format(formatter), code, report, startDate, financeTop10holderDatas == null ? 0 : financeTop10holderDatas.size()));

        report = "Top10flowholder";
        result = xTickMarketApi.getFinancial(type, code, report, startDate, endDate, XTickConst.token, MethodType.GET);
        List<XTickFinanceTop10flowholder> financeTop10flowholderDatas = JsonUtil.jsonToList(result, XTickFinanceTop10flowholder.class);
        System.out.println(String.format("[financial]time=%s,code=%s,report=%s,date=%s,size=%s", LocalDateTime.now().format(formatter), code, report, startDate, financeTop10flowholderDatas == null ? 0 : financeTop10flowholderDatas.size()));
    }

    /**
     * 获取行情数据代码示例
     *
     * @param code
     * @throws IOException
     */
    public static void demoForMarketData(String code) throws IOException {
        XTickMarketApi xTickMarketApi = new XTickMarketApi();
        int type = 1;//沪深京A股Type=1，港股Type=3
        String startDate = LocalDate.now().minusDays(30).toString();
        String endDate = LocalDate.now().toString();
        String result = xTickMarketApi.getMarket(type, code, "tick", "", startDate, startDate, XTickConst.token, MethodType.GET);
        List<Tick> ticks = JsonUtil.jsonToList(result, Tick.class);//获取tick数据
        System.out.println(String.format("[market]time=%s,code=%s,period=tick,date=%s,size=%s", LocalDateTime.now().format(formatter), code, startDate, ticks == null ? 0 : ticks.size()));
        for (String period : XTickConst.historyKlinePeriods) {//获取K线数据
            for (String fq : XTickConst.dividends) {
                result = xTickMarketApi.getMarket(type, code, period, fq, startDate, endDate, XTickConst.token, MethodType.GET);
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
    public static void demoForIndicatorData(String code) throws IOException {
        XTickIndicatorApi xTickIndicatorApi = new XTickIndicatorApi();
        int type = 1;//沪深京A股Type=1，港股Type=3
        String period = "1d";
        String fq = "front";
        String startDate = LocalDate.now().minusDays(180).toString();
        String endDate = LocalDate.now().toString();
        //KDJ指标，若计算J值，则J值=3×K值-2×D值
        String content = xTickIndicatorApi.stoch(type, code, period, fq, startDate, endDate, XTickConst.token, 9, 5, 2, 5, 2, MethodType.GET);
        List<Map> datas = JsonUtil.jsonToList(content, Map.class);
        System.out.println(String.format("[indicator]time=%s,code=%s,period=%s,fq=%s,startDate=%s,endDate=%s,size=%s", LocalDateTime.now().format(formatter), code, period, fq, startDate, endDate, datas == null ? 0 : datas.size()));
    }


    /**
     * 测试调用API接口功能入口
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        XTickMarketApi xTickMarketApi = new XTickMarketApi();
        String codeStr = xTickMarketApi.getAllCodes(XTickConst.token, MethodType.POST);//获取所有股票代码，包括沪深京股票、ETF、港股、指数
        List<String> codes = JsonUtil.jsonToList(codeStr, String.class);
        System.out.println(codes);
        String code = "000001";
        demoForMarketData(code);
        demoForFinancialData(code);
        demoForIndicatorData(code);
    }
}
