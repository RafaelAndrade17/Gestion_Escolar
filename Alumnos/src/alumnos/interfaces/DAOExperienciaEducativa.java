
package alumnos.interfaces;

import java.sql.Connection;

public interface DAOExperienciaEducativa {
 public int agregarExperienciaEducativa(Connection connection);
 public int modificarExperienciaEducativa(Connection connection);
 public int eliminarExperienciaEducativa(Connection connection);
}
