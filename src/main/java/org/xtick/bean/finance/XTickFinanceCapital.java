package org.xtick.bean.finance;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class XTickFinanceCapital implements Serializable {
    private int type;
    private String code = StringUtils.EMPTY;
    private String m_timetag;//报告截止日
    private String m_anntime;//公告日
    private Float total_capital; //总股本
    private Float circulating_capital;//已上市流通A股
    private Float restrict_circulating_capital;//限售流通股份
}
