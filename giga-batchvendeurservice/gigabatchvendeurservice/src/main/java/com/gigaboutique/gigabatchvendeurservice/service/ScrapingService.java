package com.gigaboutique.gigabatchvendeurservice.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigaboutique.gigabatchvendeurservice.configuration.SellerConfiguration;
import com.gigaboutique.gigabatchvendeurservice.dto.CommentaireDto;
import com.gigaboutique.gigabatchvendeurservice.dto.VendeurDto;



@Service
public class ScrapingService {
	
	@Autowired
	private SellerConfiguration sellerConfiguration;
	
	private Document document;
	   
    /*
    
     private final String vendeurNom = "multi-size-header__big";
    
    private final String vendeurNote = "header_trustscore";
    
    private final String vendeurLogo = "business-unit-profile-summary__image";
 
    private final String vendeurNombreDeCommentaires = "header--inline";
    
    private final String auteurCommentaire = "consumer-information__name";
    
    private final String noteCommentaire = "star-rating star-rating--medium";
    
    private final String descriptionCommentaire = "review-content__text";
    
    private final String dateCommentaire = "review-content__dateOfExperience";
    
    private final String commentaireSection = "review";
    
    private final int nombreCommentaires = 3;

    private Document document;
    
    */
 
    public VendeurDto getVendeur(String url) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        Element element = null;
    	
    	url = decodeUrl(url);

        document = getDocument(url);
               
   VendeurDto vendeurDto = new VendeurDto();
        
   vendeurDto.setNom(getElementString(element, url, sellerConfiguration.getVendeurNom()));
   vendeurDto.setNote(getElementString(element, url, sellerConfiguration.getVendeurNote()));
   vendeurDto.setLogo(getElementString(element, url, sellerConfiguration.getVendeurLogo()));
   vendeurDto.setNombreDeCommentaires(getElementString(element, url, sellerConfiguration.getVendeurNombreDeCommentaires()));
   
   
   final Elements commentaires = document.getElementsByClass(sellerConfiguration.getCommentaireSection());
   
   vendeurDto.setCommentaires(getCommentaires(commentaires, url));

