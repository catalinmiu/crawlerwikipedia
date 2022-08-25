package ro.pago.crawlerwikipedia;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ro.pago.crawlerwikipedia.service.CityCountriesManagerService;

@SpringBootApplication
@RequiredArgsConstructor
public class CrawlerwikipediaApplication implements CommandLineRunner {
    private final CityCountriesManagerService cityCountriesManagerService;

    public static void main(String[] args) {
        SpringApplication.run(CrawlerwikipediaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        cityCountriesManagerService.saveAllCountriesAndCitiesInDB();
    }
}
