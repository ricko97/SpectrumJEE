package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: technical_interv
 *
 */
@DiscriminatorValue(value="technical")
public class Technical_interv extends Interview implements Serializable {

	   
	private static final long serialVersionUID = 1L;

	public Technical_interv() {
		super();
	}   
   
}
