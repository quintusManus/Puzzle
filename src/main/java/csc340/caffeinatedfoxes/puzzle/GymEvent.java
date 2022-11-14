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
    protected long id;
    protected String name;
    protected String description;

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

    /**
     * Constructs a GymEvent object. Includes all attributes.
     * @param id
     * @param name
     * @param description
     */
    public GymEvent(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /**
     * Constructs a GymEvent object. Excludes the id attribute.
     * @param name
     * @param description
     */
    public GymEvent(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "GymEvent{" + "id=" + id + ", name=" + name + ", description=" + description + '}';
    }
    
    
}