   return vendeurDto;
   
   
}
               
        /*
        
        final Element nom = document.getElementsByClass(vendeurNom).first();
        final Element note = document.getElementsByClass(vendeurNote).first();
        final Element logo = document.getElementsByClass(vendeurLogo).first();
        final Element nombreDeCommentaires = document.getElementsByClass(vendeurNombreDeCommentaires).first();
        
        VendeurDto vendeurDto = new VendeurDto();
        
        vendeurDto.setNom(nom.text());
        vendeurDto.setNote(note.text());
        vendeurDto.setLogo(logo.absUrl("src"));
        vendeurDto.setNombreDeCommentaires(nombreDeCommentaires.text());
        
         */
  
    private List<CommentaireDto> getCommentaires(Elements elements, String url) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    	
    	List<CommentaireDto> commentaires = new ArrayList<>();
    	
    	int count = 0;
    	
    	for (Element element : elements) {
    		
    		count++;
    		
    		CommentaireDto commentaireDto = new CommentaireDto();
    		
     		
    		commentaireDto.setAuteur(getElementString(element, url, sellerConfiguration.getAuteurCommentaire()));
    		commentaireDto.setNote(getElementString(element, url, sellerConfiguration.getNoteCommentaire()));
    		
    		String date = getElementString(element, url, sellerConfiguration.getDateCommentaire());
    		

    		
    		String regex = "(\\d{4}-\\d\\d-\\d\\dT\\d\\d:\\d\\d:\\d\\d)";
    		Pattern pattern = Pattern.compile(regex);
    		Matcher matcher = pattern.matcher(date);
    		
    		if (matcher.find()) {

    		    date = matcher.group(1);
    		}
    		

    		
    		commentaireDto.setDateCommentaire(date);
    		
    	
 
    		
    		commentaires.add(commentaireDto);
    		
    		if(count == Integer.parseInt(sellerConfiguration.getNombreCommentaires())) {
    			
    			break;
    		}
    		
    	}
    	
    	return commentaires;
    	
    	
    }
    		
    		  /*
    		
    		commentaireDto.setAuteur(element.getElementsByClass(auteurCommentaire).first().text());
    		commentaireDto.setNote(element.getElementsByClass(noteCommentaire).first().getElementsByTag("img").attr("alt"));
    		commentaireDto.setDescription(element.getElementsByClass(descriptionCommentaire).first().text());
    		
    		String date = element.getElementsByAttributeValue("data-initial-state","review-dates").first().toString();
    		
    		
    		
    		String regex = "(\\d{4}-\\d\\d-\\d\\dT\\d\\d:\\d\\d:\\d\\d)";
    		Pattern pattern = Pattern.compile(regex);
    		Matcher matcher = pattern.matcher(date);
    		
    		if (matcher.find()) {

    		    date = matcher.group(1);
    		}
    		
    		commentaireDto.setDateCommentaire(date);
    		
    		*/
   
    
    
    private String getElementString(Element element, String url, Map<String, List<String>> map) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    	
    	 url = decodeUrl(url);

         document = getDocument(url);
         
         /*
         
         List<String> typeParameters1 = new ArrayList<>();
         
         List<String> typeParameters2 = new ArrayList<>();
         
         List<String> terminaisonParameters = new ArrayList<>();
         
         List<Method> methodes = new ArrayList<>();
         
         
         
         typeParameters.add("multi-size-header__big");
         
         map1.put("Class", typeParameters);
         
         map1.put("text", terminaisonParameters);
         
        
         
         typeParameters1.add("data-initial-state");
         typeParameters1.add("review-dates");
        
         map1.put("AttributeValue", typeParameters1);
            
         map1.put("toString", terminaisonParameters);

         
         //String[] arrayTypeParameters = (String[])typeParameters.toArray();
         
         //String[] arrayterminaisonParameters = (String[])terminaisonParameters.toArray();
         
          */

    	
    	Class<?> documentClass = document.getClass();
    	
    	Class<?> elementsClass = Elements.class;
    	
    	Class<?> elementClass = Element.class;
    	
    	Elements elements = null;
    	
    	
    	String leString = null;
    	
    	for (Map.Entry<String, List<String>> entry : map.entrySet()) {
    	      	
    	try {
    		
    		if(entry.getValue().size()==2) {
    	
    	Method methodeDocument = documentClass.getMethod("getElementsBy" + entry.getKey(), String.class, String.class);
    	
    	if(element == null) {
    	
    	elements = (Elements) methodeDocument.invoke(document, entry.getValue().get(0), entry.getValue().get(1));
    	
    	}else {
    		
    	elements = (Elements) methodeDocument.invoke(element, entry.getValue().get(0), entry.getValue().get(1));
    	}
    	
    		}else {
    			
    			Method methodeDocument = documentClass.getMethod("getElementsBy" + entry.getKey(), String.class);
    	    	
    	    	if(element == null) {
    	    	
    	    	elements = (Elements) methodeDocument.invoke(document, entry.getValue().get(0));
    	    	
    	    	}else {
    	    		
    	    	elements = (Elements) methodeDocument.invoke(element, entry.getValue().get(0));
    			
    		}
    		}
    	
    	Method methodeFirst = elementsClass.getMethod("first", null);
    	
    	element = (Element) methodeFirst.invoke(elements, null);
    	 	
    	}catch(NoSuchMethodException nse) {
    		
    		Method methodeTerminaison = null;
    		
    		
    		
    		if(entry.getValue().isEmpty() || entry.getValue() == null) {
    		
    		methodeTerminaison = elementClass.getMethod(entry.getKey(), null);
    		
    		leString = (String) methodeTerminaison.invoke(element, null);
    		
    		}else {
    			
    			if(entry.getValue().size()>1) {
    			
    		methodeTerminaison = elementClass.getMethod(entry.getKey(), String.class, String.class);
    			
    		leString = (String) methodeTerminaison.invoke(element, entry.getValue().get(0), entry.getValue().get(1));
    			
    		}else {
    			
    			methodeTerminaison = elementClass.getMethod(entry.getKey(), String.class);
    			
        		leString = (String) methodeTerminaison.invoke(element, entry.getValue().get(0));
        			
    			
    		}
    		
    		return leString;
    	}
    	
       	
    	}
    	
    	
    	
    	/*
    	
    	Elements elements = (Elements) method1.invoke(document, vendeurNom);
    	
    	Element element = (Element) method2.invoke(elements, null);
    	
    	String leString = (String) method3.invoke(element, null);
    	
    	*/
    	
    	}	
    	   	
		return leString;
    	
    	
    }
    
    private Document getDocument(String url) {
        try {
        	
            document = Jsoup.connect(url).get();
            
        } catch (IOException e) {

            throw new IllegalStateException("");
        }
        return document;
    }
    
    private static String decodeUrl(String url) {
        try {
        	
            url = URLDecoder.decode(url, "UTF-8");
            
        } catch (UnsupportedEncodingException e) {

            throw new IllegalStateException("");
        }
        return url;
    }

}
