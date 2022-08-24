package ro.pago.crawlerwikipedia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountryDTO {

    private String name;
    private Integer population;
    private Double area;
}
