package org.xtick.bean.finance;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class XTickFinanceCashFlow implements Serializable {
    private int type;
    private String code = StringUtils.EMPTY;
    private String m_timetag;                                 //截止日期
    private String m_anntime;                                 //披露日期
    private Float cash_received_ori_ins_contract_pre;        //收到原保险合同保费取得的现金
    private Float net_cash_received_rei_ope;                 //收到再保险业务现金净额
    private Float net_increase_insured_funds;                //保户储金及投资款净增加额
    private Float Net;                                       //处置交易性金融资产净增加额 increase_in_disposal
    private Float cash_for_interest;                         //收取利息、手续费及佣金的现金
    private Float net_increase_in_repurchase_funds;          //回购业务资金净增加额
    private Float cash_for_payment_original_insurance;       //支付原保险合同赔付款项的现金
    private Float cash_payment_policy_dividends;             //支付保单红利的现金
    private Float disposal_other_business_units;             //处置子公司及其他收到的现金
    private Float cash_received_from_pledges;                //减少质押和定期存款所收到的现金
    private Float cash_paid_for_investments;                 //投资所支付的现金
    private Float net_increase_in_pledged_loans;             //质押贷款净增加额
    private Float cash_paid_by_subsidiaries;                 //取得子公司及其他营业单位支付的现金净额
    private Float increase_in_cash_paid;                     //增加质押和定期存款所支付的现金
    private Float cass_received_sub_abs;                     //其中子公司吸收现金
    private Float cass_received_sub_investments;             //其中:子公司支付给少数股东的股利、利润
    private Float minority_shareholder_profit_loss;          //少数股东损益
    private Float unrecognized_investment_losses;            //未确认的投资损失
    private Float ncrease_deferred_income;                   //递延收益增加(减:减少)
    private Float projected_liability;                       //预计负债
    private Float increase_operational_payables;             //经营性应付项目的增加
    private Float reduction_outstanding_amounts_less;        //已完工尚未结算款的减少(减:增加)
    private Float reduction_outstanding_amounts_more;        //已结算尚未完工款的增加(减:减少)
    private Float goods_sale_and_service_render_cash;        //销售商品、提供劳务收到的现金
    private Float net_incr_dep_cob;                          //客户存款和同业存放款项净增加额
    private Float net_incr_loans_central_bank;               //向中央银行借款净增加额(万元
    private Float net_incr_fund_borr_ofi;                    //拆入资金净增加额
    private Float tax_levy_refund;                           //收到的税费与返还
    private Float cash_paid_invest;                          //投资支付的现金
    private Float other_cash_recp_ral_oper_act;              //收到的其他与经营活动有关的现金
    private Float stot_cash_inflows_oper_act;                //经营活动现金流入小计
    private Float goods_and_services_cash_paid;              //购买商品、接受劳务支付的现金
    private Float net_incr_clients_loan_adv;                 //客户贷款及垫款净增加额
    private Float net_incr_dep_cbob;                         //存放中央银行和同业款项净增加额
    private Float handling_chrg_paid;                        //支付利息、手续费及佣金的现金
    private Float cash_pay_beh_empl;                         //支付给职工以及为职工支付的现金
    private Float pay_all_typ_tax;                           //支付的各项税费
    private Float other_cash_pay_ral_oper_act;               //支付其他与经营活动有关的现金
    private Float stot_cash_outflows_oper_act;               //经营活动现金流出小计
    private Float net_cash_flows_oper_act;                   //经营活动产生的现金流量净额
    private Float cash_recp_disp_withdrwl_invest;            //收回投资所收到的现金
    private Float cash_recp_return_invest;                   //取得投资收益所收到的现金
    private Float net_cash_recp_disp_fiolta;                 //处置固定资产、无形资产和其他长期投资收到的现金
    private Float other_cash_recp_ral_inv_act;               //收到的其他与投资活动有关的现金
    private Float stot_cash_inflows_inv_act;                 //投资活动现金流入小计
    private Float cash_pay_acq_const_fiolta;                 //购建固定资产、无形资产和其他长期投资支付的现金
    private Float stot_cash_outflows_inv_act;                //投资活动现金流出小计
    private Float net_cash_flows_inv_act;                    //投资活动产生的现金流量净额
    private Float cash_recp_cap_contrib;                     //吸收投资收到的现金
    private Float cash_recp_borrow;                          //取得借款收到的现金
    private Float proc_issue_bonds;                          //发行债券收到的现金
    private Float other_cash_recp_ral_fnc_act;               //收到其他与筹资活动有关的现金
    private Float stot_cash_inflows_fnc_act;                 //筹资活动现金流入小计
    private Float cash_prepay_amt_borr;                      //偿还债务支付现金
    private Float cash_pay_dist_dpcp_int_exp;                //分配股利、利润或偿付利息支付的现金
    private Float other_cash_pay_ral_fnc_act;                //支付其他与筹资的现金
    private Float stot_cash_outflows_fnc_act;                //筹资活动现金流出小计
    private Float net_cash_flows_fnc_act;                    //筹资活动产生的现金流量净额
    private Float eff_fx_flu_cash;                           //汇率变动对现金的影响
    private Float net_incr_cash_cash_equ;                    //现金及现金等价物净增加额
    private Float cash_cash_equ_beg_period;                  //期初现金及现金等价物余额
    private Float cash_cash_equ_end_period;                  //期末现金及现金等价物余额
    private Float net_profit;                                //净利润
    private Float plus_prov_depr_assets;                     //资产减值准备
    private Float depr_fa_coga_dpba;                         //固定资产折旧、油气资产折耗、生产性物资折旧
    private Float amort_intang_assets;                       //无形资产摊销
    private Float amort_lt_deferred_exp;                     //长期待摊费用摊销
    private Float decr_deferred_exp;                         //待摊费用的减少
    private Float incr_acc_exp;                              //预提费用的增加
    private Float loss_disp_fiolta;                          //处置固定资产、无形资产和其他长期资产的损失
    private Float loss_scr_fa;                               //固定资产报废损失
    private Float loss_fv_chg;                               //公允价值变动损失
    private Float fin_exp;                                   //财务费用
    private Float invest_loss;                               //投资损失
    private Float decr_deferred_inc_tax_assets;              //递延所得税资产减少
    private Float incr_deferred_inc_tax_liab;                //递延所得税负债增加
    private Float decr_inventories;                          //存货的减少
    private Float decr_oper_payable;                         //经营性应收项目的减少
    private Float others;                                    //其他
    private Float im_net_cash_flows_oper_act;                //经营活动产生现金流量净额
    private Float conv_debt_into_cap;                        //债务转为资本
    private Float conv_corp_bonds_due_within_1y;             //一年内到期的可转换公司债券
    private Float fa_fnc_leases;                             //融资租入固定资产
    private Float end_bal_cash;                              //现金的期末余额
    private Float less_beg_bal_cash;                         //现金的期初余额
    private Float plus_end_bal_cash_equ;                     //现金等价物的期末余额
    private Float less_beg_bal_cash_equ;                     //现金等价物的期初余额
    private Float im_net_incr_cash_cash_equ;                 //现金及现金等价物的净增加额

}
