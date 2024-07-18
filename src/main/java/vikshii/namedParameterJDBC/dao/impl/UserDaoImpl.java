package vikshii.namedParameterJDBC.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import vikshii.namedParameterJDBC.dao.UserDao;
import vikshii.namedParameterJDBC.model.User;

@Repository
public class UserDaoImpl implements UserDao {
	private final String INSERT_SQL = "INSERT INTO USERS(name, address, email)  values(:name,:address,:email)";
	private final String FETCH_SQL = "select record_id, name, address, email from users";
	private final String FETCH_SQL_BY_ID = "select record_id, name, address, email from users where record_id = :id";
	private final String UPDATE_SQL = "UPDATE USERS SET name =:name, address =:address, email=:email where record_id = :id ";
	private final String DELETE_SQL_BY_ID = "DELETE FROM USERS WHERE record_id = :id";

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public User create(final User user) {
		KeyHolder holder = new GeneratedKeyHolder();

		SqlParameterSource parameters = new MapSqlParameterSource().addValue("name", user.getName())
				.addValue("address", user.getAddress()).addValue("email", user.getEmail());
		namedParameterJdbcTemplate.update(INSERT_SQL, parameters, holder);
		user.setId(holder.getKey().intValue());
		return user;
	}

	public List<User> findAll() {
		return namedParameterJdbcTemplate.query(FETCH_SQL, new UserMapper());
	}

	public List<User> findUserById(int id) {

		SqlParameterSource namedParameters = new MapSqlParameterSource("id", Integer.valueOf(id));

		return namedParameterJdbcTemplate.query(FETCH_SQL_BY_ID, namedParameters, new UserMapper());

	}

	@Override
	public List<User> findByTitleContaining(String title) {
		String q = "SELECT * from tutorials WHERE title ILIKE '%" + title + "%'";

		return namedParameterJdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(User.class));
	}

	@Override
	public int update(User user, int id) {

		List<User> userList = findUserById(id);
		SqlParameterSource parameters = new MapSqlParameterSource().addValue("name", user.getName())
				.addValue("address", user.getAddress()).addValue("email", user.getEmail()).addValue("id", user.getId());
		return namedParameterJdbcTemplate.update(UPDATE_SQL, parameters);

	}

	@Override
	public int deleteById(int id) {
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", Integer.valueOf(id));
		return namedParameterJdbcTemplate.update(DELETE_SQL_BY_ID, namedParameters);

	}

}

class UserMapper implements RowMapper {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("record_id"));
		user.setName(rs.getString("name"));
		user.setAddress(rs.getString("address"));
		user.setEmail(rs.getString("email"));
		return user;
	}
}