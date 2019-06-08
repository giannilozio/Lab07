package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import it.polito.tdp.poweroutages.db.PowerOutageDAO;

public class Model {

	PowerOutageDAO podao;
	List<EventType> idEventi = new ArrayList<>();
	List<EventType> eventiBest = new ArrayList<>();
	
	
	
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	
	public List<EventType> calcolaSequenza(Nerc nerc, int anni, int ore){

		List<EventType> parziale = new ArrayList<>();
		eventiBest= null;
		idEventi=podao.getAllEventi(nerc);
		idEventi.sort(new Comparator<EventType>() {		// ORDINO DATA CRESCENTE
			@Override
			public int compare(EventType o1, EventType o2) {
				return o1.getDateInizio().compareTo(o2.getDateInizio());
			}

		});
	
		cerca(parziale,anni,ore,0);
		
		return eventiBest;
		
	}
	
	private void cerca(List<EventType> parziale, int livello, int anni, int ore) {

		if (livello == idEventi.size()) { // CASO TERMINALE
			int totClienti = calcolaClienti(parziale);
			if (eventiBest == null || totClienti > calcolaClienti(eventiBest)) {

				eventiBest = new ArrayList<>(parziale);

			}

		} else {

			for (EventType e : idEventi) {							// CASO INTERMEDIO
				
				parziale.add(e);									// METTO L EVENTO NELL LIST

				if (aggiuntaValida(e, parziale, anni, ore)) { 		// RISPETTA I REQUISITI ?
					cerca(parziale, livello + 1, anni, ore);
				}
					parziale.remove(e);
				
			}

		}

	}

	private int calcolaClienti(List<EventType> parziale) {
		int totPersone=0;
	for(EventType et : parziale) {
		totPersone+=et.getPersone();
		}
		return totPersone;
	}


	private boolean aggiuntaValida(EventType e, List<EventType> parziale, int anni, int ore) {
	
		if (parziale.size() >=2 ) {
			
			int y1 = parziale.get(0).getDateInizio().getYear();
			int y2 = parziale.get(parziale.size()).getDateInizio().getYear();
			if ((y2 - y1 ) > anni) {
				return false;
			}
		}
		
		if(sommaOre(parziale) < ore)
		return true;
		
		return false;
	}

	public int sommaOre(List<EventType> parziale) {
		int sum = 0;
		for (EventType e : parziale) {
			sum += e.getDurata();
		}
		return sum;
	}
	



	public List<Nerc> getNercList() {
		return podao.getNercList();
	}


}
