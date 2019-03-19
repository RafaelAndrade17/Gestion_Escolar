/**
 * Nombre:              Gestión estudiantil JavaFX
 * Descripción:         Programa que permite llevar un control de estudiantes (Agregar, eliminar, modififcar, guardar, cargar)
 * Modificación:        22/02/2019
 *
 * @author Rafael Andrade Méndez
 * @vesion 2.0
 * @since 2019/02/22
 *
 */
package alumnos;

import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Alumnos extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("/alumnos/interfaz/FXMLAlumnos.fxml"));

    Scene scene = new Scene(root);

    stage.setScene(scene);
    stage.show();
    
  }

  public static void main(String[] args) {
    launch(args);
  }

}
