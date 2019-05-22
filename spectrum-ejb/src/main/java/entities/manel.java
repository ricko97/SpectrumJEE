package entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: manel
 *
 */
@Entity

public class manel implements Serializable {

	   
	@Id
	private String code;
	private static final long serialVersionUID = 1L;

	public manel() {
		super();
	}   
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}
   
}
