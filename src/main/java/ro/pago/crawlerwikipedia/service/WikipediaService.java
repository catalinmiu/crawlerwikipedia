package ro.pago.crawlerwikipedia.service;

import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import ro.pago.crawlerwikipedia.config.ServiceConfig;
import ro.pago.crawlerwikipedia.dto.CityDTO;
import ro.pago.crawlerwikipedia.dto.CountryDTO;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WikipediaService {

    private final ServiceConfig serviceConfig;
    private static final String TABLE_COUNTRY_ROW_XPATH = "//table[contains(@class, 'wikitable')][2]/tbody/tr";
    private static final String TABLE_CITY_ROW_XPATH = "//table[contains(@class, 'wikitable')]/tbody/tr";
    private static final String EMPTY_VALUE = "—";
    private static final int DENSITY_COLUMN_INDEX = 6;
    private static final int CITY_COLUMN_INDEX = 0;
    private static final int AREA_COLUMN_INDEX = 4;
    private static final int COUNTRY_NAME_COLUMN_INDEX = 1;
    private static final int POPULATION_COLUMN_INDEX = 2;

    public List<CityDTO> getAllCities() {
        Document document = getListOfLargestCitiesPage();
        Elements elements = document.selectXpath(TABLE_CITY_ROW_XPATH);

        return elements.stream().skip(3).map(e ->
                CityDTO.builder()
                        .density(getDensity(e))
                        .name(getCityName(e))
                        .population(getPopulation(e))
                        .countryName(getCountryName(e))
                        .population(getPopulation(e))
                        .build()).collect(Collectors.toList());
    }

    public List<CountryDTO> getAllCountries() {
        Document document = getListOfCountriesPage();
        Elements rows = document.selectXpath(TABLE_COUNTRY_ROW_XPATH);

        return rows.stream()
                .skip(2)
                .map(e ->
                        CountryDTO.builder()
                                .area(getArea(e))
                                .name(getCountryName(e))
                                .population(getPopulation(e))
                                .build())
                .collect(Collectors.toList());
    }

    private String getCountryName(Element element) {
        String countryName = element.children().get(COUNTRY_NAME_COLUMN_INDEX).text();
        if (!countryName.isEmpty()) return countryName;
        throw new RuntimeException("Country name not found");
    }

    private Integer getPopulation(Element element) {
        String populationString = element.children().get(POPULATION_COLUMN_INDEX).text().replace(",", "");
        if (!populationString.isEmpty()) return Integer.valueOf(populationString);
        return null;
    }

    private String getCityName(Element element) {

        String cityName = element.children().get(CITY_COLUMN_INDEX).text();
        if (!cityName.isEmpty()) return cityName;
        throw new RuntimeException("City name not found");
    }

    private Double getDensity(Element element) {

        String densityString = element.children().get(DENSITY_COLUMN_INDEX).text().replace(",", "").split(" ")[0];
        if (!densityString.isEmpty() && !EMPTY_VALUE.equals(densityString)) return Double.valueOf(densityString);
        return null;
    }

    private Double getArea(Element element) {
        String areaString = element.children().get(AREA_COLUMN_INDEX).text().replace(",", "");
        if (!areaString.isEmpty()) {
            return Double.valueOf(areaString);
        }
        return null;
    }

    private Document getListOfCountriesPage() {
        try {
            return Jsoup.connect(serviceConfig.getListOfCountriesWikiUrl()).get();
        } catch (IOException e) {
            throw new RuntimeException("Can not connect to wiki page");
        }
    }

    private Document getListOfLargestCitiesPage() {
        try {
            return Jsoup.connect(serviceConfig.getListOfLargestCitiesWikiUrl()).get();
        } catch (IOException e) {
            throw new RuntimeException("Can not connect to wiki page");
        }
    }
}
