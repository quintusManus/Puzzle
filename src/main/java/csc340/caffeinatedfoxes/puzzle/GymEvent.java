package csc340.caffeinatedfoxes.puzzle;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author sdbridges
 * This class allows the construction of a GymRoute object and provides methods
 * for getting and setting such an objects attributes. Includes a reference
 * to the SQL table gymevent.
 * Last Updated: 11/13/2022
 */
@Entity
@Table(name = "gymevent")
public class GymEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long eventID;
    protected long userID;
    protected String title;
    protected String description;

    //================= GETTERS AND SETTERS ===============

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public long getEventID() {
        return eventID;
    }

    public void setEventID(long eventID) {
        this.eventID = eventID;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
    * No-args constructor for a GymEvent object.
    */
    public GymEvent() {
    }

    public GymEvent(long eventID, long userID, String title, String description) {
        this.eventID = eventID;
        this.userID = userID;
        this.title = title;
        this.description = description;
    }

    public GymEvent(long userID, String title, String description) {
        this.userID = userID;
        this.title = title;
        this.description = description;
    }
    
    
}
