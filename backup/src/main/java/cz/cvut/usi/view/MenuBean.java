package cz.cvut.usi.view;

import org.springframework.stereotype.Component;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
@Component
public class MenuBean {

    public String doResevation() {
        return "reservation";
    }

    public String doAccountDetails() {
        return "details";
    }
}
