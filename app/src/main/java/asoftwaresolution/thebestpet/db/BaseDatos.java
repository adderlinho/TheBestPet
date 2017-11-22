package asoftwaresolution.thebestpet.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import asoftwaresolution.thebestpet.Session.SessionManager;
import asoftwaresolution.thebestpet.adaptadores.MascotasAdaptador;
import asoftwaresolution.thebestpet.pojo.Mascota;
import asoftwaresolution.thebestpet.pojo.Usuario;
import asoftwaresolution.thebestpet.restApi.ConstantesRestApi;

/**
 * Created by AdderlyS on 13/10/2017.
 */

public class BaseDatos extends SQLiteOpenHelper {
    private Context context;
    private SessionManager manager;

    public BaseDatos(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
        this.manager = new SessionManager();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryCrearTablaMascota = "CREATE TABLE " + ConstantesBaseDatos.TABLE_MASCOTA + "("+
                                        ConstantesBaseDatos.TABLE_MASCOTA_ID        +       " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                        ConstantesBaseDatos.TABLE_MASCOTA_IMAGEN    +       " INTEGER, " +
                                        ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE    +       " TEXT " +
                                        ")";

        String queryCrearTablaLikesMascota = "CREATE TABLE " + ConstantesBaseDatos.TABLE_LIKES_MASCOTAS + "("+
                                        ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID         +       " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                        ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID_MASCOTA +       " INTEGER, " +
                                        ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_LIKES      +       " INTEGER, "  +
                                        "FOREIGN KEY (" + ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID_MASCOTA + ") " +
                                        "REFERENCES " + ConstantesBaseDatos.TABLE_MASCOTA + "(" + ConstantesBaseDatos.TABLE_MASCOTA_ID +")" +
                                        ")";

        String queryCrearTablaUsuario = "CREATE TABLE " + ConstantesBaseDatos.TABLE_USUARIO + "("+
                                        ConstantesBaseDatos.TABLE_USUARIO_ID            +       " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                        ConstantesBaseDatos.TABLE_ID_USUARIO_INSTAGRAM  +       " TEXT, " +
                                        ConstantesBaseDatos.TABLE_ID_USUARIO_FIREBASE   +       " TEXT, " +
                                        ConstantesBaseDatos.TABLE_USUARIO_USERNAME      +       " TEXT " +
                                        ")";

        sqLiteDatabase.execSQL(queryCrearTablaMascota);
        sqLiteDatabase.execSQL(queryCrearTablaLikesMascota);
        sqLiteDatabase.execSQL(queryCrearTablaUsuario);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + ConstantesBaseDatos.TABLE_MASCOTA);
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + ConstantesBaseDatos.TABLE_LIKES_MASCOTAS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + ConstantesBaseDatos.TABLE_USUARIO);
        onCreate(sqLiteDatabase);
    }

    public ArrayList<Mascota> obtenerTodasLasMascotas() {
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()) {
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setImagen(registros.getInt(1));
            mascotaActual.setNombre(registros.getString(2));

            String query_likes = "SELECT COUNT("+ ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_LIKES + ") as likes " +
                                 "FROM " + ConstantesBaseDatos.TABLE_LIKES_MASCOTAS +
                                 " WHERE " + ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID_MASCOTA + " = " + mascotaActual.getId();

            Cursor registroLikes = db.rawQuery(query_likes, null);

            if (registroLikes.moveToNext())
            {
                mascotaActual.setLikes(registroLikes.getInt(0));
            }
            else
            {
                mascotaActual.setLikes(0);
            }

            mascotas.add(mascotaActual);
        }

        db.close();

        return mascotas;
    }

    public void insertarMascota(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_MASCOTA, null, contentValues);
        db.close();
    }

    public void insertarLikeMascota(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_LIKES_MASCOTAS, null, contentValues);
        db.close();
    }

    public void insertarUsuario(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_USUARIO, null, contentValues);
        db.close();
    }

    public void insertarIdFirebaseUsuario(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(ConstantesBaseDatos.TABLE_USUARIO, contentValues, ConstantesBaseDatos.TABLE_ID_USUARIO_INSTAGRAM + "=" + manager.getPreferences(context,"KEY_ID_USER_INSTAGRAM"), null);
        db.close();
    }

    public int obtenerLikesMascota(Mascota mascota) {
        int likes = 0;
        String query = "Select Count(" + ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_LIKES + ")" +
                       "From " + ConstantesBaseDatos.TABLE_LIKES_MASCOTAS +
                       " WHERE " + ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID_MASCOTA + " = " + mascota.getId();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()){
           likes = registros.getInt(0);
        }

        return likes;
    }

    public ArrayList<Mascota> obtenerMascotasPopulares() {
        ArrayList<Mascota> mascotas = new ArrayList<>();

        mascotas = obtenerTodasLasMascotas();

        Collections.sort(mascotas, new Comparator<Mascota>() {
            @Override
            public int compare(Mascota mascota1, Mascota mascota2) {
                return new Integer(mascota2.getLikes()).compareTo(new Integer(mascota1.getLikes()));
            }
        });

        ArrayList<Mascota> favoritas = new ArrayList<Mascota>();
        for(int i = 0; i <= 4; i++)
        {
            favoritas.add(new Mascota(mascotas.get(i).getImagen(), mascotas.get(i).getNombre(), mascotas.get(i).getLikes()));
        }

        return favoritas;
    }

    public ArrayList<Usuario> obtenerUsuarioRegistrado() {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_USUARIO;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()) {
            Usuario usuarioActual = new Usuario();
            usuarioActual.setId_instagram(registros.getString(1));
            usuarioActual.setId_firebase(registros.getString(2));
            usuarioActual.setUsername(registros.getString(3));
            usuarios.add(usuarioActual);
        }

        db.close();

        return usuarios;
    }

    public void limpiarDB() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ConstantesBaseDatos.TABLE_MASCOTA, null, null);
        db.close();
    }
}
