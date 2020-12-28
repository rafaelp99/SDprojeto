package BackEnd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente extends Thread {

    private Contacto fonte;
    private Contacto destino;
    private String mensagem;

    public  Cliente(Contacto fonte, Contacto destino, String mensagem) {
        this.fonte = fonte;
        this.destino = destino;
        this.mensagem = mensagem;
    }

    @Override
    public void run() {
        try {
            String servidor = this.destino.getIp();
            int porto = this.destino.getPorta();
            InetAddress serverAddress = InetAddress.getByName(servidor);
            Socket ligacao = null;
            ligacao = new Socket(serverAddress, porto);
            BufferedReader in = new BufferedReader(new InputStreamReader(ligacao.getInputStream()));
            PrintWriter out = new PrintWriter(ligacao.getOutputStream(), true);
            String request = "get" + " " + this.fonte.getNickname() + " " + "{/}" + mensagem;
            out.println(request);
            String msg;
            msg = in.readLine();
            System.out.println(msg);
            ligacao.close();
        } catch (java.net.ConnectException | UnknownHostException e) {
            System.out.println("Servidor não existe: " + e);
        } catch (IOException e) {
            System.out.println("Erro ao comunicar com o servidor: " + e);
        }
    }

}
