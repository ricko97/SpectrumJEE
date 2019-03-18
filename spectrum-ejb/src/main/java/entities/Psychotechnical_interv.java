package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: psychotechnical
 *
 */
@DiscriminatorValue(value="psychotechnical")
public class Psychotechnical_interv extends Interview implements Serializable {

	   
	private static final long serialVersionUID = 1L;

	public Psychotechnical_interv() {
		super();
	}   
   
}
