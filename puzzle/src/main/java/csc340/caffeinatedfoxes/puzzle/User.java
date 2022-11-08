package csc340.caffeinatedfoxes.puzzle;


/**
 * @author bwoods
 * This class allows the construction of a User objects and includes methods
 * for getting and setting such an object's attributes.
 * Last Updated: 11/8/2022
 */
public class User {
    protected Long id;
    protected String email;
    protected String password;
    protected String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User(String email, String password, String type) {
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public void createPassword(){
        //put this.password into the database
    }
}
