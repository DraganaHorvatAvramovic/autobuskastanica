package autobuskastanica.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import autobuskastanica.dto.LinijaDTO;
import autobuskastanica.model.Linija;

@Component
public class LinijaToLinijaDto implements Converter<Linija, LinijaDTO>{
	@Autowired
	private PrevoznikToPrevoznikDto prevoznikToPrevoznikDto;
	
	@Override
	public LinijaDTO convert(Linija source) {
		LinijaDTO linijaDTO = new LinijaDTO();
		linijaDTO.setId(source.getId());
		linijaDTO.setBrroj_mesta(source.getBrroj_mesta());
		linijaDTO.setCena_karte(source.getCena_karte());
		linijaDTO.setDestinacija(source.getDestinacija());
		linijaDTO.setVreme_polska(source.getVreme_polska());
		linijaDTO.setPrevoznikDTO(prevoznikToPrevoznikDto.convert(source.getPrevoznik()));
		
		return linijaDTO;
	}
	
	public List<LinijaDTO> convert(List<Linija> linije){
		List<LinijaDTO> linijeDTO = new ArrayList<>();
		
		for(Linija linija:linije) {
			linijeDTO.add(convert(linija));
		}
		
		return linijeDTO;
	}

}
