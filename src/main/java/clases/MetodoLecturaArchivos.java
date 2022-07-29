
package clases;

//extends Thread

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class MetodoLecturaArchivos extends Thread {
    
    private List<Usuarios> usuarios;
    private int opcion;
    private String ruta;

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

    public void lecturaBf(){

        try {

            FileReader fr= new FileReader(this.ruta);
            BufferedReader bf=new BufferedReader(fr);

            String retorno=bf.readLine();

            while(retorno!=null){                
                System.out.println(retorno);
                retorno=bf.readLine();                
            }
            
            bf.close();
            fr.close();

        } catch (IOException e) {
            System.out.println("Error en caso lectura usuarios");

            e.printStackTrace(System.out);                
        }



    }

    public void lecturaOO(){

        try {

            FileInputStream fi=new FileInputStream(this.ruta);
            ObjectInputStream oi=new ObjectInputStream(fi);

            //CASTEAMOS
            this.usuarios=(List<Usuarios>) oi.readObject();

            if (this.usuarios.isEmpty()) {

                System.out.println("La lista de usuarios estaba vacia");

            } else {

                this.usuarios.forEach(System.out::println);
            }

            oi.close();
            fi.close();
        } catch (IOException e) {

            System.out.println("Error en la lectura usuarios");
            e.printStackTrace(System.out);

        } catch (ClassNotFoundException ex) {

            System.out.println("Error en la clase");
            ex.printStackTrace(System.out);
        }
    }
        
    
    @Override
    public void run(){
    
        switch(this.opcion){
            
            case 1:
                System.out.println("Recuperando archivo Buffered");
                lecturaBf();
                break;
            case 2:
                System.out.println("Recuperando archivo .dat");
                lecturaOO();
                break;
            default:
                System.out.println("Ingreso opcion erronea");
        
        }
    
    }


    
    
}

