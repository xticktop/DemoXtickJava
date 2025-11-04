package org.xtick.bean.finance;


import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class XTickFinanceBalance implements Serializable {
    private int type;
    private String code = StringUtils.EMPTY;
    private String m_timetag;                                 //截止日期
    private String m_anntime;                                 //披露日期
    private Float internal_shoule_recv;                      //内部应收款
    private Float fixed_capital_clearance;                   //固定资产清理
    private Float should_pay_money;                          //应付分保账款
    private Float settlement_payment;                        //结算备付金
    private Float receivable_premium;                        //应收保费
    private Float accounts_receivable_reinsurance;           //应收分保账款
    private Float reinsurance_contract_reserve;              //应收分保合同准备金
    private Float dividends_payable;                         //应收股利
    private Float tax_rebate_for_export;                     //应收出口退税
    private Float subsidies_receivable;                      //应收补贴款
    private Float deposit_receivable;                        //应收保证金
    private Float apportioned_cost;                          //待摊费用
    private Float profit_and_current_assets_with_deal;       //待处理流动资产损益
    private Float current_assets_one_year;                   //一年内到期的非流动资产
    private Float long_term_receivables;                     //长期应收款
    private Float other_long_term_investments;               //其他长期投资
    private Float original_value_of_fixed_assets;            //固定资产原值
    private Float net_value_of_fixed_assets;                 //固定资产净值
    private Float depreciation_reserves_of_fixed_assets;     //固定资产减值准备
    private Float productive_biological_assets;              //生产性生物资产
    private Float public_welfare_biological_assets;          //公益性生物资产
    private Float oil_and_gas_assets;                        //油气资产
    private Float development_expenditure;                   //开发支出
    private Float right_of_split_share_distribution;         //股权分置流通权
    private Float other_non_mobile_assets;                   //其他非流动资产
    private Float handling_fee_and_commission;               //应付手续费及佣金
    private Float other_payables;                            //其他应交款
    private Float margin_payable;                            //应付保证金
    private Float internal_accounts_payable;                 //内部应付款
    private Float advance_cost;                              //预提费用
    private Float insurance_contract_reserve;                //保险合同准备金
    private Float broker_buying_and_selling_securities;      //代理买卖证券款
    private Float acting_underwriting_securities;            //代理承销证券款
    private Float international_ticket_settlement;           //国际票证结算
    private Float domestic_ticket_settlement;                //国内票证结算
    private Float deferred_income;                           //递延收益
    private Float short_term_bonds_payable;                  //应付短期债券
    private Float long_term_deferred_income;                 //长期递延收益
    private Float undetermined_investment_losses;            //未确定的投资损失
    private Float quasi_distribution_of_cash_dividends;      //拟分配现金股利
    private Float provisions_not;                            //预计负债
    private Float cust_bank_dep;                             //吸收存款及同业存放
    private Float provisions;                                //预计流动负债
    private Float less_tsy_stk;                              //减:库存股
    private Float cash_equivalents;                          //货币资金
    private Float loans_to_oth_banks;                        //拆出资金
    private Float tradable_fin_assets;                       //交易性金融资产
    private Float derivative_fin_assets;                     //衍生金融资产
    private Float bill_receivable;                           //应收票据
    private Float account_receivable;                        //应收账款
    private Float advance_payment;                           //预付款项
    private Float int_rcv;                                   //应收利息
    private Float other_receivable;                          //其他应收款
    private Float red_monetary_cap_for_sale;                 //买入返售金融资产
    private Float agency_bus_assets;                         //以公允价值计量且其变动计入当期损益的金融资产
    private Float inventories;                               //存货
    private Float other_current_assets;                      //其他流动资产
    private Float total_current_assets;                      //流动资产合计
    private Float loans_and_adv_granted;                     //发放贷款及垫款
    private Float fin_assets_avail_for_sale;                 //可供出售金融资产
    private Float held_to_mty_invest;                        //持有至到期投资
    private Float long_term_eqy_invest;                      //长期股权投资
    private Float invest_real_estate;                        //投资性房地产
    private Float accumulated_depreciation;                  //累计折旧
    private Float fix_assets;                                //固定资产
    private Float constru_in_process;                        //在建工程
    private Float construction_materials;                    //工程物资
    private Float long_term_liabilities;                     //长期负债
    private Float intang_assets;                             //无形资产
    private Float goodwill;                                  //商誉
    private Float long_deferred_expense;                     //长期待摊费用
    private Float deferred_tax_assets;                       //递延所得税资产
    private Float total_non_current_assets;                  //非流动资产合计
    private Float tot_assets;                                //资产总计
    private Float shortterm_loan;                            //短期借款
    private Float borrow_central_bank;                       //向中央银行借款
    private Float loans_oth_banks;                           //拆入资金
    private Float tradable_fin_liab;                         //交易性金融负债
    private Float derivative_fin_liab;                       //衍生金融负债
    private Float notes_payable;                             //应付票据
    private Float accounts_payable;                          //应付账款
    private Float advance_peceipts;                          //预收账款
    private Float fund_sales_fin_assets_rp;                  //卖出回购金融资产款
    private Float empl_ben_payable;                          //应付职工薪酬
    private Float taxes_surcharges_payable;                  //应交税费
    private Float int_payable;                               //应付利息
    private Float dividend_payable;                          //应付股利
    private Float other_payable;                             //其他应付款
    private Float non_current_liability_in_one_year;         //一年内到期的非流动负债
    private Float other_current_liability;                   //其他流动负债
    private Float total_current_liability;                   //流动负债合计
    private Float long_term_loans;                           //长期借款
    private Float bonds_payable;                             //应付债券
    private Float longterm_account_payable;                  //长期应付款
    private Float grants_received;                           //专项应付款
    private Float deferred_tax_liab;                         //递延所得税负债
    private Float other_non_current_liabilities;             //其他非流动负债
    private Float non_current_liabilities;                   //非流动负债合计
    private Float tot_liab;                                  //负债合计
    private Float cap_stk;                                   //实收资本(或股本)
    private Float cap_rsrv;                                  //资本公积
    private Float specific_reserves;                         //专项储备
    private Float surplus_rsrv;                              //盈余公积
    private Float prov_nom_risks;                            //一般风险准备
    private Float undistributed_profit;                      //未分配利润
    private Float cnvd_diff_foreign_curr_stat;               //外币报表折算差额
    private Float tot_shrhldr_eqy_excl_min_int;              //归属于母公司股东权益合计
    private Float minority_int;                              //少数股东权益
    private Float total_equity;                              //所有者权益合计
    private Float tot_liab_shrhldr_eqy;                      //负债和股东权益总计
}
