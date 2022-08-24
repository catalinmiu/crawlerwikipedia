package ro.pago.crawlerwikipedia.service;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import ro.pago.crawlerwikipedia.dto.CountryDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryTableService extends TableService {

    static final String TABLE_ROW_XPATH = "//table[contains(@class, 'wikitable')][2]/tbody/tr";
    int areaColumnIndex = 4;

    public CountryTableService() {
        countryNameColumnIndex = 1;
        populationColumnIndex = 2;
    }

    public List<CountryDTO> getAllCountries(Document document) {
        Elements rows = document.selectXpath(TABLE_ROW_XPATH);

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

    private Double getArea(Element element) {
        String areaString = element.children().get(areaColumnIndex).text().replace(",", "");
        if (!areaString.isEmpty()) return Double.valueOf(areaString);
        return null;
    }
}
