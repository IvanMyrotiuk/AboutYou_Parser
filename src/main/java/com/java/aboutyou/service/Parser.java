package com.java.aboutyou.service;

import com.java.aboutyou.entities.Summary;

import java.io.IOException;

public interface Parser<T> {
    T parse(String criteria, Summary.SummaryBuilder summaryBuilder) throws IOException;
}
