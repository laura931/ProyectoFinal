
package Interfaces;

import clases.Usuarios;
import java.sql.SQLException;
import java.util.List;


public interface DAOUsuarios {
    
    //CRUD
    //CREATE
    public void registrar(List<Usuarios> usuarios)throws SQLException, ClassNotFoundException;
     
    //READ
    public List<Usuarios> recuperar()throws SQLException, ClassNotFoundException;
     
    //Update
    public void modificar(Usuarios user)throws SQLException, ClassNotFoundException;
     
    //Delete
    public void eliminar(Usuarios user)throws SQLException, ClassNotFoundException;
     

}



