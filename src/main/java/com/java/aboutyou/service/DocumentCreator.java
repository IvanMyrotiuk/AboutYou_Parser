package com.java.aboutyou.service;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public interface DocumentCreator<T> {
    void create(T value, String os) throws ParserConfigurationException, TransformerException;
}
