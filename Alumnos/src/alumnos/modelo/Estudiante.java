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
package alumnos.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

public class Estudiante {

  protected StringProperty matricula;
  protected StringProperty nombre;
  protected StringProperty paterno;
  protected StringProperty materno;

  public Estudiante(String matricula, String nombre, String paterno, String materno) {
    this.matricula = new SimpleStringProperty(matricula);
    this.nombre = new SimpleStringProperty(nombre);
    this.paterno = new SimpleStringProperty(paterno);
    this.materno = new SimpleStringProperty(materno);
  }

  public String getMatricula() {
    return matricula.get();
  }

  public String getNombre() {
    return nombre.get();
  }

  public String getPaterno() {
    return paterno.get();
  }

  public String getMaterno() {
    return materno.get();
  }

  public void setMatricula(String matricula) {
    this.matricula = new SimpleStringProperty(matricula);
  }

  public void setNombre(String nombre) {
    this.nombre = new SimpleStringProperty(nombre);
  }

  public void setPaterno(String paterno) {
    this.paterno = new SimpleStringProperty(paterno);
  }

  public void setMaterno(String materno) {
    this.materno = new SimpleStringProperty(materno);
  }

  public StringProperty matriculaProperty() {
    return matricula;
  }

  public StringProperty nombreProperty() {
    return nombre;
  }

  public StringProperty paternoProperty() {
    return paterno;
  }

  public StringProperty maternoProperty() {
    return materno;
  }

  @Override
  public String toString() {
    return "Estudiante{" + "matricula=" + matricula + ", nombre=" + nombre + ", paterno=" + paterno + ", materno=" + materno + '}';
  }

}
