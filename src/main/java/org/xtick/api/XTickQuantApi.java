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
     * 量化指标-数据接口 ，行情数据全推
     * 获取沪深京股票交易日盘中实时指标数据，包括涨速、换手率、市盈率、市净率等。
     */
    public String getQunatData(int type, String field, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/quant/data";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("zip", true).put("type", type).put("field", field).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 量化指标-实时接口 ，行情数据全推
     * 获取沪深京股票交易日盘中实时指标数据，包括涨速、换手率、市盈率、市净率等。
     */
    public String getQunatTime(int type, String field, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/quant/time";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("zip", true).put("type", type).put("field", field).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }
}
