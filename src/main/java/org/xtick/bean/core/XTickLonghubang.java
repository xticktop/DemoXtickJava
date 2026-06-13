package org.xtick.bean.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class XTickLonghubang implements Serializable {

    private String code;//股票代码

    private long time;//交易时间

    private String explanation;//上榜原因

    private String name;    //名称

    private float close;    //收盘价

    private float zdf;    //涨跌幅

    private float netamt;    //龙虎榜净买额（元）

    private float buyamt;  //龙虎榜买入额（元）

    private float sellamt;    //龙虎榜卖出额（元）

    private float dealamt;    //龙虎榜成交额（元）

    private float marketamt;    //市场总成交额（元）

    private float netratio;    //净买额占总成交比

    private float amtratio;    //成交额占总成交比

    private float hsl;    //换手率

    private float freecap;    //流通市值（元）

}
