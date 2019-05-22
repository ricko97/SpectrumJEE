package publicityBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.chartistjsf.model.chart.AspectRatio;
import org.chartistjsf.model.chart.Axis;
import org.chartistjsf.model.chart.AxisType;
import org.chartistjsf.model.chart.ChartSeries;
import org.chartistjsf.model.chart.LineChartModel;
import org.chartistjsf.model.chart.LineChartSeries;
import org.primefaces.event.ItemSelectEvent;

import entities.Reclamation;
import publicityRec.ReclamationService;

@ManagedBean(name = "lineChartBean")
@SessionScoped
public class LineChartBean {
    private LineChartModel lineChartModel;
//    public LineChartBean() {
//        createLineModel();
//    }
    HashMap<String,ArrayList<Integer>> map;
    @EJB
    ReclamationService reclamationService ;
    
    @PostConstruct
    public void init() {
        createLineModel();
    }

    
    public int getNumber(int rating, List<Integer>ratings) {
    	int nb=0;
    	for (Integer integer : ratings) {
			if (integer.intValue()==rating)
				nb++;
		}
    	return nb;
    }
    public void createLineModel() {
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
        lineChartModel = new LineChartModel();
        lineChartModel.setAspectRatio(AspectRatio.GOLDEN_SECTION);
        lineChartModel.addLabel(" rating 1");
        lineChartModel.addLabel(" rating 2");
        lineChartModel.addLabel(" rating 3");
        lineChartModel.addLabel(" rating 4");
        lineChartModel.addLabel(" rating 5");
        //lineChartModel.addLabel("Saturday");
        //lineChartModel.addLabel("Sunday");
        LineChartSeries lineChartSeries1 = new LineChartSeries();
        lineChartSeries1.setName("Series 1");
        lineChartSeries1.set(getNumber(1, ratings));
        lineChartSeries1.set(getNumber(2, ratings));
        lineChartSeries1.set(getNumber(3, ratings));
        lineChartSeries1.set(getNumber(4, ratings));
        lineChartSeries1.set(getNumber(5, ratings));
       // lineChartSeries1.set(getNumber(1, ratings));
        //lineChartSeries1.set(getNumber(1, ratings));
        LineChartSeries lineChartSeries2 = new LineChartSeries();
        lineChartSeries2.setName("Series 2");
        lineChartSeries2.set(getNumber(1, ratings1));
        lineChartSeries2.set(getNumber(2, ratings1));
        lineChartSeries2.set(getNumber(3, ratings1));
        lineChartSeries2.set(getNumber(4, ratings1));
        lineChartSeries2.set(getNumber(5, ratings1));
        //lineChartSeries2.set(getNumber(1, ratings));
        //lineChartSeries2.set(getNumber(1, ratings));
       // lineChartSeries2.set();
        LineChartSeries lineChartSeries3 = new LineChartSeries();
        lineChartSeries3.setName("Series 2");
        lineChartSeries3.set(getNumber(1, ratings2));
        lineChartSeries3.set(getNumber(2, ratings2));
        lineChartSeries3.set(getNumber(3, ratings2));
        lineChartSeries3.set(getNumber(4, ratings2));
        lineChartSeries3.set(getNumber(5, ratings2));
        LineChartSeries lineChartSeries4 = new LineChartSeries();
        lineChartSeries4.setName("Series 2");
        lineChartSeries4.set(getNumber(1, ratings3));
        lineChartSeries4.set(getNumber(2, ratings3));
        lineChartSeries4.set(getNumber(3, ratings3));
        lineChartSeries4.set(getNumber(4, ratings3));
        lineChartSeries4.set(getNumber(5, ratings3));
        Axis xAxis = lineChartModel.getAxis(AxisType.X);
        lineChartModel.addSeries(lineChartSeries1);
        lineChartModel.addSeries(lineChartSeries2);
        lineChartModel.addSeries(lineChartSeries3);
        lineChartModel.addSeries(lineChartSeries4);
        lineChartModel.setAnimateAdvanced(true);
        lineChartModel.setShowTooltip(true);
    }
    public void itemSelect(ItemSelectEvent event) {
       FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected", "Item Value: "+ 
((ChartSeries) lineChartModel.getSeries().get(event.getSeriesIndex())).getData().get(event.getItemIndex())
                + ", Series name:" +
 ((ChartSeries) lineChartModel.getSeries().get(event.getSeriesIndex())).getName());
        FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(), msg);
    }
    public LineChartModel getLineModel() {
        return lineChartModel;
    }
    public void setLineModel(LineChartModel lineModel) {
        this.lineChartModel = lineModel;
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
}