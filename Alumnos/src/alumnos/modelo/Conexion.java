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
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

  private Connection connection;
  public String url = "jdbc:mysql://localhost/bd_Estudiante";
  private String usuario = "root";
  private String contrasena = "Rafalcao17";

  public Connection getConnection() {
    return connection;
  }

  public void setConnection(Connection connection) {
    this.connection = connection;
  }

  /**
   * En este método se establece la conexión entre Java y la BD de MySQL
   */
  public void establecerConexion() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      connection = DriverManager.getConnection(url, usuario, contrasena);
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
      Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Este método cierra la conexión como medida de seguridad después de utilizar la BD
   */
  public void cerrarConexion() {
    try {
      connection.close();
    } catch (SQLException ex) {
      Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
