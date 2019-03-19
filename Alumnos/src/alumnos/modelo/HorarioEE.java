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
package alumnos.modelo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HorarioEE {

  protected StringProperty salon;
  protected StringProperty horaInicio;
  protected StringProperty horaFin;
  protected StringProperty dia;
  protected StringProperty nrc;

  public HorarioEE(String nrc, String dia, String horaInicio, String horaFin, String salon) {
    this.salon = new SimpleStringProperty(salon);
    this.horaInicio = new SimpleStringProperty(horaInicio);
    this.horaFin = new SimpleStringProperty(horaFin);
    this.dia = new SimpleStringProperty(dia);
    this.nrc = new SimpleStringProperty(nrc);
  }

  public String getSalon() {
    return salon.get();
  }

  public String getHoraInicio() {
    return horaInicio.get();
  }

  public String getHoraFin() {
    return horaFin.get();
  }

  public String getDia() {
    return dia.get();
  }

  public String getNRC() {
    return nrc.get();
  }

  public void setSalon(String salon) {
    this.salon = new SimpleStringProperty(salon);
  }

  public void setHoraInicio(String horaInicio) {
    this.horaInicio = new SimpleStringProperty(horaInicio);
  }

  public void setHoraFin(String horaFin) {
    this.horaFin = new SimpleStringProperty(horaFin);
  }

  public void setDia(String dia) {
    this.dia = new SimpleStringProperty(dia);
  }

  public void setNRC(String nrcH) {
    this.nrc = new SimpleStringProperty(nrcH);
  }

  public StringProperty salonProperty() {
    return salon;
  }

  public StringProperty horaInicioProperty() {
    return horaInicio;
  }

  public StringProperty horaFinProperty() {
    return horaFin;
  }

  public StringProperty diaProperty() {
    return dia;
  }

  public StringProperty nrcProperty() {
    return nrc;
  }
}
