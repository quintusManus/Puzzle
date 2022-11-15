package csc340.caffeinatedfoxes.puzzle;

import csc340.caffeinatedfoxes.puzzle.ClimbingRoute;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 * This class includes methods for sending queries to the route table in the
 * route database.
 * @author smuska
 * Last Updated: 11/14/2022
 */
@Repository
public class RouteRepository {
    
    @Autowired
    NamedParameterJdbcTemplate template;
    
    /**
     * This method fetches all routes from the route table that belong to the
     * user with the corresponding user ID.
     * @param userID the user ID
     * @return a list of the user's routes
     */
    public List<ClimbingRoute> getRoutesByUserID(long userID) {
        String query = "select userID, routeID, name, difficulty, climbingStyle, locationAndEnvironment, notes from route where userID = " + userID;;
        return template.query(query,
                (result, rowNum)
                -> new ClimbingRoute(result.getLong("routeID"), userID, result.getString("name"), result.getString("difficulty"), result.getString("climbingStyle"), result.getString("locationAndEnvironment"), result.getString("notes")));
    }
    
    /**
     * This method fetches a route by its ID.
     * @param routeID the route ID
     * @return a Climbing Route object
     */
    public ClimbingRoute getRouteById(long routeID) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("routeID", routeID);
        String query = "select * from route where routeID=:routeID";
        return template.queryForObject(query, namedParameters, BeanPropertyRowMapper.newInstance(ClimbingRoute.class));
    }
    
    /**
     * This method allows the user to create a route.
     * @param userID the user ID
     * @param name the name of the route
     * @param difficulty the route difficulty
     * @param climbingStyle the route climbing style
     * @param locationAndEnvironment the route location and environment
     * @param notes notes on the route
     * @return an integer value
     */
    public int addRoute(long userID, String name, String difficulty, String climbingStyle, String locationAndEnvironment, String notes) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userID", userID);
        paramMap.put("name", name);
        paramMap.put("difficulty", difficulty);
        paramMap.put("climbingStyle", climbingStyle);
        paramMap.put("locationAndEnvironment", locationAndEnvironment);
        paramMap.put("notes", notes);
        String query = "INSERT INTO route(userID, name, difficulty, climbingStyle, locationAndEnvironment, notes) VALUES(:userID, :name, :difficulty, :climbingStyle, :locationAndEnvironment, :notes)";
        return template.update(query, paramMap);
    }
    
    /**
     * This method allows the climber user to add a route attempt.
     * @param routeID the ID of the route to which the attempt belongs
     * @param date the attempt date
     * @param numOfFalls the number of falls during this attempt
     * @return an integer value
     */
    public int addRouteAttempt(long routeID, Date date, int numOfFalls) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("routeID", routeID);
        paramMap.put("date", date);
        paramMap.put("numOfFalls", numOfFalls);
        String query = "INSERT INTO attempt(routeID, date, numOfFalls) VALUES(:routeID, :date, :numOfFalls)";
        return template.update(query, paramMap);
    }
    
    /**
     * This method fetches all of a route's attempts.
     * @param routeID the ID of the route to which the attempts belong
     * @return a list of the route attempts
     */
    public List<Attempt> getAllRouteAttempts(long routeID) {
        String query = "select routeID, attemptID, date, numOfFalls from attempt where routeID = " + routeID;
        return template.query(query,
                (result, rowNum)
                -> new Attempt(result.getLong("routeID"), result.getLong("attemptID"), result.getDate("date"), result.getInt("numOfFalls")));
    }
    
    /**
     * This method allows a user to delete a route.
     * @param routeID the route ID
     * @return an integer value
     */
    public int deleteRoute(long routeID) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("routeID", routeID);
        String query = "DELETE FROM route WHERE routeID = " + routeID;
        deleteRouteAttempts(routeID);
        return template.update(query, namedParameters);
    }
    
    /**
     * This method deletes all the associated attempts on a route when the
     * route is deleted.
     * @param routeID the route ID
     * @return an integer value
     */
    public int deleteRouteAttempts(long routeID) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("routeID", routeID);
        String query = "DELETE FROM attempt WHERE routeID = " + routeID;
        return template.update(query, namedParameters);
    }
    
    /**
     * This method allows the user to edit a route.
     * @param routeID the route ID
     * @param name the name of the route
     * @param difficulty the route difficulty
     * @param climbingStyle the route climbing style
     * @param locationAndEnvironment the route location and environment
     * @param notes notes on the route
     * @return an integer value
     */
    public int editRoute(long routeID, String name, String difficulty, String climbingStyle, String locationAndEnvironment, String notes) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("routeID", routeID);
        String query = "UPDATE route SET name = '" + name +
                       "', difficulty = '" + difficulty +
                       "', climbingStyle = '" + climbingStyle +
                       "', locationAndEnvironment = '" + locationAndEnvironment +
                       "', notes = '" + notes +
                       "' WHERE routeID = " + routeID;
        return template.update(query, namedParameters);
    }
}