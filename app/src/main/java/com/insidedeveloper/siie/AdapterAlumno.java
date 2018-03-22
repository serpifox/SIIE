package com.insidedeveloper.siie;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Malos on 22/03/2018.
 */

public class AdapterAlumno extends RecyclerView.Adapter<AdapterAlumno.AnimeViewHolder> {
    private List<Alumno> items;

    public static class AnimeViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public ImageView imagen;
        public TextView nombre;
        public TextView amaterno;
        public TextView apaterno;
        public TextView email, numemp, puesto;


        public AnimeViewHolder(View v) {
            super(v);
            imagen = (ImageView) v.findViewById(R.id.fotoPerfilMensaje);
            nombre = (TextView) v.findViewById(R.id.nombre);
            apaterno = (TextView) v.findViewById(R.id.paterno);
            amaterno = (TextView) v.findViewById(R.id.materno);
            email= (TextView) v.findViewById(R.id.correo);
            numemp=(TextView) v.findViewById(R.id.Nrc);
            puesto=(TextView) v.findViewById(R.id.puesto);

        }
    }

    public AdapterAlumno(List<Alumno> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public AdapterAlumno.AnimeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.anime_card, viewGroup, false);
        //regresamos la tarjeta creada
        return new AdapterAlumno.AnimeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterAlumno.AnimeViewHolder viewHolder, int i) {
        //construimos la tarjeta
        viewHolder.nombre.setText(items.get(i).getNombre());
        viewHolder.amaterno.setText(items.get(i).getMaterno());
        viewHolder.apaterno.setText(items.get(i).getPaterno());
        viewHolder.email.setText(items.get(i).getCorreo());
        viewHolder.numemp.setText(items.get(i).getMatricula());




    }

}
