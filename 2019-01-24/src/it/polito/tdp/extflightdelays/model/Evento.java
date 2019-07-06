package it.polito.tdp.extflightdelays.model;

import java.time.LocalDate;

public class Evento implements Comparable<Evento>{
	
	public enum TipoEvento {
		ARRIVO_TURISTA;
	}
	
	private TipoEvento tipo;
	private String stato;
	private LocalDate giorno;
	private int codiceTurista;
	public Evento(TipoEvento tipo, String stato, LocalDate giorno, int codiceTurista) {
		super();
		this.tipo = tipo;
		this.stato = stato;
		this.giorno = giorno;
		this.codiceTurista = codiceTurista;
	}
	public TipoEvento getTipo() {
		return tipo;
	}
	public void setTipo(TipoEvento tipo) {
		this.tipo = tipo;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public LocalDate getGiorno() {
		return giorno;
	}
	public void setGiorno(LocalDate giorno) {
		this.giorno = giorno;
	}
	public int getCodiceTurista() {
		return codiceTurista;
	}
	public void setCodiceTurista(int codiceTurista) {
		this.codiceTurista = codiceTurista;
	}
	@Override
	public int compareTo(Evento o) {
		// TODO Auto-generated method stub
		return this.giorno.compareTo(o.getGiorno());
	}
	@Override
	public String toString() {
		return "Evento [tipo=" + tipo + ", stato=" + stato + ", giorno=" + giorno + ", codiceTurista=" + codiceTurista
				+ "]";
	}
	
	

}
