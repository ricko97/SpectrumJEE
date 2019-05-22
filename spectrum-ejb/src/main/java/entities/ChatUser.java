package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ChatUser
 *
 */
@Entity

public class ChatUser implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
    private int id;
	public ChatUser() {
		super();
	}
   
}
