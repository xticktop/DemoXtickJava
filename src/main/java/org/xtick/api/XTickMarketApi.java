package org.xtick.api;

import com.google.common.collect.ImmutableMap;
import org.xtick.constant.MethodType;
import org.xtick.constant.XTickConst;
import org.xtick.http.HttpClientRest;

import java.io.IOException;
import java.util.Map;

/**
 * 行情实时数据、财务报表数据获取API接口。
 * <p>
 * 官网：http://www.xtick.top/
 */
public class XTickMarketApi {
    /**
     * 获取市场行情数据数据，包括历史数据和当日盘中实时数据
     * - 1m - 1分钟线
     * - 5m - 5分钟线
     * - 15m - 15分钟线
     * - 30m - 30分钟线
     * - 1h - 1小时线
     * - 1d - 日线
     * - 1w - 周线
     * - 1mon - 月线
     * - 1q - 季度线
     * - 1hy - 半年线
     * - 1y - 年线
     */
    public String getKlineMarket(int type, String code, String period, String fq, String startDate, String endDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/kline/market";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("code", code).put("period", period).put("fq", fq).put("startDate", startDate).put("endDate", endDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 提供日内一分钟实时数据，包括盘前9:15-25内竞价阶段数据。
     */
    public String getKlineMinute(int type, String code, String fq, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/kline/minute";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("code", code).put("fq", fq).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }


    /**
     * 获取A股上市公司财务数据
     * Balance - 资产负债表
     * Income - 利润表
     * CashFlow - 现金流量表
     * Capital - 股本表
     * Holdernum - 股东数
     * Top10holder - 十大股东
     * Top10flowholder - 十大流通股东
     * Pershareindex - 每股指标
     */

    public String getFinancial(int type, String code, String report, String startDate, String endDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/financial";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("code", code).put("report", report).put("startDate", startDate).put("endDate", endDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }
}
