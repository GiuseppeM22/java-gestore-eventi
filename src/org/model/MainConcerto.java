package org.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class MainConcerto {

	public static void main(String[] args) {
		Concerto concerto = new Concerto("Ultimo", LocalDate.now(), 2000, 0, LocalTime.now(), new BigDecimal("3.99"));

		System.out.println(concerto);

	}

}
