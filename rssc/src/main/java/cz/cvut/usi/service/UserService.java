package cz.cvut.usi.service;

import cz.cvut.usi.model.User;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
public interface UserService extends UserDetailsService{

    public void save(User user);

    public List<User> list();

    public boolean updateProperty(Long id, String property, Object value);

    public boolean login(String login, String password) ;

    public User findByLogin(String login);

    @Override
    public UserDetails loadUserByUsername(String username);
}
