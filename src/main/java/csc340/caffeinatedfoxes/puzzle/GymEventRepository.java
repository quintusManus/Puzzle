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
    
    public List<GymEvent> getAllEventsByUserID(long userID) {
        String query = "select eventID, userID, title, description from gymevent WHERE userID = " + userID;
        return template.query(query,
                (result, rowNum)
                -> new GymEvent(result.getLong("eventID"), result.getLong("userID"), result.getString("title"), result.getString("description")));
    }
    
    public GymEvent getEventById(long eventID) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("eventID", eventID);
        String query = "select * from gymevent where eventID=:eventID";
        return template.queryForObject(query, namedParameters, BeanPropertyRowMapper.newInstance(GymEvent.class));
    }
    
    public int createEvent(long userID, String title, String description) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userID", userID);
        paramMap.put("title", title);
        paramMap.put("description", description);
        String query = "INSERT INTO gymevent(userID, title, description) VALUES(:userID, :title, :description)";
        return template.update(query, paramMap);
    }
    
    public int deleteGymEvent(long eventID) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("eventID", eventID);
        String query = "DELETE FROM gymevent WHERE eventID = " + eventID;
        return template.update(query, namedParameters);
    }
}
