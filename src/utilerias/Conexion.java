package utilerias;  

import java.sql.*;  

public class Conexion {

    static String MYSQL_DATABASE = "sql5747228";  
    static String MYSQL_ROOT_PASSWORD = "CYKWF7hlum";  
    static String MYSQLUSER = "sql5747228";
    static String MYSQLHOST = "sql5.freesqldatabase.com";
    static String MYSQLPORT = "3306";  
 
    static String url = "jdbc:mysql://" + MYSQLHOST + ":" + MYSQLPORT + "/" + MYSQL_DATABASE;  

    public static Connection getConexion() {  
        Connection conexion = null;  
        try {  
            conexion = DriverManager.getConnection(url, MYSQLUSER, MYSQL_ROOT_PASSWORD);  
            System.out.println("Conectado");  
        } catch (SQLException ex) {  
            System.out.println("Fallo: " + ex.getMessage());  
        }  
        return conexion;
    }  

    private static void crearTablaUsuarios() {  
        Connection conexion = getConexion();  
        Statement stmt = null;  
        try {  
            if (conexion != null) {  
                stmt = conexion.createStatement();
                String sql = "CREATE TABLE IF NOT EXISTS usuarios ("  
                        + "id INT AUTO_INCREMENT PRIMARY KEY, "  
                        + "nombre VARCHAR(80), "  
                        + "alias VARCHAR(20) UNIQUE, "  
                        + "contraseña VARCHAR(30), "  
                        + "correo VARCHAR(50) UNIQUE, "
                        + "edad INT, " 
                        + "peso DECIMAL(5,2), "
                        + "sexo VARCHAR(15))";
                stmt.executeUpdate(sql);  
                System.out.println("Tabla 'usuarios' creada o ya existe.");  
            }  
        } catch (SQLException e) {  
            System.out.println("Error al crear la tabla: " + e.getMessage());  
        } finally {  
            try {  
                if (stmt != null) {  
                    stmt.close();  
                }  
                if (conexion != null) {  
                    conexion.close();  
                }  
            } catch (SQLException ex) {  
                System.out.println("Error al cerrar recursos: " + ex.getMessage());  
            }  
        }  
    }
    
    private static void crearTablaHistorial() {  
        Connection conexion = getConexion();  
        Statement stmt = null;  
        try {  
            if (conexion != null) {  
                stmt = conexion.createStatement();
                String sql = "CREATE TABLE IF NOT EXISTS historial ("  
                        + "idCliente INT UNIQUE, "
                        + "fecha DATE, "
                        + "ritmo INT, "
                        + "oxigenacion INT)";
                stmt.executeUpdate(sql);  
                System.out.println("Tabla 'historial' creada o ya existe.");  
            }  
        } catch (SQLException e) {  
            System.out.println("Error al crear la tabla: " + e.getMessage());  
        } finally {  
            try {  
                if (stmt != null) {  
                    stmt.close();  
                }  
                if (conexion != null) {  
                    conexion.close();  
                }  
            } catch (SQLException ex) {  
                System.out.println("Error al cerrar recursos: " + ex.getMessage());  
            }  
        }  
    }
    
    public static void main(String[] args) {
        crearTablaUsuarios();;
        crearTablaHistorial();
    }
}