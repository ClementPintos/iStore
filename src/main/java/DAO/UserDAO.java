package DAO;

import java.sql.SQLException;
import Model.User;

public interface UserDAO {

    User getUserById(int id) throws SQLException;

}
