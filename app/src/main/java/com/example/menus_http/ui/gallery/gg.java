package com.example.menus_http.ui.gallery;

import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

public class gg {
    private void iniciarFirebase() {
        // Write a message to the database

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Usuarios");
        Toast.makeText(getActivity().getApplicationContext(),"Dentro a direbase",Toast.LENGTH_SHORT).show();

        myRef.setValue("hola");
    }

}
