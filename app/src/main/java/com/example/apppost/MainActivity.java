package com.example.apppost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ArrayList<Post> postApi = new ArrayList<>();

    ListView lista;
    String[][] datos = {
            {"Matematicas", "Jorge Alvarez", "2:39", "10", "Materia ofrecida por cortesia"},
            {"Sociales", "Jorge Alvarez", "2:39", "10", "Materia ofrecida por cortesia"},
            {"Artistica", "Jorge Alvarez", "2:39", "10", "Materia ofrecida por cortesia"},
            {"Fisica", "Jorge Alvarez", "2:39", "10", "Materia ofrecida por cortesia"},
            {"Programacion", "Jorge Alvarez", "2:39", "10", "Materia ofrecida por cortesia"},
            {"Analisis", "Jorge Alvarez", "2:39", "10", "Materia ofrecida por cortesia"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView) findViewById(R.id.lvLista);
        getPosts();

        lista.setAdapter(new Adaptador(this, postApi));

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent visorDetalles = new Intent(view.getContext(), DetallesPelicula.class);
                visorDetalles.putExtra("TIT", datos[position][0]);
                visorDetalles.putExtra("DET", datos[position][4]);
                startActivity(visorDetalles);
            }
        });
    }

    private void getPosts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PostService postService = retrofit.create(PostService.class);
        Call<List<Post>> call = postService.getPost();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                for(Post post : response.body()) {
                    postApi.add(post);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
            }
        });
    }
}
