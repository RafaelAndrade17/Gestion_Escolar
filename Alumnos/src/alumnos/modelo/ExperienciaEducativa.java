/**
 * Nombre:              Gestión estudiantil JavaFX
 * Descripción:         Programa que permite llevar un control de Expe-
 *                      riencias Educativas(Agregar, eliminar, modififcar, guardar, cargar)
 * Modificación:        18/03/2019
 *
 * @author Rafael Andrade Méndez
 * @vesion 2.0
 * @since 2019/02/22
 *
 */
package alumnos.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ExperienciaEducativa {
  protected StringProperty nrc;
  protected StringProperty nombreEE;
  protected IntegerProperty creditos;
  protected IntegerProperty horasTeoricas;
  protected IntegerProperty horasPracticas;

  public ExperienciaEducativa(String nrc, String nombreEE, int creditos, int horasTeoricas, int horasPracticas) {
    this.nrc = new SimpleStringProperty(nrc);
    this.nombreEE = new SimpleStringProperty(nombreEE);
    this.creditos = new SimpleIntegerProperty(creditos);
    this.horasTeoricas = new SimpleIntegerProperty(horasTeoricas);
    this.horasPracticas = new SimpleIntegerProperty(horasPracticas);
  }

  public String getNRC() {
    return nrc.get();
  }

  public String getNombreEE() {
    return nombreEE.get();
  }

  public int getCreditos() {
    return creditos.get();
  }

  public int getHorasTeoricas() {
    return horasTeoricas.get();
  }
  
  public int getHorasPracticas() {
    return horasPracticas.get();
  }

  public void setNRC(String nrc) {
    this.nrc = new SimpleStringProperty(nrc);
  }

  public void setNombreEE(String nombreEE) {
    this.nombreEE = new SimpleStringProperty(nombreEE);
  }

  public void setCreditos(int creditos) {
    this.creditos = new SimpleIntegerProperty(creditos);
  }

  public void setHorasTeoricas(int horasTeoricas) {
    this.horasTeoricas = new SimpleIntegerProperty(horasTeoricas);
  }
  
  public void setHorasPracticas(int horasPracticas) {
    this.horasPracticas = new SimpleIntegerProperty(horasPracticas);
  }

  public StringProperty nrcProperty() {
    return nrc;
  }

  public StringProperty nombreEEProperty() {
    return nombreEE;
  }

  public IntegerProperty creditosProperty() {
    return creditos;
  }

   public IntegerProperty horasTeoricasProperty() {
    return horasTeoricas;
  }
   
  public IntegerProperty horasPracticasProperty() {
   return horasPracticas;
  }

  @Override
  public String toString() {
    return "ExperienciaEducativa{" + "nrc=" + nrc + ", nombreEE=" + nombreEE + ", creditos=" + creditos + ", horasTeoricas=" + horasTeoricas + ", horasPracticas=" + horasPracticas + '}';
  }
}
