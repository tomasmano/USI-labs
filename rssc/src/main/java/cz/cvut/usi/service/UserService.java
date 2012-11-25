package cz.cvut.usi.service;

import cz.cvut.usi.dao.UserDAO;
import cz.cvut.usi.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
@Service
public class UserService {
    @Autowired
    UserDAO userDAO;
    
    public void save(User user){
        userDAO.save(user);
    }
    
    public List<User> list(){
        return userDAO.list();
    }
    
    public boolean updateProperty(Long id, String property, Object value){
        return userDAO.updateSimplePropertyByValue(id, property, value, User.class);
    }
}
