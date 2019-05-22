package publicityRec;

import java.util.List;

import javax.ejb.Local;
import entities.Reclamation;

@Local
public interface IReclamationServiceLocal {
public int addReclamation(Reclamation reclamation);
public void removeReclamation(int id);
public Reclamation findReclamationById(int id);
public List<Reclamation> findAllReclamations();
public List<Reclamation> findReclamationByCompany(int id);

}
