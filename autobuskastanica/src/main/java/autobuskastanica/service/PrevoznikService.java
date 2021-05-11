package autobuskastanica.service;

import java.util.List;

import autobuskastanica.model.Prevoznik;

public interface PrevoznikService {
	
	Prevoznik findOne(Long id);
	
	List<Prevoznik> findALL();
	
	Prevoznik save(Prevoznik prevoznik);
	
	Prevoznik update(Prevoznik prevoznik);
	
	Prevoznik delete(Long id);

}
