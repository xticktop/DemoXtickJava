package org.xtick.bean;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommonPacket {
    private int type;//区分股票、基金、指数
    private String market;//区分交易所
    private String period;//区分数据类型，比如tick数据还是竞价数据
    private Object data;
}
