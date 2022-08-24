package ro.pago.crawlerwikipedia.entity;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "countries")
@Data
public class Country {

    @Id
    @GenericGenerator(name="COUNTRY_ID_GENERATOR",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "seq_countries"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
            })
    @GeneratedValue(generator="COUNTRY_ID_GENERATOR")
    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "population")
    private Integer population;

    @Column(name = "area")
    private Double area;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "country"
    )
    private List<City> cities;
}
