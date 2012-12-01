package cz.cvut.usi.view.security;

import org.springframework.stereotype.Component;

@Component
public class UserAction {

    public UserAction() {
    }

    public String action(String action) {
        return action;
    }
}
