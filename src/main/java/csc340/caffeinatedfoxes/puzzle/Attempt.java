
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
    protected int attemptNum;
    protected Date date;
    protected int numOfFalls;

    public Attempt() {
    }
    
    public Attempt(int attemptNum) {
        this.attemptNum = attemptNum;
    }
    
    public Attempt(Date date, int numOfFalls) {
        this.date = date;
        this.numOfFalls = numOfFalls;
    }
    
    public Attempt(int attemptNum, Date date, int numOfFalls) {
        this.attemptNum = attemptNum;
        this.date = date;
        this.numOfFalls = numOfFalls;
    }

    public int getAttemptNum() {
        return attemptNum;
    }

    public void setAttemptNum(int attemptNum) {
        this.attemptNum = attemptNum;
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
