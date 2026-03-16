package org.xtick.bean.core;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Validated
public class XTickMarketCount implements Serializable {
    private long time;             //k线日期
    private long szcje;            //深圳成交额
    private long shcje;            //上海成交额
    private long bjcje;            //北京成交额
    private long hscje;            //两市成交额
    private long hsjcje;           //三市成交额
    private long cybcje;           //创业板成交额
    private long kcbcje;           //科创板成交额
    private int up;                //上涨数
    private int down;              //下跌数
    private int fair;              //持平
}