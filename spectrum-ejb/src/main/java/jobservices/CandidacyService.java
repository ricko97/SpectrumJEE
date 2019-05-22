package jobservices;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Candidacy;
import entities.CandidacyPk;
import entities.CandidacyStatus;
import entities.Candidate;
import entities.Enterprise;
import entities.Test;
import entities.User;

@Stateful
@LocalBean
public class CandidacyService implements CandidacyServiceRemote {

	@PersistenceContext(unitName="spectrum-ejb")
	EntityManager em;
	
	@Override
	public boolean addCandidacy(int candidateId, int offerId, Candidacy candidacy) {
		if (this.searchCandidacy(candidateId, offerId)==null) {
			candidacy.setCandidacyPk(this.getCandidacyPk(candidateId, offerId));
			candidacy.setDate(new Date());
			candidacy.setStatus(CandidacyStatus.sent);
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
			if(candidacy.getCandidate().getId()==candidateId)
				candidacy.setOfferTitle(candidacy.getOfferTitle());
				candidacy.setEmail(candidacy.getEmail());
				candidacy.setName(candidacy.getName());
				candidacies.add(candidacy);
		}
		return candidacies;
	}

	@Override
	public List<Candidacy> getCandidaciesByOffer(int offerId, int entId) {
		List<Candidacy> candidacies = new ArrayList<Candidacy>();
		for (Candidacy candidacy : this.getCandidaciesByEnt(entId)) {
			if(candidacy.getJoboffer().getId()==offerId)
				candidacy.setOfferTitle(candidacy.getOfferTitle());
				candidacy.setEmail(candidacy.getEmail());
				candidacy.setName(candidacy.getName());
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
	public List<Candidacy> getCandidaciesByEnt(int entId) {
		List<Candidacy>candidacies = new ArrayList<Candidacy>();
		for (Candidacy candidacy :getAllCandidacies()) {
			if (candidacy.getJoboffer().getEnterprise().getId()==entId) {
				candidacy.setOfferTitle(candidacy.getJoboffer().getTitle());
				candidacy.setEmail(candidacy.getCandidate().getUser().getEmail());
				candidacy.setName(candidacy.getCandidate().getUser().getName());
				candidacies.add(candidacy);
			}	
		}
		return candidacies;
	}

	@Override
	public void modifyCandidacy(Candidacy candidacy) {
		em.merge(candidacy);
	}

	@Override
	public User getCandidateByEmail(String email) {
		TypedQuery<User> query = em.createQuery("select u from user u where u.email=:email",User.class);
		query.setParameter("email", email);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			Logger.getAnonymousLogger().info("user not found => "+e.getMessage());
		}
		return null;
	}

	@Override
	public void addTestsToCand(int candId, int entId) {
		Candidate cand = em.find(Candidate.class, candId);
		for (Test test : em.find(Enterprise.class, entId).getTests()) {
			cand.getPassedTests().add(test);
		}
		em.merge(cand);
	}

}
