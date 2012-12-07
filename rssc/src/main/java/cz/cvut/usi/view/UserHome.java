package cz.cvut.usi.view;

import cz.cvut.usi.model.User;
import cz.cvut.usi.service.SecurityRoleService;
import cz.cvut.usi.service.UserService;
import cz.cvut.usi.service.UserServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
@Component
@Scope(value="request")
public class UserHome {

    private User user = new User();
   
    @Autowired
    @Qualifier("userDetailsService")
    private UserService userService;
    
    @Autowired
    private SecurityRoleService securityRoleService;

    public UserHome() {
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    public String saveUser(){
        userService.save(user);
        invalidate();
        return "saved";
    }

    private void invalidate(){
        user = new User();
    }

    public List<User> list() {
        return userService.list();
    }
}
