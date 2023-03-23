package it.polito.tdp.libretto.model;

import java.time.LocalDate;

public class TestLibretto {

	public static void main(String[] args) {
		
		Libretto lib = new Libretto();
		
		lib.add(new Voto("Analisi 1", 29, LocalDate.of(2021, 2, 15)));
		lib.add(new Voto("Fisica 1", 21, LocalDate.of(2022, 6, 10)));
		lib.add(new Voto("Informatica", 25, LocalDate.of(2022, 6, 6)));
		lib.add(new Voto("Fisica 3", 22, LocalDate.of(2022, 6, 10)));

		
		lib.stampaPuntiUguali(25);
		Voto v = lib.cercaVotoPerNome("Analisi 1");
		System.out.println(v);
		
		Voto a1bis = new Voto ("Analisi 1", 29, LocalDate.of(2022, 2, 15));
		System.out.println(a1bis + " è duplicato " + lib.esisteVotoDuplicato(a1bis));	
		
		Voto f1bis = new Voto("Fisica 1", 22, LocalDate.of(2021, 4, 6));
		System.out.println(f1bis + " è in conflitto: " + lib.conflittoEsame(f1bis));
		
		try {
		lib.add(new Voto("Informatica", 25, LocalDate.of(2022, 6, 6)));
		}catch (IllegalArgumentException e) {
			System.out.println("Errore nell'inserimento voto.");
			System.out.println(e.getMessage());
		}
		
		
		Libretto migliore = lib.librettoMigliorato();
		System.out.println("Libretto migliorato");
		migliore.stampa();
		
		System.out.println("Libretto ordinato: ");
		lib.librettoOrdinatoAlfabeticamente().stampa();
		
		System.out.println("Libretto ordinato per voto: ");
		lib.librettoOrdinatoPerVoto().stampa();
	}

}
