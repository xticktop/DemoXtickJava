package org.xtick.constant;


import com.google.common.collect.ImmutableSet;

import java.util.Set;

public interface XTickConst {
    String token = "";//登录XTick官网，获取token
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
