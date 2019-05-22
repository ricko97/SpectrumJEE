package publicityBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import entities.Reclamation;
import entities.User;
import publicityRec.ReclamationService;
import publicityRec.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

@ManagedBean(name = "reclamationBean")
@SessionScoped
public class ReclamationBean implements Serializable
{ private static final long serialVersionUID = 1L; 
private Integer ratingRH; 
private Integer ratingSalary; 
private Integer ratingSchedule; 
private Integer ratingLogistic;
private Integer ratingRHAVG; 
private Integer ratingSalaryAVG; 
private Integer ratingScheduleAVG; 
private Integer ratingLogisticAVG;
private String comment;
private User entreprise;
private User loggedin;
private int id;
public List<User> companies = new ArrayList<>();
public List<Reclamation> mine = new ArrayList<>();
public String toDetails(){
	String navigateTo="/rateEntreprise?faces-redirect=true";
return navigateTo;
}


@EJB
ReclamationService reclamationService = new ReclamationService(); 
@EJB
UserService userService = new UserService();
public void addReclamation() {
	Reclamation r = new Reclamation();
	r.setComment(comment);
	r.setRatingLogistic(ratingLogistic);
	r.setRatingRH(ratingRH);
	r.setRatingSalary(ratingSalary);
	r.setRatingSchedule(ratingSchedule);
	r.setReclamedBy(loggedin);
	r.setEntreprise(entreprise);
	reclamationService.addReclamation(r); }

private Map<User,Double> sortByValue(Map<User,Double> map) {
    List<Entry<User,Double>> list = new LinkedList<>(map.entrySet());
    Collections.sort(list, new Comparator<Object>() {
        @SuppressWarnings("unchecked")
        public int compare(Object oid, Object o2) {
            return ((Comparable<Double>) ((Map.Entry<User,Double>) (oid)).getValue()).compareTo(((Map.Entry<User,Double>) (o2)).getValue());
        }
    });

    Map<User,Double> result = new LinkedHashMap<>();
    for (Iterator<Entry<User,Double>> it = list.iterator(); it.hasNext();) {
        Map.Entry<User,Double> entry = (Map.Entry<User,Double>) it.next();
        result.put(entry.getKey(), entry.getValue());
    }

    return result;
}

@PostConstruct
public void init() {
	mine = reclamationService.findReclamationByCompany(1);
	List<User> com = userService.findCompanies();
    Map<User,Double> map = new HashMap<>(); 
	for(User company : com)
	{
		List<Reclamation> recl = reclamationService.findReclamationByCompany(company.getId());
		double somme = 0;
		for(Reclamation r : recl)
		{
			somme += r.getRatingRH() + r.getRatingLogistic() + r.getRatingSalary() + r.getRatingSchedule();
		}
		map.put(company, somme);
	}
	map = sortByValue(map);
	List<Entry<User,Double>> list = new LinkedList<>(map.entrySet());
	for (Iterator<Entry<User,Double>> it = list.iterator(); it.hasNext();) {
        Map.Entry<User,Double> entry = (Map.Entry<User,Double>) it.next();
        companies.add(entry.getKey());
    }
	if(companies.size() >= 5)
	{
		companies = companies.subList(0, 4);
	}
	entreprise = userService.findUserById(id);
	List<Reclamation> reclams = reclamationService.findReclamationByCompany(id);
	double sommeRH = 0, sommeSalary = 0, sommeLog =0,sommeSch =0;
	for(Reclamation r : reclams)
	{
		sommeRH += r.getRatingRH();
		sommeLog += r.getRatingLogistic();
		sommeSalary += r.getRatingSalary();
		sommeSch += r.getRatingSchedule();
	}
	ratingLogisticAVG = (int) Math.ceil(sommeLog/reclams.size());
	ratingSalaryAVG = (int) Math.ceil(sommeSalary/reclams.size());
	ratingRHAVG = (int) Math.ceil(sommeRH/reclams.size());
	ratingScheduleAVG= (int) Math.ceil(sommeSch/reclams.size());
	
}

public User getLoggedin() {
	return loggedin;
}

public void setLoggedin(User loggedin) {
	this.loggedin = loggedin;
}

public ReclamationService getReclamationService() {
	return reclamationService;
}

public void setReclamationService(ReclamationService reclamationService) {
	this.reclamationService = reclamationService;
}

public UserService getUserService() {
	return userService;
}

public void setUserService(UserService userService) {
	this.userService = userService;
}

public String getComment() {
	return comment;
}
public void setComment(String comment) {
	this.comment = comment;
}
public Integer getRatingLogistic() {
	return ratingLogistic;
}
public Integer getRatingRH() {
	return ratingRH;
}
public Integer getRatingSalary() {
	return ratingSalary;
}
public Integer getRatingSchedule() {
	return ratingSchedule;
}
public void setRatingLogistic(Integer ratingLogistic) {
	this.ratingLogistic = ratingLogistic;
}
public void setRatingRH(Integer ratingRH) {
	this.ratingRH = ratingRH;
}
public void setRatingSalary(Integer ratingSalary) {
	this.ratingSalary = ratingSalary;
}
public void setRatingSchedule(Integer ratingSchedule) {
	this.ratingSchedule = ratingSchedule;
}
public Integer getRatingLogisticAVG() {
	return ratingLogisticAVG;
}

public Integer getRatingRHAVG() {
	return ratingRHAVG;
}

public void setRatingRHAVG(Integer ratingRHAVG) {
	this.ratingRHAVG = ratingRHAVG;
}

public Integer getRatingSalaryAVG() {
	return ratingSalaryAVG;
}

public void setRatingSalaryAVG(Integer ratingSalaryAVG) {
	this.ratingSalaryAVG = ratingSalaryAVG;
}

public Integer getRatingScheduleAVG() {
	return ratingScheduleAVG;
}

public void setRatingScheduleAVG(Integer ratingScheduleAVG) {
	this.ratingScheduleAVG = ratingScheduleAVG;
}

public User getEntreprise() {
	return entreprise;
}

public void setEntreprise(User entreprise) {
	this.entreprise = entreprise;
}

public void setRatingLogisticAVG(Integer ratingLogisticAVG) {
	this.ratingLogisticAVG = ratingLogisticAVG;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public List<User> getCompanies() {
	return companies;
}

public void setCompanies(List<User> companies) {
	this.companies = companies;
}

public List<Reclamation> getMine() {
	return mine;
}

public void setMine(List<Reclamation> mine) {
	this.mine = mine;
}


}

