package org.xtick.bean.hot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class XTickNewsInfo implements Serializable {


    private int platId;//新闻源Id



    private String id;// 新闻id


    private String platName;//新闻源名称


    private long time;// 新闻时间


    private String title;// 新闻标题


    private String info;// 新闻摘要


    private String url;// 新闻链接
}
