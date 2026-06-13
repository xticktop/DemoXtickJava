package org.xtick.bean.hot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * https://zhuanlan.zhihu.com/p/19929097921
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class XTickBKInfo implements Serializable {
    private String bkname;
    private List<String> stocks;
}
