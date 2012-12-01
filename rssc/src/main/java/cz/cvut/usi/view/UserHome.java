package cz.cvut.usi.view;

import cz.cvut.usi.model.User;
import cz.cvut.usi.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
//@Component
public class UserHome {

    private User user;
//    @Autowired
    private UserServiceImpl userService;
    
    private String email;
    private String login;
    

    public UserHome() {
        user = loadUser();
    }
//
//    private void init() {
//        if (userService.list()==null || userService.list().isEmpty()) {
//            User sample = new User("tomas", "tomy@cvut.cz", "Tomas", "Mano");
//            userService.save(sample);
//        }
//    }

    private User loadUser() {
        return new User("tomas", "tomy@cvut.cz", "Tomas", "Mano");
    }

    public String getEmail() {
        email = user.getEmail();
        return email;
    }

    public String getLogin() {
        login = user.getLogin();
        return login;
    }
    
    public void setEmail(String email){
        user.setEmail(email);
//        userService.updateProperty(user.getId(), "email", email);
    }
    
    public void setLogin(String login){
        user.setLogin(login);
//        userService.updateProperty(user.getId(), "login", login);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserServiceImpl getUserService() {
        return userService;
    }

    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

}
