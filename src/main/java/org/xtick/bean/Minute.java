package org.xtick.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.xtick.constant.XTickConst;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
@Slf4j
public class Minute {
    private long time;                 //时间戳
    @JsonIgnore
    private String date;                //时间戳对应日期
    private float open;                 //开盘价
    private float high;                 //最高价
    private float low;                  //最低价
    private float close;                //收盘价
    private double amount;               //成交总额
    private float volume;               //成交总量（手）

    @JsonProperty("time")
    public void setTime(long time) {
        this.time = time;
        Instant instant = Instant.ofEpochMilli(time);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.of("GMT+8"));
        this.date = localDateTime.format(XTickConst.formatter);
    }
}
