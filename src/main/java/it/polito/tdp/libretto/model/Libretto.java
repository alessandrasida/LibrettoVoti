package it.polito.tdp.libretto.model;

import java.util.*;

import it.polito.tdp.db.VotoDAO;

public class Libretto {
	
	private List<Voto> voti;

	public Libretto() {
		VotoDAO dao = new VotoDAO();
		this.voti = dao.listVoti();
		
		
	}
	
	/**
	 * Aggiunge un nuovo voto al libretto
	 * non fa nessun controllo
	 * @param v il voto da aggiungere
	 * @return true 
	 */
	//delega 
	public boolean add(Voto v) {
		if( this.esisteVotoDuplicato(v) || this.conflittoEsame(v)) {
			System.out.println("Voto errato");
			throw new IllegalArgumentException("Voto erraro: "+ v);
		}
		VotoDAO dao = new VotoDAO();
		dao.creatVoto(v);
		return this.voti.add(v);
	}
	
	public void stampa() {
		for(Voto v: this.voti) {
			System.out.println(v);
		}
	}
	
	public void stampaPuntiUguali(int valore) {
		for(Voto v: this.voti) {
			if( v.getPunti() == valore)
				System.out.println(v);
		}
	}
	
	public Voto cercaVotoPerNome(String corso) {
		for(Voto v: this.voti) {
			if( v.getCorso().equals(corso))
				return v;
		}
		return null;
		// throw new RuntimeException("Voto non trovato");
	}
	
	public boolean esisteVotoDuplicato( Voto nuovo) {
		for(Voto v: voti) {
			if(v.isDuplicato(nuovo))
			return true;
		}
		return false;
	}
	
	public boolean conflittoEsame( Voto vO) {
		for( Voto v: voti) {
			if(v.isConflitto(vO))
			return true;	
		}
		return false;
	}
	
	// metodo "factory" > crea nuovo oggetto non vuoto ma 
	// già con valori definiti
	public Libretto librettoMigliorato() {
		Libretto migliore = new Libretto();
		migliore.voti = new ArrayList<>();
		for(Voto v: this.voti) {
			// clone profondo
			migliore.voti.add(new Voto(v));
			//creo un nuovo oggetto e lo inizializzo con il costruttore copia
		}
		
		for(Voto v : migliore.voti) {
			v.setPunti(v.getPunti() + 2);
		}
		
		return migliore;
	}
	
	public void cancellaVotiInferiori( int punti) {
		//separo le operazioni di capire gli elementi da cancellare e cancellare gli elementi
		List<Voto> daCancellare = new ArrayList<Voto>();
		for( Voto v: this.voti) {
			if(v.getPunti() < punti) {
				daCancellare.add(v);
				//sconsigliato cancellare mentre si itera
				//non si modifica la lista su cui si sta iterando
				//genera current modification exception
				
			}
		}
		
		for(Voto v1 : daCancellare) {
			this.voti.remove(v1);
		}
		
		//this.voti.removeAll(daCancellare);   opzione simile migliore
		
	}
	
	public Libretto librettoOrdinatoAlfabeticamente() {
		Libretto ordinato = new Libretto();
		ordinato.voti = new ArrayList<>(this.voti);
				
		/*criterio di ordinamento: 
		 * naturale> alfanumerico
		 * 
		 */
		
		ordinato.voti.sort(new ComparatorByName());
		//Collections.sort(ordinato.voti, new ComparatorByName());
		
		return ordinato;
	}


	public Libretto librettoOrdinatoPerVoto() {
		Libretto ordinato = new Libretto();
		ordinato.voti = new ArrayList<>(this.voti);
		
		
		//CLASSE ANONIMA
		// new nome interfaccia tanto anonimo, non viene salvato, 
		//crea interfaccia anonima inline e costruisce l'oggetto
		// {} >> sono l'inline , dove creo la classe 
		ordinato.voti.sort(new Comparator<Voto>(){
			@Override
			public int compare(Voto o1, Voto o2) {
				return o2.getPunti()-o1.getPunti();
			}});
		return ordinato;
	}

	@Override
	public String toString() {
		String txt = "";
		for( Voto v: this.voti) {
			txt = txt + v.toString() + "\n";
		}
		return txt;
	}
	
	
	
	
}
