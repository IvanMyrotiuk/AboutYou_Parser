package com.java.aboutyou.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Offer {

    private String name;
    private String brand;
    private String color;
    private String currentPrice;
    private String initPrice;
    private String description;
    private String articleId;

}
