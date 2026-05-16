package org.xtick.bean.hot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 市场情绪实时数据Bean类
 * 用于 /doc/hot/emotion 接口
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class XTickEmotion implements Serializable {
    private long time;                 // 时间
    private String lastTime = StringUtils.EMPTY;  // 最后更新时间
    private int ztlbnum;               // 涨停连板数
    private int ztnum;                 // 涨停数
    private int dtnum;                 // 跌停数
    private int zbnum;                 // 炸板数
    private Float mhigh;               // 最高涨幅
    private long jjl;                  // 竞价量
    private Float zbl;                 // 炸板率
    private Float jj1t2;               // 竞价1进2
    private Float jj2t3;               // 竞价2进3
    private Float jj3t4;               // 竞价3进4
    private Float jj4t5;               // 竞价4进5
    private Float jj5tn;               // 竞价5进N
    private Float zb1t2;               // 炸板1进2
    private Float zb2t3;               // 炸板2进3
    private Float zb3t4;               // 炸板3进4
    private Float zb4t5;               // 炸板4进5
    private Float zb5tn;               // 炸板5进N
    private String updateTime = StringUtils.EMPTY;  // 更新时间
}
