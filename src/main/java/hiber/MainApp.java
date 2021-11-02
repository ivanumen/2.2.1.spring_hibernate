package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User ivan = new User("Ivan", "Umen", "ivam.umen@mail.ru");
      User stas = new User("Stas", "Kosyi", "kosi@mail.ru");
      User gyle = new User("Gyle", "Kosyi", "gkosi@mail.ru");
      User alex = new User("Alex", "Byrcov", "byric_syrik@mail.ru");

      Car car1 = new Car("Moskvich", 7);
      Car car2 = new Car("Lada", 578);
      Car car3 = new Car("Maz", 2107);
      Car car4 = new Car("Syzyki", 33);

      userService.add(ivan.setCar(car1).setUser(ivan));
      userService.add(stas.setCar(car2).setUser(stas));
      userService.add(gyle.setCar(car3).setUser(gyle));
      userService.add(alex.setCar(car4).setUser(alex));

      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
      }

      System.out.println(userService.getUserByCar("Lada", 7));

      try {
         User notFoundUser = userService.getUserByCar("Lamborgini", 1);
      } catch (NoResultException e) {
         System.out.println("User not found");
      }

      context.close();
   }
}
