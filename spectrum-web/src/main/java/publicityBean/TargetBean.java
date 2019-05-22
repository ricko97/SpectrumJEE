package publicityBean;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

import entities.Publicity;
import entities.Reclamation;
import entities.Target;
import entities.User;
import publicityRec.PublicityService;
import publicityRec.ReclamationService;
import publicityRec.TargetService;
import publicityRec.UserService;

@ManagedBean(name = "targetBean")
@SessionScoped
public class TargetBean implements Serializable
{ private static final long serialVersionUID = 1L; 

private User loggedin;


public List<Target> targets = new ArrayList<>();
public String name;
public int targetIdToBeUpdated;



public void saveFile(){
	Target t = new Target();
	t.setName(name);
	
	if(targetIdToBeUpdated == -1)
	{		
		targetService.addTarget(t);
		this.setTargets(targetService.findAllTargets());
	}
	else
	{
		t.setId(targetIdToBeUpdated);
		targetService.updateTarget(t);
		this.setTargetIdToBeUpdated(-1);;
		targets = targetService.findAllTargets();
	}
}


@EJB
TargetService targetService = new TargetService(); 


@PostConstruct
public void init() {
	targetIdToBeUpdated = -1;
	targets = targetService.findAllTargets();
	
	
	
}

public void displayTarget(Target tr)
{
this.setName(tr.getName());
this.setTargetIdToBeUpdated(tr.getId());
}
public void deleteTarget(Target tr)
{
	targetService.removeTarget(tr.getId());
	this.setTargets(targetService.findAllTargets());
}


public int getTargetIdToBeUpdated() {
	return targetIdToBeUpdated;
}

public void setTargetIdToBeUpdated(int targetIdToBeUpdated) {
	this.targetIdToBeUpdated = targetIdToBeUpdated;
}

public List<Target> getTargets() {
	return targets;
}


public void setTargets(List<Target> targets) {
	this.targets = targets;
}


public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}




}

