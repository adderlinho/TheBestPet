package asoftwaresolution.thebestpet.db;

/**
 * Created by AdderlyS on 13/10/2017.
 */

public class ConstantesBaseDatos {
    public static final String DATABASE_NAME = "mascotas";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_MASCOTA = "mascota";
    public static final String TABLE_MASCOTA_ID = "id";
    public static final String TABLE_MASCOTA_IMAGEN = "imagen";
    public static final String TABLE_MASCOTA_NOMBRE = "nombre";

    public static final String TABLE_LIKES_MASCOTAS = "mascota_likes";
    public static final String TABLE_LIKES_MASCOTAS_ID = "id";
    public static final String TABLE_LIKES_MASCOTAS_ID_MASCOTA = "id_mascota";
    public static final String TABLE_LIKES_MASCOTAS_LIKES = "numero_likes";

    public static final String TABLE_USUARIO = "usuario";
    public static final String TABLE_USUARIO_ID = "id";
    public static final String TABLE_USUARIO_USERNAME = "username";
}
