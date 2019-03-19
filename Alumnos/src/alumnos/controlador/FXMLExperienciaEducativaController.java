/**
 * Nombre:              Gestión ExperienciaEducativa JavaFX
 * Descripción:         Programa que permite llevar un control de EE
 *                      (Agregar, eliminar, modififcar, guardar, cargar)
 * Modificación:        18/03/2019
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
import alumnos.modelo.ExperienciaEducativa;
import alumnos.modelo.ExperienciaEducativaSQL;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLExperienciaEducativaController {

  private ObservableList<ExperienciaEducativaSQL> listaExperienciaEducativa;
  private Conexion conexion;

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private Button buttonAgregarEE;

  @FXML
  private Button buttonEditarEE;

  @FXML
  private Button buttonEliminarEE;

  @FXML
  private Button buttonLimpiarEE;

  @FXML
  private Button buttonSalirEE;

  @FXML
  private TableColumn<ExperienciaEducativaSQL, String> tableColumnCreditos;

  @FXML
  private TableColumn<ExperienciaEducativaSQL, String> tableColumnHorasP;

  @FXML
  private TableColumn<ExperienciaEducativaSQL, String> tableColumnHorasT;

  @FXML
  private TableColumn<ExperienciaEducativaSQL, String> tableColumnNRC;

  @FXML
  private TableColumn<ExperienciaEducativaSQL, String> tableColumnNombreEE;

  @FXML
  private TableView<ExperienciaEducativaSQL> tableViewEE;

  @FXML
  private TextField tfCreditos;

  @FXML
  private TextField tfHorasP;

  @FXML
  private TextField tfHorasT;

  @FXML
  private TextField tfNRC;

  @FXML
  private TextField tfNombreEE;

  @FXML
  void initialize() {
    assert buttonAgregarEE != null : "fx:id=\"buttonAgregarEE\" was not injected: check your FXML file 'FXMLExperienciaEducativa.fxml'.";
    assert buttonEditarEE != null : "fx:id=\"buttonEditarEE\" was not injected: check your FXML file 'FXMLExperienciaEducativa.fxml'.";
    assert buttonEliminarEE != null : "fx:id=\"buttonEliminarEE\" was not injected: check your FXML file 'FXMLExperienciaEducativa.fxml'.";
    assert buttonLimpiarEE != null : "fx:id=\"buttonLimpiarEE\" was not injected: check your FXML file 'FXMLExperienciaEducativa.fxml'.";
    assert buttonSalirEE != null : "fx:id=\"buttonSalirEE\" was not injected: check your FXML file 'FXMLExperienciaEducativa.fxml'.";
    assert tableColumnCreditos != null : "fx:id=\"tableColumnCreditos\" was not injected: check your FXML file 'FXMLExperienciaEducativa.fxml'.";
    assert tableColumnHorasP != null : "fx:id=\"tableColumnHorasP\" was not injected: check your FXML file 'FXMLExperienciaEducativa.fxml'.";
    assert tableColumnHorasT != null : "fx:id=\"tableColumnHorasT\" was not injected: check your FXML file 'FXMLExperienciaEducativa.fxml'.";
    assert tableColumnNRC != null : "fx:id=\"tableColumnNRC\" was not injected: check your FXML file 'FXMLExperienciaEducativa.fxml'.";
    assert tableColumnNombreEE != null : "fx:id=\"tableColumnNombreEE\" was not injected: check your FXML file 'FXMLExperienciaEducativa.fxml'.";
    assert tableViewEE != null : "fx:id=\"tableViewEE\" was not injected: check your FXML file 'FXMLExperienciaEducativa.fxml'.";
    assert tfCreditos != null : "fx:id=\"tfCreditos\" was not injected: check your FXML file 'FXMLExperienciaEducativa.fxml'.";
    assert tfHorasP != null : "fx:id=\"tfHorasP\" was not injected: check your FXML file 'FXMLExperienciaEducativa.fxml'.";
    assert tfHorasT != null : "fx:id=\"tfHorasT\" was not injected: check your FXML file 'FXMLExperienciaEducativa.fxml'.";
    assert tfNRC != null : "fx:id=\"tfNRC\" was not injected: check your FXML file 'FXMLExperienciaEducativa.fxml'.";
    assert tfNombreEE != null : "fx:id=\"tfNombreEE\" was not injected: check your FXML file 'FXMLExperienciaEducativa.fxml'.";
    conexion = new Conexion();
    conexion.establecerConexion();
    //Inicializar lista
    listaExperienciaEducativa = FXCollections.observableArrayList();
    //Llenar lista
    ExperienciaEducativaSQL.llenarInformacion(conexion.getConnection(), listaExperienciaEducativa);
    //Enlazar
    tableViewEE.setItems(listaExperienciaEducativa);
    tableColumnNRC.setCellValueFactory(new PropertyValueFactory<ExperienciaEducativaSQL, String>("nrc"));
    tableColumnNombreEE.setCellValueFactory(new PropertyValueFactory<ExperienciaEducativaSQL, String>("nombreEE"));
    tableColumnCreditos.setCellValueFactory(new PropertyValueFactory<ExperienciaEducativaSQL, String>("creditos"));
    tableColumnHorasT.setCellValueFactory(new PropertyValueFactory<ExperienciaEducativaSQL, String>("horasTeoricas"));
    tableColumnHorasP.setCellValueFactory(new PropertyValueFactory<ExperienciaEducativaSQL, String>("horasPracticas"));
    gestionarEventosEE();
    conexion.cerrarConexion();
  }

  /**
   * Este método tiene como objetivo dar de alta una EE
   */
  public void agregarEE() {
    ExperienciaEducativaSQL experiencia_educativa = new ExperienciaEducativaSQL(tfNRC.getText(),
        tfNombreEE.getText(), Integer.valueOf(tfCreditos.getText()), Integer.valueOf(tfHorasT.getText()), Integer.valueOf(tfHorasP.getText()));
    conexion.establecerConexion();
    int resultado = experiencia_educativa.agregarExperienciaEducativa(conexion.getConnection());
    conexion.cerrarConexion();
    if (resultado == 1) {
      listaExperienciaEducativa.add(experiencia_educativa);
      Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
      mensaje.setContentText("Experiencia Educativa agregada con éxito");
      mensaje.show();
    }
  }

  /**
   * Este método tiene como objetivo modificar una EE
   */
  public void modificarEE() {
    ExperienciaEducativaSQL experiencia_educativa = new ExperienciaEducativaSQL(tfNRC.getText(),
        tfNombreEE.getText(), Integer.valueOf(tfCreditos.getText()), Integer.valueOf(tfHorasT.getText()), Integer.valueOf(tfHorasP.getText()));
    conexion.establecerConexion();
    int resultado = experiencia_educativa.modificarExperienciaEducativa(conexion.getConnection());
    conexion.cerrarConexion();
    if (resultado == 1) {
      listaExperienciaEducativa.set(tableViewEE.getSelectionModel().getSelectedIndex(), experiencia_educativa);
      Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
      mensaje.setContentText("Experiencia Educativa modificada con éxito");
      mensaje.show();
    }
  }

  /**
   * Este método da de baja una EE
   */
  public void eliminarEE() {
    conexion.establecerConexion();
    int resultado = tableViewEE.getSelectionModel().getSelectedItem().eliminarExperienciaEducativa(conexion.getConnection());
    conexion.cerrarConexion();
    if (resultado == 1) {
      listaExperienciaEducativa.remove(tableViewEE.getSelectionModel().getSelectedIndex());
      Alert mensaje = new Alert(AlertType.INFORMATION);
      mensaje.setContentText("Experiencia Educativa eliminada con éxito");
      mensaje.show();
    }
  }

  /**
   * Este método limpia los campos de textos de las EE
   */
  public void limpiarEE() {
    tfNRC.setText(null);
    tfNombreEE.setText(null);
    tfCreditos.setText(null);
    tfHorasT.setText(null);
    tfHorasT.setText(null);
    buttonAgregarEE.setDisable(false);
    buttonEliminarEE.setDisable(true);
    buttonEditarEE.setDisable(true);
  }

  /**
   * Este método tiene como objetivo salir de la aplicación
   */
  public void salirEE() {
    Alert mensaje = new Alert(AlertType.INFORMATION);
    mensaje.setTitle("SALIR");
    mensaje.setContentText("HASTA PRONTO");
    mensaje.setHeaderText(null);
    mensaje.showAndWait();
    System.exit(0);
  }

  /**
   * Este método permite gestionar eventos de la interfaz gráfica como seleccionar una fila,
   * deshabilitar un botón, etc.
   */
  public void gestionarEventosEE() {
    tableViewEE.getSelectionModel().selectedItemProperty().addListener(
        new ChangeListener<ExperienciaEducativa>() {
      @Override
      public void changed(ObservableValue<? extends ExperienciaEducativa> observable, ExperienciaEducativa valorAnterior, ExperienciaEducativa valorSeleccionado) {
        if (valorSeleccionado != null) {
          tfNRC.setText(valorSeleccionado.getNRC());
          tfNombreEE.setText(valorSeleccionado.getNombreEE());
          tfCreditos.setText(String.valueOf(valorSeleccionado.getCreditos()));
          tfHorasT.setText(String.valueOf(valorSeleccionado.getHorasTeoricas()));
          tfHorasP.setText(String.valueOf(valorSeleccionado.getHorasPracticas()));
          buttonAgregarEE.setDisable(true);
          buttonEliminarEE.setDisable(false);
          buttonEditarEE.setDisable(false);
        }
      }
    }
    );
  }

}
