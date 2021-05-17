package autobuskastanica.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Linija {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = true)
	private int brroj_mesta;
	
	@Column
	private double cena_karte;
	
	@Column(nullable = false)
	private String vreme_polska;
	
	@Column
	private String destinacija;
	
	@ManyToOne
	private Prevoznik prevoznik;
	
	@OneToMany (mappedBy = "linija")
	private List<Rezervacija> rezervacije = new ArrayList<>();

	public List<Rezervacija> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(List<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}

	public Linija() {
		
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

	public Prevoznik getPrevoznik() {
		return prevoznik;
	}

	public void setPrevoznik(Prevoznik prevoznik) {
		this.prevoznik = prevoznik;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Linija other = (Linija) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Linija [id=" + id + ", brroj_mesta=" + brroj_mesta + ", cena_karte=" + cena_karte + ", vreme_polska="
				+ vreme_polska + ", destinacija=" + destinacija + ", prevoznik=" + prevoznik + "]";
	}
	
	
	
	
	
	
	
	
	

}
