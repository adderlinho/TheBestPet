package asoftwaresolution.thebestpet.restApi.model;

/**
 * Created by AdderlyS on 9/11/2017.
 */

public class FirebaseResponse {

    private String id;
    private String id_dispositivo;
    private String id_usuario_instagram;
    private String usuario_instagram;

    public FirebaseResponse(String id, String id_dispositivo, String id_usuario_instagram, String usuario_instagram) {
        this.id = id;
        this.id_dispositivo = id_dispositivo;
        this.id_usuario_instagram = id_usuario_instagram;
        this.usuario_instagram = usuario_instagram;
    }

    public FirebaseResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_dispositivo() {
        return id_dispositivo;
    }

    public void setId_dispositivo(String id_dispositivo) {
        this.id_dispositivo = id_dispositivo;
    }

    public String getId_usuario_instagram() {
        return id_usuario_instagram;
    }

    public void setId_usuario_instagram(String id_usuario_instagram) {
        this.id_usuario_instagram = id_usuario_instagram;
    }

    public String getUsuario_instagram() {
        return usuario_instagram;
    }

    public void setUsuario_instagram(String usuario_instagram) {
        this.usuario_instagram = usuario_instagram;
    }

}
