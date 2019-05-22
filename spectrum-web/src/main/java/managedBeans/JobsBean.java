package managedBeans;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
//import javax.faces.bean.ViewScoped;
import javax.servlet.http.Part;
import org.primefaces.model.UploadedFile;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import entities.JobOffer;
import jobservices.CandidacyService;
import jobservices.JobOfferService;

@ManagedBean
@SessionScoped
public class JobsBean {
	
	@EJB
	JobOfferService jobOfferService;
	@EJB
	CandidacyService candidacyService;
	
	@ManagedProperty("#{candidacyBean}")
    private CandidacyBean candidacyBean;
	
	private UploadedFile image;
	private Integer enterpriseId=1;
	private List<JobOffer>jobOffers;
	private JobOffer selectedJob;
	private JobOffer jobtToAdd;
	private String destination="C:\\Users\\Ricko\\Eclipse\\Serveur\\wildfly-9.0.1.Final\\standalone\\"
			+ "deployments\\spectrum-ear.ear\\spectrum-web.war\\resources\\images\\jobs";
	private String destination2 ="C:\\Users\\Ricko\\Documents\\GitHub\\SpectrumJEE\\spectrum-web\\"
			+ "src\\main\\webapp\\resources\\images\\jobs";
	private Part file;
	
	@PostConstruct
	public void init() {
		jobtToAdd = new JobOffer();
		jobOffers = jobOfferService.getjobOffersByEnt(enterpriseId);
	}

	public String editJob() throws Exception {
		if (file==null && selectedJob.getImage().isEmpty()) {
			selectedJob.setImage("joboffer.jpg");
		}else {
			selectedJob.setImage(file.getSubmittedFileName());
			try (InputStream input = file.getInputStream()) {
				 String fileName = file.getSubmittedFileName();
				         Files.copy(input, new File(destination, fileName).toPath());
				         Files.copy(input, new File(destination2, fileName).toPath());
				     }
				     catch (IOException e) {
				         Logger.getAnonymousLogger().info(e.getMessage());
				     }
		}
		jobOfferService.modifyJobOffer(selectedJob);
		file = null;
		return "jobs";
	}
	
	public String save() throws Exception {
		if (file==null) {
			jobtToAdd.setImage("joboffer.jpg");
		}else {
			jobtToAdd.setImage(file.getSubmittedFileName());
			try (InputStream input = file.getInputStream()) {
				 String fileName = file.getSubmittedFileName();
				         Files.copy(input, new File(destination, fileName).toPath());
				     }
				     catch (IOException e) {
				         Logger.getAnonymousLogger().info(e.getMessage());
				     }
		}
		jobtToAdd.setStart(new Date());
		jobOfferService.addJobOffer(enterpriseId, jobtToAdd);
		file = null;
		jobtToAdd = new JobOffer();
		return "jobs";
	}
	
	public String deleteJob() {
		System.out.println("--------1------------");
		jobOfferService.removeJobOffer(selectedJob.getId());
		System.out.println(selectedJob.getId()+" "+selectedJob.getTitle());
		System.out.println("--------2------------");
		return "jobs";
	}
	
	
	public String jobOffersPdf() throws IOException {
        Document document = new Document(PageSize.A4, 30, 30, 30, 30);
        try {
            PdfWriter.getInstance(document, new FileOutputStream("D:\\Job_offers_"+
        jobOfferService.getEntById(enterpriseId).getUser().getName()+".pdf"));
            document.open();
            document.addAuthor("Spectrum");
            document.addTitle("Job offers "+jobOfferService.getEntById(enterpriseId).getUser().getName());
            Paragraph paragraph = new Paragraph("Job offers \t\t\t\t\t "+jobOfferService.getEntById(enterpriseId).getUser().getName()+"\n\n\n");
            document.add(paragraph);
            paragraph.setExtraParagraphSpace(5);
            PdfPTable tableau = new PdfPTable(4);
            tableau.addCell(new Paragraph("Title"));
            tableau.addCell(new Paragraph("Start date"));
            tableau.addCell(new Paragraph("End date"));
            tableau.addCell(new Paragraph("Description"));
            for (JobOffer offer : jobOffers) {
            	tableau.addCell(new Paragraph(offer.getTitle()));
            	tableau.addCell(new Paragraph(offer.getStart().toString()));
            	tableau.addCell(new Paragraph(offer.getEnd().toString()));
            	tableau.addCell(new Paragraph(offer.getDescription()));
			}
            document.add(tableau);
            document.add(new Paragraph("\n\n\nSpectrum Team"));
            document.add(new Paragraph(new Date().toString()));
            
        } catch (DocumentException | FileNotFoundException e) {
            System.out.println(e);
        }
        document.close();
        System.out.println("Génération du document reussie !..");
        Runtime.getRuntime().exec(new String[] {"cmd.exe", "/C", "D:\\Job_offers_"+
                jobOfferService.getEntById(enterpriseId).getUser().getName()+".pdf"});
        return "jobs";
    }
	
	public String showcand() {
		candidacyBean.getCandidacies().clear();
		candidacyBean.setCandidacies(candidacyService.getCandidaciesByOffer(selectedJob.getId(),enterpriseId));
		return "candidacies";
	}
	
	public List<JobOffer> getJobOffers() {
		return jobOffers;
	}

	public void setJobOffers(List<JobOffer> jobOffers) {
		this.jobOffers = jobOffers;
	}

	public JobOffer getSelectedJob() {
		return selectedJob;
	}

	public void setSelectedJob(JobOffer selectedJob) {
		this.selectedJob = selectedJob;
	}

	public UploadedFile getImage() {
		return image;
	}

	public void setImage(UploadedFile image) {
		this.image = image;
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	public JobOffer getJobtToAdd() {
		return jobtToAdd;
	}

	public void setJobtToAdd(JobOffer jobtToAdd) {
		this.jobtToAdd = jobtToAdd;
	}

	public CandidacyBean getCandidacyBean() {
		return candidacyBean;
	}

	public void setCandidacyBean(CandidacyBean candidacyBean) {
		this.candidacyBean = candidacyBean;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
}
