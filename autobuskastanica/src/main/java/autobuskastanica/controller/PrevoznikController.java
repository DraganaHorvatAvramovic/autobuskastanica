package autobuskastanica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import autobuskastanica.dto.PrevoznikDTO;
import autobuskastanica.model.Prevoznik;
import autobuskastanica.service.PrevoznikService;
import autobuskastanica.support.PrevoznikDtoToPrevoznik;
import autobuskastanica.support.PrevoznikToPrevoznikDto;

@RestController
@RequestMapping(value = "/api/prevoznici", produces = MediaType.APPLICATION_JSON_VALUE)
public class PrevoznikController {
	
	@Autowired
	private PrevoznikService prevoznikService;
	
	@Autowired
	private PrevoznikToPrevoznikDto toPrevoznikDto;
	
	@Autowired
	private PrevoznikDtoToPrevoznik toPrevoznik;
	
	@GetMapping
	 public ResponseEntity<List<PrevoznikDTO>> getAll(){
		
		List<Prevoznik> prevoznici = prevoznikService.findALL();
		
		return new ResponseEntity<>(toPrevoznikDto.convert(prevoznici), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PrevoznikDTO> create (@RequestBody PrevoznikDTO prevoznikDTO){
		
		Prevoznik prevoznik = toPrevoznik.convert(prevoznikDTO);
		Prevoznik sacuvanPrevoznik = prevoznikService.save(prevoznik);
		
		return new ResponseEntity<>(toPrevoznikDto.convert(sacuvanPrevoznik), HttpStatus.CREATED);
	}
}
