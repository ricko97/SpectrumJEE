package bean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.validation.ValidationException;

@FacesValidator("bean.EmailValidator")
public class EmailValidator implements Validator{
 private static final String EMAIL_PATTERN ="-+@-+\\--+";
 @Override
 public void  validate(FacesContext context, UIComponent component,Object value) {
	 
	 Pattern pattern =Pattern.compile(EMAIL_PATTERN);
	 Matcher matcher =pattern.matcher(value.toString());
	 if(!matcher.matches()) {
		 
		 FacesMessage msg= new FacesMessage("Invalid E-mail format");
		 msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	 }
	 
 }
	
}
