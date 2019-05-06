package entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: skill
 *
 */
@Entity(name="cv")
public class cv implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String label;
	private static final long serialVersionUID = 1L;
	private String name;
	private String skills;
	private String experienceProf;
	private String academique;
	private String langues;
	private String coordonnees;
	
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getLabel() {
		return label;
	}



	public void setLabel(String label) {
		this.label = label;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getSkills() {
		return skills;
	}



	public void setSkills(String skills) {
		this.skills = skills;
	}



	public String getExperienceProf() {
		return experienceProf;
	}



	public void setExperienceProf(String experienceProf) {
		this.experienceProf = experienceProf;
	}



	public String getAcademique() {
		return academique;
	}



	public void setAcademique(String academique) {
		this.academique = academique;
	}



	public String getLangues() {
		return langues;
	}



	public void setLangues(String langues) {
		this.langues = langues;
	}



	public String getCoordonnees() {
		return coordonnees;
	}



	public void setCoordonnees(String coordonnees) {
		this.coordonnees = coordonnees;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public cv() {
		super();
	}   
}