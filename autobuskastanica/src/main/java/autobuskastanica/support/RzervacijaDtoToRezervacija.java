package autobuskastanica.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import autobuskastanica.dto.RezervacijaDTO;
import autobuskastanica.model.Rezervacija;
import autobuskastanica.service.LinijaService;
import autobuskastanica.service.RezeravcijaService;

@Component
public class RzervacijaDtoToRezervacija implements Converter<RezervacijaDTO, Rezervacija>{

	@Autowired
	private RezeravcijaService rezervacijaService;
	
	@Autowired
	private LinijaService linijaService;
	
	@Override
	public Rezervacija convert(RezervacijaDTO source) {
		Rezervacija rezervacija;
		
		if(source.getId() == null) {
			rezervacija = new Rezervacija();
		} else {
			rezervacija = rezervacijaService.findOne(source.getId());
		}
		
		if(rezervacija != null) {
			rezervacija.setLinija(linijaService.findOne(source.getLinijaDTO().getId()));
		}
		
		return rezervacija;
	}
	
	

}
