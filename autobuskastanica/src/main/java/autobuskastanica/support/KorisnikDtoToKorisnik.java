package autobuskastanica.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import autobuskastanica.dto.KorisnikDTO;
import autobuskastanica.model.Korisnik;
import autobuskastanica.service.KorisnikService;

@Component
public class KorisnikDtoToKorisnik implements Converter<KorisnikDTO, Korisnik>{
	
	@Autowired
	private KorisnikService korisnikService;

	@Override
	public Korisnik convert(KorisnikDTO source) {
		Korisnik korisnik = null;
		
		if(source.getId() != null) {
            korisnik = korisnikService.findOne(source.getId()).get();
        }

        if(korisnik == null) {
            korisnik = new Korisnik();
        }
        
        korisnik.setKorisnickoIme(source.getKorisnickoIme());
        korisnik.seteMail(source.geteMail());
        korisnik.setIme(source.getIme());
        korisnik.setPrezime(source.getPrezime());

		
		return korisnik;
	}
	
	
}
