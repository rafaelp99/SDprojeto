package BackEnd;

import FrontEnd.JanelaChat;
import java.awt.Component;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Sistema implements Serializable {

    private boolean isSaved = true;
    public static final String NOMEPROGRAMA = "Sistema de Mensagens";
    private final String NOMEFICHEIRO = "Sistema.Dados";
    private ListaContactos listaUsers;
    private Contacto currentUser;
    private JanelaChat janela;

    public Sistema() throws IOException {
        listaUsers = new ListaContactos();
        currentUser = null;
        janela = null;
        isSaved = true;
    }

    public String getNOMEFICHEIRO() {
        return NOMEFICHEIRO;
    }

    public boolean isIsSaved() {
        return isSaved;
    }

    public void setSaved(boolean isSaved) {
        this.isSaved = isSaved;
    }

    public ListaContactos getListaContactos() {
        return listaUsers;
    }

    public void setListaContactos(ListaContactos listaContactos) {
        this.listaUsers = listaContactos;
    }

    public Contacto getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Contacto currentUser) {
        this.currentUser = currentUser;
    }

    public JanelaChat getJanela() {
        return janela;
    }

    public void setJanela(JanelaChat janela) {
        this.janela = janela;
    }

    public boolean containsUser(String nickname, String email) {                  //Método que verifica se já existe uma gravação com o nome dado
        for (Contacto user : listaUsers.getContactos()) {
            if (nickname.equals(user.getNickname()) && email.equals(user.getEmail())) {
                return true;
            }
        }
        return false;
    }

    public void setCurrentUser(String nickname, String email) {
        for (Contacto user : listaUsers.getContactos()) {
            if (nickname.equals(user.getNickname()) && email.equals(user.getEmail())) {
                this.currentUser = user;
            }
        }
    }

    public Sistema readSistema(Component componente) throws ClassNotFoundException, IOException { //Método que lê um ficheiro stream objeto e retorna um objeto Sistema. Se ocorrer um erro, irá delvolver um novo Objeto criado e gravá-lo depois.
        ObjectInputStream inputSistema = null;
        ObjectOutputStream outputSistema = null;
        try {
            inputSistema = new ObjectInputStream(new FileInputStream(this.getNOMEFICHEIRO()));
            Sistema sistema = (Sistema) inputSistema.readObject();
            sistema.setSaved(true);
            return sistema;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(componente, "Os seus dados não foram carregados com sucesso. " + ex.getMessage(), "InputStream", JOptionPane.WARNING_MESSAGE);
            outputSistema = new ObjectOutputStream(new FileOutputStream(this.getNOMEFICHEIRO()));
            Sistema sistema = new Sistema();
            outputSistema.writeObject(sistema);
            return sistema;
        } finally {
            if (inputSistema != null) {
                inputSistema.close();
            }
            if (outputSistema != null) {
                outputSistema.close();
            }
        }
    }

    public void writeSistema(Component componente) throws IOException {                             //Método que grava o objeto da class sistema num ficheiro stream objeto. Se ocorrer um erro, fecha a stream.
        ObjectOutputStream outputSistema = null;
        try {
            outputSistema = new ObjectOutputStream(new FileOutputStream(this.getNOMEFICHEIRO()));
            this.setSaved(true);
            outputSistema.writeObject(this);
            JOptionPane.showMessageDialog(componente, "Os seus dados foram guardados com Sucesso.", "OutputStream", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(componente, "Os seus dados não foram guardados com sucesso. " + ex.getMessage(), "OutputStream", JOptionPane.WARNING_MESSAGE);
        } finally {
            if (outputSistema != null) {
                outputSistema.close();
            }
        }
    }

}
