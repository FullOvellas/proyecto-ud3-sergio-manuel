package com.vaultapp.model.pojos.books;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DocsItem{

	@JsonProperty("publish_year")
	private List<Integer> publishYear;

	@JsonProperty("author_name")
	private List<String> authorName; //to model list[0]

	@JsonProperty("edition_count")
	private int editionCount;

	@JsonProperty("first_publish_year")
	private int firstPublishYear; // to model

	@JsonProperty("seed")
	private List<String> seed;

	@JsonProperty("author_alternative_name")
	private List<String> authorAlternativeName;

	@JsonProperty("subject")
	private List<String> subject;

	@JsonProperty("isbn")
	private List<String> isbn;

	@JsonProperty("edition_key")
	private List<String> editionKey;

	@JsonProperty("public_scan_b")
	private boolean publicScanB;

	@JsonProperty("title_suggest")
	private String titleSuggest;

	@JsonProperty("language")
	private List<String> language;

	@JsonProperty("type")
	private String type;

	@JsonProperty("title") //to model
	private String title;

	@JsonProperty("last_modified_i")
	private int lastModifiedI;

	@JsonProperty("has_fulltext")
	private boolean hasFulltext;

	@JsonProperty("subject_key")
	private List<String> subjectKey;

	@JsonProperty("key")
	private String key;

	@JsonProperty("author_key")
	private List<String> authorKey;

	@JsonProperty("publisher_facet")
	private List<String> publisherFacet;

	@JsonProperty("ebook_access")
	private String ebookAccess;

	@JsonProperty("subject_facet")
	private List<String> subjectFacet;

	@JsonProperty("_version_")
	private long version;

	@JsonProperty("publisher")
	private List<String> publisher;

	@JsonProperty("author_facet")
	private List<String> authorFacet;

	@JsonProperty("publish_date")
	private List<String> publishDate;

	@JsonProperty("ebook_count_i")
	private int ebookCountI;

	@JsonProperty("cover_i")
	private int coverI;

	@JsonProperty("id_librarything")
	private List<String> idLibrarything;

	@JsonProperty("id_goodreads")
	private List<String> idGoodreads;

	@JsonProperty("number_of_pages_median")
	private int numberOfPagesMedian;

	@JsonProperty("cover_edition_key")
	private String coverEditionKey;

	@JsonProperty("ia_collection")
	private List<String> iaCollection;

	@JsonProperty("lcc")
	private List<String> lcc;

	@JsonProperty("lending_edition_s")
	private String lendingEditionS;

	@JsonProperty("lccn")
	private List<String> lccn;

	@JsonProperty("publish_place")
	private List<String> publishPlace;

	@JsonProperty("contributor")
	private List<String> contributor;

	@JsonProperty("ia")
	private List<String> ia;

	@JsonProperty("lending_identifier_s")
	private String lendingIdentifierS;

	@JsonProperty("lcc_sort")
	private String lccSort;

	@JsonProperty("ddc")
	private List<String> ddc;

	@JsonProperty("printdisabled_s")
	private String printdisabledS;

	@JsonProperty("ia_collection_s")
	private String iaCollectionS;

	@JsonProperty("ia_box_id")
	private List<String> iaBoxId;

	@JsonProperty("oclc")
	private List<String> oclc;

	@JsonProperty("ddc_sort")
	private String ddcSort;

	@JsonProperty("place")
	private List<String> place;

	@JsonProperty("place_key")
	private List<String> placeKey;

	@JsonProperty("first_sentence")
	private List<String> firstSentence;

	@JsonProperty("place_facet")
	private List<String> placeFacet;

	@JsonProperty("id_amazon")
	private List<String> idAmazon;

	@JsonProperty("person_key")
	private List<String> personKey;

	@JsonProperty("person_facet")
	private List<String> personFacet;

	@JsonProperty("time_facet")
	private List<String> timeFacet;

	@JsonProperty("person")
	private List<String> person;

	@JsonProperty("subtitle")
	private String subtitle;

	@JsonProperty("time_key")
	private List<String> timeKey;

	@JsonProperty("time")
	private List<String> time;

	@JsonProperty("id_wikidata")
	private List<String> idWikidata;

	@JsonProperty("ia_loaded_id")
	private List<String> iaLoadedId;

	@JsonProperty("id_better_world_books")
	private List<String> idBetterWorldBooks;

	@JsonProperty("id_amazon_de_asin")
	private List<String> idAmazonDeAsin;

	@JsonProperty("id_amazon_it_asin")
	private List<String> idAmazonItAsin;

	@JsonProperty("id_amazon_co_uk_asin")
	private List<String> idAmazonCoUkAsin;

	@JsonProperty("id_bcid")
	private List<String> idBcid;

	@JsonProperty("id_google")
	private List<String> idGoogle;

	@JsonProperty("id_nla")
	private List<String> idNla;

	@JsonProperty("id_british_national_bibliography")
	private List<String> idBritishNationalBibliography;

	@JsonProperty("id_overdrive")
	private List<String> idOverdrive;

	@JsonProperty("id_alibris_id")
	private List<String> idAlibrisId;

	@JsonProperty("id_scribd")
	private List<String> idScribd;

	@JsonProperty("id_amazon_ca_asin")
	private List<String> idAmazonCaAsin;

	@JsonProperty("id_hathi_trust")
	private List<String> idHathiTrust;

	@JsonProperty("id_paperback_swap")
	private List<String> idPaperbackSwap;

	@JsonProperty("id_british_library")
	private List<String> idBritishLibrary;

	@JsonProperty("id_bibliothèque_nationale_de_france_bnf")
	private List<String> idBibliothQueNationaleDeFranceBnf;

	@JsonProperty("id_depósito_legal")
	private List<String> idDepSitoLegal;

	@JsonProperty("id_canadian_national_library_archive")
	private List<String> idCanadianNationalLibraryArchive;

	public List<Integer> getPublishYear(){
		return publishYear;
	}

	public List<String> getAuthorName(){
		return authorName;
	}

	public int getEditionCount(){
		return editionCount;
	}

	public int getFirstPublishYear(){
		return firstPublishYear;
	}

	public List<String> getSeed(){
		return seed;
	}

	public List<String> getAuthorAlternativeName(){
		return authorAlternativeName;
	}

	public List<String> getSubject(){
		return subject;
	}

	public List<String> getIsbn(){
		return isbn;
	}

	public List<String> getEditionKey(){
		return editionKey;
	}

	public boolean isPublicScanB(){
		return publicScanB;
	}

	public String getTitleSuggest(){
		return titleSuggest;
	}

	public List<String> getLanguage(){
		return language;
	}

	public String getType(){
		return type;
	}

	public String getTitle(){
		return title;
	}

	public int getLastModifiedI(){
		return lastModifiedI;
	}

	public boolean isHasFulltext(){
		return hasFulltext;
	}

	public List<String> getSubjectKey(){
		return subjectKey;
	}

	public String getKey(){
		return key;
	}

	public List<String> getAuthorKey(){
		return authorKey;
	}

	public List<String> getPublisherFacet(){
		return publisherFacet;
	}

	public String getEbookAccess(){
		return ebookAccess;
	}

	public List<String> getSubjectFacet(){
		return subjectFacet;
	}

	public long getVersion(){
		return version;
	}

	public List<String> getPublisher(){
		return publisher;
	}

	public List<String> getAuthorFacet(){
		return authorFacet;
	}

	public List<String> getPublishDate(){
		return publishDate;
	}

	public int getEbookCountI(){
		return ebookCountI;
	}

	public int getCoverI(){
		return coverI;
	}

	public List<String> getIdLibrarything(){
		return idLibrarything;
	}

	public List<String> getIdGoodreads(){
		return idGoodreads;
	}

	public int getNumberOfPagesMedian(){
		return numberOfPagesMedian;
	}

	public String getCoverEditionKey(){
		return coverEditionKey;
	}

	public List<String> getIaCollection(){
		return iaCollection;
	}

	public List<String> getLcc(){
		return lcc;
	}

	public String getLendingEditionS(){
		return lendingEditionS;
	}

	public List<String> getLccn(){
		return lccn;
	}

	public List<String> getPublishPlace(){
		return publishPlace;
	}

	public List<String> getContributor(){
		return contributor;
	}

	public List<String> getIa(){
		return ia;
	}

	public String getLendingIdentifierS(){
		return lendingIdentifierS;
	}

	public String getLccSort(){
		return lccSort;
	}

	public List<String> getDdc(){
		return ddc;
	}

	public String getPrintdisabledS(){
		return printdisabledS;
	}

	public String getIaCollectionS(){
		return iaCollectionS;
	}

	public List<String> getIaBoxId(){
		return iaBoxId;
	}

	public List<String> getOclc(){
		return oclc;
	}

	public String getDdcSort(){
		return ddcSort;
	}

	public List<String> getPlace(){
		return place;
	}

	public List<String> getPlaceKey(){
		return placeKey;
	}

	public List<String> getFirstSentence(){
		return firstSentence;
	}

	public List<String> getPlaceFacet(){
		return placeFacet;
	}

	public List<String> getIdAmazon(){
		return idAmazon;
	}

	public List<String> getPersonKey(){
		return personKey;
	}

	public List<String> getPersonFacet(){
		return personFacet;
	}

	public List<String> getTimeFacet(){
		return timeFacet;
	}

	public List<String> getPerson(){
		return person;
	}

	public String getSubtitle(){
		return subtitle;
	}

	public List<String> getTimeKey(){
		return timeKey;
	}

	public List<String> getTime(){
		return time;
	}

	public List<String> getIdWikidata(){
		return idWikidata;
	}

	public List<String> getIaLoadedId(){
		return iaLoadedId;
	}

	public List<String> getIdBetterWorldBooks(){
		return idBetterWorldBooks;
	}

	public List<String> getIdAmazonDeAsin(){
		return idAmazonDeAsin;
	}

	public List<String> getIdAmazonItAsin(){
		return idAmazonItAsin;
	}

	public List<String> getIdAmazonCoUkAsin(){
		return idAmazonCoUkAsin;
	}

	public List<String> getIdBcid(){
		return idBcid;
	}

	public List<String> getIdGoogle(){
		return idGoogle;
	}

	public List<String> getIdNla(){
		return idNla;
	}

	public List<String> getIdBritishNationalBibliography(){
		return idBritishNationalBibliography;
	}

	public List<String> getIdOverdrive(){
		return idOverdrive;
	}

	public List<String> getIdAlibrisId(){
		return idAlibrisId;
	}

	public List<String> getIdScribd(){
		return idScribd;
	}

	public List<String> getIdAmazonCaAsin(){
		return idAmazonCaAsin;
	}

	public List<String> getIdHathiTrust(){
		return idHathiTrust;
	}

	public List<String> getIdPaperbackSwap(){
		return idPaperbackSwap;
	}

	public List<String> getIdBritishLibrary(){
		return idBritishLibrary;
	}

	public List<String> getIdBibliothQueNationaleDeFranceBnf(){
		return idBibliothQueNationaleDeFranceBnf;
	}

	public List<String> getIdDepSitoLegal(){
		return idDepSitoLegal;
	}

	public List<String> getIdCanadianNationalLibraryArchive(){
		return idCanadianNationalLibraryArchive;
	}
}