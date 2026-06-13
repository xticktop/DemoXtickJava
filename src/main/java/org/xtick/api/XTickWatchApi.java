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
     * 获取沪深京股票历史买卖五档数据
     */
    public String getTickHistory(int type, String code, String tradeDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/order/history";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("code", code).put("tradeDate", tradeDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }


    /**
     * 按交易日，获取全市场成交额统计，包括科创板、创业板、北证、两市等成交额统计。
     */
    public String getAmount(String tradeDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/order/amount";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("tradeDate", tradeDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 龙虎榜详情历史数据。盘后获取。
     */
    public String getLonghubang(String tradeDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/order/longhubang";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("tradeDate", tradeDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 买卖五档-实时数据（新接口）
     * 获取沪深京股票交易日盘中实时买卖五档数据
     */
    public String getFiveLevel(int type, String code, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/order/five";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("code", code).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 分钟K线-实时数据（新接口）
     * 获取沪深京股票交易日盘中实时分钟K线数据
     */
    public String getOrderMinute(int type, String code, String fq, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/order/minute";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("code", code).put("fq", fq).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 日K线-实时数据（新接口）
     * 获取沪深京股票交易日盘中实时日K线数据
     */
    public String getOrderDay(int type, String code, String fq, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/order/day";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("code", code).put("fq", fq).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    public String getOrderFactor(int type, String code, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/order/factor";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("code", code).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }
}
