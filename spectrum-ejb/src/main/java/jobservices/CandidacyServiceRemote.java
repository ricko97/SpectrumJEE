package jobservices;

import java.util.List;

import javax.ejb.Remote;

import entities.Candidacy;
import entities.CandidacyPk;
import entities.User;

@Remote
public interface CandidacyServiceRemote {

	public boolean addCandidacy(int candidateId, int offerId, Candidacy candidacy);
	public void modifyCandidacy(Candidacy candidacy);
	public boolean cancelCandidacy(int candidateId, int offerId);
	public List<Candidacy> getCandidaciesByCand(int candidateId);
	public List<Candidacy> getCandidaciesByOffer(int offerId,int entId);
	public Candidacy searchCandidacy(int candidateId, int offerId);
	public CandidacyPk getCandidacyPk(int candidateId, int offerId);
	public List<Candidacy> getCandidaciesByEnt(int entId);
	public List<Candidacy> getAllCandidacies();
	public User getCandidateByEmail(String email);
	public void addTestsToCand(int candId,int entId);
	
}
