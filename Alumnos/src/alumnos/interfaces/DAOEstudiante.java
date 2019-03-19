package alumnos.interfaces;

import java.sql.Connection;

public interface DAOEstudiante {
 public int agregarEstudiante(Connection connection);
 public int modificarEstudiante(Connection connection);
 public int eliminarEstudiante(Connection connection);
 
}
