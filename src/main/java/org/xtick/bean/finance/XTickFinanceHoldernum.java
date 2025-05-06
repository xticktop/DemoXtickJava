package org.xtick.bean.finance;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import java.io.Serializable;

@Data
@Slf4j
public class XTickFinanceHoldernum implements Serializable {

    private int type;
    private String code = StringUtils.EMPTY;
    private String endDate;//截止日期
    private String declareDate;//公告日期
    private Float shareholder;//股东总数
    private Float shareholderA;//A股东户数
    private Float shareholderB;//B股东户数
    private Float shareholderH;//H股东户数
    private Float shareholderFloat;//已流通股东户数
    private Float shareholderOther;//未流通股东户数
}
