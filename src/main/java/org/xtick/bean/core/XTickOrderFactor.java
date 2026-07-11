package org.xtick.bean.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 实时行情指标Bean类
 * 用于 /doc/order/factor 接口
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class XTickOrderFactor implements Serializable {
    private String code = StringUtils.EMPTY;  // 股票代码
    private Float x001;                       // 涨速
    private Float x002;                       // 换手率
    private Float x003;                       // 市盈率
    private Float x004;                       // 市净率
    private Float x005;                       // 总市值
    private Float x006;                       // 流通市值
    private Float x007;                       // 量比
    private Float x008;                       // 委比
    private Float x009;                       // 振幅
    private Float x010;                       // 均价
}
