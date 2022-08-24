package ro.pago.crawlerwikipedia.service;

import org.jsoup.nodes.Element;

public abstract class TableService {
    protected int countryNameColumnIndex;
    protected int populationColumnIndex;

    protected String getCountryName(Element element) {
        String countryName = element.children().get(countryNameColumnIndex).text();
        if (!countryName.isEmpty()) return countryName;
        throw new RuntimeException("Country name not found");
    }

    protected Integer getPopulation(Element element) {
        String populationString = element.children().get(populationColumnIndex).text().replace(",", "");
        if (!populationString.isEmpty()) return Integer.valueOf(populationString);
        return null;
    }
}
