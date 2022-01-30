package peaksoft;

import peaksoft.model.User;
import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;
import peaksoft.util.HibernateUtil;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl user = new UserServiceImpl();
                user.createUsersTable();

//        user.saveUser("Ali", "Zhumaev", (byte) 24);
//        user.saveUser("Aika", "Jud", (byte) 24);
//        user.saveUser("Muhamed", "Alanovv", (byte) 17);
//        user.saveUser("Aiz", "Japarova", (byte) 20);

//        for (User u:user.getAllUsers()) {
//            System.out.println(u);
//        }
//
//        user.cleanUsersTable();
//        user.createUsersTable();
//        user.removeUserById(2);
        // user.saveUser("Aika", "Zamirova", (byte) 20);

//        user.removeUserById(1);
//        user.dropUsersTable();

//        HibernateUtil.shutDown();

    }
}
