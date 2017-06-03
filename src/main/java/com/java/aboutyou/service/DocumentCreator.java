package com.java.aboutyou.service;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

/**
 * Interface<code> DocumentCreator</> that has to be implemented by all specific
 * Creators to create document(xml etc.)
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 02-06-2017
 */
public interface DocumentCreator<T> {
    /**
     * Method <code> create</> that crete document from value and store it
     * on your computer depends on operation system
     * @param value value that should be transformed to document
     * @param os operation system(Windows or Linux)
     * @throws ParserConfigurationException
     * @throws TransformerException
     */
    void create(T value, String os) throws ParserConfigurationException, TransformerException;
}
