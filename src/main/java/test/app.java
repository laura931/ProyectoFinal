
package test;

import DAO.UsuarioDAOImp;
import clases.HiloEnvio;
import clases.HiloRecepcion;
import clases.MetodoEscrituraArchivos;
import clases.MetodoLecturaArchivos;
import clases.Usuarios;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class app {
    public static void main(String[] args) {
        
        
        List<Usuarios> listaUsuariosEnvio=new ArrayList<>();
        listaUsuariosEnvio.add(new Usuarios("Laura","Maza","lauramaza@gmail.com","3814067084"));
        listaUsuariosEnvio.add(new Usuarios("Juan","Perez","juanperez@gmail.com","3511234567"));
        listaUsuariosEnvio.add(new Usuarios("Nicolas","Scaglione","nicoscaglione@gmail.com","3519876543"));
        listaUsuariosEnvio.add(new Usuarios("Nicolas","Berdu","nicolasberdu@gmail.com","3516543217"));
        listaUsuariosEnvio.add(new Usuarios("Azoka","Tano","azokatano@gmail.com","3511237894"));
        listaUsuariosEnvio.add(new Usuarios("Han","Solo","hansolo@gmail.com","3517845126"));
        listaUsuariosEnvio.add(new Usuarios("John","Week","johnweek@gmail.com","3811111111"));
        
        HiloEnvio he=new HiloEnvio();
        HiloRecepcion hr=new HiloRecepcion();
        
        he.setListaUsuarios(listaUsuariosEnvio);
        he.start();
        
        try {
            hr.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(app.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        hr.start();
        
        try {
            hr.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(app.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        List<Usuarios> usuariosRecepcion=hr.getUsuariosRecuperados();
        
        
        String rutaDat = "usuariosDat.dat";
        MetodoEscrituraArchivos hiloEscrituraDat =new MetodoEscrituraArchivos();
        hiloEscrituraDat.setUsuarios(usuariosRecepcion);
        hiloEscrituraDat.setRuta(rutaDat);
        hiloEscrituraDat.setOpcion(2);

        hiloEscrituraDat.run();
        
        String rutaTxt = "usuariosBF.txt";
        MetodoEscrituraArchivos hiloEscriturabf =new MetodoEscrituraArchivos();
        hiloEscriturabf.setUsuarios(usuariosRecepcion);
        hiloEscriturabf.setRuta(rutaTxt);
        hiloEscriturabf.setOpcion(1);
        hiloEscriturabf.run();
        
        
        
        int opciones;
        boolean ban = true;
        
        String  menu="--------------------------------------------------------\n"
                + "Ingrese una de estas opciones:\n"
                + "(1) Opciones de CRUD\n"
                + "(2) Ordenar Lista de usuarios por apellido\n"
                + "(3) Guardar usuarios en txt\n"
                + "(4) Guardar usuarios en archivo .dat\n"
                + "(5) Recuperar lista de usuarios del archivo txt\n"
                + "(6) Recuperar lista de usuarios del archivo .dat\n"
                + "(7) Buscar un usuario por id"
                + "(0) Escriba 0 para salir\n"
                + "--------------------------------------------------------\n"
                + "Opcion seleccionada: ";
        
        String subMenu = "--------------------------------------------------------\n"
                + " Opciones de CRUD:\n"
                + " (1) Crear un usuario \n"
                + " (2) Modificar un usuario \n"
                + " (3) Eliminar usuarios por nombre \n"
                + " (0) Volver al menu anterior \n"
                + "--------------------------------------------------------\n"
                + "Opcion seleccionada: ";
        
        Usuarios nuevoUsuario = null;
        
        while(ban){
            try {
                opciones = cargarNumeroInt(menu);
                switch(opciones){
                    case 0:
                        ban = false;
                        
                        break;
                        
                    case 1:  // opciones de CRUD
                        
                        int opcionSubMenu;
                        boolean banSubMenu = true;
                        
                        while(banSubMenu){
                            opcionSubMenu = cargarNumeroInt(subMenu);
                            switch (opcionSubMenu){
                                case 1: // crear usuarios
                                    
                                    listaUsuariosEnvio.clear();
                                    
                                    nuevoUsuario = crearUsuario();
                                    listaUsuariosEnvio.add(nuevoUsuario);
                                    
                                    UsuarioDAOImp usuarioDAOImp = new UsuarioDAOImp(); 
                                    usuarioDAOImp.registrar(listaUsuariosEnvio);
                                    
                                    usuariosRecepcion = usuarioDAOImp.recuperar();
                                    break;
                                case 2: // modificar usuario
                                    
                                    modificarUsuario();
                                    
                                    //Realizamos la recarga de los usuarios en base de datos
                                    hr.start();
                                    usuariosRecepcion = hr.getUsuariosRecuperados();
                                    
                                    break;
                                case 3: // eliminar usuario
                                    
                                    eliminarUsuarioPorNombre();
                                    
                                    //Realizamos la recarga de los usuarios en base de datos
                                    hr.start();
                                    usuariosRecepcion = hr.getUsuariosRecuperados();
                                    break;
                                case 0:
                                    banSubMenu = false;
                                    break;
                                default:
                                    System.out.println("La opción ingresada es incorrecta. Intente nuevamente.");
                            }
                        }
                        
                        break;
                    case 2:  // Ordenar lista de usuarios y mostrarla ordenada

                        ordenarUsuariosPorApellidoYMostrar(usuariosRecepcion);

                        break;
                    case 3: // Guardar en txt (buffered)
                        
                        if(!usuariosRecepcion.isEmpty()){
                            
                            hiloEscriturabf.setUsuarios(usuariosRecepcion);
                            hiloEscriturabf.setRuta(rutaTxt);
                            hiloEscriturabf.setOpcion(1);
                            hiloEscriturabf.run();
                        }
                        else {
                            System.out.println("No hay usuarios cargados en base de datos. Crear usuarios nuevos desde el menu de opciones de CRUD.");
                        }

                        break;
                    case 4: // guardar en .dat
                        
                        if(!usuariosRecepcion.isEmpty()){
                            
                            hiloEscrituraDat.setUsuarios(usuariosRecepcion);
                            hiloEscrituraDat.setRuta(rutaDat);
                            hiloEscrituraDat.setOpcion(2);

                            hiloEscrituraDat.run();
                            
                        }
                        else {
                            System.out.println("No hay usuarios cargados en base de datos. Crear usuarios nuevos desde el menu de opciones de CRUD.");
                        }
                        
                        break;
                    case 5: //recuperar txt
                            
                            MetodoLecturaArchivos hiloLecturaBf =new MetodoLecturaArchivos();
                            hiloLecturaBf.setRuta("usuariosBF.txt");
                            hiloLecturaBf.setOpcion(1);
                            hiloLecturaBf.run();

                        break;
                    case 6: // recuperar .dat

                            MetodoLecturaArchivos hiloLecturaDat =new MetodoLecturaArchivos();
                            hiloLecturaDat.setRuta("usuariosDat.dat");
                            hiloLecturaDat.setOpcion(2);
                            hiloLecturaDat.start();
                        break;
                    case 7: // Buscar Usuario por id

                        opcionSieteBusquedaDeUsuario(usuariosRecepcion);

                        break;
                    default:
                        System.out.println("La opción ingresada es incorrecta.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error en la selección de opciones. El dato ingresado no es un número."+e);
            } catch (ClassNotFoundException ex) {

                System.out.println("Error en la conexion de la base de datos.");
                ex.printStackTrace(System.out);
            } catch (SQLException ex) {
                System.out.println("Error de app.");
                ex.printStackTrace(System.out);
            }
            

        }
    }
    
    
    /**
     * Metodo que permite crear un usuario nuevo.
     * devuelve un nuevo usuario
     * @return 
     */
    public static Usuarios crearUsuario(){
        Usuarios usuario = null;
        
        //tring nombre, String apellido, String email, String telefono
        
        String nombre = cargarString("Ingrese el nombre del usuario: ");
        String apellido = cargarString("Ingrese el apellido del usuario: ");
        
        String email = cargarString("Ingrese el email del usuario: ");
        String telefono = cargarString("Ingrese el telefono del usuario: ");
        
        usuario = new Usuarios(nombre, apellido, email, telefono);
        
        return usuario;
    }
    
    
    /*********************************************************************************************************
     * cargarNumeroInt(String texto)
     * Funcion que permite la carga de números enteros
     * @param texto
     * @return 
     *********************************************************************************************************/
    public static int cargarNumeroInt(String texto){
        Scanner sn = new Scanner(System.in);
        System.out.println(texto);
        int numero=sn.nextInt();
        
        return numero;
    }
    
    /*********************************************************************************************************
     * cargarString(String texto)
     * Funcion para cargar textos
     * @param texto
     * @return txt
     *********************************************************************************************************/
    public static String cargarString(String texto){
        Scanner sn = new Scanner(System.in);
        System.out.println(texto);
        String txt=sn.nextLine();
        
        return txt;   
    }
    
    /*********************************************************************************************************
     * Metodo que ordena los usuarios por apellido y los mmuestra
     * @param usuariosRecepcion 
     *********************************************************************************************************/
    public static void ordenarUsuariosPorApellidoYMostrar(List<Usuarios> usuariosRecepcion){
        System.out.println("\n Lista de usuarios ordenada por apellidos: ");
        System.out.println("------------------------------------------------------------------------------");
        usuariosRecepcion.stream().sorted( (Usuarios u1, Usuarios u2) -> u1.getApellido().compareToIgnoreCase(u2.getApellido()) ).forEach(System.out :: println);
    }
    
    
    /*********************************************************************************************************
     * Desarrollo de la opción siete del menu - busqueda de usuario por ID
     * @param usuariosRecepcion 
     *********************************************************************************************************/
    public static void opcionSieteBusquedaDeUsuario(List<Usuarios> usuariosRecepcion){
        int idUsuario = cargarNumeroInt("Ingrese el id de un usuario: ");

        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Buscando el usuario de id: " + idUsuario);

        Usuarios usuarioEncontrado = estaEnLista(usuariosRecepcion, idUsuario);

        if(usuarioEncontrado != null){
            System.out.println("Usuario encontrado:");
            System.out.println(usuarioEncontrado.toString());
        }
        else{
            System.out.println("No se encontró el usuario.");
        }
        System.out.println("------------------------------------------------------------------------------");
    }
    
    /*********************************************************************************************************
     * Metodo que verifica si un usuario se encuentra en la lista de usuarios (recepcionados)
     * Devuelve el usuario encontrado y si no lo encuentra, devuelve un null
     * @param listaUsuarios
     * @param id
     * @return 
     *********************************************************************************************************/
    public static Usuarios estaEnLista(List<Usuarios> listaUsuarios,int id){
                
        for(Usuarios usuario: listaUsuarios){
            if(usuario.getId() == id){
                return usuario;
            }
        }
        return null;
    }
    
    /**
     * Metodo que permite eliminar usuarios con un determinado nombre
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static void eliminarUsuarioPorNombre() throws SQLException, ClassNotFoundException{
        String nombreUsuario = cargarString("Ingrese el nombre de los usuarios a eliminar.");
        
        Usuarios usuario = new Usuarios(nombreUsuario, "", "", "");
        
        UsuarioDAOImp usuarioDAOImp = new UsuarioDAOImp(); 
        usuarioDAOImp.eliminar(usuario);
    }
    
    /**
     * Metodo que permite modificar un usuario segun su email
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static void modificarUsuario() throws SQLException, ClassNotFoundException{
        String nombreUsuario = cargarString("Ingrese el nombre de los usuarios a eliminar.");
        
        Usuarios usuario = new Usuarios(nombreUsuario, "", "", "");
        
        UsuarioDAOImp usuarioDAOImp = new UsuarioDAOImp(); 
        usuarioDAOImp.eliminar(usuario);
    }
}
