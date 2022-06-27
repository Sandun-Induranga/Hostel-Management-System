import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lk.d24.hostel_system.entity.CustomEntity;
import lk.d24.hostel_system.entity.User;
import lk.d24.hostel_system.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("lk/d24/hostel_system/view/login-form.fxml"))));
        primaryStage.getIcons().add(new Image("lk/d24/hostel_system/view/asserts/icon-image.jpg"));
        primaryStage.setTitle("Hostel Management System");
        primaryStage.centerOnScreen();
        primaryStage.show();
//        Session session = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//        session.save(new User("user","user"));
//        // need to add JPA Facet to the Module
////        Query query = session.createQuery("SELECT r.resId, r.room, Room.type, r.date, r.keyMoney, r.keyMoneyStatus FROM Reservation r INNER JOIN Room ON r.room = Room.id");
//        transaction.commit();
//        session.close();
    }
}
