package Entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: event
 *
 */
@Entity(name="event")
public class Event implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String title;
	private Date start;
	private Date end;
	private int places;
	private String location;
	private static final long serialVersionUID = 1L;

	public Event() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}   
	public Date getStart() {
		return this.start;
	}

	public void setStart(Date start) {
		this.start = start;
	}   
	public Date getEnd() {
		return this.end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}   
	public int getPlaces() {
		return this.places;
	}

	public void setPlaces(int places) {
		this.places = places;
	}   
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
   
}
