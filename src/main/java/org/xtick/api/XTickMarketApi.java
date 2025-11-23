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
public class XTickMarketApi {
    /**
     * 获取市场所有股票代码
     * 返回数据实例 1-000001 代表 type-code
     * 其中沪深京A股type=1，港股type=3，沪深指数type=10，沪深ETF type=20
     */
    public String getAllCodes(String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/codes";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("zip", true).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 获取市场行情数据数据，包括历史数据和当日盘中实时数据
     * 行情数据支持交易日内盘内实时更新，如有需要其他K线数据，比如三分钟k线或者2小时K线等，可联系作者。
     * 入参1：type 股票类别
     * 沪深京A股type=1，港股type=3，沪深指数type=10，沪深ETF type=20;
     * 入参2：code 股票代码
     * 比如平安银行为000001
     * 入参3：period 用于表示要获取的周期，枚举取值如下：
     * - tick - 分笔数据
     * - 1m - 1分钟线
     * - 5m - 5分钟线
     * - 15m - 15分钟线
     * - 30m - 30分钟线
     * - 1h - 1小时线
     * - 1d - 日线
     * - 1w - 周线
     * - 1mon - 月线
     * - 1q - 季度线
     * - 1hy - 半年线
     * - 1y - 年线
     * 参数4：fq 除权方式，用于K线数据复权计算，对tick等其他周期数据无效，枚举取值如下：
     * - 1 不复权
     * - 2 前复权
     * - 3 后复权
     * - 4 等比前复权
     * - 5 等比后复权
     * 参数5：时间范围，用于指定数据请求范围，表示的范围是[startDate , endDate]区间（包含前后边界）。
     * 特别说明：
     * period为tick类型，则单次请求时间跨度最大为一天，即startDate和endDate日期需设置为同一天。
     * period为分钟类型（包括1m、5m、15m、30m、1h），则单次请求时间跨度最大为一月，即endDate - startDate不超过30天。
     * 如果需要获取盘中实时行情数据，endDate参数填写当天交易日日期即可，tick数据包含早盘竞价数据。
     * - startDate - 起始时间，日期格式：2025-03-25
     * - endDate- 结束时间，日期格式：2025-03-25
     * 入参6：token 登录XTick网站，注册获取
     */
    public String getMarket(int type, String code, String period, String fq, String startDate, String endDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/market";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("zip", true).put("code", code).put("period", period).put("fq", fq).put("startDate", startDate).put("endDate", endDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }

    /**
     * 获取A股上市公司财务数据
     * 财务指标每日更新
     * 入参1：type 股票类别
     * 沪深京A股type=1，港股type=3;
     * 入参2：code 股票代码
     * 比如平安银行为000001
     * 入参3：report 用于表示要获取的财务报表，枚举取值如下：
     * - Balance - 资产负债表
     * - Income - 利润表
     * - CashFlow - 现金流量表
     * - Capital - 股本表
     * - Holdernum - 股东数
     * - Top10holder - 十大股东
     * - Top10flowholder - 十大流通股东
     * - Pershareindex - 每股指标
     * 参数4：时间范围，用于指定数据请求范围，表示的范围是[startDate , endDate]区间（包含前后边界）。
     * - startDate - 起始时间，日期格式：2025-03-25
     * - endDate- 结束时间，日期格式：2025-03-25
     * 入参5：token 登录XTick网站，注册获取
     */

    public String getFinancial(int type, String code, String report, String startDate, String endDate, String token, MethodType method) throws IOException {
        String url = XTickConst.serverUrl + "/doc/financial";
        Map<String, Object> para = ImmutableMap.<String, Object>builder().put("type", type).put("zip", true).put("code", code).put("report", report).put("startDate", startDate).put("endDate", endDate).put("token", token).build();
        return method.equals(MethodType.GET) ? HttpClientRest.getIntance().get(url, para) : HttpClientRest.getIntance().post(url, para);
    }
}
