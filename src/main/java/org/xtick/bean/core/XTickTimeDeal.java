package org.xtick.bean.core;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class XTickTimeDeal implements Serializable {
    private long time;
    private float price;//成交价
    private float zd;//涨跌额
    private long cjl;//成交量
    private long cje;//成交额
    private int trend;//-1主动性卖，1主动性买
}