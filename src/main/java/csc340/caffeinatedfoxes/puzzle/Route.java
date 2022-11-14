
package csc340.caffeinatedfoxes.puzzle;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author smuska
 * This class allows the construction of a Route object and provides methods
 * for getting and setting such an objects attributes. Includes a reference
 * to the SQL table route.
 * Last Updated: 11/8/2022
 */
@Entity
@Table(name = "route")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    protected String name;
    protected String difficulty;
    protected String climbingStyle;
    protected String locationAndEnvironment;
    protected String notes;

    //================= GETTERS AND SETTERS ===============
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getClimbingStyle() {
        return climbingStyle;
    }

    public void setClimbingStyle(String climbingStyle) {
        this.climbingStyle = climbingStyle;
    }

    public String getLocationAndEnvironment() {
        return locationAndEnvironment;
    }

    public void setLocationAndEnvironment(String locationAndEnvironment) {
        this.locationAndEnvironment = locationAndEnvironment;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * No-args constructor for a Route object.
     */
    public Route() {
    }

    /**
     * Constructs a Route object. Excludes the id attribute.
     * @param name
     * @param difficulty
     * @param climbingStyle
     * @param locationAndEnvironment
     * @param notes 
     */
    public Route(String name, String difficulty, String climbingStyle, String locationAndEnvironment, String notes) {
        this.name = name;
        this.difficulty = difficulty;
        this.climbingStyle = climbingStyle;
        this.locationAndEnvironment = locationAndEnvironment;
        this.notes = notes;
    }

    /**
     * Constructs a Route object. Includes the id attribute.
     * @param id
     * @param name
     * @param difficulty
     * @param climbingStyle
     * @param locationAndEnvironment
     * @param notes 
     */
    public Route(long id, String name, String difficulty, String climbingStyle, String locationAndEnvironment, String notes) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.climbingStyle = climbingStyle;
        this.locationAndEnvironment = locationAndEnvironment;
        this.notes = notes;
    }
    
        /**
     * Constructs a Route object. Excludes the climbing style attribute amd locationAndEnvironment attribute
     * @param id
     * @param name
     * @param difficulty
     * @param notes 
     */
    public Route(long id, String name, String difficulty, String notes) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Route{}";
    }
    
    
}
