package com.gigaboutique.gigabatchproduitservice.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.gigaboutique.gigabatchproduitservice.exception.BatchProduitException;

@Service
public class ScrapingGenericService {

	private Document document;

	public Elements getElements(String url, Map<String, List<String>> map) throws BatchProduitException {

		url = decodeUrl(url);

		document = getDocument(url);

		Class<?> documentClass = document.getClass();

		Class<?> elementsClass = Elements.class;

		Elements elements = null;

		Element element = null;

		for (Map.Entry<String, List<String>> entry : map.entrySet()) {

			try {

				if (entry.getValue().size() == 2) {

					Method methodeDocument = documentClass.getMethod("getElementsBy" + entry.getKey(), String.class, String.class);

					if (element == null) {

						elements = (Elements) methodeDocument.invoke(document, entry.getValue().get(0), entry.getValue().get(1));

					} else {

						elements = (Elements) methodeDocument.invoke(element, entry.getValue().get(0), entry.getValue().get(1));
					}

				} else {

					Method methodeDocument = documentClass.getMethod("getElementsBy" + entry.getKey(), String.class);

					if (element == null) {

						elements = (Elements) methodeDocument.invoke(document, entry.getValue().get(0));

					} else {

						elements = (Elements) methodeDocument.invoke(element, entry.getValue().get(0));

					}
				}

				Method methodeFirst = elementsClass.getMethod("first", null);

				element = (Element) methodeFirst.invoke(elements, null);

			} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException e) {

				return elements;
			}

		}
		return elements;
	}

	public String getElementString(Element element, String url, Map<String, List<String>> map)
			throws BatchProduitException {

		url = decodeUrl(url);

		document = getDocument(url);

		Class<?> documentClass = document.getClass();

		Class<?> elementsClass = Elements.class;

		Class<?> elementClass = Element.class;

		Elements elements = null;

		String leString = null;

		for (Map.Entry<String, List<String>> entry : map.entrySet()) {

			try {
				try {

					if (entry.getValue().size() == 2) {

						Method methodeDocument = documentClass.getMethod("getElementsBy" + entry.getKey(), String.class, String.class);

						if (element == null) {

							elements = (Elements) methodeDocument.invoke(document, entry.getValue().get(0), entry.getValue().get(1));

						} else {

							elements = (Elements) methodeDocument.invoke(element, entry.getValue().get(0), entry.getValue().get(1));
						}

					} else {

						Method methodeDocument = documentClass.getMethod("getElementsBy" + entry.getKey(), String.class);

						if (element == null) {

							elements = (Elements) methodeDocument.invoke(document, entry.getValue().get(0));

						} else {

							elements = (Elements) methodeDocument.invoke(element, entry.getValue().get(0));

						}
					}

					Method methodeFirst = elementsClass.getMethod("first", null);

					element = (Element) methodeFirst.invoke(elements, null);

				} catch (NoSuchMethodException nse) {

					Method methodeTerminaison = null;

					if (entry.getValue().isEmpty() || entry.getValue() == null || entry.getValue().get(0).equals("")) {

						methodeTerminaison = elementClass.getMethod(entry.getKey(), null);

						leString = (String) methodeTerminaison.invoke(element, null);

					} else {

						if (entry.getValue().size() > 1) {

							methodeTerminaison = elementClass.getMethod(entry.getKey(), String.class, String.class);

							leString = (String) methodeTerminaison.invoke(element, entry.getValue().get(0), entry.getValue().get(1));

						} else {

							methodeTerminaison = elementClass.getMethod(entry.getKey(), String.class);

							leString = (String) methodeTerminaison.invoke(element, entry.getValue().get(0));

						}

						return leString;
					}

				}
			} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException e) {

				throw new BatchProduitException("problème lors du generic scraping getElementString" + e.getMessage());

			}
		}

		return leString;

	}

	private Document getDocument(String url) throws BatchProduitException {
		try {

			document = Jsoup.connect(url).get();

		} catch (IOException e) {

			throw new BatchProduitException("problème lors de l'extraction de la page html getDocument");
		}
		return document;
	}

	private static String decodeUrl(String url) throws BatchProduitException {
		try {

			url = URLDecoder.decode(url, "UTF-8");

		} catch (UnsupportedEncodingException e) {

			throw new BatchProduitException("problème encoding scheme decodeUrl");
		}
		return url;
	}

}
