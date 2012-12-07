package cz.cvut.usi.handler;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("---------------------------------");
        System.out.println(">>>>>>>>> CustomAuthenticationSuccessHandler invoked");
        System.out.println("---------------------------------");
        Collection<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        System.out.println(">>>>>>> "+roles.contains("ROLE_ADMIN"));
        if (roles.contains("ROLE_ADMIN")) {
            response.sendRedirect("/admim/test.xhtml");
            return;
        }
        response.sendRedirect("/home.xhtml");
    }
}
