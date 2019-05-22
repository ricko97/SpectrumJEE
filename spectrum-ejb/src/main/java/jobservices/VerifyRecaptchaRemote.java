package jobservices;

import javax.ejb.Remote;

@Remote
public interface VerifyRecaptchaRemote {
	public boolean verify(String gRecaptchaResponse);
}
