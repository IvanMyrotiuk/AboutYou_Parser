package com.java.aboutyou.entities;

import lombok.Builder;
import lombok.Getter;

/**
 * Class<code> Summary</> represent an entity that will store data resources
 * information for current project
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 02-06-2017
 */
@Getter
@Builder
public class Summary {
    private Integer requestCount;
    private Integer runTime;
    private Long memoryFootprint;
    private Integer extractedProducts;
}
