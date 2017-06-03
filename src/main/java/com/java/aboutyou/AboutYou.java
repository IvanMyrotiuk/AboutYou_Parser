package com.java.aboutyou;


import com.java.aboutyou.entities.Offer;
import com.java.aboutyou.entities.Summary;
import com.java.aboutyou.service.DocumentCreator;
import com.java.aboutyou.serviceImpl.OfferParser;
import com.java.aboutyou.serviceImpl.OfferXMLDocumentCreator;
import com.java.aboutyou.service.Parser;
import org.apache.log4j.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;


/**
 * Class<code> AboutYou</> represent executable class that
 * retrieve information from site AboutYou and create xml document using retrieved information
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 02-06-2017
 */
public class AboutYou {
    private static final Logger LOGGER = Logger.getLogger(AboutYou.class.getName());

    public static void main(String[] args) throws IOException, TransformerException, ParserConfigurationException {

        Long startTime = System.currentTimeMillis();
        Summary.SummaryBuilder summaryBuilder = Summary.builder();

        List<Offer> offers = getOffers(args[0], summaryBuilder);//"Blanche"

        createXMLDocument(offers);

        Long endTime = System.currentTimeMillis();
        summaryBuilder.runTime((int) (endTime - startTime));
        long memory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        summaryBuilder.memoryFootprint(memory/1024);
        printSummary(summaryBuilder);
    }

    private static void createXMLDocument(List<Offer> offers) throws ParserConfigurationException, TransformerException {
        String os = System.getProperty("os.name").toLowerCase();
        DocumentCreator<List<Offer>> documentCreator = new OfferXMLDocumentCreator();
        documentCreator.create(offers, os);
    }

    private static List<Offer> getOffers(String criteria, Summary.SummaryBuilder summaryBuilder) throws IOException {
        Parser<List<Offer>> parser = new OfferParser();
        List<Offer> offers = parser.parse(criteria, summaryBuilder);
        summaryBuilder.extractedProducts(offers.size());
        return offers;
    }

    private static void printSummary(Summary.SummaryBuilder summaryBuilder) {
        Summary summary = summaryBuilder.build();

        LOGGER.info("Amount of triggered HTTP request: " + summary.getRequestCount());
        LOGGER.info("Run-time: " + summary.getRunTime()+" ms");
        LOGGER.info("Amount of extracted products: " + summary.getExtractedProducts());
        LOGGER.info("Memory Footprint: " + summary.getMemoryFootprint()+" kB");
    }
}
