/**
 * Nombre:              Gestión de horario JavaFX
 * Descripción:         Programa que permite llevar un control de horario (Agregar, eliminar,
 *                      guardar, cargar)
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

public class HorarioEESQL extends HorarioEE{
  
  public HorarioEESQL(String nrc, String dia, String horaInicio, String horaFin, String salon) {
    super(nrc, dia, horaInicio, horaFin, salon);
  }
  
   /**
   * Este método permite dar de alta un nuevo horario a partir de sentencias SQL
   *
   * @param connection variable de tipo Connection
   * @return devuelve 1 si se efectuaron cambios o 0 si no efectuó ningún cambio
   */
  public int agregarHorario(Connection connection) {
    try {
      PreparedStatement instruccion = connection.prepareStatement("INSERT INTO horarioEE (salon, "
          + "horaInicio, horaFin, dia, idExperienciaEducativa) SELECT * FROM (SELECT ?, ?, ?, ?,(SELECT idExperienciaEducativa "
          + "FROM experiencia_educativa WHERE nrc= ?)) AS tmp WHERE NOT EXISTS (SELECT salon, horaInicio, horaFin, "
          + "dia FROM horarioEE WHERE (dia = ? AND idExperienciaEducativa=(SELECT idExperienciaEducativa FROM experiencia_educativa WHERE nrc= ?)) "
          + "OR (salon=? AND horaInicio=? AND horaFin=? AND dia =?)) LIMIT 1;"); //Evitar la inyección SQL y parametrizar
      instruccion.setString(1, salon.get());//Sustituir el signo de interrogacion por lo que contiene la propiedad de matricula
      instruccion.setString(2, horaInicio.get());
      instruccion.setString(3, horaFin.get());
      instruccion.setString(4, dia.get());
      instruccion.setString(5, nrc.get());
      instruccion.setString(6, dia.get());
      instruccion.setString(7, nrc.get());
      instruccion.setString(8, salon.get());
      instruccion.setString(9, horaInicio.get());
      instruccion.setString(10, horaFin.get());
      instruccion.setString(11, dia.get());
      return instruccion.executeUpdate(); //Lo que devuelve es la cantidad de registros afectados
    } catch (SQLException ex) {
      Logger.getLogger(HorarioEE.class.getName()).log(Level.SEVERE, null, ex);
      return 0;
    }
  }
  
   /**
   * Este método permite dar de baja un horario a partir de sentencias SQL
   *
   * @param connection variable de tipo Connection
   * @return devuelve 1 si se efectuaron cambios o 0 si no efectuó ningún cambio
   */
  public int eliminarHorario(Connection connection) {
    try {
      PreparedStatement instruccion = connection.prepareStatement("DELETE FROM horarioEE WHERE "
          + "(SELECT idExperienciaEducativa FROM experiencia_educativa WHERE nrc = ? AND dia = ?)");
      instruccion.setString(1, nrc.get());
      instruccion.setString(2, dia.get());
      return instruccion.executeUpdate(); //Lo que devuelve es la cantidad de registros afectados
    } catch (SQLException ex) {
      Logger.getLogger(HorarioEE.class.getName()).log(Level.SEVERE, null, ex);
      return 0;
    }
  }
    
    /**
   * Este método permite recabar la información adherida en la BD a partir de sentencias SQL
   *
   * @param connection variable de tipo Connection
   * @param horario devuelve 1 si se efectuaron cambios o 0 si no efectuó ningún cambio
   */
    public static void llenarInformacionHorario(Connection connection, ObservableList<HorarioEESQL> horario) {
    try {
      Statement statement = connection.createStatement();
      ResultSet resultado = statement.executeQuery(" SELECT a.nrc, b.dia, b.horaInicio, "
          + "b.horaFin, b.salon FROM experiencia_educativa a, horarioEE b "
          + "WHERE a.idExperienciaEducativa = b.idExperienciaEducativa");
      while (resultado.next()) {
        horario.add(new HorarioEESQL(
            resultado.getString("nrc"),
            resultado.getString("dia"),
            resultado.getString("horaInicio"),
            resultado.getString("horaFin"),
            resultado.getString("salon")
        )
        );
      }
    } catch (SQLException ex) {
      Logger.getLogger(HorarioEE.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
   
}
