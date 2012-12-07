package cz.cvut.usi.service;

import cz.cvut.usi.dao.SecurityRoleDAO;
import cz.cvut.usi.model.SecurityRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
@Service
@Transactional
public class SecurityRoleService {
    
    @Autowired 
    SecurityRoleDAO securityRoleDAO;

    public SecurityRole getAdminRole() {
        return securityRoleDAO.getAdminRole();
    }

    public SecurityRole getUserRole() {
        return securityRoleDAO.getUserRole();
    }
}
