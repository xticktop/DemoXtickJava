package org.xtick.bean;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Option {
    private String filter; //定义筛选条件
    private String sort; //定义排序字段
    private int asc; //定义排序方式  0:降序 1:升序
    private int limit = 10000;//定义截取长度
}
