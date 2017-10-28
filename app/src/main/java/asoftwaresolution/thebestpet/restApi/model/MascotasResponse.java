package asoftwaresolution.thebestpet.restApi.model;

import java.util.ArrayList;

import asoftwaresolution.thebestpet.pojo.Mascota;

/**
 * Created by AdderlyS on 25/10/2017.
 */

public class MascotasResponse {
    ArrayList<Mascota> mascotas;

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
}
