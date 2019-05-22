package entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class Reclamation implements Serializable {
	
	
	private static final long serialVersionUID = 714810594773659929L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="comment")
	private String comment;
	@Column(name="ratingRH")
	private float ratingRH;
	@Column(name="ratingSalary")
	private float ratingSalary;
	@Column(name="ratingSchedule")
	private float ratingSchedule;
	@Column(name="ratingLogistic")
	private float ratingLogistic;
	@ManyToOne(cascade = CascadeType.ALL)
	User entreprise;
	@ManyToOne(cascade = CascadeType.ALL)
	User reclamedBy;
	
	public Reclamation() {
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}
	public String getComment() {
		return comment;
	}
	public float getRatingLogistic() {
		return ratingLogistic;
	}
	public float getRatingRH() {
		return ratingRH;
	}
	public float getRatingSalary() {
		return ratingSalary;
	}
	public float getRatingSchedule() {
		return ratingSchedule;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setRatingLogistic(float ratingLogistic) {
		this.ratingLogistic = ratingLogistic;
	}
	public void setRatingRH(float ratingRH) {
		this.ratingRH = ratingRH;
	}
	public void setRatingSalary(float ratingSalary) {
		this.ratingSalary = ratingSalary;
	}
	public void setRatingSchedule(float ratingSchedule) {
		this.ratingSchedule = ratingSchedule;
	}
	public User getEntreprise() {
		return entreprise;
	}
	public User getReclamedBy() {
		return reclamedBy;
	}
	public void setEntreprise(User entreprise) {
		this.entreprise = entreprise;
	}
	public void setReclamedBy(User reclamedBy) {
		this.reclamedBy = reclamedBy;
	}

}

