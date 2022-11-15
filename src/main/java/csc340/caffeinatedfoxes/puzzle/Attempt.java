
package csc340.caffeinatedfoxes.puzzle;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A class for constructing Attempt objects that will hold the data of climber
 * attempts on climbing routes. Includes a reference to the MySQL table attempt.
 * @author smuska
 * Last Updated: 11/14/2022
 */
@Entity
@Table(name = "attempt")
public class Attempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long attemptID;
    protected long routeID;
    protected Date date;
    protected int numOfFalls;

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
     * This method fetches the attempt ID.
     * @return the attempt ID
     */
    public long getAttemptID() {
        return attemptID;
    }

    /**
     * This method allows the user to set the attempt ID.
     * @param attemptID the attempt ID
     */
    public void setAttemptID(long attemptID) {
        this.attemptID = attemptID;
    }

    /**
     * This method fetches the date on which the attempt was performed.
     * @return the attempt date
     */
    public Date getDate() {
        return date;
    }

    /**
     * This method allows the user to set the date on which the attempt was
     * performed.
     * @param date the attempt date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * This method fetches the number of times a climber fell during a route
     * attempt.
     * @return the number of falls
     */
    public int getNumOfFalls() {
        return numOfFalls;
    }

    /**
     * This method allows the user to set the number of times a climber fell
     * during a route attempt.
     * @param numOfFalls the number of falls
     */
    public void setNumOfFalls(int numOfFalls) {
        this.numOfFalls = numOfFalls;
    }
    
    /**
     * A no-args constructor for the Attempt class.
     */
    public Attempt() {
    }
    
    /**
     * Constructs an Attempt object with the attemptID attribute
     * @param attemptID the attempt ID
     */
    public Attempt(int attemptID) {
        this.attemptID = attemptID;
    }
    
    /**
     * Constructs an Attempt object with the date and numOfFalls attributes.
     * @param date the attempt date
     * @param numOfFalls the number of falls
     */
    public Attempt(Date date, int numOfFalls) {
        this.date = date;
        this.numOfFalls = numOfFalls;
    }
    
    /**
     * Constructs an Attempt object. Excludes the routeID attribute.
     * @param attemptID the attempt ID
     * @param date the attempt date
     * @param numOfFalls the number of falls
     */
    public Attempt(long attemptID, Date date, int numOfFalls) {
        this.attemptID = attemptID;
        this.date = date;
        this.numOfFalls = numOfFalls;
    }

    /**
     * Constructs an attempt object.
     * @param routeID the route ID
     * @param attemptID the attempt ID
     * @param date the attempt date
     * @param numOfFalls the number of falls
     */
    public Attempt(long routeID, long attemptID, Date date, int numOfFalls) {
        this.routeID = routeID;
        this.attemptID = attemptID;
        this.date = date;
        this.numOfFalls = numOfFalls;
    }
}
