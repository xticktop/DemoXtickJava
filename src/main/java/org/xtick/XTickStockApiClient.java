package org.xtick;

import com.google.common.collect.ImmutableMap;
import org.xtick.bean.Minute;
import org.xtick.bean.Tick;
import org.xtick.bean.finance.*;
import org.xtick.constant.MethodType;
import org.xtick.constant.XTickConst;
import org.xtick.http.HttpClientRest;
import org.xtick.util.JsonUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * 行情实时数据、财务报表数据获取API接口。
 * 官网：http://www.xtick.top/
 */
public class XTickStockApiClient {
    /**
     * 获取市场所有股票代码
     * 返回数据实例 1-000001 代表 type-code
     * 其中type为1代表沪深京A股，3代表港股（沪深港通支持的港股），10代表沪深指数 20代表ETF
     */
    public String getAllCodes(String token, MethodType method) throws IOException {
        String url = "http://api.xtick.top/doc/codes";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("zip", true).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 获取财务数据
     */
    public String getFinancialData(int type, String code, String report, String startDate, String endDate, String token, MethodType method) throws IOException {
        String url = "http://api.xtick.top/doc/financial";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("zip", true).put("code", code).put("report", report).put("startDate", startDate).put("endDate", endDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 获取市场行情数据数据
     */
    public String getMarketData(int type, String code, String period, String fq, String startDate, String endDate, String token, MethodType method) throws IOException {
        String url = "http://api.xtick.top/doc/market";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("zip", true).put("code", code).put("period", period).put("fq", fq).put("startDate", startDate).put("endDate", endDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    public void DemoForFinancialData() throws IOException {
        int type = 1;//沪深京A股Type=1，港股Type=3，ETF Type=20
        String code = "000001";
        String startDate = "2020-04-25";
        String endDate = LocalDate.now().toString();
        //获取财务指标数据
        String report = "Pershareindex";
        String result = getFinancialData(type, code, report, startDate, endDate, XTickConst.token, MethodType.GET);
        List<XTickFinancePershareIndex> financePershareIndexDatas = JsonUtil.jsonToList(result, XTickFinancePershareIndex.class);
        System.out.println(String.format("code=%s,report=%s,date=%s,financial data size=%s", code, report, startDate, financePershareIndexDatas.size()));

        report = "Balance";
        result = getFinancialData(type, code, report, startDate, endDate, XTickConst.token, MethodType.GET);
        List<XTickFinanceBalance> financeBalanceDatas = JsonUtil.jsonToList(result, XTickFinanceBalance.class);
        System.out.println(String.format("code=%s,report=%s,date=%s,financial data size=%s", code, report, startDate, financeBalanceDatas.size()));

        report = "CashFlow";
        result = getFinancialData(type, code, report, startDate, endDate, XTickConst.token, MethodType.GET);
        List<XTickFinanceCashFlow> financeCashFlowDatas = JsonUtil.jsonToList(result, XTickFinanceCashFlow.class);
        System.out.println(String.format("code=%s,report=%s,date=%s,financial data size=%s", code, report, startDate, financeCashFlowDatas.size()));

        report = "Capital";
        result = getFinancialData(type, code, report, startDate, endDate, XTickConst.token, MethodType.GET);
        List<XTickFinanceCapital> financeCapitalDatas = JsonUtil.jsonToList(result, XTickFinanceCapital.class);
        System.out.println(String.format("code=%s,report=%s,date=%s,financial data size=%s", code, report, startDate, financeCapitalDatas.size()));

        report = "Holdernum";
        result = getFinancialData(type, code, report, startDate, endDate, XTickConst.token, MethodType.GET);
        List<XTickFinanceHoldernum> financeHoldernumDatas = JsonUtil.jsonToList(result, XTickFinanceHoldernum.class);
        System.out.println(String.format("code=%s,report=%s,date=%s,financial data size=%s", code, report, startDate, financeHoldernumDatas.size()));

        report = "Top10holder";
        result = getFinancialData(type, code, report, startDate, endDate, XTickConst.token, MethodType.GET);
        List<XTickFinanceTop10holder> financeTop10holderDatas = JsonUtil.jsonToList(result, XTickFinanceTop10holder.class);
        System.out.println(String.format("code=%s,report=%s,date=%s,financial data size=%s", code, report, startDate, financeTop10holderDatas.size()));

        report = "Top10flowholder";
        result = getFinancialData(type, code, report, startDate, endDate, XTickConst.token, MethodType.GET);
        List<XTickFinanceTop10flowholder> financeTop10flowholderDatas = JsonUtil.jsonToList(result, XTickFinanceTop10flowholder.class);
        System.out.println(String.format("code=%s,report=%s,date=%s,financial data size=%s", code, report, startDate, financeTop10flowholderDatas.size()));
    }

    public void DemoForMarketData() throws IOException {
        int type = 1;//沪深京A股Type=1，港股Type=3
        String code = "000001";
        String startDate = "2025-04-25";
        String endDate = LocalDate.now().toString();
        String result = getMarketData(type, code, "tick", "", startDate, startDate, XTickConst.token, MethodType.GET);
        List<Tick> ticks = JsonUtil.jsonToList(result, Tick.class);//获取tick数据
        System.out.println(String.format("code=%s,period=tick,date=%s,history data size=%s", code, startDate, ticks.size()));
        for (String period : XTickConst.historyKlinePeriods) {//获取K线数据
            for (String fq : XTickConst.dividends) {
                result = getMarketData(type, code, period, fq, startDate, endDate, XTickConst.token, MethodType.GET);
                List<Minute> klines = JsonUtil.jsonToList(result, Minute.class);
                System.out.println(String.format("code=%s,period=%s,fq=%s,startDate=%s,endDate=%s,history data size=%s", code, period, fq, startDate, endDate, klines.size()));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        XTickStockApiClient client = new XTickStockApiClient();
        String codeStr = client.getAllCodes(XTickConst.token, MethodType.POST);
        List<String> codes = JsonUtil.jsonToList(codeStr, String.class);
        System.out.println(codes);
        String result = client.getMarketData(10, "000001", "1h", "none", LocalDate.now().minusDays(1).toString(), LocalDate.now().plusDays(1).toString(), XTickConst.token, MethodType.GET);
        List<Minute> datas = JsonUtil.jsonToList(result, Minute.class);//获取1分钟数据
        System.out.println(datas);
        //client.DemoForFinancialData();//获取财务数据代码示例
        //client.DemoForMarketData();//获取历史数据代码示例
    }
}
