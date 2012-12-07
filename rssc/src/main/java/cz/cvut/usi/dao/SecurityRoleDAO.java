package cz.cvut.usi.dao;

import cz.cvut.usi.model.SecurityRole;
import cz.cvut.usi.model.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class SecurityRoleDAO {

    @PersistenceContext
    private EntityManager em;
    
    public SecurityRole getAdminRole(){
        List<SecurityRole> result = null;
        result = em.createQuery("SELECT s from SecurityRole s WHERE s.roleName=:param").setParameter("param", "ROLE_ADMIN").getResultList();
        return result.isEmpty() ? null : (SecurityRole) result.get(0);
    }
    
    public SecurityRole getUserRole(){
        List<SecurityRole> result = null;
        result = em.createQuery("SELECT s from SecurityRole s WHERE s.roleName=:param").setParameter("param", "ROLE_USER").getResultList();
        return result.isEmpty() ? null : (SecurityRole) result.get(0);
    }
  
    public void saveUserRole(User user){
        SecurityRole role = new SecurityRole();
        role.setRoleName("ROLE_USER");
        role.setUser(user);
        em.persist(role);
    }
}
