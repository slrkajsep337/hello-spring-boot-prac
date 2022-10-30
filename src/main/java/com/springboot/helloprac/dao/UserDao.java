package com.springboot.helloprac.dao;


import com.springboot.helloprac.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserDao {

    private final DataSource ds;
    private final JdbcTemplate jt;

    public UserDao(DataSource ds, JdbcTemplate jt) {
        this.ds = ds;
        this.jt = jt;
    }

//    public void add(User user) {
//        this.jt.update("insert into users(id, name, password) value (?, ?, ?)",
//                user.getId(), user.getName(), user.getPassword());
//    }

    public int add(User user) {
        return this.jt.update("insert into users(id, user, password) value (?, ?, ?)",
                user.getId(), user.getName(), user.getPassword());
    }



//    public void deleteAll() {
//        this.jt.update("delete from users");
//    }

    public int deleteAll() {
        return this.jt.update("delete from users");
    }


    public User findById(String id) {
        String sql = "select * from users where id=?";
        RowMapper<User> rowMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User(rs.getString("id"),rs.getString("name"), rs.getString("password"));
                return user;
            }
        };
        return this.jt.queryForObject(sql, rowMapper, id);
    }


}
