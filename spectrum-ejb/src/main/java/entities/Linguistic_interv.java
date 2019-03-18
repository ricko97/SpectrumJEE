package Entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: linguistic_interv
 *
 */
@DiscriminatorValue(value="linguistic")
public class Linguistic_interv extends Interview implements Serializable {

	   
	private static final long serialVersionUID = 1L;

	public Linguistic_interv() {
		super();
	}   

   
}
