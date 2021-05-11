package autobuskastanica.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import autobuskastanica.dto.RezervacijaDTO;
import autobuskastanica.model.Rezervacija;

@Component
public class RezervacijaToRezervacijaDto implements Converter<Rezervacija, RezervacijaDTO>{
	
	@Autowired
	private LinijaToLinijaDto toLinijaDto;
	
	@Override
	public RezervacijaDTO convert(Rezervacija source) {
		RezervacijaDTO rezervacijaDTO = new RezervacijaDTO();
		rezervacijaDTO.setId(source.getId());
		rezervacijaDTO.setLinijaDTO(toLinijaDto.convert(source.getLinija()));
		return rezervacijaDTO;
	}
	
	public List<RezervacijaDTO> convert(List<Rezervacija> rezervacije){
		List<RezervacijaDTO> rezervacijeDTO = new ArrayList<>();
		
		for(Rezervacija r:rezervacije) {
			rezervacijeDTO.add(convert(r));
		}
		return rezervacijeDTO;
	}

}
