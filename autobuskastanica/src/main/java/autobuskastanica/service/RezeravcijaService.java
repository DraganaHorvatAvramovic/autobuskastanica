package autobuskastanica.service;

import java.util.List;

import autobuskastanica.model.Rezervacija;

public interface RezeravcijaService {
	
	Rezervacija findOne(Long id);
	List<Rezervacija> findAll();
	
	Rezervacija save(Rezervacija rezervacija);
	
	Rezervacija update(Rezervacija rezervacija);
	
	Rezervacija delete(Long id);
	

}
