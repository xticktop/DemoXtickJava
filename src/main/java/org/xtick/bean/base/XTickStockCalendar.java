package org.xtick.bean.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Validated
public class XTickStockCalendar implements Serializable {

    private String code = StringUtils.EMPTY;

    private long time;//k线日期

    private int status;//1-正常交易，2-休市（股市休市），3-休市(个股停牌)
    private long preTime;//上一个开盘时间
}
