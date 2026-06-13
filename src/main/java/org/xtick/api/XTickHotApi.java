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
public class XTickHotApi {


    /**
     * 获取沪深京股票交易日盘中资金流数据。盘中实时更新。
     * 资金区分标准如下：
     * 特大单：成交金额大于或等于100万元或成交量大于或等于5000手
     * 大单：成交金额大于或等于20万元或成交量大于或等于1000手
     * 中单：成交金额大于或等于4万元或成交量大于或等于200手
     * 小单：其它为小单
     */
    public String getHotMoney(int type, String code, String startDate, String endDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/hot/money";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("code", code).put("startDate", startDate).put("endDate", endDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }


    /**
     * 获取沪深京股票交易日盘中盘中涨停、跌停、炸板数据。盘中实时更新。
     * flag ，枚举取值如下：
     * - 1 - 涨停
     * - 2 - 跌停
     * - 3 - 炸板
     */
    public String getHotBoard(int type, int flag, String tradeDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/hot/board";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("flag", flag).put("tradeDate", tradeDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }


    /**
     * 获取财联社、同花顺、东方财富等主流金融平台资讯信息，跟随市场热点、核心。盘中实时更新。
     * 参数1：minutes 最新消息时间范围，表示获取几分钟内的最新消息。
     * 注意：
     * minutes取值大于0，按按照minutes参数，获取minutes时间内的最新消息。
     * minutes取值为0，按按照tradeDate参数，获取历史数据。
     * 参数2：tradeDate 时间范围，若需要获取历史数据，则需要将minutes设置为0。
     */
    public String getHotNews(int minutes, String tradeDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/hot/news";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("minutes", minutes).put("tradeDate", tradeDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 获取股票盘中日内分时数据，保留了价格在每个时间点的变化细节，股价全天的波动轨迹。盘中实时更新。
     */
    public String getHotTimekline(int type, String code, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/hot/timekline";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("code", code).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 获取概念板块、地域板块、行业板块数据，以及概念板块下对应的成分股数据。
     * symbol 用于表示要获取的概念板块的分类，枚举取值如下：
     * - sw1 - 申万一级行业划分
     * - sw2 - 申万二级行业划分
     * - sw3 - 申万三级行业划分
     * - zjh1 -  证监会一级行业划分
     * - zjh2 -  证监会二级行业划分
     * - ahy - A平台行业划分
     * - afg - A平台风格划分
     * - agn - A平台概念划分
     * - bgn - B平台概念划分
     * - bdy1 - B平台一级地域划分
     * - bdy1 - B平台二级地域划分
     * - cgn -  C平台概念划分
     */
    public String getHotBk(String symbol, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/hot/bk";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("symbol", symbol).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 获取个股关联的概念板块、地域板块、行业板块数据。
     */
    public String getHotGainian(String code, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/hot/gainian";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("code", code).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 市场情绪-实时接口（新接口）
     * 获取市场情绪指标数据，包括涨停家数、跌停家数、炸板率等
     */
    public String getHotEmotion(int type, String tradeDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/hot/emotion";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("tradeDate", tradeDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 竞价数据-历史接口（新接口）
     * 获取历史竞价数据
     */
    public String getHotBidHistory(int type, String code, String startDate, String endDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/hot/bidhistory";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("code", code).put("startDate", startDate).put("endDate", endDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 竞价详情-实时接口（新接口）
     * 获取竞价阶段详细数据
     */
    public String getHotBidDetail(int type, String code, String tradeDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/hot/biddetail";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("code", code).put("tradeDate", tradeDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 增量更新-热点数据（新接口）
     * 获取热点数据增量更新
     */
    public String getHotDayUpdate(String dataType, String symbol, String tradeDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/hot/dayupdate";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("dataType", dataType).put("symbol", symbol).put("tradeDate", tradeDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }
}
