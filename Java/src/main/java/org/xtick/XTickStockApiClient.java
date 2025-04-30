package org.xtick;

import com.google.common.collect.ImmutableMap;
import okhttp3.*;
import org.xtick.bean.Minute;
import org.xtick.bean.Tick;
import org.xtick.constant.MethodType;
import org.xtick.constant.XTickConst;
import org.xtick.http.HttpClientRest;
import org.xtick.util.JsonUtil;
import org.xtick.util.XTickUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class XTickStockApiClient {
    public String getHistoryData(int type, String code, String period, String fq, String startDate, String endDate, String token, MethodType method) throws IOException {
        String url = "http://api.xtick.top/doc/history";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("zip", true).put("code", code).put("period", period).put("fq", fq).put("startDate", startDate).put("endDate", endDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }


    public void DemoForHistoryData() throws IOException {
        int type = 1;//沪深京A股Type=1，港股Type=3
        String code = "000001";
        String startDate = "2025-04-25";
        String endDate = LocalDate.now().toString();
        String result = getHistoryData(type, code, "tick", "", startDate, startDate, XTickConst.token, MethodType.GET);
        List<Tick> ticks = JsonUtil.jsonToList(result, Tick.class);
        System.out.println(String.format("code=%s,period=tick,date=%s,history data size=%s", code, startDate, ticks.size()));
        for (String period : XTickConst.historyKlinePeriods) {
            for (String fq : XTickConst.dividends) {
                result = getHistoryData(type, code, period, fq, startDate, endDate, XTickConst.token, MethodType.GET);
                List<Minute> klines = JsonUtil.jsonToList(result, Minute.class);
                System.out.println(String.format("code=%s,period=%s,fq=%s,startDate=%s,endDate=%s,history data size=%s", code, period, fq, startDate, endDate, klines.size()));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        XTickStockApiClient client = new XTickStockApiClient();
        client.DemoForHistoryData();//获取历史数据代码示例
    }
}
