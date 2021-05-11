package autobuskastanica.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import autobuskastanica.model.Rezervacija;
import autobuskastanica.repository.RezervacijaRepository;
import autobuskastanica.service.RezeravcijaService;

@Service
public class JpaRezervacijaService implements RezeravcijaService {
	
	@Autowired
	private RezervacijaRepository rezrvacijaRepository;

	@Override
	public Rezervacija findOne(Long id) {
		return rezrvacijaRepository.findOneById(id);
	}

	@Override
	public List<Rezervacija> findAll() {
		return rezrvacijaRepository.findAll();
	}

	@Override
	public Rezervacija save(Rezervacija rezervacija) {
		return rezrvacijaRepository.save(rezervacija);
	}

	@Override
	public Rezervacija update(Rezervacija rezervacija) {
		return rezrvacijaRepository.save(rezervacija);
	}

	@Override
	public Rezervacija delete(Long id) {
		Optional<Rezervacija> rezervacija = rezrvacijaRepository.findById(id);
		if(rezervacija.isPresent()) {
			rezrvacijaRepository.deleteById(id);
			return rezervacija.get();
		}
		return null;
	}
	
	

}
