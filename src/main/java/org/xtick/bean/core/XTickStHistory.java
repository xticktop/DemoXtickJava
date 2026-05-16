package org.xtick.bean.core;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class XTickStHistory implements Serializable {
    private int type;

    private String code = StringUtils.EMPTY;


    private long time;

    private String name;//名称
}