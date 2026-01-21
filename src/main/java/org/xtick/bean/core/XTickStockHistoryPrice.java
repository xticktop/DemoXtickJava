package org.xtick.bean.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class XTickStockHistoryPrice implements Serializable {

    private int type;

    private String code = StringUtils.EMPTY;

    private long time;//k线日期

    private float open;

    private float close;

    private float high;

    private float low;

    private float volume;

    private float amount;

    private float preClose;

    private float maxPrice;

    private float minPrice;
    private int mark;//涨停板 -1为跌停板，1为涨停板
}
