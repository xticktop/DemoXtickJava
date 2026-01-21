package org.xtick.bean.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class XTickTransactionCount implements Serializable {

    private int type;

    private String code = StringUtils.EMPTY;


    private long time;//k线日期


    private int buyNumber;//主买单总单数

    private int sellNumber;//主卖单总单数

    private int transactionNumber;//成交笔数


    private float buyMostAmount;//主买特大单成交额

    private float buyBigAmount;//主买大单成交额

    private float buyMediumAmount;//主买中单成交额

    private float buySmallAmount;//主买小单成交额


    private float sellMostAmount;//主卖特大单成交额

    private float sellBigAmount;//主卖大单成交额

    private float sellMediumAmount;//主卖中单成交额

    private float sellSmallAmount;//主卖小单成交额


    private int buyMostVolume;//主买特大单成交量

    private int buyBigVolume;//主买大单成交量

    private int buyMediumVolume;//主买中单成交量

    private int buySmallVolume;//主买小单成交量


    private int sellMostVolume;//主卖特大单成交量

    private int sellBigVolume;//主卖大单成交量

    private int sellMediumVolume;//主卖中单成交量

    private int sellSmallVolume;//主卖小单成交量

}