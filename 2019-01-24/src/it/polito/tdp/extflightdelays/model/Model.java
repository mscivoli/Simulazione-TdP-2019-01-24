package it.polito.tdp.extflightdelays.model;

import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	private Graph<String, DefaultWeightedEdge> grafo;
	private List<String> stati;
	ExtFlightDelaysDAO dao = new ExtFlightDelaysDAO();

	public void creaGrafo() {
		this.grafo = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		stati = dao.loadAllStates();
		
		Graphs.addAllVertices(this.grafo, stati);
		
		for(String s1 : this.grafo.vertexSet()) {
			for(String s2 : this.grafo.vertexSet()) {
				if(!s1.equals(s2)) {
					int peso = dao.getPesoArco(s1, s2);
					if(peso>0) {
						Graphs.addEdge(this.grafo, s1, s2, peso);
					}
				}
			}
		}
		
		System.out.println("GRAFO CREATO");
		System.out.println(this.grafo.vertexSet().size());
		System.out.println(this.grafo.edgeSet().size());
	}

	public Graph<String, DefaultWeightedEdge> getGrafo() {
		// TODO Auto-generated method stub
		return this.grafo;
	}

}
