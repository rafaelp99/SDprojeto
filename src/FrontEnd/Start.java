package FrontEnd;

import BackEnd.Contacto;
import BackEnd.ListaContactos;
import BackEnd.Servidor;
import BackEnd.Sistema;
import java.io.IOException;

public class Start {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Sistema sys = new Sistema();
        Contacto autor = new Contacto("teste", "teste@gmail.com", "MI", "192.168.1.12", 2001);
        sys.getListaContactos().getContactos().add(autor);
        //Contacto destino = new Contacto("PCPORTATIL", "fejieg@gmail.com", "MI", "192.168.1.7", 4000);
        //ListaContactos l1 = new ListaContactos();
        //l1.getContactos().add(destino);
        //new Servidor(sys);
        new Login(sys).setVisible(true);
        
        System.out.println(sys.getListaContactos());
    }
    
    

}
