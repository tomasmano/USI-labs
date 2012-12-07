package cz.cvut.usi.handler;

import java.util.Set;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
public class WelcomeController {

//    @RequestMapping(value = "/admin/")
//    protected View welcome() {
//
//        Set<String> roles = AuthorityUtils
//                .authorityListToSet(SecurityContextHolder.getContext()
//                .getAuthentication().getAuthorities());
//        if (roles.contains("ROLE_ADMIN")) {
//            return new RedirectView("/admin/home.xhtml");
//        }
//        return new RedirectView("home.xhtml");
//    }
}
