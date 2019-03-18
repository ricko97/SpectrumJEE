package Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: workshop
 *
 */
@Entity(name="workshop")
public class Workshop implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int places;
	private Date date;
	private static final long serialVersionUID = 1L;

	public Workshop() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public int getPlaces() {
		return this.places;
	}

	public void setPlaces(int places) {
		this.places = places;
	}   
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
   
}
