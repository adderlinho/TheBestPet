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

    private String  instagram_id;
    private String  urlFoto;
    private String  nombreCompleto;
    private int     instagram_likes;
    private String  id_media;

    public Mascota(int imagen, String nombre, int likes) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.likes = likes;
    }

    public Mascota(String instagram_id, String urlFoto, String nombreCompleto, int instagram_likes, String id_media)
    {
        this.instagram_id = instagram_id;
        this.urlFoto = urlFoto;
        this.nombreCompleto = nombreCompleto;
        this.instagram_likes = instagram_likes;
        this.id_media = id_media;
    }

    public Mascota() {

    }

    public int getInstagram_likes() {
        return instagram_likes;
    }

    public void setInstagram_likes(int instagram_likes) {
        this.instagram_likes = instagram_likes;
    }

    public String getInstagram_id() {
        return instagram_id;
    }

    public void setInstagram_id(String instagram_id) {
        this.instagram_id = instagram_id;
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

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getId_media() {
        return id_media;
    }

    public void setId_media(String id_media) {
        this.id_media = id_media;
    }
}
