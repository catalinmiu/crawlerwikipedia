package ro.pago.crawlerwikipedia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityDTO {

    private String name;
    private String countryName;
    private Integer population;
    private Double density;
}
