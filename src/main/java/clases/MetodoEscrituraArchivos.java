
package clases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class MetodoEscrituraArchivos extends Thread{
    
    private List<Usuarios> usuarios;
    private String ruta;
    private int opcion;
    private int primeraCargaDat = 0;
    private int primeraCargaTxt = 0;

    public List<Usuarios> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuarios> usuarios) {
        this.usuarios = usuarios;
    }


    public int getOpcion() {
        return opcion;
    }

    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
   
        
    public void escribirArchivoOO(){
        Path archivo=Paths.get(this.ruta);
        
        try {
            
            FileOutputStream fo=new FileOutputStream(this.ruta);
            ObjectOutputStream oo=new ObjectOutputStream(fo);
            
            if(this.primeraCargaDat != 0)
                verificarExistenciaUsuariosDat();
            
            this.primeraCargaDat ++;
            
            oo.writeObject(this.usuarios);

            oo.close();
            fo.close();
        } catch (IOException e) {
            System.out.println("Error al generar el archivo: " +e);
        }
        
        System.out.println("Se creo el archivo .dat");
    }
    
    
    private void verificarExistenciaUsuariosDat(){
        try {
            FileInputStream fi=new FileInputStream(this.ruta);
            ObjectInputStream oi=new ObjectInputStream(fi);

            //CASTEAMOS
            List<Usuarios> usuariosEnArchivo=(List<Usuarios>) oi.readObject();
            /*if (usuariosEnArchivo.isEmpty()) {
                System.out.println("La lista de usuarios estaba vacia");
            } else {
                usuariosEnArchivo.forEach(System.out::println);
            }*/
            
            List<Usuarios> usuariosSinCargar = new ArrayList<>();
            for(Usuarios usuario : this.usuarios){
                // si el usuario no fue cargado en el archivo .dat, lo agregamos a la lista de usuarios sin cargar
                if(!usuariosEnArchivo.contains(usuario)){
                    usuariosSinCargar.add(usuario);
                }
            }
            
            //Por último, reemplazamos la lista de usuarios que se cargarán por la lista de usuarios que aun no se cargaron en archivo.
            this.usuarios.clear();
            this.usuarios.addAll(usuariosSinCargar);
            
            oi.close();
            fi.close();
            
        } catch (IOException e) {
            System.out.println("Error en la lectura usuarios dat");
            e.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error en la clase");
            ex.printStackTrace(System.out);
        }
    }
    
    public void escribirArchivoBf(){              
        try {
            
            FileWriter fw = new FileWriter(this.ruta,true);
            BufferedWriter bw=new BufferedWriter(fw);
            
            // Mapeamos la lista
            List<String> escritura=this.usuarios.stream().map(x->x.getApellido().toString().toUpperCase()
                    +"," +x.getNombre().toString().toUpperCase()+"\n").collect(Collectors.toList());
            
            if(this.primeraCargaTxt != 0){
                //Verificamos que los usuarios que están por agregarse no se encuentren cargados ya
                verificarExistenciaUsuariosBf(escritura);
            }
            this.primeraCargaTxt ++;
            
            for (String e : escritura) {
                bw.write(e);
            }

            System.out.println("Se creo el archivo .txt");

            bw.close();
            fw.close();
        } catch (IOException e) {
        }
     
    } 
    
    private void verificarExistenciaUsuariosBf( List<String> usuariosMapeados){
        try {
            FileReader fr= new FileReader(this.ruta);
            BufferedReader bf=new BufferedReader(fr);

            String retorno=bf.readLine();
            List<String> usuariosMapeadosSinCargar = new ArrayList<>();
            while(retorno!=null){  
                
                //Si el usuario guardado existe en la lista de usuarios mapeados, se debe guardar en una lista de usuarios mapeados sin cargar
                boolean existeUsuario = false;
                for (String usuarioMapeado : usuariosMapeados) {
                    if(usuarioMapeado.equalsIgnoreCase(retorno)){
                        existeUsuario = true;
                    }
                }
                
                
                if(!existeUsuario){
                    usuariosMapeadosSinCargar.add(retorno);
                }
                
                retorno=bf.readLine();                
            }
            
            
            usuariosMapeados.clear();
            usuariosMapeados =  usuariosMapeadosSinCargar;
            bf.close();
            fr.close();
            
            
        } catch (IOException e) {
            System.out.println("Error en caso lectura usuarios txt5");
            e.printStackTrace(System.out);                
        }
    }
        
        
    @Override 
    public void run(){
        switch(this.opcion){
            case 1:
                escribirArchivoBf();
                System.out.println("Se escribieron los registros para usuarios con buffered.");
                break;
            case 2:
                escribirArchivoOO();
                System.out.println("Se escribieron los registros para usuarios con .dat.");
                break;
            default:
                System.out.println("Se ingreso una opcion sin respuesta");
        }
    }
}
