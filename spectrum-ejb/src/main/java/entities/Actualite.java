package entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Actualite
 *
 */
@Entity

public class Actualite implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int Id ;
	private String Content;
	@ManyToOne
	private User owner;
	@OneToMany(fetch=FetchType.EAGER, mappedBy="holder")
	private List<Commentaire>commentaires;
	public Actualite() {
		super();
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public List<Commentaire> getCommentaires() {
		return commentaires;
	}	
	public void setCommentaires(List<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	
	
	
   
}
