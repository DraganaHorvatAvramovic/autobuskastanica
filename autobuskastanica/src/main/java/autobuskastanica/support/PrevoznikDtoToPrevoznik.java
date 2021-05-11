package autobuskastanica.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import autobuskastanica.dto.PrevoznikDTO;
import autobuskastanica.model.Prevoznik;
import autobuskastanica.service.LinijaService;
import autobuskastanica.service.PrevoznikService;

@Component
public class PrevoznikDtoToPrevoznik  implements Converter<PrevoznikDTO, Prevoznik>{

	@Autowired
	private PrevoznikService prevoznikService;
	
	@Autowired
	private LinijaService linijaService;
	
	@Override
	public Prevoznik convert(PrevoznikDTO source) {
		Prevoznik prevoznik;
		
		if(source.getId() == null) {
			prevoznik = new Prevoznik();
		} else {
			prevoznik = prevoznikService.findOne(source.getId());
		}
		
		if(prevoznik != null) {
			prevoznik.setNaziv(source.getNaziv());
			prevoznik.setAdresa(source.getAdresa());
			prevoznik.setPIB(source.getPIB());			
			
		}
		
		return prevoznik;
	}

}
