package autobuskastanica.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import autobuskastanica.model.Prevoznik;
import autobuskastanica.repository.PrevoznikRepository;
import autobuskastanica.service.PrevoznikService;

@Service
public class JpaPrevoznikService implements PrevoznikService {

	
	@Autowired
	private PrevoznikRepository prevoznikRepository;
	
	@Override
	public Prevoznik findOne(Long id) {
		return prevoznikRepository.findOneById(id);
	}

	@Override
	public List<Prevoznik> findALL() {
		return prevoznikRepository.findAll();
	}

	@Override
	public Prevoznik save(Prevoznik prevoznik) {
		return prevoznikRepository.save(prevoznik);
	}

	@Override
	public Prevoznik update(Prevoznik prevoznik) {
		return prevoznikRepository.save(prevoznik);
	}

	@Override
	public Prevoznik delete(Long id) {
		Optional<Prevoznik> prevoznik = prevoznikRepository.findById(id);
		if(prevoznik.isPresent()) {
			prevoznikRepository.deleteById(id);
			return prevoznik.get();
		}
		return null;
	}

}
