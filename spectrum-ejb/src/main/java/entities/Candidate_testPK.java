package entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Candidate_testPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int candidateID;
	private int testID;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + candidateID;
		result = prime * result + testID;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Candidate_testPK other = (Candidate_testPK) obj;
		if (candidateID != other.candidateID)
			return false;
		if (testID != other.testID)
			return false;
		return true;
	}

}
