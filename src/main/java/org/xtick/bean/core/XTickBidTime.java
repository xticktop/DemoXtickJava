package org.xtick.bean.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 竞价数据实时Bean类
 * 用于 /doc/core/bidtime 接口
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class XTickBidTime implements Serializable {
    private int type;       // 标的类别
    private String code;    // 股票代码
    private long time;      // 交易时间
    private int seq;        // bar顺序
    private float price;    // 最新价
    private float close;    // 前收盘价
    private float jjzf;     // 竞价涨幅
    private long jjl;       // 竞价量
    private long jje;       // 竞价金额
    private long nol;       // 未匹配量
    private long noe;       // 未匹配金额
    private int trend;      // 交易方向（-1：主动性卖，0：中性盘，1：主动性买）
}
