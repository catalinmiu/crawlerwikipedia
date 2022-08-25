package ro.pago.crawlerwikipedia.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = ServiceConfig.PREFIX)
@Configuration
public class ServiceConfig {
    public static final String PREFIX = "props";
    private String listOfCountriesWikiUrl;
    private String listOfLargestCitiesWikiUrl;
    private HashMap<String, String> countryNameExceptionsMap;
}
