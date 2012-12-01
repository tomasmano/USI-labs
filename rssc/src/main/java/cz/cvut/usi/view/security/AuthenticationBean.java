package cz.cvut.usi.view.security;

import cz.cvut.usi.model.User;
import cz.cvut.usi.service.UserService;
import cz.cvut.usi.service.UserServiceImpl;
import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationBean {

    @Autowired
    @Qualifier("authenticationManager")
    protected AuthenticationManager authenticationManager;
    
    @Autowired
    @Qualifier("userDetailsService")
    private UserService userService;
    
    User user = new User();

    private String password;
    private String email;
    private String login;

    public AuthenticationBean() {
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail() {
        this.email = user.getEmail();
        return email;
    }

    public void setEmail(String email) {
        userService.updateProperty(user.getId(), "email", email);
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
//        userService.updateProperty(user.getId(), "login", login);
        this.login = login;
    }

    /**
     * @return @throws IOException
     * @throws ServletException
     */
    public String doLogin() throws IOException, ServletException {

        try {


            Authentication request = new UsernamePasswordAuthenticationToken(
                    this.login, this.password);
//            System.out.println(">>>>>>>>>>>>>"+login+password);
//            System.out.println(">>>>>>>>>>>>>"+request);
            Authentication result = authenticationManager.authenticate(request);

            SecurityContextHolder.getContext().setAuthentication(result);

        } catch (AuthenticationException e) {

            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, e
                    .getMessage(), e.getMessage()));
            return null;
        }

        user = userService.findByLogin(login);

        ExternalContext context = FacesContext.getCurrentInstance()
                .getExternalContext();

        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();

        SavedRequest savedRequest =
                new HttpSessionRequestCache().getRequest(request, response);

        context.redirect(savedRequest.getRedirectUrl());

        FacesContext.getCurrentInstance().responseComplete();

        return null;
    }

    public String logout() throws IOException {
        this.login = "";
        this.password = "";
        ExternalContext context = FacesContext.getCurrentInstance()
                .getExternalContext();
        context.redirect(context.getRequestContextPath()
                + "/j_spring_security_logout");
        FacesContext.getCurrentInstance().responseComplete();
        return null;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    
}
