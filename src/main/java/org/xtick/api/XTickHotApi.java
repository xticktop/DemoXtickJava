package org.xtick.api;

import com.google.common.collect.ImmutableMap;
import org.xtick.constant.MethodType;
import org.xtick.constant.XTickConst;
import org.xtick.http.HttpClientRest;

import java.io.IOException;
import java.util.Map;

/**
 * 短线热点数据API接口。
 * <p>
 * 官网：http://www.xtick.top/
 */
public class XTickHotApi {

    /**
     * 连板天梯-实时接口
     * 获取沪深京股票交易日盘中涨停板、跌停板、炸板数据。
     * flag 枚举取值：1-涨停，2-跌停，3-炸板
     */
    public String getHotBoard(int type, int flag, String tradeDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/hot/board";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("flag", flag).put("tradeDate", tradeDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 市场情绪-实时接口
     * 获取市场情绪指标数据，包括涨停家数、跌停家数、炸板率等
     */
    public String getHotEmotion(int type, String tradeDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/hot/emotion";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("tradeDate", tradeDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 资金流向-实时接口
     * 获取沪深京股票交易日盘中资金流数据，盘中实时更新。
     */
    public String getHotTimeMoney(int type, String code, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/hot/timemoney";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("code", code).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 资金流向-历史接口
     * 获取沪深京股票交易日盘中资金流数据，盘后更新。
     */
    public String getHotHistoryMoney(int type, String code, String startDate, String endDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/hot/historymoney";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("code", code).put("startDate", startDate).put("endDate", endDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 竞价数据-历史接口
     * 获取历史竞价数据
     */
    public String getHotBidHistory(int type, String code, String startDate, String endDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/hot/bidhistory";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("code", code).put("startDate", startDate).put("endDate", endDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 竞价详情-实时接口
     * 获取竞价阶段详细数据
     */
    public String getHotBidDetail(int type, String code, String tradeDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/hot/biddetail";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("code", code).put("tradeDate", tradeDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 新闻资讯-实时接口
     * 获取主流金融平台资讯信息，跟随市场热点。盘中实时更新。
     */
    public String getHotNews(int minutes, String tradeDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/hot/news";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("minutes", minutes).put("tradeDate", tradeDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 日内分时-实时接口
     * 获取股票盘中日内分时数据。盘中实时更新。
     */
    public String getHotTimekline(int type, String code, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/hot/timekline";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("code", code).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 概念板块成分股数据
     * 获取概念板块、地域板块、行业板块数据，以及板块下对应的成分股数据。
     */
    public String getHotBk(String symbol, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/hot/bk";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("symbol", symbol).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 股票关联概念板块数据
     * 获取个股关联的概念板块、地域板块、行业板块数据。
     */
    public String getHotGainian(String code, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/hot/gainian";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("code", code).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 增量更新-热点数据
     * 获取热点数据增量更新，提供交易日当天全市场增量数据。
     */
    public String getHotDayUpdate(String dataType, String symbol, String tradeDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/hot/dayupdate";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("dataType", dataType).put("symbol", symbol).put("tradeDate", tradeDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }
}
