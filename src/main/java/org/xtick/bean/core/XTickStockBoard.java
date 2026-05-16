package org.xtick.bean.core;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class XTickStockBoard implements Serializable {

    private int type;

    private String code = StringUtils.EMPTY;

    private long time;              //k线日期

    private int flag;           //1涨停 2跌停 3炸板

    private int count;           //炸板次数

    private long ftime;          //首次触及条件时间（涨停或者跌停板）

    private int days;           //连续涨停天数

    private float preClose;            //昨日收盘价

    private float price;          //最新价(收盘价)

    private float volume;           //成交量

    private float amount;           //成交额

    private float bv1;           //买一量

    private float sv1;           //卖一量

    private float bp1;           //买一价

    private float sp1;           //卖一价

    private long updateTime;//更新操作时间戳
}
