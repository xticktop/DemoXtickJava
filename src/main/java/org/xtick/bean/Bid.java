package org.xtick.bean;


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
public class Bid implements Serializable {
    private int type;
    private String code = StringUtils.EMPTY;
    private long time;              //k线日期
    private float price;            //最新价
    private float close;            //前收盘价
    private float jjzf;                //竞价涨幅
    private long jjl;                 //竞价量
    private long jje;                  //竞价金额
    private long nol;               //未匹配量
    private long noe;               //未匹配金额
}