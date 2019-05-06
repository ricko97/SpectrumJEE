package bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entities.Candidate;
import services.RegisterLocal;


@ManagedBean
@ViewScoped
public class Profilebean {

private Candidate a = new Candidate();

	
	@EJB
	private RegisterLocal registerLocal;

	@PostConstruct
	private void init() {
		 a= registerLocal.findCandidateById(1);

	}

	public Candidate getA() {
		return a;
	}

	public void setA(Candidate a) {
		this.a = a;
	}



}
