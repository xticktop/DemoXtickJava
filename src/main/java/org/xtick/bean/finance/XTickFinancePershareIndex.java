package org.xtick.bean.finance;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
@Slf4j
public class XTickFinancePershareIndex implements Serializable {
    private int type;
    private String code = StringUtils.EMPTY;
    private String m_timetag;                                 //报告截止日
    private String m_anntime;                                 //公告日
    private Float s_fa_ocfps;                                //每股经营活动现金流量
    private Float s_fa_bps;                                  //每股净资产
    private Float s_fa_eps_basic;                            //基本每股收益
    private Float s_fa_eps_diluted;                          //稀释每股收益
    private Float s_fa_undistributedps;                      //每股未分配利润
    private Float s_fa_surpluscapitalps;                     //每股资本公积金
    private Float adjusted_earnings_per_share;               //扣非每股收益
    private Float du_return_on_equity;                       //净资产收益率
    private Float sales_gross_profit;                        //销售毛利率
    private Float inc_revenue_rate;                          //主营收入同比增长
    private Float du_profit_rate;                            //净利润同比增长
    private Float inc_net_profit_rate;                       //归属于母公司所有者的净利润同比增长
    private Float adjusted_net_profit_rate;                  //扣非净利润同比增长
    private Float inc_total_revenue_annual;                  //营业总收入滚动环比增长
    private Float inc_net_profit_to_shareholders_annual;     //归属净利润滚动环比增长
    private Float adjusted_profit_to_profit_annual;          //扣非净利润滚动环比增长
    private Float equity_roe;                                //加权净资产收益率
    private Float net_roe;                                   //摊薄净资产收益率
    private Float total_roe;                                 //摊薄总资产收益率
    private Float gross_profit;                              //毛利率
    private Float net_profit;                                //净利率
    private Float actual_tax_rate;                           //实际税率
    private Float pre_pay_operate_income;                    //预收款 / 营业收入
    private Float sales_cash_flow;                           //销售现金流 / 营业收入
    private Float gear_ratio;                                //资产负债比率
    private Float inventory_turnover;                        //存货周转率

}
