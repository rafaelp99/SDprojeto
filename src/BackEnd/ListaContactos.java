package BackEnd;

import java.io.Serializable;
import java.util.ArrayList;

public class ListaContactos implements Serializable {

    private ArrayList<Contacto> listaContactos;

    public ListaContactos() {
        listaContactos = new ArrayList<Contacto>();
    }

    public ArrayList<Contacto> getContactos() {
        return listaContactos;
    }

    @Override
    public String toString() {
        return "ListaContactos{ " + listaContactos + '}';
    }
}
