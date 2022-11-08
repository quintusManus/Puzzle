
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
 *
 * @author sofie
 */
@Repository
public class RouteRepository {
    
    @Autowired
    NamedParameterJdbcTemplate template;
    
    public List<Route> getAllRoutes() {
        String query = "select id, name, difficulty, locationAndEnvironment, notes from route"; //Query we're typing on MySQL?
        return template.query(query, //Template runs the query and gives a list of results.
                (result, rowNum)
                -> new Route(result.getLong("id"), result.getString("name"), result.getString("difficulty"), result.getString("locationAndEnvironment"), result.getString("notes")));
    }
    
    public int addRoute(String name, String difficulty, String locationAndEnvironment, String notes) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("name", name);
        paramMap.put("difficulty", difficulty);
        paramMap.put("locationAndEnvironment", locationAndEnvironment);
        paramMap.put("notes", notes);
        String query = "INSERT INTO route(name, difficulty, locationAndEnvironment, notes) VALUES(:name, :difficulty, :locationAndEnvironment, :notes)";
        return template.update(query, paramMap);
    }
}
