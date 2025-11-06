package org.xtick.constant;


import com.google.common.collect.ImmutableSet;

import java.time.format.DateTimeFormatter;
import java.util.Set;

public interface XTickConst {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    String token = "85a317d32ca6260f46977eac5b460f2a";//登录XTick官网，获取token

    Set<String> reports = ImmutableSet.<String>builder()//财务报表
            .add("'Balance'")
            .add("Income")
            .add("CashFlow")
            .add("Capital")
            .add("Holdernum")
            .add("Top10holder")
            .add("Top10flowholder")
            .add("Pershareindex")
            .build();

    Set<String> dividends = ImmutableSet.<String>builder()
            .add("none")
            .add("front")
            .add("back")
            .add("front_ratio")
            .add("back_ratio")
            .build();

    Set<String> markets = ImmutableSet.<String>builder()
            .add("SZ")
            .add("SH")
            .add("HK")
            .add("BJ")
            .build();

    Set<String> timeKlinePeriods = ImmutableSet.<String>builder()
            .add("1m")
            .add("5m")
            .add("15m")
            .add("30m")
            .add("1h")
            .add("2h")
            .add("1d")
            .build();

    Set<String> historyKlinePeriods = ImmutableSet.<String>builder()
            .add("1m")
            .add("5m")
            .add("15m")
            .add("30m")
            .add("1h")
            .add("2h")
            .add("1d")
            .add("1w")
            .add("1mon")
            .add("1q")
            .add("1hy")
            .add("1y")
            .build();

    Set<String> allPeriods = ImmutableSet.<String>builder()
            .add("tick")
            .add("time")
            .add("1m")
            .add("5m")
            .add("15m")
            .add("30m")
            .add("1h")
            .add("2h")
            .add("1d")
            .add("1w")
            .add("1mon")
            .add("1q")
            .add("1hy")
            .add("1y")
            .build();

}
