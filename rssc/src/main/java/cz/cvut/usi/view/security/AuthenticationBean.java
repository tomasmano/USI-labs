package cz.cvut.usi.view.security;

import cz.cvut.usi.model.User;
import cz.cvut.usi.service.UserService;
import java.io.IOException;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private String firstName;
    private String lastName;
    private boolean loggedIn = false;
    private boolean isAdmin = false;

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
        if (user.getId() != null) {
            this.email = userService.findById(user.getId()).getEmail();
        }
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
        this.login = login;
    }

    public String getFirstName() {
        if (user.getId() != null) {
            this.firstName = userService.findById(user.getId()).getFirstName();
        }
        return firstName;
    }

    public void setFirstName(String firstName) {
        userService.updateProperty(user.getId(), "firstname", firstName);
        this.firstName = firstName;
    }

    public String getLastName() {
        if (user.getId() != null) {
            this.lastName = userService.findById(user.getId()).getLastName();
        }
        return lastName;
    }

    public void setLastName(String lastName) {
        userService.updateProperty(user.getId(), "lastname", lastName);
        this.lastName = lastName;
    }

    /**
     * @return @throws IOException
     * @throws ServletException
     */
    public String doLogin() throws IOException, ServletException {

        try {


            Authentication request = new UsernamePasswordAuthenticationToken(
                    this.login, this.password);
            Authentication result = authenticationManager.authenticate(request);

            SecurityContextHolder.getContext().setAuthentication(result);

            Collection<String> roles = AuthorityUtils.authorityListToSet(result.getAuthorities());
            if (roles.contains("ROLE_ADMIN")) {
                isAdmin = true;
            }

        } catch (AuthenticationException e) {

            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, e
                    .getMessage(), e.getMessage()));
            return null;
        }

        user = userService.findByLogin(login);
        loggedIn = true;

//        ExternalContext context = FacesContext.getCurrentInstance()
//                .getExternalContext();
//
//        HttpServletRequest request = (HttpServletRequest) context.getRequest();
//        HttpServletResponse response = (HttpServletResponse) context.getResponse();
//
//        SavedRequest savedRequest =
//                new HttpSessionRequestCache().getRequest(request, response);
//
//        context.redirect(savedRequest.getRedirectUrl());
//
//        FacesContext.getCurrentInstance().responseComplete();

        return "success";
    }

    public String logout() throws IOException {
        this.login = "";
        this.password = "";
        this.loggedIn = false;
        this.isAdmin = false;
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
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

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
}
