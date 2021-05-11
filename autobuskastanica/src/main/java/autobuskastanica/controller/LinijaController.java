package autobuskastanica.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import autobuskastanica.dto.LinijaDTO;
import autobuskastanica.model.Linija;
import autobuskastanica.service.LinijaService;
import autobuskastanica.support.LinijaDtoToLinija;
import autobuskastanica.support.LinijaToLinijaDto;

@RestController
@RequestMapping(value = "/api/linije", produces = MediaType.APPLICATION_JSON_VALUE)
public class LinijaController {
	
	@Autowired
	private LinijaService linijaService;
	
	@Autowired
	private LinijaToLinijaDto linijToLinijaDto;
	
	@Autowired
	private LinijaDtoToLinija linijaDtoToLinija;
	
//	@GetMapping
//	public ResponseEntity<List<LinijaDTO>> getAll(){
//		
//		List<Linija> linije = linijaService.findAll();
//		
//		return new ResponseEntity<>(linijToLinijaDto.convert(linije), HttpStatus.OK);
//	}
//	
	
	@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")	
	@GetMapping
	public ResponseEntity<List<LinijaDTO>> getAll(
			@RequestParam(required=false) String destinacija,
			@RequestParam(required=false) Long prevoznikId,
			@RequestParam(required=false) Double cena_karte,
			@RequestParam(defaultValue = "0") int pageNo){
		
		Page<Linija> linijaPage = linijaService.pretraga(destinacija, prevoznikId, cena_karte, pageNo);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Total-Pages", linijaPage.getTotalPages() + "");
		
		return new ResponseEntity<>(linijToLinijaDto.convert(linijaPage.getContent()), responseHeaders, HttpStatus.OK);
		
	}
	@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<LinijaDTO> getOne(@PathVariable Long id){
		
		Linija linija = linijaService.findOne(id);
		
		if(linija != null) {
			return new ResponseEntity<>(linijToLinijaDto.convert(linija), HttpStatus.OK);
		}else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<LinijaDTO> update(@PathVariable Long id, @Valid @RequestBody LinijaDTO linijaDTO){
		
		if(!id.equals(linijaDTO.getId())) {
			 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Linija linija = linijaDtoToLinija.convert(linijaDTO);
		Linija sacuvanaLinija = linijaService.update(linija);
		
		return new ResponseEntity<>(linijToLinijaDto.convert(sacuvanaLinija), HttpStatus.OK);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LinijaDTO> create(@Valid @RequestBody LinijaDTO linijaDTO){
		
		Linija linija = linijaDtoToLinija.convert(linijaDTO);
		Linija sacuvanaLinija = linijaService.save(linija);
		
		return new ResponseEntity<>(linijToLinijaDto.convert(sacuvanaLinija), HttpStatus.CREATED);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Linija> delete (@PathVariable Long id){
		Linija obrisanaLinija = linijaService.delete(id);
		
		if(obrisanaLinija != null) {
			  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
		
	}
	
	
	
	
	
	

}
