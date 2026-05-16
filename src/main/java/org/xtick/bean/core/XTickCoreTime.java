package org.xtick.bean.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 核心指标实时数据Bean类
 * 用于 /doc/core/time 接口
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class XTickCoreTime implements Serializable {
    private long x001;  //时间戳

    private float x002;  //昨收价

    private float x003;  //现价

    private float x004;  //开盘价

    private float x005;  //最高价

    private float x006;  //最低价

    private int x007;  //总手

    private float x008;  //总金额

    private float x009;  //量比

    private String x010;  //股票代码

    private String x011;  //股票名称

    private float x012;  //买入价

    private float x013;  //卖出价

    private float x014;  //买一量

    private float x015;  //卖一量

    private float x016;  //涨跌

    private float x017;  //振幅（%）

    private float x018;  //涨停价

    private float x019;  //跌停价

    private int x020;  //涨停板或者跌停板，-1跌停板，1：涨停板

    private float x021;  //均价

    private float x022;  //现均差（%）

    private float x023;  //涨速

    private float x024;  //1分钟涨速

    private float x025;  //2分钟涨速

    private float x026;  //3分钟涨速

    private float x027;  //4分钟涨速

    private float x028;  //5分钟涨速

    private float x029;  //涨幅（%）

    private float x030;  //3日涨幅（%）

    private float x031;  //5日涨幅（%）

    private float x032;  //10日涨幅（%）

    private float x033;  //20日涨幅（%）

    private float x034;  //30日涨幅（%）

    private float x035;  //60日涨幅（%）

    private float x036;  //今年涨幅（%）

    private float x037;  //换手率（实际）

    private float x038;  //换手率

    private float x039;  //5日换手率

    private float x040;  //10日换手率

    private float x041;  //20日换手率

    private float x042;  //市盈率（静）

    private float x043;  //市盈率（动）

    private float x044;  //市盈率（TTM）

    private String x045;  //最新报告期

    private float x046;  //流通市值

    private float x047;  //总市值

    private float x048;  //市净率

    private float x049;  //每股经营活动现金流量

    private float x050;  //每股净资产

    private float x051;  //基本每股收益

    private float x052;  //稀释每股收益

    private float x053;  //每股未分配利润

    private float x054;  //每股资本公积金

    private float x055;  //扣非每股收益

    private float x056;  //净资产收益率

    private float x057;  //销售毛利率

    private float x058;  //主营收入同比增长

    private float x059;  //净利润同比增长

    private float x060;  //归属于母公司所有者的净利润同比增长

    private float x061;  //扣非净利润同比增长

    private float x062;  //加权净资产收益率

    private float x063;  //摊薄净资产收益率

    private float x064;  //毛利率

    private float x065;  //净利率

    private float x066;  //预收款 / 营业收入

    private float x067;  //销售现金流 / 营业收入

    private float x068;  //资产负债比率

    private float x069;  //存货周转率

    private float x070;  //股东总数

    private float x071;  //A股东户数

    private float x072;  //B股东户数

    private float x073;  //H股东户数

    private float x074;  //总股本

    private float x075;  //已上市流通A股
}
