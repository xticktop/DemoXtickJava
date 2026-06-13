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
     获取A股历史交易，包含交易所交易日历和个股交易日历。
     交易所是指上交所、深交所、北交所的交易日历。
     */
    public String getCalendar(String code, String startDate, String endDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/calendar";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("code", code).put("token", token).put("startDate", startDate).put("endDate", endDate).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 获取市场所有股票代码
     * symbol 用于表示要获取的股票分类，枚举取值如下：
     * - all - 全部股票
     * - sz - 深交所股票
     * - sh - 上交所股票
     * - bj -  北交所股票
     * - hk - 港交所股票
     * - index- 指数
     * - cyb - 创业板股票
     * - kcb - 科创板股票
     * - etf - 全部ETF
     * - st- st股票
     * - ts- 退市股票
     */
    public String getStockInfo(String symbol, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/stockinfo";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("symbol", symbol).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }
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
     * 提供日内一分钟实时数据，
     */
    public String getKlineMinute(int type, String code, String fq, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/kline/minute";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("code", code).put("fq", fq).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 股东数
     * 获取A股上市公司股东数量历史数据，数据范围：2001年-至今
     */
    public String getHolderNum(String code, String startDate, String endDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/holdernum";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("code", code).put("startDate", startDate).put("endDate", endDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 财务指标
     * 获取A股上市公司核心财务指标数据
     */
    public String getCoreFinancial(String code,  String startDate, String endDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/gaap";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("code", code).put("startDate", startDate).put("endDate", endDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 十大股东
     * 获取A股上市公司十大股东信息，数据范围：2004年-至今
     */
    public String getTopHolder(String code, String startDate, String endDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/topholder";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("code", code).put("startDate", startDate).put("endDate", endDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 十大流通股东
     * 获取A股上市公司十大流通股东信息，数据范围：2004年-至今
     */
    public String getTopFlowHolder(String code, String startDate, String endDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/topflowholder";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("code", code).put("startDate", startDate).put("endDate", endDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }
}
