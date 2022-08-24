package ro.pago.crawlerwikipedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.pago.crawlerwikipedia.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

}
