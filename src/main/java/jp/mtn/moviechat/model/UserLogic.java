package jp.mtn.moviechat.model;

import jp.mtn.moviechat.dao.UsersDAO;

public class UserLogic {
    public boolean register(Register register) {
        UsersDAO dao = new UsersDAO();
        return dao.create(register);
    }

    public User login(Login login) {
        UsersDAO dao = new UsersDAO();
        return dao.findByLogin(login);
    }

    public boolean delete(User user) {
        UsersDAO dao = new UsersDAO();
        return dao.deleteByUserId(user.getUserId());
    }
}
