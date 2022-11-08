
package csc340.caffeinatedfoxes.puzzle;

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
@Table(name = "route")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    protected String name;
    protected String difficulty;
    protected String locationAndEnvironment;
    protected String notes;

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

    public Route() {
    }

    public Route(String name, String difficulty, String locationAndEnvironment, String notes) {
        this.name = name;
        this.difficulty = difficulty;
        this.locationAndEnvironment = locationAndEnvironment;
        this.notes = notes;
    }

    public Route(long id, String name, String difficulty, String locationAndEnvironment, String notes) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.locationAndEnvironment = locationAndEnvironment;
        this.notes = notes;
    }
}
