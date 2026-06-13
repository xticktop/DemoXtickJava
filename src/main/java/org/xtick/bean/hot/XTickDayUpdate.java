package org.xtick.bean.hot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 增量更新数据Bean类
 * 用于 /doc/hot/dayupdate 接口
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class XTickDayUpdate implements Serializable {
    private List<Map<String, Object>> data;  // 增量数据列表
}
