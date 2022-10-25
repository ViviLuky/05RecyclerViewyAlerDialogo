package com.vivianafemenia.a05_recyclerviewyalerdialogo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vivianafemenia.a05_recyclerviewyalerdialogo.adapters.ToDoAdapter;
import com.vivianafemenia.a05_recyclerviewyalerdialogo.databinding.ActivityMainBinding;
import com.vivianafemenia.a05_recyclerviewyalerdialogo.modelos.ToDo;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private ArrayList<ToDo> todoList;
    private ToDoAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        todoList=new ArrayList<>();
        //creaTareas();

        adapter = new ToDoAdapter(todoList, R.layout.todo_view_model, MainActivity.this);
        binding.contentMain.contenedor.setAdapter(adapter);
        //layoutManager = new LinearLayoutManager(MainActivity.this);
        layoutManager = new GridLayoutManager(this,2);
        binding.contentMain.contenedor.setLayoutManager(layoutManager);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createToDo().show();
            }
        });


    }

     private AlertDialog createToDo(){

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("CREATE ToDo");
        builder.setCancelable(false);

        View todoAlert = LayoutInflater.from(this).inflate(R.layout.todo_model_alert,null);
         EditText txtTitulo = todoAlert.findViewById(R.id.txtTituloTodoModelAlert);
         EditText txtContenido = todoAlert.findViewById(R.id.txtContenidoTodoModelAlert);
         builder.setView(todoAlert);

         builder.setNegativeButton("Cancelar",null);
         builder.setPositiveButton("Crear", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialogInterface, int i) {
                 if(!txtTitulo.getText().toString().isEmpty() && !txtContenido.getText().toString().isEmpty()){

                     todoList.add(new ToDo(txtTitulo.getText().toString(), txtContenido.getText().toString()));
                     adapter.notifyDataSetChanged();

                 }
                 else {
                     Toast.makeText(MainActivity.this, "Faltan Datos", Toast.LENGTH_SHORT).show();
                 }
             }
         });
         return builder.create();
     }

    private void creaTareas() {
        for (int i = 0; i < 1000000; i++) {
            todoList.add(new ToDo("Titulo "+i,"Contenido "+i));

        }
    }



}