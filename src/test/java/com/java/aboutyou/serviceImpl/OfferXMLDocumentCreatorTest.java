package com.java.aboutyou.serviceImpl;

import com.java.aboutyou.entities.Offer;
import com.java.aboutyou.exception.UnsupportedOSException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.*;

/**
 * Created by Ivan on 03.06.2017. All rights reserved.
 */
public class OfferXMLDocumentCreatorTest {

    private List<Offer> offers;
    private OfferXMLDocumentCreator documentCreator;
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void shouldSetInitialState(){
        offers = Collections.EMPTY_LIST;
        documentCreator = new OfferXMLDocumentCreator();
    }

    @Test
    public void shouldCreateFileForWindows() throws Exception {
        String os = "windows";
        documentCreator.create(offers, os);
        assertThat("was created by path:", new File("C:/java/XML/orders.xml").exists(), is(Boolean.TRUE) );
    }

    @Test
    public void shouldThrowAnExceptionAsProgramDoesNotSupportSuchOS() throws Exception {
        String os = "WindowsHAHA";
        exception.expect(UnsupportedOSException.class);
        exception.expectMessage("Operation system is not Windows or Linux." +
                " Try to run it on Linux or Windows");
        documentCreator.create(offers, os);
    }

}