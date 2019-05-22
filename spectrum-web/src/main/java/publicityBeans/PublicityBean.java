package publicityBeans;

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

@ManagedBean(name = "publicityBean")
@SessionScoped
public class PublicityBean implements Serializable
{ private static final long serialVersionUID = 1L; 

private User loggedin;

public List<Publicity> publicities = new ArrayList<>();
public List<Publicity> mine = new ArrayList<>();
public List<Target> targets = new ArrayList<>();
public User postedBy;
public int targetSelected;
private Part uploadedFile;
private String folder = "D:\\JEE\\workspace\\pi\\pi-web\\src\\main\\webapp\\resources\\files";

public String name;
public String description;

public Part getUploadedFile() {
return uploadedFile;
}
public void deletePublicity(Publicity p)
{
	publicityService.removePublicity(p.getId());
	this.setMine(publicityService.findPublicityByOwner(1));
}
public void setUploadedFile(Part uploadedFile) {
this.uploadedFile = uploadedFile;
}


public void saveFile() {
	
	Publicity publicity = new Publicity();
try (InputStream input = uploadedFile.getInputStream()) {
String fileName = uploadedFile.getSubmittedFileName();
System.out.println("uploadedFile.getSubmittedFileName : " + fileName ); 
System.out.println("uploadedFile.getName() : " + uploadedFile.getName() ); 

String[] t = fileName.split("\\\\");

        Files.copy(input, new File(folder, t[t.length-1]).toPath());

publicity.setPhoto(t[t.length-1]);

    }
    catch (IOException e) {
        e.printStackTrace();
    }
publicity.setName(name);
Set<Target> selected = new HashSet<>();
Target s = targetService.findTargetById(targetSelected);
selected.add(s);
//publicity.setPostedBy(postedBy);
//publicity.setTargetAudience(selected);
publicity.setDescription(description);
publicityService.addPublicity(publicity);
mine.add(publicity);
this.setMine(mine);
System.out.println("test avant return");

}


public User getLoggedin() {
	return loggedin;
}
public void setLoggedin(User loggedin) {
	this.loggedin = loggedin;
}
public User getPostedBy() {
	return postedBy;
}
public void setPostedBy(User postedBy) {
	this.postedBy = postedBy;
}
public String getFolder() {
	return folder;
}
public void setFolder(String folder) {
	this.folder = folder;
}
public PublicityService getPublicityService() {
	return publicityService;
}
public void setPublicityService(PublicityService publicityService) {
	this.publicityService = publicityService;
}
public TargetService getTargetService() {
	return targetService;
}
public void setTargetService(TargetService targetService) {
	this.targetService = targetService;
}


@EJB
PublicityService publicityService = new PublicityService(); 
@EJB
TargetService targetService = new TargetService(); 


@PostConstruct
public void init() {
	publicities = publicityService.findAllPublicities();
	mine = publicityService.findPublicityByOwner(1);
	targets = targetService.findAllTargets();
	
	
	
}



public List<Publicity> getPublicities() {
	return publicities;
}



public void setPublicities(List<Publicity> publicities) {
	this.publicities = publicities;
}

public List<Publicity> getMine() {
	return mine;
}
public void setMine(List<Publicity> mine) {
	this.mine = mine;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public List<Target> getTargets() {
	return targets;
}

public void setTargets(List<Target> targets) {
	this.targets = targets;
}

public int getTargetSelected() {
	return targetSelected;
}

public void setTargetSelected(int targetSelected) {
	this.targetSelected = targetSelected;
}




}

