package jobservices;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Candidacy;
import entities.CandidacyPk;
import entities.CandidacyStatus;

@Stateless
public class CandidacyService implements CandidacyServiceRemote {

	@PersistenceContext(unitName="spectrum-ejb")
	EntityManager em;
	
	@Override
	public boolean addCandidacy(int candidateId, int offerId, Candidacy candidacy) {
		if (this.searchCandidacy(candidateId, offerId)==null) {
			candidacy.setCandidacyPk(this.getCandidacyPk(candidateId, offerId));
			candidacy.setDate(new Date());
			candidacy.setStatus(CandidacyStatus.pending);
			em.persist(candidacy);
			return true;
		}else
			return false;
	}

	@Override
	public boolean cancelCandidacy(int candidateId, int offerId) {
		if (this.searchCandidacy(candidateId, offerId)!=null) {
			Candidacy candidacy = em.find(Candidacy.class, this.getCandidacyPk(candidateId, offerId));
			em.remove(candidacy);
			return true;
		}else
			return false;
			
	}

	@Override
	public List<Candidacy> getCandidaciesByCand(int candidateId) {
		List<Candidacy>candidacies = new ArrayList<Candidacy>();
		for (Candidacy candidacy : this.getAllCandidacies()) {
			if(candidacy.getCandidacyPk().getCandidateID()==candidateId)
				candidacies.add(candidacy);
		}
		return candidacies;
	}

	@Override
	public List<Candidacy> getCandidaciesByOffer(int offerId) {
		List<Candidacy> candidacies = new ArrayList<Candidacy>();
		for (Candidacy candidacy : this.getAllCandidacies()) {
			if(candidacy.getCandidacyPk().getJobID()==offerId)
				candidacies.add(candidacy);
		}
		return candidacies;
	}

	@Override
	public Candidacy searchCandidacy(int candidateId, int offerId) {
		Candidacy candidacy = em.find(Candidacy.class, this.getCandidacyPk(candidateId, offerId));
		if (candidacy!=null)
			return candidacy;
		else
			return null;
	}

	@Override
	public CandidacyPk getCandidacyPk(int candidateId, int offerId) {
		CandidacyPk candidacyPk = new CandidacyPk();
		candidacyPk.setCandidateID(candidateId);
		candidacyPk.setJobID(offerId);
		return candidacyPk;
	}

	@Override
	public List<Candidacy> getAllCandidacies() {
		return em.createQuery("select c from candidacy c",Candidacy.class).getResultList();
	}

	@Override
	public void modifyCandidacy(Candidacy candidacy) {
		em.merge(candidacy);
	}

}
