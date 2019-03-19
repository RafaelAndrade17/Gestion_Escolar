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
package alumnos.controlador;

import alumnos.modelo.Conexion;
import alumnos.modelo.Estudiante;
import alumnos.modelo.EstudianteSQL;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FXMLAlumnosController implements Initializable {

  private ObservableList<EstudianteSQL> listaEstudiante;
  private Conexion conexion;

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private Button buttonAgregar;

  @FXML
  private Button buttonEditar;

  @FXML
  private Button buttonEliminar;

  @FXML
  private Button buttonGuardar;

  @FXML
  private Button buttonLimpiar;

  @FXML
  private Button buttonSalir;

  @FXML
  private Button buttonControlEE;

  @FXML
  private Button buttonControlHorario;

  @FXML
  private TableColumn<EstudianteSQL, String> tablecolumnMaterno;

  @FXML
  private TableColumn<EstudianteSQL, String> tablecolumnMatricula;

  @FXML
  private TableColumn<EstudianteSQL, String> tablecolumnNombre;

  @FXML
  private TableColumn<EstudianteSQL, String> tablecolumnPaterno;

  @FXML
  private TableView<EstudianteSQL> tableviewTabla;

  @FXML
  private TextField textfieldMaterno;

  @FXML
  private TextField textfieldMatricula;

  @FXML
  private TextField textfieldNombre;

  @FXML
  private TextField textfieldPaterno;

  @FXML
  private MenuBar menuBar;

  @FXML
  public void initialize(URL url, ResourceBundle rb) {
    assert buttonAgregar != null : "fx:id=\"buttonAgregar\" was not injected: check your FXML file 'FXMLAlumnos.fxml'.";
    assert buttonEditar != null : "fx:id=\"buttonEditar\" was not injected: check your FXML file 'FXMLAlumnos.fxml'.";
    assert buttonEliminar != null : "fx:id=\"buttonEliminar\" was not injected: check your FXML file 'FXMLAlumnos.fxml'.";
    assert buttonGuardar != null : "fx:id=\"buttonGuardar\" was not injected: check your FXML file 'FXMLAlumnos.fxml'.";
    assert buttonLimpiar != null : "fx:id=\"buttonLimpiar\" was not injected: check your FXML file 'FXMLAlumnos.fxml'.";
    assert buttonControlEE != null : "fx:id=\"buttonControlEE\" was not injected: check your FXML file 'FXMLAlumnos.fxml'.";
    assert buttonControlHorario != null : "fx:id=\"buttonControlHorario\" was not injected: check your FXML file 'FXMLAlumnos.fxml'.";
    assert buttonSalir != null : "fx:id=\"buttonSalir\" was not injected: check your FXML file 'FXMLAlumnos.fxml'.";
    assert tablecolumnMaterno != null : "fx:id=\"tablecolumnMaterno\" was not injected: check your FXML file 'FXMLAlumnos.fxml'.";
    assert tablecolumnMatricula != null : "fx:id=\"tablecolumnMatricula\" was not injected: check your FXML file 'FXMLAlumnos.fxml'.";
    assert tablecolumnNombre != null : "fx:id=\"tablecolumnNombre\" was not injected: check your FXML file 'FXMLAlumnos.fxml'.";
    assert tablecolumnPaterno != null : "fx:id=\"tablecolumnPaterno\" was not injected: check your FXML file 'FXMLAlumnos.fxml'.";
    assert tableviewTabla != null : "fx:id=\"tableviewTabla\" was not injected: check your FXML file 'FXMLAlumnos.fxml'.";
    assert textfieldMaterno != null : "fx:id=\"textfieldMaterno\" was not injected: check your FXML file 'FXMLAlumnos.fxml'.";
    assert textfieldMatricula != null : "fx:id=\"textfieldMatricula\" was not injected: check your FXML file 'FXMLAlumnos.fxml'.";
    assert textfieldNombre != null : "fx:id=\"textfieldNombre\" was not injected: check your FXML file 'FXMLAlumnos.fxml'.";
    assert textfieldPaterno != null : "fx:id=\"textfieldPaterno\" was not injected: check your FXML file 'FXMLAlumnos.fxml'.";
    assert menuBar != null : "fx:id=\"menuBar\" was not injected: check your FXML file 'FXMLAlumnos.fxml'.";
    conexion = new Conexion();
    conexion.establecerConexion();
    //Inicializar lista
    listaEstudiante = FXCollections.observableArrayList();
    //Llenar lista
    EstudianteSQL.llenarInformacion(conexion.getConnection(), listaEstudiante);
    //Enlazar
    tableviewTabla.setItems(listaEstudiante);
    tablecolumnMatricula.setCellValueFactory(new PropertyValueFactory<EstudianteSQL, String>("matricula"));
    tablecolumnNombre.setCellValueFactory(new PropertyValueFactory<EstudianteSQL, String>("nombre"));
    tablecolumnPaterno.setCellValueFactory(new PropertyValueFactory<EstudianteSQL, String>("paterno"));
    tablecolumnMaterno.setCellValueFactory(new PropertyValueFactory<EstudianteSQL, String>("materno"));
    gestionarEventos();
    conexion.cerrarConexion();
  }

  /**
   * Este método permite gestionar eventos de la interfaz gráfica como seleccionar una fila,
   * deshabilitar un botón, etc.
   */
  public void gestionarEventos() {
    tableviewTabla.getSelectionModel().selectedItemProperty().addListener(
        new ChangeListener<Estudiante>() {
      @Override
      public void changed(ObservableValue<? extends Estudiante> observable, Estudiante valorAnterior, Estudiante valorSeleccionado) {
        if (valorSeleccionado != null) {
          textfieldMatricula.setText(valorSeleccionado.getMatricula());
          textfieldNombre.setText(valorSeleccionado.getNombre());
          textfieldPaterno.setText(valorSeleccionado.getPaterno());
          textfieldMaterno.setText(valorSeleccionado.getMaterno());
          buttonAgregar.setDisable(true);
          buttonEliminar.setDisable(false);
          buttonEditar.setDisable(false);
        }
      }
    }
    );

  }

  /**
   * Este método permite dejar en blanco todos los campos de texto de la interfaz
   */
  public void limpiar() {
    textfieldMatricula.setText(null);
    textfieldNombre.setText(null);
    textfieldPaterno.setText(null);
    textfieldMaterno.setText(null);
    buttonAgregar.setDisable(false);
    buttonEliminar.setDisable(true);
    buttonEditar.setDisable(true);
  }

  /**
   * Este método permite mandar un mensaje y salir de la aplicación
   */
  public void salir() {
    Alert mensaje = new Alert(AlertType.INFORMATION);
    mensaje.setTitle("SALIR");
    mensaje.setContentText("HASTA PRONTO");
    mensaje.setHeaderText(null);
    mensaje.showAndWait();
    System.exit(0);
  }

  /**
   * Este método tiene como objetivo dar de alta a un estudiante
   */
  public void agregar() {
    EstudianteSQL estudiante = new EstudianteSQL(textfieldMatricula.getText(),
        textfieldNombre.getText(), textfieldPaterno.getText(), textfieldMaterno.getText());
    conexion.establecerConexion();
    int resultado = estudiante.agregarEstudiante(conexion.getConnection());
    conexion.cerrarConexion();
    if (resultado == 1) {
      listaEstudiante.add(estudiante);
      Alert mensaje = new Alert(AlertType.INFORMATION);
      mensaje.setContentText("Estudiante agregado con éxito");
      mensaje.show();
    }
  }

  /**
   * Este método permite modificar la información de un estudiante
   */
  public void modificar() {
    EstudianteSQL estudiante = new EstudianteSQL(textfieldMatricula.getText(),
        textfieldNombre.getText(), textfieldPaterno.getText(), textfieldMaterno.getText());
    conexion.establecerConexion();
    int resultado = estudiante.modificarEstudiante(conexion.getConnection());
    conexion.cerrarConexion();
    if (resultado == 1) {
      listaEstudiante.set(tableviewTabla.getSelectionModel().getSelectedIndex(), estudiante);
      Alert mensaje = new Alert(AlertType.INFORMATION);
      mensaje.setContentText("Estudiante modificado con éxito");
      mensaje.show();
    }
  }

  /**
   * Este método da de baja o elimina a un estudiante previamente registrado en el sistema
   */
  public void eliminar() {
    conexion.establecerConexion();
    int resultado = tableviewTabla.getSelectionModel().getSelectedItem().eliminarEstudiante(conexion.getConnection());
    conexion.cerrarConexion();
    if (resultado == 1) {
      listaEstudiante.remove(tableviewTabla.getSelectionModel().getSelectedIndex());
      Alert mensaje = new Alert(AlertType.INFORMATION);
      mensaje.setContentText("Estudiante eliminado con éxito");
      mensaje.show();
    }
  }

  /**
   * Este metodo tiene como objetivo llamar al Stage de Control de EE
   *
   * @throws IOException
   */
  public void llamarStage2() throws IOException {
    Stage stage = new Stage();
    Parent root2 = FXMLLoader.load(getClass().getResource("/alumnos/interfaz/FXMLExperienciaEducativa.fxml"));
    Scene scene2 = new Scene(root2);
    stage.setScene(scene2);
    stage.show();
  }

  /**
   * Este metodo tiene como objetivo llamar al Stage de Control de Horario
   *
   * @throws IOException
   */
  public void llamarStage3() throws IOException {
    Stage stage = new Stage();
    Parent root3 = FXMLLoader.load(getClass().getResource("/alumnos/interfaz/FXMLHorarioEE.fxml"));
    Scene scene3 = new Scene(root3);
    stage.setScene(scene3);
    stage.show();

  }

}
