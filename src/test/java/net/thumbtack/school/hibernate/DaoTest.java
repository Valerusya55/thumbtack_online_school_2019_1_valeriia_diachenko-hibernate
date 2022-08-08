package net.thumbtack.school.hibernate;

import net.thumbtack.school.practice.exeption.ServerException;
import net.thumbtack.school.practice.model.User;
import org.junit.jupiter.api.Test;

public class DaoTest extends BaseDaoTest{
    @Test
    public void testRegisterUser() throws ServerException {
        User user = new User("Светлана", "Петрова", "Витальевна",
                "Луговая", "7", null, "Svetlana", "123456");
        userDao.registerUser(user);
        User userFromDB = userDao.getUserById(user.getId());
        checkUserFields(user, userFromDB);
    }
    @Test
    public void testRegisterUser1() throws ServerException {
        User user = new User("Светлана", "Петрова", "Витальевна",
                "Луговая", "7", null, "Svetlana", "123456");
        userDao.registerUser(user);
        User userFromDB1 = userDao.getUserByLogin(user.getLogin(), user.getPassword());
        checkUserFields(user, userFromDB1);
    }
    @Test
    public void testRegisterUser2() throws ServerException {

    }
}
