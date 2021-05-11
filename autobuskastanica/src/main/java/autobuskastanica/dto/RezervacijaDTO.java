package autobuskastanica.dto;


public class RezervacijaDTO {

	private Long id;
	
	private LinijaDTO linijaDTO;

	public RezervacijaDTO() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LinijaDTO getLinijaDTO() {
		return linijaDTO;
	}

	public void setLinijaDTO(LinijaDTO linijaDTO) {
		this.linijaDTO = linijaDTO;
	}
	
	
}
