package autobuskastanica.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import autobuskastanica.dto.KorisnikDTO;
import autobuskastanica.model.Korisnik;

@Component
public class KorisnikToKorisnikDto implements Converter<Korisnik, KorisnikDTO>{

	@Override
	public KorisnikDTO convert(Korisnik source) {
KorisnikDTO korisnikDTO = new KorisnikDTO();
		
		korisnikDTO.setId(source.getId());
		korisnikDTO.seteMail(source.geteMail());
		korisnikDTO.setIme(source.getIme());
		korisnikDTO.setPrezime(source.getPrezime());
		korisnikDTO.setKorisnickoIme(source.getKorisnickoIme());
		return korisnikDTO;
	}
	
	public List<KorisnikDTO> convert(List<Korisnik> korisnici){
		List<KorisnikDTO> korisnikDTOS =  new ArrayList<>();
		
		for(Korisnik k: korisnici) {
			KorisnikDTO dto = convert(k);
			korisnikDTOS.add(dto);
		}
		
		return korisnikDTOS;
	}

}
