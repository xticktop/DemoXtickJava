package org.xtick.bean;

import lombok.Data;

@Data
public class Tick {
    private long time;                 //时间戳
    private float lastPrice;            //最新价
    private float open;                 //开盘价
    private float high;                 //最高价
    private float low;                  //最低价
    private float lastClose;            //前收盘价
    private long amount;               //成交总额
    private long volume;               //成交总量（手）
    private float[] askPrice;             //委卖价
    private float[] bidPrice;             //委买价
    private long[] askVol;               //委卖量
    private long[] bidVol;               //委买量

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(time).append(",")
                .append(lastPrice).append(",")
                .append(open).append(",")
                .append(high).append(",")
                .append(low).append(",")
                .append(lastClose).append(",")
                .append(amount).append(",")
                .append(volume).append(",")
                .append(askPrice[0]).append(",")
                .append(askPrice[1]).append(",")
                .append(askPrice[2]).append(",")
                .append(askPrice[3]).append(",")
                .append(askPrice[4]).append(",")
                .append(bidPrice[0]).append(",")
                .append(bidPrice[1]).append(",")
                .append(bidPrice[2]).append(",")
                .append(bidPrice[3]).append(",")
                .append(bidPrice[4]).append(",")
                .append(askVol[0]).append(",")
                .append(askVol[1]).append(",")
                .append(askVol[2]).append(",")
                .append(askVol[3]).append(",")
                .append(askVol[4]).append(",")
                .append(bidVol[0]).append(",")
                .append(bidVol[1]).append(",")
                .append(bidVol[2]).append(",")
                .append(bidVol[3]).append(",")
                .append(bidVol[4]);
        return sb.toString();
    }
}
