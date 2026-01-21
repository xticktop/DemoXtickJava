package org.xtick.constant;


import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

public interface XTickConst {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    String token = "1a4056fb433621225db38b4924e225a2";//登录XTick官网，获取token
    String serverUrl = "http://api.xtick.top/";

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
            .add("1")
            .add("2")
            .add("3")
            .add("4")
            .add("5")
            .build();

    List<Integer> types = ImmutableList.<Integer>builder()
            .add(1)
            .add(3)
            .add(10)
            .add(20)
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
