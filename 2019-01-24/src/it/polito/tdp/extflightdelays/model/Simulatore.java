package it.polito.tdp.extflightdelays.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.extflightdelays.model.Evento.TipoEvento;

public class Simulatore {
	
	private PriorityQueue<Evento> queue;
	private int turisti;
	private int giorni;
	private Map<Integer, String> mappaTuristi;
	private String statoIniziale;
	private Graph<String, DefaultWeightedEdge> grafo;
	Model m = new Model();
	Random r = new Random();
	LocalDate partenza;
	
	public void init(int turisti, int giorni, String statoIniziale) {
		this.queue = new PriorityQueue<Evento>();
		m.creaGrafo();
		this.grafo = m.getGrafo();
		this.turisti = turisti;
		this.giorni = giorni;
		this.statoIniziale = statoIniziale;
		this.partenza = LocalDate.now();
		this.mappaTuristi = new HashMap<Integer, String>();
		
		
		for(int i = 1; i<=turisti; i++) {
			mappaTuristi.put(i, statoIniziale);
		}
		
		for(Integer i : mappaTuristi.keySet()) {
			queue.add(new Evento(TipoEvento.ARRIVO_TURISTA, mappaTuristi.get(i), partenza, i));
		}
	}
	
	public void simula() {
		while(!queue.isEmpty()) {
			
			Evento e = queue.poll();
			System.out.println(e.toString());
			String stato = e.getStato();
			String arrivo = null;
			List<String> listaDestinazioni = Graphs.successorListOf(grafo, stato);
			if(!listaDestinazioni.isEmpty()) {
				double pesoTotale = 0.0;
				for(String s : listaDestinazioni) {
					pesoTotale += grafo.getEdgeWeight(grafo.getEdge(stato, s));
				}
				Map<String, Double> mappaDestinazioni = new HashMap<String, Double>();
				double pesoDiPrima = 0;
				for(String s : listaDestinazioni) {
					mappaDestinazioni.put(s, grafo.getEdgeWeight(grafo.getEdge(stato, s))/pesoTotale+pesoDiPrima);
					pesoDiPrima += grafo.getEdgeWeight(grafo.getEdge(stato, s))/pesoTotale;
				}
				
				double probabilita = r.nextDouble();
				for(String s : mappaDestinazioni.keySet()) {
					if(mappaDestinazioni.get(s)>probabilita) {
						arrivo = s;
						break;
					}
				}
				
				if(e.getGiorno().plusDays(1).isBefore(partenza.plusDays(giorni+1))) {
					queue.add(new Evento(e.getTipo(), arrivo, e.getGiorno().plusDays(1), e.getCodiceTurista()));
					mappaTuristi.put(e.getCodiceTurista(), arrivo);
				}
			}
			
				
		}
	}

}
