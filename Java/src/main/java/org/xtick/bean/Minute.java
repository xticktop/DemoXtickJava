package org.xtick.bean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Minute {
    private long time;                 //时间戳
    private float open;                 //开盘价
    private float high;                 //最高价
    private float low;                  //最低价
    private float close;                //收盘价
    private double amount;               //成交总额
    private float volume;               //成交总量（手）
}
