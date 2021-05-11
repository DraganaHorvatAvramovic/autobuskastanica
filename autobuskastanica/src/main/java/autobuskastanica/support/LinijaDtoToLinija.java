package autobuskastanica.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import autobuskastanica.dto.LinijaDTO;
import autobuskastanica.model.Linija;
import autobuskastanica.service.LinijaService;
import autobuskastanica.service.PrevoznikService;

@Component
public class LinijaDtoToLinija implements Converter<LinijaDTO, Linija>{
	
	@Autowired
	private LinijaService linijaService;
	
	@Autowired
	private PrevoznikService prevoznikService;
	
	@Override
	public Linija convert(LinijaDTO source) {
		Linija linija;
		
		if(source.getId() == null) {
			linija = new Linija();
		} else {
			linija = linijaService.findOne(source.getId());
		}
		
		if(linija != null) {
			linija.setBrroj_mesta(source.getBrroj_mesta());
			linija.setCena_karte(source.getCena_karte());
			linija.setDestinacija(source.getDestinacija());
			linija.setVreme_polska(source.getVreme_polska());
			if(source.getPrevoznikDTO() != null) {
				linija.setPrevoznik(prevoznikService.findOne(source.getPrevoznikDTO().getId()));
			}
			
		}
		
		return linija;
	}

}
