package org.xtick.api;

import com.google.common.collect.ImmutableMap;
import org.xtick.constant.MethodType;
import org.xtick.constant.XTickConst;
import org.xtick.http.HttpClientRest;

import java.io.IOException;
import java.util.Map;

/**
 * 查询订阅和取消订阅
 * 官网：http://www.xtick.top/
 */
public class XTickWebSocketApi {

    /**
     * 查询已订阅服务
     *
     * @param token
     * @param method
     * @return
     * @throws IOException
     */
    public String querySubscribe(String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/querysubscribe";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("zip", false).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 取消已订阅服务
     */
    public String unsubscribe(String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/unsubscribe";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("zip", true).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }
}
