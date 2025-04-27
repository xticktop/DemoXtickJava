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
public class TickPacket{
    private String period;
    private String authCode;
    private int seqNo;
    private Map<String, Tick> data;
}
