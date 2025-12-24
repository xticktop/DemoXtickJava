package org.xtick.api;

import com.google.common.collect.ImmutableMap;
import org.xtick.constant.MethodType;
import org.xtick.constant.XTickConst;
import org.xtick.http.HttpClientRest;

import java.io.IOException;
import java.util.Map;

/**
 * 量化数据接口
 * <p>
 * 官网：http://www.xtick.top/
 */
public class XTickQuantApi {

    /**
     * 核心指标-实时接口
     * 获取沪深京股票交易日盘中实时指标数据，包括涨速、换手率、市盈率、市净率等。
     */
    public String getCoreTime(int type, String code, String field, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/core/time";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("zip", true).put("code", code).put("field", field).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }


    /**
     * 复权数据-变更接口
     * 获取有复权变化的股票，方便更新k线历史前复权数据。
     */
    public String getCoreChange(int type, String tradeDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/core/change";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("zip", true).put("type", type).put("tradeDate", tradeDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 量化指标-实时接口 ，行情数据全推
     * 获取沪深京股票交易日盘中实时指标数据，包括涨速、换手率、市盈率、市净率等。
     */
    public String getQunatData(int type, String field, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/quant/data";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("zip", true).put("type", type).put("field", field).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }
}
