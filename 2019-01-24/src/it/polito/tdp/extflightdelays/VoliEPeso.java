package it.polito.tdp.extflightdelays;

public class VoliEPeso implements Comparable<VoliEPeso>{
	
	private String stato;
	private double peso;
	public VoliEPeso(String stato, double peso) {
		super();
		this.stato = stato;
		this.peso = peso;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	@Override
	public int compareTo(VoliEPeso o) {
		if(this.peso>o.getPeso())
			return -1;
		else return 1;
	}
	
	

}
