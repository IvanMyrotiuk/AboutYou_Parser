package com.java.aboutyou.serviceImpl;

import com.java.aboutyou.entities.Offer;
import com.java.aboutyou.entities.Summary;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

/**
 * Created by Ivan on 02.06.2017. All rights reserved.
 */
public class OfferParserTest {

    private OfferParser offerParser;
    private Summary.SummaryBuilder summaryBuilder;

    @Before
    public void shouldSetUpInitialStatesForTests() throws Exception {
        offerParser = new OfferParser();
        summaryBuilder = Summary.builder();
    }

    @Test
    public void shouldParseSiteForSpecifiedCriteria() throws Exception {
        String criteria = "Blanche";
        List<Offer> offers = offerParser.parse(criteria, summaryBuilder);
        Summary summary = summaryBuilder.build();
        assertThat("summery has requestCount equals to 7 ", summary.getRequestCount(), is(7));
        assertThat("size of retrieved offers equals to 6", offers.size(), is(6));
        assertThat("offers contains elements of offers", offers, not(Collections.EMPTY_LIST));
    }

    @Test
    public void shouldReturnAnEmptyListAsThereIsNoSuchOffer() throws Exception {
        String criteria = "XXXXXXXXXXXXXXXXXXXXXBlanche";
        List<Offer> offers = offerParser.parse(criteria, summaryBuilder);
        Summary summary = summaryBuilder.build();
        assertThat("summery has requestCount equals to 1 ", summary.getRequestCount(), is(1));
        assertThat("size of retrieved offers equals to 0", offers.size(), is(0));
        assertThat("offers contains elements of offers", offers, is(Collections.EMPTY_LIST));
    }

}