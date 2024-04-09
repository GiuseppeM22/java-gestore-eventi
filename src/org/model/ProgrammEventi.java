package org.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProgrammEventi {

	private String titolo;
	private List<Evento> eventi;

	// COSTRUTTORE
	public ProgrammEventi(String titolo) {
		this.titolo = titolo;
		this.eventi = new ArrayList<Evento>();
	}

	// METODI
	// un metodo che aggiunge alla lista un Evento, passato come parametro
	public void aggiungiAllaLista(Evento evento) {
		this.eventi.add(evento);
	}

	// un metodo che restituisce una lista con tutti gli eventi presenti in una
	// certa data
	public List<Evento> eventiInData(LocalDate data) {
		List<Evento> eventiInData = new ArrayList<>();
		for (Evento evento : eventi) {
			if (evento.getData().equals(data)) {
				eventiInData.add(evento);
			}
		}
		return eventiInData;
	}

	// un metodo che restituisce quanti eventi sono presenti nel programma
	public Integer eventiPresenti() {
		return eventi.size();
	}

	// un metodo che svuota la lista di eventi
	public void svuotaLista() {
		eventi.clear();
	}

	// un metodo che restituisce una stringa che mostra il titolo del programma e
	// tutti gli eventi ordinati per data
	public String listaEventiOrdinatiPerData() {
		eventi.sort(Comparator.comparing(Evento::getData));

		StringBuilder sb = new StringBuilder();
		sb.append("Titolo del programma: ").append(titolo).append("\n");
		for (Evento evento : eventi) {
			sb.append(evento.getData().format(DateTimeFormatter.ofPattern("dd-MM-yyy"))).append(" - ")
					.append(evento.getTitolo()).append("\n");
		}
		return sb.toString();
	}
}
