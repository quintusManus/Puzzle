package csc340.caffeinatedfoxes.puzzle;


import csc340.caffeinatedfoxes.puzzle.Route;
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
 * This class includes methods for sending queries to the gymroute database.
 * Last Updated: 11/8/2022
 */
@Repository
public class GymRouteRepository {
    
    @Autowired
    NamedParameterJdbcTemplate template;
    
    public List<GymRoute> getAllRoutes() {
        String query = "select id, name, difficulty, climbingStyle, locationAndEnvironment, notes from gymroute";
        return template.query(query,
                (result, rowNum)
                -> new GymRoute(result.getLong("id"), result.getString("name"), result.getString("difficulty"), result.getString("climbingStyle"), result.getString("locationAndEnvironment"), result.getString("notes")));
    }
    
    public GymRoute getRouteById(long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        String query = "select * from gymroute where id=:id";
        return template.queryForObject(query, namedParameters, BeanPropertyRowMapper.newInstance(GymRoute.class));
    }
    
    public int createRoute(String name, String difficulty, String climbingStyle, String locationAndEnvironment, String notes) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("name", name);
        paramMap.put("difficulty", difficulty);
        paramMap.put("climbingStyle", climbingStyle);
        paramMap.put("locationAndEnvironment", locationAndEnvironment);
        paramMap.put("notes", notes);
        String query = "INSERT INTO gymroute(name, difficulty, climbingStyle, locationAndEnvironment, notes) VALUES(:name, :difficulty, :climbingStyle, :locationAndEnvironment, :notes)";
        return template.update(query, paramMap);
    }
    
    public int deleteGymRoute(long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        String query = "DELETE FROM gymroute WHERE id = " + id;
        return template.update(query, namedParameters);
    }
    
    public int editGymRoute(long id, String name, String difficulty, String climbingStyle, String locationAndEnvironment, String notes) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        String query = "UPDATE gymroute SET name = '" + name +
                       "', difficulty = '" + difficulty +
                       "', climbingStyle = '" + climbingStyle +
                       "', locationAndEnvironment = '" + locationAndEnvironment +
                       "', notes = '" + notes +
                       "' WHERE id = " + id;
        return template.update(query, namedParameters);
    }
}
