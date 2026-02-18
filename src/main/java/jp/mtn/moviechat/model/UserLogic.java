package jp.mtn.moviechat.model;

import jp.mtn.moviechat.dao.UsersDAO;

public class UserLogic {
    public boolean registerUser(Register register) {
        UsersDAO dao = new UsersDAO();
        return dao.save(register);
    }

    public User authenticate(Login login) {
        UsersDAO dao = new UsersDAO();
        return dao.findByMail(login);
    }

    public boolean deleteUser(User user) {
        UsersDAO dao = new UsersDAO();
        return dao.deleteByUserId(user.getUserId());
    }
}
