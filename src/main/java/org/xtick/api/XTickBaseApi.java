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
public class XTickBaseApi {
    /**
     * 获取市场所有股票代码
     * 返回数据实例 1-000001 代表 type-code
     * 其中沪深京A股type=1，港股type=3，沪深指数type=10，沪深ETF type=20
     */
    public String getCalendar(String code, String startDate, String endDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/calendar";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("zip", true).put("code", code).put("token", token).put("startDate", startDate).put("endDate", endDate).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 获取市场所有股票代码
     * 返回数据实例 1-000001 代表 type-code
     * 其中沪深京A股type=1，港股type=3，沪深指数type=10，沪深ETF type=20
     */
    public String getStockInfo(String symbol, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/stockinfo";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("zip", true).put("symbol", symbol).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }
}
