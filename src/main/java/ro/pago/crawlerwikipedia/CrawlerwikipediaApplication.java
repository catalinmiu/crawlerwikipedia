package ro.pago.crawlerwikipedia;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ro.pago.crawlerwikipedia.service.WikipediaCrawlerService;

@SpringBootApplication
@RequiredArgsConstructor
public class CrawlerwikipediaApplication implements CommandLineRunner {
    private final WikipediaCrawlerService wikipediaCrawlerService;

    public static void main(String[] args) {
        SpringApplication.run(CrawlerwikipediaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        wikipediaCrawlerService.saveAllCountriesAndCitiesInDB();
    }
}
