package ro.pago.crawlerwikipedia.client;

import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import ro.pago.crawlerwikipedia.config.ServiceConfig;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class WikipediaClient {

    private final ServiceConfig serviceConfig;

    public Document getListOfCountriesPage() {
        try {
            return Jsoup.connect(serviceConfig.getListOfCountriesWikiUrl()).get();
        } catch (IOException e) {
            return null;
        }
    }

    public Document getListOfLargestCitiesPage() {
        try {
            return Jsoup.connect(serviceConfig.getListOfLargestCitiesWikiUrl()).get();
        } catch (IOException e) {
            return null;
        }
    }
}
