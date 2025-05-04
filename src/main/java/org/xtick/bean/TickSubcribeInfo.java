package org.xtick.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TickSubcribeInfo {
    private String token;
    private List<String> authCodes;//订阅类别 period.market  tick.SH
}