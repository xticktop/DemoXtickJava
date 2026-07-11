package org.xtick.bean.finance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * 财务指标核心数据Bean类
 * 用于 /doc/core 接口（财务指标）
 * 返回动态字段，使用Map存储
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class XTickFinancialCore implements Serializable {
    private String code = StringUtils.EMPTY;

    private String reportDate;                                 //报告截止日

    private String publicDate;                                 //公告日

    private Float ocfps;                                //每股经营活动现金流量

    private Float bps;                                  //每股净资产

    private Float basicEps;                            //基本每股收益

    private Float dilutedEps;                          //稀释每股收益

    private Float dps;                      //每股未分配利润

    private Float fund;                     //每股资本公积金

    private Float equity;                       //净资产收益率

    private Float salesProfit;                        //销售毛利率

    private Float revenueInc;                          //主营收入同比增长

    private Float profitInc;                            //净利润同比增长

    private Float netProfitM;                       //归属于母公司所有者的净利润同比增长

    private Float netProfitA;                  //扣非净利润同比增长



    private Float roe;                                   //摊薄净资产收益率


    private Float grossProfit;                              //毛利率

    private Float netProfit;                                //净利率


    private Float prePay;                    //预收款 / 营业收入

    private Float salesCash;                           //销售现金流 / 营业收入

    private Float gearRatio;                                //资产负债比率

    private Float turnover;                        //存货周转率
}
