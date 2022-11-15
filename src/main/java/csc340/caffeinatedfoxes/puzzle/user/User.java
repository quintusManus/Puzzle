package csc340.caffeinatedfoxes.puzzle.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class allows the construction of a User object and provides methods
 * for getting(accessors) and setting(mutators) such an objects attributes. Includes a reference
 * to the SQL table users.
 * @author sdbridges
 * Last Updated: 11/10/2022
 */
@Entity  //Used to establush mapping between business logic of User class to users table in database
@Table(name = "users")
public class User {
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //
    private Long id;
     
    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "type", nullable = false, length = 20)
    private String type;

    @Column(nullable = false, length = 64)
    private String password;

    //================= GETTERS AND SETTERS ===============
    
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
    
    /**
    * No-args constructor for a User object.
    */
    public User() {
    }

    /**
     * Constructs a User object. Includes all attributes.
     * @param id
     * @param email
     * @param password
     * @param name
     * @param type 
     */
    public User(Long id, String email, String password, String name, String type) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.type = type;
    }
    
    /**
    * Constructs a User object. Includes all name, type, and email attributes.
    * @param name
    * @param type
    * @param email 
    */
    public User(String name, String type, String email) {
        this.email = email;
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", password=" + password + ", name=" + name + ", type=" + type + '}';
    }
}
