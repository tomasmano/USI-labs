package cz.cvut.usi.view;

import cz.cvut.usi.service.UserServiceImpl;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
//@Component
public class LoginHome {

    private String login;
    private String password;
    
//    @Autowired
    private UserServiceImpl userService;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserServiceImpl getUserService() {
        return userService;
    }

    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    public String doLogin() {
        boolean result = userService.login(login, password);
        FacesMessage message = null;
        if (result) {
            return "success";
        } else {
            message = new FacesMessage("Invalid username or password");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        return null;
    }
    
     public String doLogout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "logout";
    }    
}
