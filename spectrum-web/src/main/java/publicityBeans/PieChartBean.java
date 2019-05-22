package publicityBeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.chartistjsf.model.chart.PieChartModel;
import org.primefaces.event.ItemSelectEvent;

import entities.Reclamation;
import publicityRec.ReclamationService;

@ManagedBean
@SessionScoped
public class PieChartBean {
    private PieChartModel pieChartModel;
    private PieChartModel pieChartModelHR;
    private PieChartModel pieChartModelLogistic;
    private PieChartModel pieChartModelSchedule;
    
    HashMap<String,ArrayList<Integer>> map;
    @EJB
    ReclamationService reclamationService ;
    
    @PostConstruct
    public void init() {
        createPieChart();
    }
    
    public int getNumber(int rating, List<Integer>ratings) {
    	int nb=0;
    	for (Integer integer : ratings) {
			if (integer.intValue()==rating)
				nb++;
		}
    	return nb;
    }
    
    public void createPieChart() {
    	List<Integer> ratings = new ArrayList<Integer>();
    	List<Integer> ratings1 = new ArrayList<Integer>();
    	List<Integer> ratings2 = new ArrayList<Integer>();
    	List<Integer> ratings3 = new ArrayList<Integer>();
    	//Reclamation r = reclamationService.;
    	
    	map = new HashMap<>();
        ArrayList<Integer> rating = new ArrayList<>();
        for(int i =0; i<5;i++) {
            rating.add(i,0);
        }
        map.put("HR rating", rating);
        map.put("Work balance rating", rating);
        map.put("Work conditions rating", rating);
        map.put("Salary rating", rating);
       
        for (Reclamation reclamation : reclamationService.findReclamationByCompany(1)) {
			ratings.add((int) reclamation.getRatingLogistic());
		}
        for (Reclamation reclamation : reclamationService.findReclamationByCompany(1)) {
			ratings1.add((int) reclamation.getRatingRH());
		}
        for (Reclamation reclamation : reclamationService.findReclamationByCompany(1)) {
			ratings2.add((int) reclamation.getRatingSalary());
		}
        for (Reclamation reclamation : reclamationService.findReclamationByCompany(1)) {
			ratings3.add((int) reclamation.getRatingSchedule());
		}
        
         
        // createChat(map.get("Work balance rating"), schedule, "Work balance rating");
        //Query1="Select "
        pieChartModelHR = new PieChartModel();
        
        pieChartModelHR.addLabel("1 star");
        pieChartModelHR.addLabel("2 stars");
        pieChartModelHR.addLabel("3 stars");
        pieChartModelHR.addLabel("4 stars");
        pieChartModelHR.addLabel("5 stars");
        pieChartModelHR.set(getNumber(1, ratings));
        pieChartModelHR.set(getNumber(2, ratings));
        pieChartModelHR.set(getNumber(3, ratings));
        pieChartModelHR.set(getNumber(4, ratings));
        pieChartModelHR.set(getNumber(5, ratings));
        pieChartModelHR.setShowTooltip(true);
        
        pieChartModel = new PieChartModel();
        pieChartModel.addLabel("1 star");
        pieChartModel.addLabel("2 stars");
        pieChartModel.addLabel("3 stars");
        pieChartModel.addLabel("4 stars");
        pieChartModel.addLabel("5 stars");
        pieChartModel.set(getNumber(1, ratings1));
        pieChartModel.set(getNumber(2, ratings1));
        pieChartModel.set(getNumber(3, ratings1));
        pieChartModel.set(getNumber(4, ratings1));
        pieChartModel.set(getNumber(5, ratings1));
        pieChartModel.setShowTooltip(true);
        
        pieChartModelLogistic = new PieChartModel();
        pieChartModelLogistic.addLabel("1 star");
        pieChartModelLogistic.addLabel("2 stars");
        pieChartModelLogistic.addLabel("3 stars");
        pieChartModelLogistic.addLabel("4 stars");
        pieChartModelLogistic.addLabel("5 stars");
        pieChartModelLogistic.set(getNumber(1, ratings2));
        pieChartModelLogistic.set(getNumber(2, ratings2));
        pieChartModelLogistic.set(getNumber(3, ratings2));
        pieChartModelLogistic.set(getNumber(4, ratings2));
        pieChartModelLogistic.set(getNumber(5, ratings2));
        pieChartModelLogistic.setShowTooltip(true);
        
        pieChartModelSchedule = new PieChartModel();
        pieChartModelSchedule.addLabel("1 star");
        pieChartModelSchedule.addLabel("2 stars");
        pieChartModelSchedule.addLabel("3 stars");
        pieChartModelSchedule.addLabel("4 stars");
        pieChartModelSchedule.addLabel("5 stars");
        pieChartModelSchedule.set(getNumber(1, ratings3));
        pieChartModelSchedule.set(getNumber(2, ratings3));
        pieChartModelSchedule.set(getNumber(3, ratings3));
        pieChartModelSchedule.set(getNumber(4, ratings3));
        pieChartModelSchedule.set(getNumber(5, ratings3));
        pieChartModelSchedule.setShowTooltip(true);
    }
    public void pieItemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected", "Item Value: "
                + pieChartModel.getData().get(event.getItemIndex()));
        FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(), msg);
    }
    public PieChartModel getPieChartModel() {
    	 
        return pieChartModel;
    }
    public void setPieChartModel(PieChartModel pieChartModel) {
        this.pieChartModel = pieChartModel;
    }
    public void addMap(String key, float value)
    {
        if(value == 1)
           {
               map.get(key).set(0, map.get(key).get(0) + 1);
           }
           else if(value == 2)
           {
               map.get(key).set(1, map.get(key).get(1) + 1);
           }
           else if(value == 3)
           {
               map.get(key).set(2, map.get(key).get(2) + 1);
           }
           else if(value == 4)
           {
               map.get(key).set(3, map.get(key).get(3) + 1);
           }
           else if(value == 5)
           {
               map.get(key).set(4, map.get(key).get(4) + 1);
           }
    }
	public PieChartModel getPieChartModelHR() {
		return pieChartModelHR;
	}
	public void setPieChartModelHR(PieChartModel pieChartModelHR) {
		this.pieChartModelHR = pieChartModelHR;
	}
	public PieChartModel getPieChartModelLogistic() {
		return pieChartModelLogistic;
	}
	public void setPieChartModelLogistic(PieChartModel pieChartModelLogistic) {
		this.pieChartModelLogistic = pieChartModelLogistic;
	}
	public PieChartModel getPieChartModelSchedule() {
		return pieChartModelSchedule;
	}
	public void setPieChartModelSchedule(PieChartModel pieChartModelSchedule) {
		this.pieChartModelSchedule = pieChartModelSchedule;
	}
    
}