package csc340.caffeinatedfoxes.puzzle;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * The database configuration class. Ensures connection to database.
 * @author smuska
 * Last Updated: 11/6/2022
 */
@Configuration
public class DatabaseConfigJDBC {
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/route");
        dataSource.setUsername( "root" );
        dataSource.setPassword( "" );
        return dataSource;
      }
   }