package com.insidedeveloper.siie;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class adapterMaestro extends RecyclerView.Adapter<adapterMaestro.AnimeViewHolder> {
    private List<Maestro> items;

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

        }
    }

    public adapterMaestro(List<Maestro> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public AnimeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.anime_card, viewGroup, false);
        return new AnimeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AnimeViewHolder viewHolder, int i) {
        viewHolder.nombre.setText(items.get(i).getNombre());
        viewHolder.amaterno.setText(items.get(i).getAmaterno());
        viewHolder.apaterno.setText(items.get(i).getApaterno());
        viewHolder.email.setText(items.get(i).getEmail());

    }
}
