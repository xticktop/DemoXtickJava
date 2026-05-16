package org.xtick.bean.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 量化因子历史数据Bean类
 * 用于 /doc/quant/history 接口
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class XTickQuantHistory implements Serializable {
    private String code = StringUtils.EMPTY;  // 股票代码
    private String tradeDate = StringUtils.EMPTY;  // 交易日期
    private Float x001;                       // 因子x001
    private Float x002;                       // 因子x002
    private Float x003;                       // 因子x003
    private Float x004;                       // 因子x004
    private Float x005;                       // 因子x005
    private Float x006;                       // 因子x006
    private Float x007;                       // 因子x007
    private Float x008;                       // 因子x008
    private Float x009;                       // 因子x009
    private Float x010;                       // 因子x010
}
