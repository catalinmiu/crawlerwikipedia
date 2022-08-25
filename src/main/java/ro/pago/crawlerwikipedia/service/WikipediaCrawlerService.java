package ro.pago.crawlerwikipedia.service;

import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import ro.pago.crawlerwikipedia.client.WikipediaClient;
import ro.pago.crawlerwikipedia.config.ServiceConfig;
import ro.pago.crawlerwikipedia.dto.CityDTO;
import ro.pago.crawlerwikipedia.dto.CountryDTO;
import ro.pago.crawlerwikipedia.entity.City;
import ro.pago.crawlerwikipedia.entity.Country;
import ro.pago.crawlerwikipedia.repository.CityRepository;
import ro.pago.crawlerwikipedia.repository.CountryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WikipediaCrawlerService {

    private final WikipediaClient wikipediaClient;
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final CountryTableService countryTable;
    private final CityTableService cityTable;
    private final ServiceConfig serviceConfig;

    public void saveAllCountriesAndCitiesInDB() {

        Document countriesPage = wikipediaClient.getListOfCountriesPage();
        List<CountryDTO> countryDTOList = countryTable.getAllCountries(countriesPage);
        Document citiesPage = wikipediaClient.getListOfLargestCitiesPage();
        List<CityDTO> cityDTOList = cityTable.getAllCities(citiesPage);

        List<Country> countryList = countryDTOList.stream().map(c -> Country.builder()
                    .countryName(c.getName())
                    .population(c.getPopulation())
                    .area(c.getArea()).build()).collect(Collectors.toList());

        List<Country> savedCountries = countryRepository.saveAll(countryList);

        List<City> cities = cityDTOList.stream().map(c -> City.builder()
                .name(c.getName())
                .country(getCountryByNameFromList(savedCountries, c.getCountryName()))
                .population(c.getPopulation())
                .density(c.getDensity()).build()).collect(Collectors.toList());

        cityRepository.saveAll(cities);
    }

    private Country getCountryByNameFromList(List<Country> countries, String name) {
        if (serviceConfig.getCountryNameExceptions().containsKey(name)) {
            name = serviceConfig.getCountryNameExceptions().get(name);
        }

        String finalName = name;
        return countries.stream()
                .filter(c -> c.getCountryName().equals(finalName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Country name not found in list" + finalName));
    }
}
