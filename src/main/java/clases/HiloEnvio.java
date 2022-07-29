
package clases;

import DAO.UsuarioDAOImp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;



public class HiloEnvio extends Thread{
    
    private List<Usuarios> listaUsuarios;
    
    public List<Usuarios> getLista() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }


    private void registrarUsuariosEnBD() throws SQLException, ClassNotFoundException{
        UsuarioDAOImp usuarioDAOImp = new UsuarioDAOImp(); 
        usuarioDAOImp.registrar(this.listaUsuarios);
    }
    
    
    @Override
    public void run() {
        
           
        try {
            System.out.println("Registrando lista de usuarios en base de datos");

            registrarUsuariosEnBD();

        } catch (ClassNotFoundException ex) {

            System.out.println("Error en la conexion");
            ex.printStackTrace(System.out);
        } catch (SQLException ex) {
            System.out.println("Error de app");
            ex.printStackTrace(System.out);
        }
    }        
    
    
    
}
