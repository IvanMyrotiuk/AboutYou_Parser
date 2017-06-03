package com.java.aboutyou.service;

import com.java.aboutyou.entities.Summary;

import java.io.IOException;

/**
 * Interface<code> Parser</> that has to be implemented by all specific
 * Parsers to parse site and get needed information from it
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 02-06-2017
 */
public interface Parser<T> {
    /**
     * Method <code> parse</> retrieve information from AboutYou site for specified criteria'
     * @param criteria specified criteria for retrieving information
     * @param summaryBuilder collector that collect data resources information
     * @return objects that was created from retrieved information
     * @throws IOException
     */
    T parse(String criteria, Summary.SummaryBuilder summaryBuilder) throws IOException;
}
