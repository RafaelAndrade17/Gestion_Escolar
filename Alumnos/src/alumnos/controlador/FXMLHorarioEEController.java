/**
 * Nombre:              Gestión de Horario JavaFX
 * Descripción:         Programa que permite llevar un control de Horario
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
import alumnos.modelo.ExperienciaEducativa;
import alumnos.modelo.ExperienciaEducativaSQL;
import alumnos.modelo.HorarioEE;
import alumnos.modelo.HorarioEESQL;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLHorarioEEController {

  private ObservableList<HorarioEESQL> horario;
  private ObservableList<ExperienciaEducativaSQL> listaExperienciaEducativa;
  private Conexion conexion;

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private Button buttonAgregarH;

  @FXML
  private Button buttonEliminarH;

  @FXML
  private Button buttonLimpiarH;

  @FXML
  private TableColumn<HorarioEESQL, String> tcDia;

  @FXML
  private TableColumn<HorarioEESQL, String> tcHoraFin;

  @FXML
  private TableColumn<HorarioEESQL, String> tcHoraInicio;

  @FXML
  private TableColumn<ExperienciaEducativaSQL, String> tcNRC;

  @FXML
  private TableColumn<HorarioEESQL, String> tcNRCHorario;

  @FXML
  private TableColumn<ExperienciaEducativaSQL, String> tcNombreEE;

  @FXML
  private TableColumn<HorarioEESQL, String> tcSalon;

  @FXML
  private TextField tfDia;

  @FXML
  private TextField tfHoraFin;

  @FXML
  private TextField tfHoraInicio;

  @FXML
  private TextField tfNRCHorario;

  @FXML
  private TextField tfSalon;

  @FXML
  private TableView<ExperienciaEducativaSQL> tvExperienciaEducativa;

  @FXML
  private TableView<HorarioEESQL> tvHorarioEE;

  @FXML
  void initialize() {
    assert buttonAgregarH != null : "fx:id=\"buttonAgregarH\" was not injected: check your FXML file 'FXMLHorarioEE.fxml'.";
    assert buttonEliminarH != null : "fx:id=\"buttonEliminarH\" was not injected: check your FXML file 'FXMLHorarioEE.fxml'.";
    assert buttonLimpiarH != null : "fx:id=\"buttonLimpiarH\" was not injected: check your FXML file 'FXMLHorarioEE.fxml'.";
    assert tcDia != null : "fx:id=\"tcDia\" was not injected: check your FXML file 'FXMLHorarioEE.fxml'.";
    assert tcHoraFin != null : "fx:id=\"tcHoraFin\" was not injected: check your FXML file 'FXMLHorarioEE.fxml'.";
    assert tcHoraInicio != null : "fx:id=\"tcHoraInicio\" was not injected: check your FXML file 'FXMLHorarioEE.fxml'.";
    assert tcNRC != null : "fx:id=\"tcNRC\" was not injected: check your FXML file 'FXMLHorarioEE.fxml'.";
    assert tcNRCHorario != null : "fx:id=\"tcNRCHorario\" was not injected: check your FXML file 'FXMLHorarioEE.fxml'.";
    assert tcNombreEE != null : "fx:id=\"tcNombreEE\" was not injected: check your FXML file 'FXMLHorarioEE.fxml'.";
    assert tcSalon != null : "fx:id=\"tcSalon\" was not injected: check your FXML file 'FXMLHorarioEE.fxml'.";
    assert tfDia != null : "fx:id=\"tfDia\" was not injected: check your FXML file 'FXMLHorarioEE.fxml'.";
    assert tfHoraFin != null : "fx:id=\"tfHoraFin\" was not injected: check your FXML file 'FXMLHorarioEE.fxml'.";
    assert tfHoraInicio != null : "fx:id=\"tfHoraInicio\" was not injected: check your FXML file 'FXMLHorarioEE.fxml'.";
    assert tfNRCHorario != null : "fx:id=\"tfNRCHorario\" was not injected: check your FXML file 'FXMLHorarioEE.fxml'.";
    assert tfSalon != null : "fx:id=\"tfSalon\" was not injected: check your FXML file 'FXMLHorarioEE.fxml'.";
    assert tvExperienciaEducativa != null : "fx:id=\"tvExperienciaEducativa\" was not injected: check your FXML file 'FXMLHorarioEE.fxml'.";
    assert tvHorarioEE != null : "fx:id=\"tvHorarioEE\" was not injected: check your FXML file 'FXMLHorarioEE.fxml'.";
    conexion = new Conexion();
    conexion.establecerConexion();
    //Inicializar lista
    horario = FXCollections.observableArrayList();
    listaExperienciaEducativa = FXCollections.observableArrayList();
    //Llenar lista
    ExperienciaEducativaSQL.llenarInformacion(conexion.getConnection(), listaExperienciaEducativa);
    HorarioEESQL.llenarInformacionHorario(conexion.getConnection(), horario);
    //Enlazar
    tvExperienciaEducativa.setItems(listaExperienciaEducativa);
    tcNRC.setCellValueFactory(new PropertyValueFactory<ExperienciaEducativaSQL, String>("nrc"));
    tcNombreEE.setCellValueFactory(new PropertyValueFactory<ExperienciaEducativaSQL, String>("nombreEE"));
    tvHorarioEE.setItems(horario);
    tcNRCHorario.setCellValueFactory(new PropertyValueFactory<HorarioEESQL, String>("nrc"));
    tcDia.setCellValueFactory(new PropertyValueFactory<HorarioEESQL, String>("dia"));
    tcHoraInicio.setCellValueFactory(new PropertyValueFactory<HorarioEESQL, String>("horaInicio"));
    tcHoraFin.setCellValueFactory(new PropertyValueFactory<HorarioEESQL, String>("horaFin"));
    tcSalon.setCellValueFactory(new PropertyValueFactory<HorarioEESQL, String>("salon"));
    gestionarEventosH();
    conexion.cerrarConexion();
  }

  /**
   * Este método tiene como objetivo dar de alta un horario
   */
  public void agregarH() {
    HorarioEESQL horarioEE = new HorarioEESQL(tfNRCHorario.getText(),
        tfDia.getText(), tfHoraInicio.getText(), tfHoraFin.getText(), tfSalon.getText());
    conexion.establecerConexion();
    int resultado = horarioEE.agregarHorario(conexion.getConnection());
    conexion.cerrarConexion();
    if (resultado == 1) {
      horario.add(horarioEE);
      Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
      mensaje.setContentText("Horario agregado con éxito");
      mensaje.show();
    }
  }

  /**
   * Este método tiene como objetivo dar de baja un horario
   */
  public void eliminarH() {
    conexion.establecerConexion();
    int resultado = tvHorarioEE.getSelectionModel().getSelectedItem().eliminarHorario(conexion.getConnection());
    conexion.cerrarConexion();
    if (resultado == 1) {
      horario.remove(tvHorarioEE.getSelectionModel().getSelectedIndex());
      Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
      mensaje.setContentText("Horario eliminado con éxito");
      mensaje.show();
    }
  }

  /**
   * Este método tiene como objetivo limpiar los campos de texto de un horario
   */
  public void limpiarH() {
    tfNRCHorario.setText(null);
    tfDia.setText(null);
    tfHoraInicio.setText(null);
    tfHoraFin.setText(null);
    tfSalon.setText(null);
    buttonAgregarH.setDisable(false);
    buttonEliminarH.setDisable(true);
  }

  /**
   * Este método permite gestionar eventos de la interfaz gráfica como seleccionar una fila,
   * deshabilitar un botón, etc.
   */
  public void gestionarEventosH() {
    tvHorarioEE.getSelectionModel().selectedItemProperty().addListener(
        new ChangeListener<HorarioEE>() {
      @Override
      public void changed(ObservableValue<? extends HorarioEE> observable, HorarioEE valorAnterior, HorarioEE valorSeleccionado) {
        if (valorSeleccionado != null) {
          tfNRCHorario.setText(valorSeleccionado.getNRC());
          tfDia.setText(valorSeleccionado.getDia());
          tfHoraInicio.setText(valorSeleccionado.getHoraInicio());
          tfHoraFin.setText(valorSeleccionado.getHoraFin());
          tfSalon.setText(valorSeleccionado.getSalon());
          buttonAgregarH.setDisable(true);
          buttonEliminarH.setDisable(false);
        }
      }
    }
    );
  }

}
