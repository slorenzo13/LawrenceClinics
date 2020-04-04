
package com.example.lawrenceclinics.api.respuestas;

import com.example.lawrenceclinics.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatosEspecialidad {

    @SerializedName("nombre_area")
    @Expose
    private String nombreArea;
    @SerializedName("id_area")
    @Expose
    private String idArea;

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    public String getIdArea() {
        return idArea;
    }

    public void setIdArea(String idArea) {
        this.idArea = idArea;
    }

    public static int obtenerImagen(DatosEspecialidad d) {
        switch (d.idArea) {
            case "1":
                return R.drawable.icono_traumatologia;
            case "2":
                return R.drawable.icono_cardiologia_2;
            case "3":
                return R.drawable.icono_obstetricia;
            case "4":
                return R.drawable.icono_ginecologia;
            case "5":
                return R.drawable.icono_pediatria;
            case "6":
                return R.drawable.icono_alergologia;
            case "7":
                return R.drawable.logo_dermatologia;
            case "8":
                return R.drawable.logo_endocrinologia;
            default:
                return R.drawable.ic_launcher_background;
        }
    }


}

