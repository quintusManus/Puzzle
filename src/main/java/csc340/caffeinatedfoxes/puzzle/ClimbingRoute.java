
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
public class ClimbingRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long routeID;
    protected long userID;
    protected String name;
    protected String difficulty;
    protected String climbingStyle;
    protected String locationAndEnvironment;
    protected String notes;

    public long getRouteID() {
        return routeID;
    }

    public void setRouteID(long routeID) {
        this.routeID = routeID;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
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
    public ClimbingRoute() {
    }

    public ClimbingRoute(long userID) {
        this.userID = userID;
    }

    /**
     * Constructs a Route object. Excludes the id attributes.
     * @param name
     * @param difficulty
     * @param climbingStyle
     * @param locationAndEnvironment
     * @param notes 
     */
    public ClimbingRoute(String name, String difficulty, String climbingStyle, String locationAndEnvironment, String notes) {
        this.name = name;
        this.difficulty = difficulty;
        this.climbingStyle = climbingStyle;
        this.locationAndEnvironment = locationAndEnvironment;
        this.notes = notes;
    }

    public ClimbingRoute(long routeID, String name, String difficulty, String climbingStyle, String locationAndEnvironment, String notes) {
        this.routeID = routeID;
        this.name = name;
        this.difficulty = difficulty;
        this.climbingStyle = climbingStyle;
        this.locationAndEnvironment = locationAndEnvironment;
        this.notes = notes;
    }
    
    public ClimbingRoute(String name, long userID, String difficulty, String climbingStyle, String locationAndEnvironment, String notes) {
        this.name = name;
        this.userID = userID;
        this.difficulty = difficulty;
        this.climbingStyle = climbingStyle;
        this.locationAndEnvironment = locationAndEnvironment;
        this.notes = notes;
    }

    public ClimbingRoute(long routeID, long userID, String name, String difficulty, String climbingStyle, String locationAndEnvironment, String notes) {
        this.routeID = routeID;
        this.userID = userID;
        this.name = name;
        this.difficulty = difficulty;
        this.climbingStyle = climbingStyle;
        this.locationAndEnvironment = locationAndEnvironment;
        this.notes = notes;
    }    
}
