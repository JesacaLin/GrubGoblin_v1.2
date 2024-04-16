package org.JesacaLin;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Application {
    public static BasicDataSource basicDataSource;
    public static void main(String[] args) {
        /**
         * Application is the class that launches Grub Goblin v3 by creating
         * the objects needed to interact with the user and database.
         */
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl("jdbc:postgresql://localhost:5432/GrubGoblin");
        basicDataSource.setUsername("postgres");
        basicDataSource.setPassword("postgres1");
        //Controller expects the DAOs it needs to be "injected" in the constructor.
        //Add new DAO here
        JdbcTemplate jdbcTemplate = new JdbcTemplate(basicDataSource);
        SqlRowSet result = jdbcTemplate.queryForRowSet("select * from place");
        while (result.next()) {
            System.out.println(result.getString("place_name"));
        }

    }
}