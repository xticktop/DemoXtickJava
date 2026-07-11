package org.xtick.api;

import com.google.common.collect.ImmutableMap;
import org.xtick.constant.MethodType;
import org.xtick.constant.XTickConst;
import org.xtick.http.HttpClientRest;

import java.io.IOException;
import java.util.Map;

/**
 * 盯盘实时数据API接口。
 * <p>
 * 官网：http://www.xtick.top/
 */
public class XTickWatchApi {

    /**
     * 日K线-实时数据（新接口）
     * 获取盘中实时日K线数据。支持批量参数，支持ALL参数。该接口单次获取全市场行情数据，非常适合盯盘。
     */
    public String getOrderDay(int type, String code, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/order/day";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("code", code).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 分钟K线-实时数据（新接口）
     * 获取盘中分钟K线实时数据。支持批量参数，支持ALL参数。
     */
    public String getOrderMinute(int type, String code, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/order/minute";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("code", code).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 深度行情-实时数据
     * 获取盘中深度行情实时数据。
     */
    public String getDeep(int type, String code, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/order/deep";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("code", code).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 深度行情-历史数据
     * 获取盘中深度行情历史数据，盘后更新数据。
     */
    public String getTickHistory(int type, String code, String tradeDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/order/history";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("code", code).put("tradeDate", tradeDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 成交统计-实时接口
     * 按交易日，获取全市场成交额统计，包括科创板、创业板、北证、两市等成交额统计。
     */
    public String getAmount(String tradeDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/order/amount";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("tradeDate", tradeDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 日K线复权-更新接口
     * 盘后获取当天全市场股票日线数据，包括前复权、不复权、后复权三种方式。
     * 仅支持近一周内的增量数据调用，主要是方便更新日K线。
     */
    public String getFqKline(int type, String fq, String tradeDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/order/fqkline";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("fq", fq).put("tradeDate", tradeDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }
}
