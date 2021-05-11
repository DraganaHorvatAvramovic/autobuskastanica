package autobuskastanica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import autobuskastanica.dto.RezervacijaDTO;
import autobuskastanica.model.Linija;
import autobuskastanica.model.Rezervacija;
import autobuskastanica.service.LinijaService;
import autobuskastanica.service.RezeravcijaService;
import autobuskastanica.support.RezervacijaToRezervacijaDto;

@RestController
@RequestMapping(value = "/api/rezervacije", produces = MediaType.APPLICATION_JSON_VALUE)
public class RezervacijaController {
	
	@Autowired
	private LinijaService linijaService;
	
	@Autowired
	private RezeravcijaService rezervacijaService;
	
	@Autowired
	private RezervacijaToRezervacijaDto toReazervacijaDto;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RezervacijaDTO> create (@RequestParam(required = false) Long id, @RequestParam(required = false) int brojMesta){
		
		Linija linija = linijaService.findOne(id);
		
		int brMesta= brojMesta;
		
		if(linija.getBrroj_mesta() >= brMesta) {
			linija.setBrroj_mesta(linija.getBrroj_mesta()-brojMesta);
			linijaService.update(linija);
			
			Rezervacija rezervacija = new Rezervacija();
			rezervacija.setLinija(linija);
			rezervacijaService.save(rezervacija);
			
			return new ResponseEntity<>(toReazervacijaDto.convert(rezervacija), HttpStatus.OK);
			
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}

}
