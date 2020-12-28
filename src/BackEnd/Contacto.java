package BackEnd;

public class Contacto {

    private String nickname;
    private String email;
    private String curso;
    private String ip;
    private int porta;

    public Contacto(String nickname, String email, String curso, String ip, int porta) {
        this.nickname = nickname;
        this.email = email;
        this.curso = curso;
        this.ip = ip;
        this.porta = porta;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getCurso() {
        return curso;
    }

    public String getIp() {
        return ip;
    }

    public int getPorta() {
        return porta;
    }

    @Override
    public String toString() {
        return "Contacto{" + "nickname=" + nickname + ", email=" + email + ", curso=" + curso + ", ip=" + ip + ", porta=" + porta + '}';
    }

}
