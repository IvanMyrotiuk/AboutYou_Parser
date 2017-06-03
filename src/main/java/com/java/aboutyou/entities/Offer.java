package com.java.aboutyou.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Class<code> Offer</> represent an entity that will store all pulled
 * information from specified site and than use it to create XML document
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 02-06-2017
 */
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
