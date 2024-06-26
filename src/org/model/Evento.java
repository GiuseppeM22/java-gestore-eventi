package org.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Evento {
	private String titolo;
	private LocalDate data;
	private int numeroPostiTotale;
	private int numeroPostiPrenotati;

	// COSTRUTTORE

	public Evento(String titolo, LocalDate data, int numeroPostiTotale, int numeroPostiPrenotati)
			throws IllegalArgumentException {
		if (data.isBefore(LocalDate.now())) {
			throw new IllegalArgumentException("La data non può essere nel passato");
		}

		if (numeroPostiTotale <= 0) {
			throw new IllegalArgumentException("Il numero di posti totali deve essere positivo");
		}

		if (numeroPostiPrenotati < 0) {
			throw new IllegalArgumentException("Il numero di posti prenotabili deve essere positivo");
		}

		this.titolo = titolo;
		this.data = data;
		this.numeroPostiTotale = numeroPostiTotale;
		this.numeroPostiPrenotati = 0;
	}

	// GETTER & SETTER

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) throws IllegalArgumentException {
		if (data.isBefore(LocalDate.now())) {
			throw new IllegalArgumentException("La data non può essere nel passato");
		}
		this.data = data;
	}

	public int getNumeroPostiTotale() {
		return numeroPostiTotale;
	}

	public int getNumeroPostiPrenotati() {
		return numeroPostiPrenotati;
	}

	// TO STRING E ALTRI METODI

	public void prenota(int numeroPosti) throws IllegalStateException {
		if (data.isBefore(LocalDate.now())) {
			throw new IllegalStateException("Impossibile prenotare per un evento passato");
		}
		if (numeroPosti > (numeroPostiTotale - numeroPostiPrenotati)) {
			throw new IllegalStateException("Non ci sono abbastanza posti disponibili");
		}
		numeroPostiPrenotati += numeroPosti;
	}

	public void disdici(int numeroPosti) throws IllegalStateException {
		if (data.isBefore(LocalDate.now())) {
			throw new IllegalStateException("Impossibile disdire per un evento passato");
		}
		if (numeroPosti > numeroPostiPrenotati) {
			throw new IllegalStateException("Non ci sono abbastanza prenotazioni da disdire");
		}
		numeroPostiPrenotati -= numeroPosti;
	}

	@Override
	public String toString() {
		return data.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + " - " + titolo;
	}

}
