package BackEnd;

import FrontEnd.JanelaChat;
import java.net.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class AtendedorPedidos extends Thread {

    Socket ligacao;
    BufferedReader in;
    PrintWriter out;
    Sistema sistema;

    public AtendedorPedidos(Socket ligacao,Sistema sistema) {
        this.ligacao = ligacao;
        this.sistema = sistema;
        try {
            this.in = new BufferedReader(new InputStreamReader(ligacao.getInputStream()));
            this.out = new PrintWriter(ligacao.getOutputStream());
        } catch (IOException ex) {
            System.out.println("Erro do servidor: " + ex);
            System.exit(1);
        }
    }

    public void run() {
        try {
            String response;
            String msg = in.readLine();
            System.out.println("Request=" + msg);
            StringTokenizer tokens = new StringTokenizer(msg);
            String metodo = tokens.nextToken();
            String nickname = tokens.nextToken();
            if (metodo.equals("get")) {
                response = "101\n";
                SimpleDateFormat formatadorTempo = new SimpleDateFormat("HH:mm:ss");
                Date data = new Date();
                String tempo ="<"+ formatadorTempo.format(data)+"> ";
                this.sistema.getJanela().getChat().append(tempo + nickname + ": " + msg.substring(msg.indexOf("{/}") + 3) + "\n");
                out.println(response);
            } else {
                out.println("201\n");
            }
            out.flush();
            out.close();
            in.close();
            ligacao.close();
        } catch (IOException e) {
            System.out.println("Erro no servidor: " + e);
            System.exit(1);
        }
    }
}
