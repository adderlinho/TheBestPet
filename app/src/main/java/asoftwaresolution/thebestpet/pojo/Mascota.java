package asoftwaresolution.thebestpet.pojo;

import java.io.Serializable;

/**
 * Created by AdderlyS on 26/09/2017.
 */

public class Mascota implements Serializable {
    private int id;
    private int imagen;
    private String nombre;
    private int likes;

    public Mascota(int imagen, String nombre, int likes) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.likes = likes;
    }

    public Mascota() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
