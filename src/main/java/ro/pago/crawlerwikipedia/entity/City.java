package ro.pago.crawlerwikipedia.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cities")
public class City {

    @Id
    @GenericGenerator(name="CITY_ID_GENERATOR",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "seq_cities"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
            })
    @GeneratedValue(generator="CITY_ID_GENERATOR")
    @Column(name = "city_id")
    private Integer cityId;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "name")
    private String name;

    @Column(name = "population")
    private Integer population;

    @Column(name = "density")
    private Double density;
}
