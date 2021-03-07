package com.gigaboutique.gigabatchproduitservice.service;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

@Service
public class ScrapingXmlParserService {
	
	public Map<String, List<String>> parseXml(String toParse){
		
		Map<String, List<String>> map = null;
		
		try {
			map = convertToMapForGeneringScraping (initXml(toParse));
		} catch (JDOMException | IOException e) {

		}
		
		return map;
	}

	private Document initXml(String toParse) throws JDOMException, IOException {

		SAXBuilder sb = new SAXBuilder();

		Document doc = null;

			doc = sb.build(new StringReader(toParse));
	
		return doc;
	}


   private Map<String, List<String>> convertToMapForGeneringScraping(Document document){
	   
	   Element element = document.getRootElement();
	   
	   Map<String, List<String>> map = new HashMap<>();
	   
	   if (element.getName().equals("baliseshtml")){
		   
		   for (Element elementChild : element.getChildren()) {
			   
			   List<String> values = new ArrayList<>();
			   
			   values.add(elementChild.getAttributeValue("value1"));
			   
			   if(!elementChild.getAttributeValue("value2").isEmpty()) {
				   
				   values.add(elementChild.getAttributeValue("value2"));
			   }
			   
			   map.put(elementChild.getAttributeValue("nom"), values);
		   }
	   }
	   return map;
   }
}