package publicityBeans;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;


import entities.Publicity;
@ManagedBean
@SessionScoped
public class UploadUtils   {

	private static final String upload = "C:\\Users\\asus\\Desktop\\pi feriel pub"; 
    final Date date = new Date();
    String year;
    String month;

    public static String uploadFile(Publicity pub, Part fileOrigin) {
       
       String fileName="";  
       File mainDirectory = new File(upload );
       
       File deposerdDirectory = new File(upload + "/" + pub.getPhoto());
       
       if (!mainDirectory.exists())
        	mainDirectory.mkdir();

        if (!deposerdDirectory.exists())
        	deposerdDirectory.mkdir();
       
        try (InputStream input = fileOrigin.getInputStream()) {
        	fileName = fileOrigin.getSubmittedFileName();
            Files.copy(input, new File(deposerdDirectory, fileName).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
		return fileName;
    }


    public static String getExtension(String filename) {

        String ext = filename.substring(filename.lastIndexOf(".") + 1);
        return ext;
    }
	
}
