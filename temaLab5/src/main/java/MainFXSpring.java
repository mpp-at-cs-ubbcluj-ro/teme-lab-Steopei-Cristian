import ctrl.ComputerRepairShopController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.ComputerRepairServices;
import services.ServicesException;

public class MainFXSpring extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RepairShopWindow.fxml"));
            var service = getService();
            var controller = new ComputerRepairShopController();
            controller.setService(service);
            loader.setControllerFactory(ctrl -> controller);
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Computer Repairs Shop");
            primaryStage.show();
        }catch(Exception e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setContentText("Error while starting app "+e);
            System.out.println(e);
            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    static ComputerRepairServices getService() throws ServicesException {
        // pentru configurare folosind XML
        ApplicationContext context = new ClassPathXmlApplicationContext("RepairShopConfig.xml");

        //pentru configurare folosind JavaConfig
        //ApplicationContext context=new AnnotationConfigApplicationContext(RepairShopConfig.class);
        return context.getBean(ComputerRepairServices.class);
    }
}
