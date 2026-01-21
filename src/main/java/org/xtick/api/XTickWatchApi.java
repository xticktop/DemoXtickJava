package org.xtick.api;

import com.google.common.collect.ImmutableMap;
import org.xtick.bean.Option;
import org.xtick.constant.MethodType;
import org.xtick.constant.XTickConst;
import org.xtick.http.HttpClientRest;
import org.xtick.util.JsonUtil;

import java.io.IOException;
import java.util.Map;

/**
 * 盯盘实时数据API接口。
 * <p>
 * 官网：http://www.xtick.top/
 */
public class XTickWatchApi {
    /**
     * 获取沪深京股票交易日盘中实时行情数据，包括买卖五档数据、1分钟数据、日线数据。
     */
    public String getTickTime(int type, String code, String period, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/order/time";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("period", period).put("code", code).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 获取沪深京股票历史买卖五档数据
     */
    public String getTickHistory(int type, String code, String tradeDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/order/history";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("code", code).put("tradeDate", tradeDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 开盘竞价阶段，个股的所有竞价信息。当天竞价完成后，9:25更新完数据。
     */
    public String getBidDetail(int type, String code, String tradeDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/bid/detail";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("code", code).put("tradeDate", tradeDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 获取沪深京股票的历史竞价数据
     */
    public String getBidHistory(int type, String code, String startDate, String endDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/bid/history";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("code", code).put("startDate", startDate).put("endDate", endDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 获取沪深京股票交易日盘中实时竞价数据，竞价时间段：9:15-9:25。每次调用接口返回最新竞价数据。可以根据option参数过滤排序
     */
    public String getBidTime(int type, String code, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/bid/time";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("code", code).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 获取沪深京股票交易日盘中实时竞价数据，竞价时间段：9:15-9:25。每次调用接口返回最新竞价数据。可以根据option参数过滤排序
     */
    public String getBidTime(int type, String code, Option option, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/bid/time";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("option", JsonUtil.toJson(option)).put("code", code).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }
}
