package services_cand_interv;

import java.util.List;

import javax.ejb.Remote;

import entities.Candidacy;
import entities.CandidacyPk;

@Remote
public interface CandidacyServiceRemote {

	public boolean addCandidacy(int candidateId, int offerId, Candidacy candidacy);
	public boolean cancelCandidacy(int candidateId, int offerId);
	public List<Candidacy> getCandidaciesByCand(int candidateId);
	public List<Candidacy> getCandidaciesByOffer(int offerId);
	public Candidacy searchCandidacy(int candidateId, int offerId);
	public CandidacyPk getCandidacyPk(int candidateId, int offerId);
	public List<Candidacy> getAllCandidacies();
	
}
