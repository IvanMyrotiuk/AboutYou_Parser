package com.java.aboutyou.serviceImpl;

import com.java.aboutyou.entities.Offer;
import com.java.aboutyou.entities.Summary;
import com.java.aboutyou.service.Parser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OfferParser implements Parser<List<Offer>> {
    @Override
    public synchronized List<Offer> parse(String criteria, Summary.SummaryBuilder summaryBuilder) throws IOException {

        int requestCount = 0;
        String homePage = "https://www.aboutyou.de";
        StringBuilder criteriaUrl = new StringBuilder();
        criteriaUrl.append(homePage).append("/suche?term=").append(criteria).append("&category=20201");
        Document doc = Jsoup.connect(criteriaUrl.toString()).timeout(1000).get();
        requestCount++;

        Elements elems = doc.select("div.row.list-wrapper.product-image-list");

        return parseOffers(homePage, elems, requestCount, summaryBuilder);
    }

    private List<Offer> parseOffers(String homePage, Elements elems, Integer requestCount, Summary.SummaryBuilder summaryBuilder) throws IOException {
        List<Offer> offers = new ArrayList<>();
        for (Element elem : elems.select("div.col-xs-4.isLayout3")) {
            Offer.OfferBuilder offer = Offer.builder();

            String productUrl = elem.select("div.product-image.loaded a").attr("href");

            offer.name(elem.select("div.js-product-name.product-name a").text());

            offer.brand(elem.select("div.product-brand a").text());

            parsePrice(offer, elem);

            Document productDoc = Jsoup.connect(homePage + productUrl).timeout(1000).get();
            requestCount++;
            Element productElem = productDoc.body();
            offer.color(productElem.select("div.adp-stylepicker.bottom-5 a").attr("title"));
            offer.description(productElem.select("div.description-text.bottom-0 p").text());
            offer.articleId(productElem.select("div.description-text.bottom-0 small span").text());
            offers.add(offer.build());
        }
        summaryBuilder.requestCount(requestCount);
        return offers;
    }

    private void parsePrice(final Offer.OfferBuilder offer, final Element elem){

        String initialPrice = elem.select("div.js-product-price.product-price h5.price.isStriked span").attr("data-price");

        String currentPrice = elem.select("div.js-product-price.product-price h5.price.isOffer.actual-price.actual-price span").attr("data-price");

        if (initialPrice.isEmpty() || currentPrice.isEmpty()) {
            currentPrice = elem.select("div.js-product-price.product-price h5.price.actual-price.actual-price span").attr("data-price");
            initialPrice = currentPrice;
        }

        offer.initPrice(initialPrice).currentPrice(currentPrice);
    }
}
