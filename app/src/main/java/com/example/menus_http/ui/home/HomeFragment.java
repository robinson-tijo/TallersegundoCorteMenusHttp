package com.example.menus_http.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.menus_http.R;
import com.example.menus_http.ui.models.Usuario;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    EditText editTextCedula;
    EditText editTextNombre;
    EditText editTextApellido;
    EditText editTextTelefono;
    EditText editTextCorreo;
    EditText editTextContrasena;
    Button buttonRegistrarUsuario;

    FirebaseDatabase database;
    DatabaseReference myRef;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = v.findViewById(R.id.text_home2);

        editTextCedula = v.findViewById(R.id.editTextCedula);
        editTextNombre = v.findViewById(R.id.editTextNombre);
        editTextApellido = v.findViewById(R.id.editTextApellido);
        editTextTelefono = v.findViewById(R.id.editTextTelefono);
        editTextCorreo = v.findViewById(R.id.editTextCorreo);
        editTextContrasena = v.findViewById(R.id.editTextContrasena);
        buttonRegistrarUsuario = v.findViewById(R.id.buttonRegistrarUsuario);
        iniciarFirebase();
        

        

        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        buttonRegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validarCampos();
            }
        });
        return v;
    }



    private void iniciarFirebase() {
        // Write a message to the database

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Usuarios");
        Toast.makeText(getActivity().getApplicationContext(),"Dentro a direbase",Toast.LENGTH_SHORT).show();

        //myRef.setValue("hola");
    }



    public void limpiarCampos(){
        editTextNombre.setText("");
        editTextApellido.setText("");
        editTextCedula.setText("");
        editTextCorreo.setText("");
        editTextContrasena.setText("");
        editTextTelefono.setText("");
    }
}