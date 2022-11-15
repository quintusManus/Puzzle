
package csc340.caffeinatedfoxes.puzzle;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class allows the construction of a Route object and provides methods
 * for getting and setting such an object's attributes. The purpose of this
 * class is to record climbing route data. Includes a reference to the MySQL
 * table route.
 * @author smuska
 * Last Updated: 11/14/2022
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

    /**
     * This method fetches the route ID.
     * @return the route ID
     */
    public long getRouteID() {
        return routeID;
    }

    /**
     * This method allows the user to set the route ID.
     * @param routeID the route ID
     */
    public void setRouteID(long routeID) {
        this.routeID = routeID;
    }

    /**
     * This method fetches the user ID.
     * @return the user ID
     */
    public long getUserID() {
        return userID;
    }

    /**
     * This method allows the user to set the user ID.
     * @param userID the user ID
     */
    public void setUserID(long userID) {
        this.userID = userID;
    }

    /**
     * This method fetches the route name.
     * @return the route name
     */
    public String getName() {
        return name;
    }

    /**
     * This method allows the user to set the route name.
     * @param name the route name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method fetches the route difficulty.
     * @return the route difficulty
     */
    public String getDifficulty() {
        return difficulty;
    }

    /**
     * This method allows the user to set the route difficulty.
     * @param difficulty the route difficulty
     */
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * This method fetches the route climbing style.
     * @return the route climbing style
     */
    public String getClimbingStyle() {
        return climbingStyle;
    }

    /**
     * This method allows the user to set the route climbing style.
     * @param climbingStyle the route climbing style
     */
    public void setClimbingStyle(String climbingStyle) {
        this.climbingStyle = climbingStyle;
    }

    /**
     * This method fetches the route location and environment.
     * @return the route location and environment
     */
    public String getLocationAndEnvironment() {
        return locationAndEnvironment;
    }

    /**
     * This method allows the user to set the route location and environment.
     * @param locationAndEnvironment the route location and environment
     */
    public void setLocationAndEnvironment(String locationAndEnvironment) {
        this.locationAndEnvironment = locationAndEnvironment;
    }

    /**
     * This method fetches any notes on the route.
     * @return notes on the route
     */
    public String getNotes() {
        return notes;
    }

    /**
     * This method allows the user to set any notes on the route.
     * @param notes notes on the route
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * A no-args constructor for a Route object.
     */
    public ClimbingRoute() {
    }

    /**
     * Constructs a ClimbingRoute object with the userID attribute.
     * @param userID the user ID
     */
    public ClimbingRoute(long userID) {
        this.userID = userID;
    }

    /**
     * Constructs a ClimbingRoute object. Excludes the id attributes.
     * @param name the name of the route
     * @param difficulty the route difficulty
     * @param climbingStyle the route climbing style
     * @param locationAndEnvironment the route location and environment
     * @param notes notes on the route
     */
    public ClimbingRoute(String name, String difficulty, String climbingStyle, String locationAndEnvironment, String notes) {
        this.name = name;
        this.difficulty = difficulty;
        this.climbingStyle = climbingStyle;
        this.locationAndEnvironment = locationAndEnvironment;
        this.notes = notes;
    }

    /**
     * Constructs a ClimbingRoute object. Excludes the userID attribute.
     * @param routeID the route ID
     * @param name the name of the route
     * @param difficulty the route difficulty
     * @param climbingStyle the route climbing style
     * @param locationAndEnvironment the route location and environment
     * @param notes notes on the route
     */
    public ClimbingRoute(long routeID, String name, String difficulty, String climbingStyle, String locationAndEnvironment, String notes) {
        this.routeID = routeID;
        this.name = name;
        this.difficulty = difficulty;
        this.climbingStyle = climbingStyle;
        this.locationAndEnvironment = locationAndEnvironment;
        this.notes = notes;
    }
    
    /**
     * Constructs a ClimbingRoute object. Excludes the routeID attribute.
     * @param name the name of the route
     * @param userID the ID of the user to whom the route belongs
     * @param difficulty the route difficulty
     * @param climbingStyle the route climbing style
     * @param locationAndEnvironment the route location and environment
     * @param notes notes on the route
     */
    public ClimbingRoute(String name, long userID, String difficulty, String climbingStyle, String locationAndEnvironment, String notes) {
        this.name = name;
        this.userID = userID;
        this.difficulty = difficulty;
        this.climbingStyle = climbingStyle;
        this.locationAndEnvironment = locationAndEnvironment;
        this.notes = notes;
    }

    /**
     * Constructs a route object.
     * @param routeID the route ID
     * @param userID the ID of the user to whom the route belongs
     * @param name the name of the route
     * @param difficulty the route difficulty
     * @param climbingStyle the route climbing style
     * @param locationAndEnvironment the route location and environment
     * @param notes notes on the route
     */
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
