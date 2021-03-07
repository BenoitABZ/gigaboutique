package com.gigaboutique.gigabatchproduitservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigaboutique.gigabatchproduitservice.configuration.ProductConfiguration;


@Service
public class ScrapingUnitService {
	
	@Autowired
	ProductConfiguration productConfiguration;
	
	
	
	
/*
	@Autowired
	private SellerConfiguration sellerConfiguration;

	private Document document;

	public VendeurDto getVendeur(String url) throws BatchVendeurException {

		Element element = null;

		url = decodeUrl(url);

		document = getDocument(url);

		VendeurDto vendeurDto = new VendeurDto();

		try {
			vendeurDto.setNom(getElementString(element, url, sellerConfiguration.getVendeurNom()));
			vendeurDto.setNote(getElementString(element, url, sellerConfiguration.getVendeurNote()));
			vendeurDto.setLogo(getElementString(element, url, sellerConfiguration.getVendeurLogo()));
			vendeurDto.setNombreDeCommentaires(extractNumbers(getElementString(element, url, sellerConfiguration.getVendeurNombreDeCommentaires())));

			final Elements commentaires = document.getElementsByClass(sellerConfiguration.getCommentaireSection());

			vendeurDto.setCommentaires(getCommentaires(commentaires, url));

		} catch (SecurityException | IllegalArgumentException e) {

			throw new BatchVendeurException("problème lors de la constitution du vendeur getVendeur" + e.getMessage());
		}

		return vendeurDto;

	}

	private List<CommentaireDto> getCommentaires(Elements elements, String url) throws BatchVendeurException {

		List<CommentaireDto> commentaires = new ArrayList<>();

		int count = 0;

		for (Element element : elements) {

			count++;

			CommentaireDto commentaireDto = new CommentaireDto();

			try {
				commentaireDto.setAuteur(getElementString(element, url, sellerConfiguration.getAuteurCommentaire()));
				commentaireDto.setNote(extractNumbers(getElementString(element, url, sellerConfiguration.getNoteCommentaire())));
				commentaireDto.setDescription(getElementString(element, url, sellerConfiguration.getDescriptionCommentaire()));

				String date = getElementString(element, url, sellerConfiguration.getDateCommentaire());

				String regex = "(\\d{4}-\\d\\d-\\d\\dT\\d\\d:\\d\\d:\\d\\d)";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(date);

				if (matcher.find()) {

					date = matcher.group(1);
				}

				commentaireDto.setDateCommentaire(date);

				commentaires.add(commentaireDto);

				if (count == Integer.parseInt(sellerConfiguration.getNombreCommentaires())) {

					break;
				}

			} catch (SecurityException | IllegalArgumentException | BatchVendeurException e) {

				throw new BatchVendeurException("problème lors de la constitution des commentaires geCommentaires" + e.getMessage());
			}

		}

		return commentaires;

	}

	private String getElementString(Element element, String url, Map<String, List<String>> map)
			throws BatchVendeurException {

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

						Method methodeDocument = documentClass.getMethod("getElementsBy" + entry.getKey(), String.class,
								String.class);

						if (element == null) {

							elements = (Elements) methodeDocument.invoke(document, entry.getValue().get(0),
									entry.getValue().get(1));

						} else {

							elements = (Elements) methodeDocument.invoke(element, entry.getValue().get(0),
									entry.getValue().get(1));
						}

					} else {

						Method methodeDocument = documentClass.getMethod("getElementsBy" + entry.getKey(),
								String.class);

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

				throw new BatchVendeurException("problème lors du generic scraping getElementString" + e.getMessage());

			}
		}

		return leString;

	}

	private String extractNumbers(String toExtract) {

		return toExtract.trim().replaceAll("[^0-9]", "");

	}

	private Document getDocument(String url) throws BatchVendeurException {
		try {

			document = Jsoup.connect(url).get();

		} catch (IOException e) {

			throw new BatchVendeurException("problème lors de l'extraction de la page html getDocument");
		}
		return document;
	}

	private static String decodeUrl(String url) throws BatchVendeurException {
		try {

			url = URLDecoder.decode(url, "UTF-8");

		} catch (UnsupportedEncodingException e) {

			throw new BatchVendeurException("problème encoding scheme decodeUrl");
		}
		return url;
	}
*/
}
