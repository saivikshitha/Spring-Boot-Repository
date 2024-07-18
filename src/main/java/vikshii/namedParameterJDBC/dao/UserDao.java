package vikshii.namedParameterJDBC.dao;

import java.util.List;

import vikshii.namedParameterJDBC.model.User;

public interface UserDao {
	User create(User user);

	int update(User user, int id);

	int deleteById(int id);

	List<User> findAll();

	List<User> findUserById(int id);

	List<User> findByTitleContaining(String title);
}
