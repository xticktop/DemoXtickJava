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
public class XTickCoreApi {

    /**
     * 核心指标-实时接口
     * 获取沪深京股票交易日盘中实时指标数据，包括涨速、换手率、市盈率、市净率等。
     */
    public String getCoreTime(int type, String code, String field, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/core/time";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("code", code).put("field", field).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }


    /**
     * 股票除权除息历史数据，盘后更新。
     * 可以按单个股票获取个股除权除息历史记录，也可以使用all参数，获取全市场的股票除权除息数据。
     */
    public String getCoreChuQuan(int type, String code, String startDate, String endDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/core/chuquan";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("code", code)
                .put("startDate", startDate).put("endDate", endDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 停牌股票历史数据，盘后更新。
     * 可以按单个股票获取个股停牌历史记录，也可以使用all参数，获取全市场股票的停牌数据。
     */
    public String getCoreTingpai(int type, String code, String startDate, String endDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/core/tingpai";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("code", code)
                .put("startDate", startDate).put("endDate", endDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * ST股票历史数据，数据从2022年3月开始，盘后更新。
     * 可以按单个股票获取个股ST历史记录，也可以使用all参数，获取全市场的ST股票数据。
     */
    public String getCoreSt(int type, String code, String startDate, String endDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/core/st";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("code", code)
                .put("startDate", startDate).put("endDate", endDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }


    /**
     * 股票涨跌停历史数据，盘后更新。
     * 可以按单个股票获取个股涨跌停历史记录，也可以使用all参数，获取全市场股票的涨跌停数据。
     */
    public String getCorePrice(int type, String code, int fq, String startDate, String endDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/core/price";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("code", code).put("fq", fq)
                .put("startDate", startDate).put("endDate", endDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 股票分时成交数据接口
     */
    public String getCoreFenbi(int type, String code, String tradeDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/core/fenbi";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("code", code).put("tradeDate", tradeDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 股票分价成交数据接口，盘后更新
     */
    public String getCoreFenjia(int type, String code, String tradeDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/core/fenjia";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("code", code).put("tradeDate", tradeDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 资金流
     */
    public String getCoreTransaction(int type, String code, String tradeDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/core/transaction";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("code", code).put("tradeDate", tradeDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }
}
