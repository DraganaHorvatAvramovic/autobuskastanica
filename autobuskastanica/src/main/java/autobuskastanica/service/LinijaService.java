package autobuskastanica.service;

import java.util.List;

import org.springframework.data.domain.Page;

import autobuskastanica.model.Linija;

public interface LinijaService {
	
	Linija findOne(Long id);
	
	List<Linija> findAll();
	
	Linija save(Linija linija);
	
	Linija update(Linija linija);
	
	Linija delete (Long	id);
		
	//Page<Linija> pretraga (String destinacija, Long prevoznikId, Double cena_karte, int pageNo);
	
	Page<Linija> pretraga(String destinacija, Long prevoznikId, Double cena_karte, int pageNo);

}
