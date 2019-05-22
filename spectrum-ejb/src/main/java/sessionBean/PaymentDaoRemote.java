package sessionBean;

import java.util.List;

import javax.ejb.Remote;

import entities.Payment;

@Remote
public interface PaymentDaoRemote {
	public void addPayment(Payment e);
	public void updatePayment(Payment e);
	public Payment findPayment(int id);
	public void deletePayment(Payment e);
	public List<Payment> addPayment();

}
