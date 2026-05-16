package org.xtick.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.xtick.util.JsonUtil;

import java.io.File;
import java.io.IOException;
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

    /**
     * 按照指标字段组织数据，方便按字段查找所有股票数据
     * 一层key为字段名称，二层key为股票code，value为数据
     *
     * @return
     */
    public Map<String, Map<String, Object>> toFieldMap() {
        List<Object> codes = data.get("code");
        Map<String, Map<String, Object>> result = new TreeMap<>();//一级key为指标，二级key为股票代码
        data.entrySet().stream().forEach(entry -> {
            if (!"code".equalsIgnoreCase(entry.getKey())) {
                Map<String, Object> map = result.computeIfAbsent(entry.getKey(), k -> new TreeMap<>());
                for (int index = 0; index < codes.size(); index++) {
                    map.put(codes.get(index).toString(), entry.getValue().get(index));
                }
            }
        });
        return result;
    }


    /**
     * 按照股票code组织数据，方便按个股查找所有字段数据
     * 一层key为股票code，二层key为字段名称，value为数据
     *
     * @return
     */
    public Map<String, Map<String, Object>> toStockMap() {
        List<Object> codes = data.get("code");
        Map<String, Map<String, Object>> result = new TreeMap<>();//一级key为指标，二级key为股票代码
        data.entrySet().stream().forEach(entry -> {
            if (!"code".equalsIgnoreCase(entry.getKey())) {
                for (int index = 0; index < codes.size(); index++) {
                    Map<String, Object> map = result.computeIfAbsent(String.valueOf(codes.get(index)), k -> new TreeMap<>());
                    map.put(entry.getKey(), entry.getValue().get(index));
                }
            }
        });
        return result;
    }
}
