package ro.pago.crawlerwikipedia.service;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import ro.pago.crawlerwikipedia.dto.CityDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityTableService extends TableService {

    static final String TABLE_ROW_XPATH = "//table[contains(@class, 'wikitable')]/tbody/tr";
    static final String EMPTY_VALUE = "—";

    public CityTableService() {
        countryNameColumnIndex = 1;
        populationColumnIndex = 2;
    }
    int densityColumnIndex = 6;
    int cityColumnIndex = 0;

    public List<CityDTO> getAllCities(Document document) {
        Elements elements = document.selectXpath(TABLE_ROW_XPATH);

        return elements.stream().skip(3).map(e ->
                CityDTO.builder()
                        .density(getDensity(e))
                        .name(getCityName(e))
                        .population(getPopulation(e))
                        .countryName(getCountryName(e))
                        .population(getPopulation(e))
                        .build()).collect(Collectors.toList());
    }

    private String getCityName(Element element) {

        String cityName = element.children().get(cityColumnIndex).text();
        if (!cityName.isEmpty()) return cityName;
        throw new RuntimeException("City name not found");
    }

    private Double getDensity(Element element) {

        String densityString = element.children().get(densityColumnIndex).text().replace(",", "").split(" ")[0];
        if (!densityString.isEmpty() && !EMPTY_VALUE.equals(densityString)) return Double.valueOf(densityString);
        return null;
    }
}
