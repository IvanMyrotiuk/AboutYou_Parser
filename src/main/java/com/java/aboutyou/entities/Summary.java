package com.java.aboutyou.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class Summary {
    private Integer requestCount;
    private Integer runTime;
    private Long memoryFootprint;
    private Integer extractedProducts;
}
