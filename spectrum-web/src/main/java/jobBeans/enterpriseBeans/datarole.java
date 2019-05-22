package managedBeans.enterpriseBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import entities.Role;

@ManagedBean
@SessionScoped
public class datarole {
public Role[] getRoles() {
	return Role.values();
}

}
