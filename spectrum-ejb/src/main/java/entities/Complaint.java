package entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: complaint
 *
 */
@Entity(name="complaint")

public class Complaint implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String object;
	private String msg;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	private static final long serialVersionUID = 1L;

	public Complaint() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getObject() {
		return this.object;
	}

	public void setObject(String object) {
		this.object = object;
	}   
	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
   
}
