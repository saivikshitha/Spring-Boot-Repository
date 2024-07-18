package vikshii.namedParameterJDBC.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import vikshii.namedParameterJDBC.dao.UserDao;
import vikshii.namedParameterJDBC.model.User;

@Service
public class UserService {

	@Autowired
	UserDao userDao;
	private static int num = 1;

	public List<User> getUserDetails() {

		List<User> userDetails = userDao.findAll();
		userDetails.forEach(action -> {
			action.setTodayDate(new Date());
		});
		return userDetails;
	}

	public List<User> findAll(String title) {

		List<User> list = new ArrayList<User>();
		list = userDao.findAll();
		list.stream().forEach(action -> {
			action.setName(action.getName() + "" + num);
			num++;
		});
		num = 1;
		return list;

	}

	public List<User> findUserById(int id) {
		List<User> list = new ArrayList<User>();
		list = userDao.findUserById(id);
		list.stream().forEach(action -> {
			action.setVillage("Hyd");
			action.setTodayDate(new Date());
		});
		return list;

	}

	public User createUser(User user) {
		return userDao.create(new User(user.getName(), user.getAddress(), user.getEmail()));

	}

	public int updateUser(int id, @RequestBody User user) {
		return userDao.update(user, id);

	}

	public int deleteUser(int id) {
		return userDao.deleteById(id);

	}
}
