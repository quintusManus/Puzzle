
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
}
