package autobuskastanica.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import autobuskastanica.model.Linija;

@Repository
public interface LinijaRepository extends JpaRepository<Linija, Long>{
	
	Linija findOneById(Long id);
	
	//Page<Linija> findByDestinacijaAndPrevoznikIdAndCena_karteAndPageNo(String destinacija, Long prevoznikId, Double cena_karte, Pageable pageNo);
	
	//Page<Linija> findByDestinacijaAndPrevoznikIdAndCena_karteAndPageNo(String destinacija, Long prevoznikId, Double cena_karte, Pageable pageNo);
	
	@Query("SELECT p FROM Linija p WHERE "
    		+ "(:destinacija IS NULL OR p.destinacija like %:destinacija%) AND "
    		+ "(:prevoznikId IS NULL OR p.prevoznik.id = :prevoznikId) AND "
    		+ "(:cena_karte IS NULL OR p.cena_karte <= :cena_karte)")
    Page<Linija> pretraga(@Param("destinacija") String destinacija, @Param("prevoznikId") Long prevoznikId, @Param("cena_karte") Double cena_karte, Pageable pageable);

}
