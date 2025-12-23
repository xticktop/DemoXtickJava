package org.xtick.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuantPacket implements Serializable {
    private int seqNo;
    private int type;//区分股票、基金、指数
    private String market;//区分交易所
    private String period;//区分数据类型，比如tick数据还是竞价数据

    private Map<String, List<Object>> data;//一级key为指标名称，value为数据列表，固定key为code，代表股票序列

    public  Map<String, Map<String, Object>> toMap() {
        List<Object> codes = data.get("code");
        Map<String, Map<String, Object>> result = new TreeMap<>();//一级key为指标，二级key为股票代码
        data.entrySet().stream().forEach(entry -> {
            Map<String, Object> map = result.computeIfAbsent(entry.getKey(), k -> new TreeMap<>());
            for (int index = 0; index < codes.size(); index++) {
                map.put(codes.get(index).toString(), entry.getValue().get(index));
            }
        });
        return result;
    }
}
