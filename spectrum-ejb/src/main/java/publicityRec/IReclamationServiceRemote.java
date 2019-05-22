package publicityRec;

import java.util.List;

import javax.ejb.Remote;

import entities.Reclamation;

@Remote
public interface IReclamationServiceRemote {
public int addReclamation(Reclamation reclamation);
public void removeReclamation(int id);
public Reclamation findReclamationById(int id);
public List<Reclamation> findAllReclamations();
public List<Reclamation> findReclamationByCompany(int id);

}
