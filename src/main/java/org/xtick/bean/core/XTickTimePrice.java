package org.xtick.bean.core;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class XTickTimePrice implements Serializable {
    private float price;//成交价
    private long np;//内盘
    private long wp;//外盘
    private long cjl;//成交量
    private float ratio;//占比
}