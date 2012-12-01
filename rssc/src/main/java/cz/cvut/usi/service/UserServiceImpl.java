package cz.cvut.usi.service;

import cz.cvut.usi.dao.UserDAO;
import cz.cvut.usi.model.SecurityRole;
import cz.cvut.usi.model.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
@Transactional
@Service("userDetailsService")
public class UserServiceImpl implements UserService{

    @Autowired
    UserDAO userDAO;

    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    @Override
    public List<User> list() {
        return userDAO.list();
    }

    @Override
    public boolean updateProperty(Long id, String property, Object value) {
        System.out.println(">>>>>>>>> updateProperty called..  "+id+" "+property+" "+value);
        return userDAO.updateSimplePropertyByValue(id, property, value, User.class);
    }

    @Override
    public User findById(Long id) {
        return userDAO.findById(id);
    }

    @Override
    public User findByLogin(String login) {
        return userDAO.findByLogin(login);
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException, DataAccessException {

        cz.cvut.usi.model.User user = userDAO.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username.");
        }

        @SuppressWarnings("unused")
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (SecurityRole role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(username, user.getPassword(),
                user.isActive(), user.isActive(), user.isActive(),
                user.isActive(), authorities);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(userDetails);
        return userDetails;
    }
}
