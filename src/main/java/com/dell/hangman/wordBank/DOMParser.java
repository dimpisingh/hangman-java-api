package com.dell.hangman.wordBank;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


/*
@Todo: how to cache the word list instead of reading this file on each request?!
 */
public class DOMParser {

    public static List read() {

        List words = new ArrayList<String>();

        try {

            String filePath = "src/main/java/com/dell/hangman/wordBank/wordBank.xml";
            File fXmlFile = new File(filePath);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(fXmlFile);

            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("word");

            for (int temp = 0; temp < nodeList.getLength(); temp++) {

                Node node = nodeList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;
                    words.add(element.getTextContent());

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return words;
    }
}
