package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import it.polito.tdp.poweroutages.db.PowerOutageDAO;

public class Model {

	PowerOutageDAO podao;
	List<EventType> idEventi = new ArrayList<>();
	List<EventType> eventiBest = new ArrayList<>();
	private int maxPersone;
	private int totOre;
	
	
	
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	
	public List<EventType> calcolaSequenza(Nerc nerc, int anni, int ore){

		
		eventiBest= null;
		idEventi=podao.getAllEventi(nerc);
		idEventi.sort(new Comparator<EventType>() {		// ORDINO DATA CRESCENTE
			@Override
			public int compare(EventType o1, EventType o2) {
				return o1.getDateInizio().compareTo(o2.getDateInizio());
			}

		});
	
		cerca(new ArrayList<EventType>(),anni,ore);
		
		return eventiBest;
		
	}
	
	private void cerca(List<EventType> parziale, int anni,int ore) {

			if (calcolaClienti(parziale) > maxPersone) {
					maxPersone = calcolaClienti(parziale);
					eventiBest = new ArrayList<EventType>(parziale);
				
			} 

			for (EventType e : idEventi) {							// CASO INTERMEDIO
			
				if (!parziale.contains(e)) {
					
					parziale.add(e);									// METTO L EVENTO NELL LIST
				
				if (aggiuntaValida(e, parziale, anni, ore) && sommaOre(parziale ,ore)) 	{	// RISPETTA I REQUISITI ?
					cerca(parziale, anni, ore);
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
			
			int y1 = parziale.get(0).getYear();
			int y2 = parziale.get(parziale.size() - 1).getYear();
			if ((y2 - y1 + 1) > anni) {
				return false;
			}
		}
		
	
		return true;
	}

	public boolean sommaOre(List<EventType> parziale, int ore) {
		int sum = 0;
		for (EventType e : parziale) {
			sum += e.getDurata();
		}
		
		if(sum>ore) {
		return false;
		}
		totOre=sum;
		return true;
		
	}
	



	public List<Nerc> getNercList() {
		return podao.getNercList();
	}


	public List<EventType> getEventiBest() {
		return eventiBest;
	}


	public void setEventiBest(List<EventType> eventiBest) {
		this.eventiBest = eventiBest;
	}


	public int getMaxPersone() {
		return maxPersone;
	}


	public void setMaxPersone(int maxPersone) {
		this.maxPersone = maxPersone;
	}


	public int getTotOre() {
		return totOre;
	}


	public void setTotOre(int totOre) {
		this.totOre = totOre;
	}


}
