
package clases;

import DAO.UsuarioDAOImp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HiloRecepcion extends Thread {
    private List<Usuarios> usuariosRecuperados = new ArrayList<>();


    public List<Usuarios> getUsuariosRecuperados() {
        return usuariosRecuperados;
    }

    public void setUsuariosRecuperados(List<Usuarios> usuariosRecuperados) {
        this.usuariosRecuperados = usuariosRecuperados;
    }
    
    private void recuperarUsuariosDeBD() throws SQLException, ClassNotFoundException{
        UsuarioDAOImp usuarioDAOImp = new UsuarioDAOImp();
        usuariosRecuperados = usuarioDAOImp.recuperar();
    }
    
    
    
    @Override
    public void run() {
        
        try {
            System.out.println("Recuperando usuarios de la base de datos");
            System.out.println("------------------------------------------------------------------------------");
            
            recuperarUsuariosDeBD();
            

        } catch (SQLException ex) {
            Logger.getLogger(HiloRecepcion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HiloRecepcion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    
    
    
}
