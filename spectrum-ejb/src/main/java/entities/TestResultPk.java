package entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class TestResultPk implements Serializable{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int candidateId;
	private int testId;
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + candidateId;
		result = prime * result + testId;
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
		TestResultPk other = (TestResultPk) obj;
		if (candidateId != other.candidateId)
			return false;
		if (testId != other.testId)
			return false;
		return true;
	}
	
}
