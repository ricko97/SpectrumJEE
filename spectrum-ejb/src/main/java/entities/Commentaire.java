package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Commentaire
 *
 */
@Entity

public class Commentaire implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int Id;
	private String content;
	@ManyToOne
	private User owner;
	@ManyToOne
	private Actualite holder;
	public Commentaire() {
		super();
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setActualite(Actualite a){
		this.holder=a;
	}
	

}
