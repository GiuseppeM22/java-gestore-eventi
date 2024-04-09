package org.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Concerto extends Evento {

	private LocalTime ora;
	private BigDecimal prezzo;

	// COSTRUTTORI
	public Concerto(String titolo, LocalDate data, int numeroPostiTotale, int numeroPostiPrenotati, LocalTime ora,
			BigDecimal prezzo) throws IllegalArgumentException {
		super(titolo, data, numeroPostiTotale, numeroPostiPrenotati);

		this.ora = ora;
		this.prezzo = prezzo;
	}

	// GETTER & SETTER
	public LocalTime getOra() {
		return ora;
	}

	public void setOra(LocalTime ora) {
		this.ora = ora;
	}

	public BigDecimal getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(BigDecimal prezzo) {
		this.prezzo = prezzo;
	}

	// ALTRI METODI & TO STRING

	public String getDataFormattata() {
		return getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	public String getOraFormattata() {
		return ora.format(DateTimeFormatter.ofPattern("HH:mm"));
	}

	public String getPrezzoFormattato() {
		return String.format("%.2f€", prezzo);
	}

	@Override
	public String toString() {
		return getDataFormattata() + " " + getOraFormattata() + " - " + getTitolo() + " - " + getPrezzoFormattato();
	}

}
