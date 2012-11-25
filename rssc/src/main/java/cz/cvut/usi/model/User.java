package cz.cvut.usi.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
@Entity
@Table(name = "users") //user je SQL klicove slovo, nejde ho pouzit po pojmenovani tabulky
public class User implements Serializable {

    @Id
    private Long id;
    
    private String login;
    private String email;
    private String firstName;
    private String lastName;

    public User() {
    }

    public User(String login, String email, String firstName, String lastName) {
        this.login = login;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", login=" + login + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + '}';
    }
    
    
}
