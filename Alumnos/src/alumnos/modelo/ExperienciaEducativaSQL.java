/**
 * Nombre:              Gestión de EE JavaFX
 * Descripción:         Programa que permite llevar un control de EE (Agregar, eliminar, modififcar, guardar, cargar)
 * Modificación:        18/03/2019
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
import javafx.collections.ObservableList;

public class ExperienciaEducativaSQL extends ExperienciaEducativa {

  public ExperienciaEducativaSQL(String nrc, String nombreEE, int creditos, int horasTeoricas, int horasPracticas) {
    super(nrc, nombreEE, creditos, horasTeoricas, horasPracticas);
  }

  /**
   * Este método permite dar de alta una EE a partir de sentencias SQL
   *
   * @param connection variable de tipo Connection
   * @return devuelve 1 si se efectuaron cambios o 0 si no efectuó ningún cambio
   */
  public int agregarExperienciaEducativa(Connection connection) {
    try {
      PreparedStatement instruccion = connection.prepareStatement("INSERT INTO experiencia_educativa(nrc, nombreEE, "
          + "creditos, horasTeoricas, horasPracticas) VALUES (?, ?, ?, ?, ?)"); //Evitar la inyección SQL y parametrizar
      instruccion.setString(1, nrc.get());//Sustituir el signo de interrogacion por lo que contiene la propiedad de matricula
      instruccion.setString(2, nombreEE.get());
      instruccion.setInt(3, creditos.get());
      instruccion.setInt(4, horasTeoricas.get());
      instruccion.setInt(5, horasPracticas.get());
      return instruccion.executeUpdate(); //Lo que devuelve es la cantidad de registros afectados
    } catch (SQLException ex) {
      Logger.getLogger(ExperienciaEducativa.class.getName()).log(Level.SEVERE, null, ex);
      return 0;
    }
  }

  /**
   * Este método permite dar de alta una EE a partir de sentencias SQL
   *
   * @param connection variable de tipo Connection
   * @return devuelve 1 si se efectuaron cambios o 0 si no efectuó ningún cambio
   */
  public int modificarExperienciaEducativa(Connection connection) {
    try {
      PreparedStatement instruccion = connection.prepareStatement("UPDATE experiencia_educativa SET nombreEE = ?, creditos = ?, horasTeoricas = ?, horasPracticas = ? WHERE nrc = ?");
      instruccion.setString(1, nombreEE.get());
      instruccion.setInt(2, creditos.get());
      instruccion.setInt(3, horasTeoricas.get());
      instruccion.setInt(4, horasPracticas.get());
      instruccion.setString(5, nrc.get());//Sustituir el signo de interrogacion por lo que contiene la propiedad de matricula
      return instruccion.executeUpdate(); //Lo que devuelve es la cantidad de registros afectados
    } catch (SQLException ex) {
      Logger.getLogger(ExperienciaEducativa.class.getName()).log(Level.SEVERE, null, ex);
      return 0;
    }
  }

  /**
   * Este método permite dar de alta una EE a partir de sentencias SQL
   *
   * @param connection variable de tipo Connection
   * @return devuelve 1 si se efectuaron cambios o 0 si no efectuó ningún cambio
   */
  public int eliminarExperienciaEducativa(Connection connection) {
    try {
      PreparedStatement instruccion = connection.prepareStatement("DELETE FROM experiencia_educativa WHERE nrc = ?");
      instruccion.setString(1, nrc.get());
      return instruccion.executeUpdate(); //Lo que devuelve es la cantidad de registros afectados
    } catch (SQLException ex) {
      Logger.getLogger(ExperienciaEducativa.class.getName()).log(Level.SEVERE, null, ex);
      return 0;
    }
  }

  /**
   * Este método permite recabar la información adherida en la BD a partir de sentencias SQL
   *
   * @param connection variable de tipo Connection
   * @param listaExperienciaEducativa devuelve 1 si se efectuaron cambios o 0 si no efectuó ningún
   * cambio
   */
  public static void llenarInformacion(Connection connection, ObservableList<ExperienciaEducativaSQL> listaExperienciaEducativa) {
    try {
      Statement statement = connection.createStatement();
      ResultSet resultado = statement.executeQuery("SELECT nrc, nombreEE, creditos, horasTeoricas, horasPracticas FROM experiencia_educativa");
      while (resultado.next()) {
        listaExperienciaEducativa.add(new ExperienciaEducativaSQL(
            resultado.getString("nrc"),
            resultado.getString("nombreEE"),
            resultado.getInt("creditos"),
            resultado.getInt("horasTeoricas"),
            resultado.getInt("horasPracticas")
        )
        );
      }
    } catch (SQLException ex) {
      Logger.getLogger(ExperienciaEducativa.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

}
