package org.xtick.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TickSubcribeInfo {
    private String token;
    /**
     * 解释
     * 订阅类别 period.market.type  tick.SH.1
     * period代表周期，可取枚举值如下：tick time   代表tick数据和K线数据
     * market代表市场，可取枚举值如下：SZ SH BJ HK 代表深交所、上交所、北交所、港交所
     * type代表数据类型，可取枚举值如下：1 3 10 20  代表沪深京A股type=1，港股type=3，沪深指数type=10，沪深ETF type=20;
     * <p>
     * <p>
     * 最后，总结，大家关注以下枚举值即可
     * 订阅tick数据可取枚举值如下：
     * 深交所：tick.SZ.1  tick.SZ.10  tick.SZ.20
     * 上交所：tick.SH.1  tick.SH.10  tick.SH.20
     * 北交所：tick.BJ.1
     * 港交所：tick.HK.3
     * <p>
     * 订阅time数据可取枚举值如下：
     * 深交所：time.SZ.1  time.SZ.10  time.SZ.20
     * 上交所：time.SH.1  time.SH.10  time.SH.20
     * 北交所：time.BJ.1
     * 港交所：time.HK.3
     */
    private List<String> authCodes;//订阅类别 period.market.type  tick.SH.1
}