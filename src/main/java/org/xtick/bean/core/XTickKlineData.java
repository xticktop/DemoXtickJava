package org.xtick.bean.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * K线数据Bean类
 * 用于 /doc/kline/minute 和 /doc/kline/market 接口
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class XTickKlineData implements Serializable {
    private int type = 0;              // 标的类别
    private String code = StringUtils.EMPTY;  // 股票代码
    private long time;                 // 交易时间
    private float open;                // 开盘价
    private float close;               // 收盘价
    private float high;                // 最高价
    private float low;                 // 最低价
    private long volume;               // 成交量
    private long amount;               // 成交额
    private float preClose;            // 前收盘价
}
