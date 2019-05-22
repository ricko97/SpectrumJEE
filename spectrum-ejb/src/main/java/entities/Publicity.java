package entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Publicity implements Serializable {
	
	
	private static final long serialVersionUID = 375842352197576868L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="decription")
	private String description;
	@Column(name="photo")
	private String photo;
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Target> targetAudience;
	@ManyToOne(cascade = CascadeType.ALL)
	User postedBy;
	
	
	public int getId() {
		return id;
	}
	public String getDescription() {
		return description;
	}
	public String getName() {
		return name;
	}
	public String getPhoto() {
		return photo;
	}
	public Publicity() {
		// TODO Auto-generated constructor stub
	}
	public Set<Target> getTargetAudience() {
		return targetAudience;
	}
	public void setTargetAudience(Set<Target> targetAudience) {
		this.targetAudience = targetAudience;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	} 
	public User getPostedBy() {
		return postedBy;
	}
	public void setPostedBy(User postedBy) {
		this.postedBy = postedBy;
	}

}
