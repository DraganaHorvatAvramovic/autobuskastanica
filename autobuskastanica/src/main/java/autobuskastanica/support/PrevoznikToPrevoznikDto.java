package autobuskastanica.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import autobuskastanica.dto.PrevoznikDTO;
import autobuskastanica.model.Prevoznik;

@Component
public class PrevoznikToPrevoznikDto implements Converter<Prevoznik, PrevoznikDTO>{

	@Override
	public PrevoznikDTO convert(Prevoznik source) {
		PrevoznikDTO dto = new PrevoznikDTO();
		
		dto.setId(source.getId());
		dto.setNaziv(source.getNaziv());
		dto.setAdresa(source.getAdresa());
		dto.setPIB(source.getPIB());
		
		return dto;
	}
	
	public List<PrevoznikDTO> convert(List<Prevoznik> prevoznici) {
		List<PrevoznikDTO> prevozniciDTO = new ArrayList<>();
		
		for (Prevoznik p: prevoznici) {
			prevozniciDTO.add(convert(p));
		}
		
		return prevozniciDTO;
	}

}
