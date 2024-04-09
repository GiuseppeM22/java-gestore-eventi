package org.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean continuaInserimentoEventi = true;
		Evento nuovoEvento = null;
		ProgrammEventi eventiInProgramma = new ProgrammEventi("EVENTI ANNO 2024");

		// Ciclo while che mi permette se l'utente vuole di inserire piu eventi a fine
		// inserimento del precedente
		while (continuaInserimentoEventi) {
			String titolo = "";
			LocalDate data = null;
			int capienza = 0;
			int postiPrenotati = 0;
			boolean flag = false;

			// Creazione di un nuovo evento
			// questo ciclo while mi permette di rimanere all'interno del ciclo finche i
			// dati non sono corretti evitando di ri-runnare
			while (!flag) {
				try {
					System.out.print("Inserisci il titolo dell'evento: ");
					titolo = scan.nextLine();

					System.out.print("Inserisci la data dell'evento (formato yyyy-MM-dd): ");
					String inputData = scan.nextLine();
					data = LocalDate.parse(inputData);
					if (data.isBefore(LocalDate.now())) {
						throw new IllegalArgumentException("La data non può essere nel passato");
					}

					System.out.print("Inserisci la capienza della location: ");
					String inputCapienza = scan.nextLine();
					try {
						capienza = Integer.parseInt(inputCapienza);
						if (capienza <= 0) {
							throw new IllegalArgumentException("La capienza della location deve essere positiva");
						}
					} catch (NumberFormatException e) {
						throw new IllegalArgumentException("Inserire un numero valido per la capienza");
					}

					flag = true;
				} catch (DateTimeParseException e) {
					System.out.println("Errore: Il formato della data non è corretto. Inserire nel formato yyyy-MM-dd");
				} catch (IllegalArgumentException | NullPointerException e) {
					System.out.println("Errore: " + e.getMessage());
				}
			}

			try {
				nuovoEvento = new Evento(titolo, data, capienza, postiPrenotati);
				System.out.println("Nuovo evento creato: " + nuovoEvento);

				// Prenotazioni posti
				boolean prenotazioneFlag = true;
				// questo ciclo while mi permette di rimanere all'interno del ciclo finche i
				// dati non sono corretti evitando di ri-runnare, uguale al precedente ma valido
				// per la
				// seconda fase di
				// richieste all'utente
				while (prenotazioneFlag) {
					try {
						System.out.println("-------------------------------------");
						System.out.print("Vuoi effettuare una prenotazione? (Si/No): ");
						String risposta = scan.nextLine();
						if (risposta.equalsIgnoreCase("si")) {
							System.out.print("Inserisci il numero di posti da prenotare: ");
							int postiDaPrenotare = Integer.parseInt(scan.nextLine());
							if (postiDaPrenotare <= 0) {
								throw new IllegalArgumentException();
							}
							nuovoEvento.prenota(postiDaPrenotare);
							System.out.println("-------------------------------------");
							System.out.println("Prenotazione effettuata con successo.");
						} else if (risposta.equalsIgnoreCase("no")) {
							prenotazioneFlag = false;
						} else {
							System.out.println("Risposta non valida. Inserisci 'Si' o 'No'.");
						}
					} catch (IllegalArgumentException e) {
						System.out.println("Errore durante la prenotazione: " + e.getMessage());
					} catch (IllegalStateException e) {
						System.out.println("Errore durante la prenotazione: " + e.getMessage());
					}
				}

				// Stampo gli attuali posti prenotati e disponibili
				System.out.println("-------------------------------------");
				System.out.println("Numero di posti prenotati: " + nuovoEvento.getNumeroPostiPrenotati());
				System.out.println("-------------------------------------");
				System.out.println("Numero di posti disponibili: "
						+ (nuovoEvento.getNumeroPostiTotale() - nuovoEvento.getNumeroPostiPrenotati()));

				// Disdette
				// Se non sono state fatte prenotazioni controllo che non venga chiesto nulla
				// per la disdetta
				if (nuovoEvento.getNumeroPostiPrenotati() > 0) {

					boolean disdettaFlag = true;
					while (disdettaFlag) {
						try {
							System.out.println("-------------------------------------");
							System.out.print("Vuoi effettuare una disdetta? (Si/No): ");
							String risposta = scan.nextLine();
							if (risposta.equalsIgnoreCase("si")) {
								System.out.print("Inserisci il numero di posti da disdire: ");
								int postiDaDisdire = Integer.parseInt(scan.nextLine());
								nuovoEvento.disdici(postiDaDisdire);
								System.out.println("Disdetta effettuata con successo.");
							} else if (risposta.equalsIgnoreCase("no")) {
								disdettaFlag = false;
							} else {
								System.out.println("Risposta non valida. Inserisci 'Si' o 'No'.");
							}
						} catch (IllegalArgumentException e) {
							System.out.println("Errore durante la disdetta: " + e.getMessage());
						} catch (IllegalStateException e) {
							System.out.println("Errore durante la disdetta: " + e.getMessage());
						}
					}
				}

				// Stampa dei posti prenotati e disponibili
				System.out.println("-------------------------------------");
				System.out.println("Numero di posti prenotati: " + nuovoEvento.getNumeroPostiPrenotati());
				System.out.println("-------------------------------------");
				System.out.println("Numero di posti disponibili: "
						+ (nuovoEvento.getNumeroPostiTotale() - nuovoEvento.getNumeroPostiPrenotati()));

			} catch (IllegalArgumentException e) {
				System.out.println("Errore durante la creazione dell'evento: " + e.getMessage());
			}
			System.out.println();
			System.out.println("**********************************");

			// AGGIUNGO L'EVENTO CREATO ALLA LISTA DI PROGRAMMI

			eventiInProgramma.aggiungiAllaLista(nuovoEvento);

			// Chiedo all'utente se vuole inserire un altro evento
			System.out.print("Vuoi inserire un altro evento? (Si/No): ");
			String risposta = scan.nextLine();
			if (risposta.equalsIgnoreCase("no")) {
				continuaInserimentoEventi = false;
			}
		}

		// TESTO LA MIA CLASSE ProgrammEventi
		System.out.println();

		System.out.println("////////////////// PROGRAMMI IN LISTA ///////////////////////");

		System.out.println();

		String eventiInPrograma = eventiInProgramma.listaEventiOrdinatiPerData();
		System.out.println(eventiInPrograma);

		scan.close();
	}
}
