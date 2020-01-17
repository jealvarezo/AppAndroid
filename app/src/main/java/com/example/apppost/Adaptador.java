package com.example.apppost;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {

    private static LayoutInflater inflater = null;

    Context contexto;
    ArrayList<Post> postApi;

    public Adaptador(Context contexto, ArrayList<Post> postApi){
        this.contexto = contexto;
        this.postApi = postApi;
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View vista = inflater.inflate(R.layout.elemento_lista, null);

        TextView id = (TextView) vista.findViewById(R.id.tvId);
        TextView titulo = (TextView) vista.findViewById(R.id.tvTitulo);
        ImageView image = (ImageView) vista.findViewById(R.id.imImagen);
        RatingBar calificacion = (RatingBar) vista.findViewById(R.id.ratingBarPel);

        id.setText(postApi.get(position).getId());
        titulo.setText(postApi.get(position).getTitle());
        image.setImageResource(R.drawable.imagen);

        calificacion.setProgress(Integer.valueOf(postApi.get(position).getCalificacion()));

        image.setTag(position);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent visorImagen = new Intent(contexto, VisorImagen.class);
                visorImagen.putExtra("IMG", postApi.get((Integer)v.getTag()).getId());
                contexto.startActivity(visorImagen);
            }
        });

        return vista;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
