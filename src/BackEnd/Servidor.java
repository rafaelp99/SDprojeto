package BackEnd;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor extends Thread{

    Sistema sistema;
    
    public Servidor(Sistema sistema) {
        this.sistema = sistema;
    }

    @Override
    public void run() {
        ServerSocket servidor = null;
        try {
            servidor = new ServerSocket(sistema.getCurrentUser().getPorta());
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        sistema.getJanela().setVisible(true);

        while (true) {
            try {
                Socket ligacao = servidor.accept();
                AtendedorPedidos handler = new AtendedorPedidos(ligacao, sistema);
                handler.start();
            } catch (IOException ex) {
                System.out.println("Erro do servidor: " + ex);
                System.exit(1);
            }

        }
    }

    

}
