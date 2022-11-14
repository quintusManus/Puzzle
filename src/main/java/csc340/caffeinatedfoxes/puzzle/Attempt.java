
package csc340.caffeinatedfoxes.puzzle;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author sofie
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

    public Attempt() {
    }
    
    public Attempt(int attemptID) {
        this.attemptID = attemptID;
    }
    
    public Attempt(Date date, int numOfFalls) {
        this.date = date;
        this.numOfFalls = numOfFalls;
    }
    
    public Attempt(long attemptID, Date date, int numOfFalls) {
        this.attemptID = attemptID;
        this.date = date;
        this.numOfFalls = numOfFalls;
    }

    public Attempt(long routeID, long attemptID, Date date, int numOfFalls) {
        this.routeID = routeID;
        this.attemptID = attemptID;
        this.date = date;
        this.numOfFalls = numOfFalls;
    }

    public long getRouteID() {
        return routeID;
    }

    public void setRouteID(long routeID) {
        this.routeID = routeID;
    }

    public long getAttemptID() {
        return attemptID;
    }

    public void setAttemptID(long attemptID) {
        this.attemptID = attemptID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNumOfFalls() {
        return numOfFalls;
    }

    public void setNumOfFalls(int numOfFalls) {
        this.numOfFalls = numOfFalls;
    }
    
}
