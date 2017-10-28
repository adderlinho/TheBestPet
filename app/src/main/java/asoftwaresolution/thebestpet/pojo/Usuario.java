package asoftwaresolution.thebestpet.pojo;

import java.io.Serializable;

/**
 * Created by AdderlyS on 26/09/2017.
 */

public class Usuario implements Serializable {
    private String id;
    private String username;
    private String profile_picture;

    public Usuario(String id, String username, String profile_picture) {
        this.id = id;
        this.username = username;
        this.profile_picture = profile_picture;
    }

    public Usuario() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
