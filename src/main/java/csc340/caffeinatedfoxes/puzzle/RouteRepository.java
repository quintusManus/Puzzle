package csc340.caffeinatedfoxes.puzzle;


import csc340.caffeinatedfoxes.puzzle.ClimbingRoute;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 * @author smuska
 * This class includes methods for sending queries to the route database.
 * Last Updated: 11/8/2022
 */
@Repository
public class RouteRepository {
    
    @Autowired
    NamedParameterJdbcTemplate template;
    
    public List<ClimbingRoute> getRoutesByUserID(long userID) {
        String query = "select userID, routeID, name, difficulty, climbingStyle, locationAndEnvironment, notes from route where userID = " + userID;;
        return template.query(query,
                (result, rowNum)
                -> new ClimbingRoute(result.getLong("routeID"), userID, result.getString("name"), result.getString("difficulty"), result.getString("climbingStyle"), result.getString("locationAndEnvironment"), result.getString("notes")));
    }
    
    public ClimbingRoute getRouteById(long routeID) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("routeID", routeID);
        String query = "select * from route where routeID=:routeID";
        return template.queryForObject(query, namedParameters, BeanPropertyRowMapper.newInstance(ClimbingRoute.class));
    }
    
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
    
    public int addRouteAttempt(long routeID, Date date, int numOfFalls) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("routeID", routeID);
        paramMap.put("date", date);
        paramMap.put("numOfFalls", numOfFalls);
        String query = "INSERT INTO attempt(routeID, date, numOfFalls) VALUES(:routeID, :date, :numOfFalls)";
        return template.update(query, paramMap);
    }
    
    public List<Attempt> getAllRouteAttempts(long routeID) {
        String query = "select routeID, attemptID, date, numOfFalls from attempt where routeID = " + routeID;
        return template.query(query,
                (result, rowNum)
                -> new Attempt(result.getLong("routeID"), result.getLong("attemptID"), result.getDate("date"), result.getInt("numOfFalls")));
    }
    
    public int deleteRoute(long routeID) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("routeID", routeID);
        String query = "DELETE FROM route WHERE routeID = " + routeID;
        return template.update(query, namedParameters);
    }
    
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