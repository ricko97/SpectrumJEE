package services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class manel
 */
@Stateless
@LocalBean
public class manel implements manelRemote, manelLocal {

    /**
     * Default constructor. 
     */
    public manel() {
        // TODO Auto-generated constructor stub
    }

}
