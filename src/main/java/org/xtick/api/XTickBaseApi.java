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
}
