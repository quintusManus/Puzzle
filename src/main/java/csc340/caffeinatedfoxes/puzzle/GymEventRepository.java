package csc340.caffeinatedfoxes.puzzle;

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
 * @author sdbridges
 * This class includes methods for sending queries to the gymevent database.
 * Last Updated: 11/13/2022
 */
@Repository
public class GymEventRepository {
    
    @Autowired
    NamedParameterJdbcTemplate template;
    
    public List<GymEvent> getAllEvents() {
        String query = "select id, name, description from gymevent";
        return template.query(query,
                (result, rowNum)
                -> new GymEvent(result.getLong("id"), result.getString("name"), result.getString("description")));
    }
    
    public GymEvent getEventById(long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        String query = "select * from gymevent where id=:id";
        return template.queryForObject(query, namedParameters, BeanPropertyRowMapper.newInstance(GymEvent.class));
    }
    
    public int createEvent(String name, String description) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        paramMap.put("description", description);
        String query = "INSERT INTO gymevent(name, description) VALUES(:name, :description)";
        return template.update(query, paramMap);
    }
    
    public int deleteGymEvent(long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        String query = "DELETE FROM gymevent WHERE id = " + id;
        return template.update(query, namedParameters);
    }
}
