package ro.pago.crawlerwikipedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.pago.crawlerwikipedia.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
}
