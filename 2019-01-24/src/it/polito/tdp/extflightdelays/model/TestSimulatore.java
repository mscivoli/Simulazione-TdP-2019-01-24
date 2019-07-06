package it.polito.tdp.extflightdelays.model;

public class TestSimulatore {

	public static void main(String[] args) {
		Simulatore sim = new Simulatore();
		
		sim.init(5, 20, "PA");
		sim.simula();
	}

}
