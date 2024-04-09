package org.model;

import java.time.LocalDate;

public class Main {

	public static void main(String[] args) {
		Evento nuovoEvento = new Evento("prova", LocalDate.now(), 10000);
		
		System.out.println(nuovoEvento);

	}
	

}
