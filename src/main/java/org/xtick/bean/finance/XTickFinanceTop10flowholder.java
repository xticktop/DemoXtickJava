package org.xtick.bean.finance;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import java.io.Serializable;

@Data
@Slf4j
public class XTickFinanceTop10flowholder implements Serializable {
    private int type;
    private String code = StringUtils.EMPTY;
    private String endDate;//截止日期
    private String declareDate;//公告日期
    private String name;//股东名称
    private Float quantity;//持股数量
    private String reason;//变动原因
    private Float ratio;//持股比例
    private String nature;//股份性质
    @JsonProperty("rank")
    private int rankId;//持股排名

}
