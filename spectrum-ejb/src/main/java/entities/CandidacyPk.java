package Entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CandidacyPk implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private int jobID;
	private int candidateID;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + candidateID;
		result = prime * result + jobID;
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
		CandidacyPk other = (CandidacyPk) obj;
		if (candidateID != other.candidateID)
			return false;
		if (jobID != other.jobID)
			return false;
		return true;
	}
	

}
