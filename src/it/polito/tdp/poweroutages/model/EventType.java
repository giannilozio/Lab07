package it.polito.tdp.poweroutages.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class EventType {
	
	private int persone;
	private int idEvent;
	private LocalDateTime dateInizio;
	private LocalDateTime dateFine;
	private long durata;
	private int year;
	
	
	
	
	public EventType(LocalDateTime dateInizio, LocalDateTime dateFine, int persone, int idEvent) {
		this.dateInizio=dateInizio;
		this.dateFine=dateFine;
		this.persone=persone;
		this.idEvent=idEvent;
		
		LocalDateTime tempDateTime = LocalDateTime.from(dateInizio);
		this.durata = tempDateTime.until(dateFine, ChronoUnit.HOURS);

		this.year = dateInizio.getYear();
	}




	public int getPersone() {
		return persone;
	}




	public void setPersone(int persoene) {
		this.persone = persoene;
	}




	public int getIdEvent() {
		return idEvent;
	}




	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}




	public LocalDateTime getDateInizio() {
		return dateInizio;
	}




	public void setDateInizio(LocalDateTime dateInizio) {
		this.dateInizio = dateInizio;
	}




	public LocalDateTime getDateFine() {
		return dateFine;
	}




	public void setDateFine(LocalDateTime dateFine) {
		this.dateFine = dateFine;
	}




	public long getDurata() {
		return durata;
	}




	public void setDurata(long outageDuration) {
		this.durata = outageDuration;
	}




	public int getYear() {
		return year;
	}




	public void setYear(int year) {
		this.year = year;
	}




	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}




	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	

}
