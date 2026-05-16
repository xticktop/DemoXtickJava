package org.xtick.bean.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class XTickLevelOne {
    private long time;                 //时间戳
    private float lastPrice;            //最新价
    private float open;                 //开盘价
    private float high;                 //最高价
    private float low;                  //最低价
    private float lastClose;            //前收盘价
    private float amount;               //成交总额
    private float volume;               //成交总量（手）
    private int transactionNum;          //成交笔数
    private float bp1;//买一价
    private float bp2; //买二价
    private float bp3; //买三价
    private float bp4; //买四价
    private float bp5; //买五价
    private float bv1; //买一量
    private float bv2; //买二量
    private float bv3;  //买三量
    private float bv4; //买四量
    private float bv5; //买五量
    private float sp1; //卖一价
    private float sp2; //卖二价
    private float sp3; //卖三价
    private float sp4; //卖四价
    private float sp5; //卖五价
    private float sv1; //卖一量
    private float sv2; //卖二量
    private float sv3; //卖三量
    private float sv4; //卖四量
    private float sv5; //卖五量

}
