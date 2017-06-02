package com.java.aboutyou.serviceImpl;

import com.java.aboutyou.entities.Offer;
import com.java.aboutyou.exception.UnsupportedOSException;
import com.java.aboutyou.service.DocumentCreator;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class OfferXMLDocumentCreator implements DocumentCreator<List<Offer>> {

    private static final Logger LOGGER = Logger.getLogger(OfferXMLDocumentCreator.class.getName());

    private static final String WINDOWS_DIRECTORY = "C:/java/XML";
    private static final String lINUX_DIRECTORY = "/home/java/XML";
    private static final String ORDERS_XML = "/orders.xml";
    private static final String WINDOWS = "windows";
    private static final String LINUX = "linux";

    @Override
    public void create(List<Offer> value, String os) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        Document document = documentBuilder.newDocument();

        createXMLDocument(value, document);

        transformToXMLDocument(document, os);
    }

    private void createXMLDocument(List<Offer> value, Document document) {
        Element offers = document.createElement("offers");
        document.appendChild(offers);
        for (Offer offer : value) {
            Element offerElement = document.createElement("offer");
            offers.appendChild(offerElement);

            Element name = document.createElement("name");
            name.appendChild(document.createTextNode(offer.getName()));
            offerElement.appendChild(name);

            Element brand = document.createElement("brand");
            brand.appendChild(document.createTextNode(offer.getBrand()));
            offerElement.appendChild(brand);

            Element color = document.createElement("color");
            color.appendChild(document.createTextNode(offer.getColor()));
            offerElement.appendChild(color);

            Element price = document.createElement("price");
            price.appendChild(document.createTextNode(offer.getCurrentPrice()));
            offerElement.appendChild(price);

            Element initPrice = document.createElement("initialPrice");
            initPrice.appendChild(document.createTextNode(offer.getInitPrice()));
            offerElement.appendChild(initPrice);

            Element description = document.createElement("description");
            description.appendChild(document.createTextNode(offer.getDescription()));
            offerElement.appendChild(description);

            Element articleId = document.createElement("articleId");
            articleId.appendChild(document.createTextNode(offer.getArticleId()));
            offerElement.appendChild(articleId);
        }
    }

    private void transformToXMLDocument(Document document, String os) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);

        transformer.transform(source, getStreamResult(os));
    }

    private StreamResult getStreamResult(String os) {
        if (os.contains(WINDOWS)) {
            new File(WINDOWS_DIRECTORY).mkdirs();
            LOGGER.info("File has been stored to " + WINDOWS_DIRECTORY + ORDERS_XML);
            return new StreamResult(new File(WINDOWS_DIRECTORY + ORDERS_XML));
        } else if (os.contains(LINUX)) {
            new File(lINUX_DIRECTORY).mkdirs();
            LOGGER.info("File has been stored to " + lINUX_DIRECTORY + ORDERS_XML);
            return new StreamResult(new File(lINUX_DIRECTORY + ORDERS_XML));
        } else {
            throw new UnsupportedOSException("Operation system is not Windows or Linux." +
                    " Try to run it on Linux or Windows");
        }
    }
}
