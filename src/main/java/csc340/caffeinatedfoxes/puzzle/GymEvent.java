package csc340.caffeinatedfoxes.puzzle;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class allows the construction of a GymEvent object and provides methods
 * for getting and setting such an object's attributes. Includes a reference
 * to the SQL table gymevent.
 * @author sdbridges
 * Last Updated: 11/14/2022
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

    /**
     * This method fetches the event title.
     * @return the event title
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method allows the user to set the title of the event.
     * @param title the title of the event
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * This method fetches the event ID.
     * @return the event ID
     */
    public long getEventID() {
        return eventID;
    }

    /**
     * This method allows the user to set the event ID.
     * @param eventID the event ID
     */
    public void setEventID(long eventID) {
        this.eventID = eventID;
    }

    /**
     * This method fetches the ID of the user to whom the event belongs.
     * @return the user ID
     */
    public long getUserID() {
        return userID;
    }

    /**
     * This method allows the user to set the ID of the user to whom the event
     * belongs.
     * @param userID the user ID
     */
    public void setUserID(long userID) {
        this.userID = userID;
    }

    /**
     * This method fetches the event description.
     * @return the event description
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method allows the user to set the event description.
     * @param description the event description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
    * A no-args constructor for a GymEvent object.
    */
    public GymEvent() {
    }

    /**
     * Constructor for a GymEvent object.
     * @param eventID the event ID
     * @param userID the ID of the user to whom the event belongs
     * @param title the event title
     * @param description the event description
     */
    public GymEvent(long eventID, long userID, String title, String description) {
        this.eventID = eventID;
        this.userID = userID;
        this.title = title;
        this.description = description;
    }

    /**
     * Constructor for a GymEvent object. Excludes the eventID attribute.
     * @param userID the ID of the user to whom the event belongs
     * @param title the event title
     * @param description the event description
     */
    public GymEvent(long userID, String title, String description) {
        this.userID = userID;
        this.title = title;
        this.description = description;
    }
}
