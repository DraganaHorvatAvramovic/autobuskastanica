package autobuskastanica.dto;

public class LinijaDTO {
	
	private Long id;
	
	private int brroj_mesta;
	
	private double cena_karte;
	
	private String vreme_polska;
	
	private String destinacija;
	
	private PrevoznikDTO prevoznikDTO;

	public LinijaDTO() {
	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getBrroj_mesta() {
		return brroj_mesta;
	}

	public void setBrroj_mesta(int brroj_mesta) {
		this.brroj_mesta = brroj_mesta;
	}

	public double getCena_karte() {
		return cena_karte;
	}

	public void setCena_karte(double cena_karte) {
		this.cena_karte = cena_karte;
	}

	public String getVreme_polska() {
		return vreme_polska;
	}

	public void setVreme_polska(String vreme_polska) {
		this.vreme_polska = vreme_polska;
	}

	public String getDestinacija() {
		return destinacija;
	}

	public void setDestinacija(String destinacija) {
		this.destinacija = destinacija;
	}

	public PrevoznikDTO getPrevoznikDTO() {
		return prevoznikDTO;
	}

	public void setPrevoznikDTO(PrevoznikDTO prevoznikDTO) {
		this.prevoznikDTO = prevoznikDTO;
	}
	
	
	

}
