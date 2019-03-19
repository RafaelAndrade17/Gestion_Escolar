
package alumnos.interfaces;

import java.sql.Connection;


public interface DAOHorarioEE {
  public int agregarHorario(Connection connection);
  public int eliminarHorario(Connection connection);
}
