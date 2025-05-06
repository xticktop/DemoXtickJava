package org.xtick.bean.finance;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
@Slf4j
public class XTickFinanceIncome implements Serializable {
    private int type;
    private String code = StringUtils.EMPTY;
    private String m_timetag;                                 //截止日期
    private String m_anntime;                                 //披露日期
    private Float revenue_inc;                               //营业收入
    private Float earned_premium;                            //已赚保费
    private Float real_estate_sales_income;                  //房地产销售收入
    private Float total_operating_cost;                      //营业总成本
    private Float real_estate_sales_cost;                    //房地产销售成本
    private Float research_expenses;                         //研发费用
    private Float surrender_value;                           //退保金
    private Float net_payments;                              //赔付支出净额
    private Float net_withdrawal_ins_con_res;                //提取保险合同准备金净额
    private Float policy_dividend_expenses;                  //保单红利支出
    private Float reinsurance_cost;                          //分保费用
    private Float change_income_fair_value;                  //公允价值变动收益
    private Float futures_loss;                              //期货损益
    private Float trust_income;                              //托管收益
    private Float subsidize_revenue;                         //补贴收入
    private Float other_business_profits;                    //其他业务利润
    private Float net_profit_excl_merged_int_inc;            //被合并方在合并前实现净利润
    private Float int_inc;                                   //利息收入
    private Float handling_chrg_comm_inc;                    //手续费及佣金收入
    private Float less_handling_chrg_comm_exp;               //手续费及佣金支出
    private Float other_bus_cost;                            //其他业务成本
    private Float plus_net_gain_fx_trans;                    //汇兑收益
    private Float il_net_loss_disp_noncur_asset;             //非流动资产处置收益
    private Float inc_tax;                                   //所得税费用
    private Float unconfirmed_invest_loss;                   //未确认投资损失
    private Float net_profit_excl_min_int_inc;               //归属于母公司所有者的净利润
    private Float less_int_exp;                              //利息支出
    private Float other_bus_inc;                             //其他业务收入
    private Float revenue;                                   //营业总收入
    private Float total_expense;                             //营业成本
    private Float less_taxes_surcharges_ops;                 //营业税金及附加
    private Float sale_expense;                              //销售费用
    private Float less_gerl_admin_exp;                       //管理费用
    private Float financial_expense;                         //财务费用
    private Float less_impair_loss_assets;                   //资产减值损失
    private Float plus_net_invest_inc;                       //投资收益
    private Float incl_inc_invest_assoc_jv_entp;             //联营企业和合营企业的投资收益
    private Float oper_profit;                               //营业利润
    private Float plus_non_oper_rev;                         //营业外收入
    private Float less_non_oper_exp;                         //营业外支出
    private Float tot_profit;                                //利润总额
    private Float net_profit_incl_min_int_inc;               //净利润
    private Float net_profit_incl_min_int_inc_after;         //净利润(扣除非经常性损益后)
    private Float minority_int_inc;                          //少数股东损益
    private Float s_fa_eps_basic;                            //基本每股收益
    private Float s_fa_eps_diluted;                          //稀释每股收益
    private Float total_income;                              //综合收益总额
    private Float total_income_minority;                     //归属于少数股东的综合收益总额
    private Float other_compreh_inc;                         //其他收益
}
