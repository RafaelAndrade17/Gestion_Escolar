/**
 * Nombre:              Gestión estudiantil JavaFX
 * Descripción:         Programa que permite llevar un control de estudiantes (Agregar, eliminar, modififcar, guardar, cargar)
 * Modificación:        09/03/2019
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

public class EstudianteSQL extends Estudiante{

  public EstudianteSQL(String matricula, String nombre, String paterno, String materno) {
    super(matricula, nombre, paterno, materno);
  }
  /**
   * Este método permite dar de alta a un estudiante a partir de sentencias SQL
   *
   * @param connection variable de tipo Connection
   * @return devuelve 1 si se efectuaron cambios o 0 si no efectuó ningún cambio
   */
  public int agregarEstudiante(Connection connection) {
    try {
      PreparedStatement instruccion = connection.prepareStatement("INSERT INTO estudiante(matricula, nombre, "
          + "paterno, materno) VALUES (?, ?, ?, ?)"); //Evitar la inyección SQL y parametrizar
      instruccion.setString(1, matricula.get());//Sustituir el signo de interrogacion por lo que contiene la propiedad de matricula
      instruccion.setString(2, nombre.get());
      instruccion.setString(3, paterno.get());
      instruccion.setString(4, materno.get());
      return instruccion.executeUpdate(); //Lo que devuelve es la cantidad de registros afectados
    } catch (SQLException ex) {
      Logger.getLogger(Estudiante.class.getName()).log(Level.SEVERE, null, ex);
      return 0;

    }
  }

  /**
   * Este método permite modificar la información de un estudiante a partir de sentencias SQL
   *
   * @param connection variable de tipo Connection
   * @return devuelve 1 si se efectuaron cambios o 0 si no efectuó ningún cambio
   */
  public int modificarEstudiante(Connection connection) {
    try {
      PreparedStatement instruccion = connection.prepareStatement("UPDATE estudiante SET nombre = ?, paterno = ?, materno = ? WHERE matricula = ?");
      instruccion.setString(1, nombre.get()); //Sustituir el signo de interrogacion por lo que contiene la propiedad de matricula
      instruccion.setString(2, paterno.get());
      instruccion.setString(3, materno.get());
      instruccion.setString(4, matricula.get());
      return instruccion.executeUpdate(); //Lo que devuelve es la cantidad de registros afectados
    } catch (SQLException ex) {
      Logger.getLogger(Estudiante.class.getName()).log(Level.SEVERE, null, ex);
      return 0;
    }
  }

  /**
   * Este método permite dar de baja o eliminar a un estudiante a partir de una sentencia SQL
   *
   * @param connection variable de tipo Connection
   * @return devuelve 1 si se efectuaron cambios o 0 si no efectuó ningún cambio
   */
  public int eliminarEstudiante(Connection connection) {
    try {
      PreparedStatement instruccion = connection.prepareStatement("DELETE FROM estudiante WHERE matricula = ?");
      instruccion.setString(1, matricula.get());
      return instruccion.executeUpdate(); //Lo que devuelve es la cantidad de registros afectados
    } catch (SQLException ex) {
      Logger.getLogger(Estudiante.class.getName()).log(Level.SEVERE, null, ex);
      return 0;
    }
  }

  /**
   * Este método permite recabar la información adherida en la BD a partir de sentencias SQL
   *
   * @param connection variable de tipo Connection
   * @param listaEstudiante devuelve 1 si se efectuaron cambios o 0 si no efectuó ningún cambio
   */
  public static void llenarInformacion(Connection connection, ObservableList<EstudianteSQL> listaEstudiante) {
    try {
      Statement statement = connection.createStatement();
      ResultSet resultado = statement.executeQuery("SELECT matricula, nombre, paterno, materno FROM estudiante");
      while (resultado.next()) {
        listaEstudiante.add(new EstudianteSQL(
            resultado.getString("matricula"),
            resultado.getString("nombre"),
            resultado.getString("paterno"),
            resultado.getString("materno")
        )
        );
      }
    } catch (SQLException ex) {
      Logger.getLogger(Estudiante.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
