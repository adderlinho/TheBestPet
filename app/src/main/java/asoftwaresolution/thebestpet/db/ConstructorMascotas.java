package asoftwaresolution.thebestpet.db;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;

import asoftwaresolution.thebestpet.R;
import asoftwaresolution.thebestpet.pojo.Mascota;
import asoftwaresolution.thebestpet.pojo.Usuario;
import asoftwaresolution.thebestpet.restApi.ConstantesRestApi;

/**
 * Created by AdderlyS on 13/10/2017.
 */

public class ConstructorMascotas {

    private static final int LIKE = 1;
    private Context context;

    public ConstructorMascotas(Context context) {
        this.context = context;
    }

    public ArrayList<Mascota> obtenerDatos() {
        /*ArrayList<Mascota> mascotas = new ArrayList<Mascota>();
        mascotas.add(new Mascota(R.drawable.dog1, "Elvis", 3));
        mascotas.add(new Mascota(R.drawable.dog2, "Zoey", 2));
        mascotas.add(new Mascota(R.drawable.dog3, "Hunter", 6));
        mascotas.add(new Mascota(R.drawable.dog4, "Izzy", 3));
        mascotas.add(new Mascota(R.drawable.dog5, "Kobe", 4));
        mascotas.add(new Mascota(R.drawable.dog6, "Sasha", 2));
        mascotas.add(new Mascota(R.drawable.dog7, "Trixie", 6));
        mascotas.add(new Mascota(R.drawable.dog8, "Rufus", 2));
        mascotas.add(new Mascota(R.drawable.dog9, "Simba", 5));
        mascotas.add(new Mascota(R.drawable.dog10, "Cookie", 7));*/
        BaseDatos db = new BaseDatos(context);
        //db.limpiarDB();
        insertarMascotas(db);
        return db.obtenerTodasLasMascotas();
    }

    public void insertarMascotas(BaseDatos db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_IMAGEN, R.drawable.dog1);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Elvis");

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_IMAGEN, R.drawable.dog2);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Zoey");

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_IMAGEN, R.drawable.dog3);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Hunter");

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_IMAGEN, R.drawable.dog4);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Izzy");

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_IMAGEN, R.drawable.dog5);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Kobe");

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_IMAGEN, R.drawable.dog6);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Sasha");

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_IMAGEN, R.drawable.dog7);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Trixie");

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_IMAGEN, R.drawable.dog8);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Rufus");

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_IMAGEN, R.drawable.dog9);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Simba");

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_IMAGEN, R.drawable.dog10);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Cookie");

        db.insertarMascota(contentValues);
    }

    public void darLikeMascota(Mascota mascota) {
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID_MASCOTA, mascota.getId());
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_LIKES, LIKE);
        db.insertarLikeMascota(contentValues);
    }

    public void insertarUsuarioDB(String id_instagram, String id_firebase, String usuario) {
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_ID_USUARIO_INSTAGRAM, id_instagram);
        contentValues.put(ConstantesBaseDatos.TABLE_ID_USUARIO_FIREBASE, id_firebase);
        contentValues.put(ConstantesBaseDatos.TABLE_USUARIO_USERNAME, usuario);
        db.insertarUsuario(contentValues);
    }

    public void insertarIdFirebaseUsuarioDB(String id_firebase)
    {
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_ID_USUARIO_FIREBASE, id_firebase);
        db.insertarIdFirebaseUsuario(contentValues);
    }

    public int obtenerLikesMascota(Mascota mascota) {
        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikesMascota(mascota);
    }

    public ArrayList<Mascota> obtenerMascotasFavoritas() {
        BaseDatos db = new BaseDatos(context);
        insertarMascotas(db);
        return db.obtenerMascotasPopulares();
    }

    public ArrayList<Usuario> obtenerUsuarioRegistrado() {
        BaseDatos db = new BaseDatos(context);
        return db.obtenerUsuarioRegistrado();
    }
}
