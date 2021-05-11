package autobuskastanica.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import autobuskastanica.model.Linija;
import autobuskastanica.repository.LinijaRepository;
import autobuskastanica.service.LinijaService;

@Service
public class JpaLinijaService implements LinijaService{
	
	@Autowired
	private LinijaRepository linijaRepository;

	@Override
	public Linija findOne(Long id) {
		return linijaRepository.findOneById(id);
	}

	@Override
	public List<Linija> findAll() {
		return linijaRepository.findAll();
	}

	@Override
	public Linija save(Linija linija) {
		return linijaRepository.save(linija);
	}

	@Override
	public Linija update(Linija linija) {
		return linijaRepository.save(linija);
	}

	@Override
	public Linija delete(Long id) {
		Optional<Linija> linija = linijaRepository.findById(id);
		if(linija.isPresent()) {
			linijaRepository.deleteById(id);
			return linija.get();
		}
		return null;
	}

	@Override
	public Page<Linija> pretraga(String destinacija, Long prevoznikId, Double cena_karte, int pageNo) {
		return linijaRepository.pretraga(destinacija, prevoznikId, cena_karte,  PageRequest.of(pageNo, 2));
	}

//	@Override
//	public Page<Linija> pretraga(String destinacija, Long prevoznikId, Double cena_karte, int pageNo) {
//		return linijaRepository.findByDestinacijaAndPrevoznikIdAndCena_karteAndPageNo(destinacija, prevoznikId, cena_karte,PageRequest.of(pageNo, 2));
//	}

//	@Override
//	public Page<Linija> pretraga(String destinacija, Long prevoznikId, Double cena_karte, int pageNo) {
//		return linijaRepository.findByDestinacijaAndPrevoznikIdAndCena_karteAndPageNo(destinacija, prevoznikId, cena_karte, PageRequest.of(pageNo, 5));
//		
//	}

	
	
	
	

}
