package csc340.caffeinatedfoxes.puzzle.user;

/**
 *
 * @author Stuart Bridges
 */
import javax.persistence.*;
 
@Entity
@Table(name = "users")
public class User {
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     
    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "type", nullable = false, length = 20)
    private String type;

    @Column(nullable = false, length = 64)
    private String password;
     
    //We let Hibernate generate the database schema automatically using create in the application.properties for initial schema, not nullable default value is true

    public User() {
    }

    public User(Long id, String email, String password, String name, String type) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.type = type;
    }
    
    public User(String name, String type, String email) {
        this.email = email;
        this.name = name;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", password=" + password + ", name=" + name + ", type=" + type + '}';
    }
     
    
}
