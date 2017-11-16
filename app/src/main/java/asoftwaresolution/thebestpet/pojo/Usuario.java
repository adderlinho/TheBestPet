package asoftwaresolution.thebestpet.pojo;

import java.io.Serializable;

/**
 * Created by AdderlyS on 26/09/2017.
 */

public class Usuario implements Serializable {
    private String id_instagram;
    private String id_firebase;
    private String username;
    private String profile_picture;

    public Usuario(String id_instagram, String id_firebase, String username, String profile_picture) {
        this.id_instagram = id_instagram;
        this.id_firebase = id_firebase;
        this.username = username;
        this.profile_picture = profile_picture;
    }

    public Usuario() {

    }

    public String getId_instagram() {
        return id_instagram;
    }

    public void setId_instagram(String id_instagram) {
        this.id_instagram = id_instagram;
    }

    public String getId_firebase() {
        return id_firebase;
    }

    public void setId_firebase(String id_firebase) {
        this.id_firebase = id_firebase;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }
}
