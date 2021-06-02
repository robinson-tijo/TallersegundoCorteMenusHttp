package  com.example.menus_http.ui.slideshow;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import com.example.menus_http.AdmininistrasBdSqlite;
import com.example.menus_http.R;

public class SlideshowFragment extends Fragment {

    EditText editTextCedula;
    EditText editTextNombre;
    EditText editTextApellido;
    EditText editTextTelefono;
    EditText editTextCorreo;

    Button buttonConsultarUsuario,buttonModificarUsuario;

    private SlideshowViewModel slideshowViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View v = inflater.inflate(R.layout.fragment_slideshow, container, false);
        final TextView textView = v.findViewById(R.id.text_slideshow);

        editTextCedula = v.findViewById(R.id.editTextCedula);
        editTextNombre = v.findViewById(R.id.editTextNombre);
        editTextApellido = v.findViewById(R.id.editTextApellido);
        editTextTelefono = v.findViewById(R.id.editTextTelefono);
        editTextCorreo = v.findViewById(R.id.editTextCorreo);
        buttonModificarUsuario = v.findViewById(R.id.buttonModificarUsuario);
        buttonConsultarUsuario = v.findViewById(R.id.buttonConsultarUsuario);
        buttonConsultarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarUsuario();
            }
        });

        buttonModificarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificarUsuario();
            }
        });

        slideshowViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return v;
    }

    public void buscarUsuario(){
        AdmininistrasBdSqlite admininistrarBdSqlite = new AdmininistrasBdSqlite(getContext(),"administracion",null,1);
        SQLiteDatabase BaseDeDatos =admininistrarBdSqlite.getWritableDatabase();

        String cedula = editTextCedula.getText().toString();


        if( cedula.equals("")){

            Toast.makeText(getActivity().getApplicationContext(),"PORFAVOR LLENE EL CAMPO DE CEDULA",Toast.LENGTH_SHORT).show();

        }else{

            Cursor fila = BaseDeDatos.rawQuery
                    ("select nombre, apellido,telefono, correo from usuarios where cedula="+cedula,null);
            if(fila.moveToFirst()){
                editTextNombre.setText(fila.getString(0));
                editTextApellido.setText(fila.getString(1));
                editTextTelefono.setText(fila.getString(2));
                editTextCorreo.setText(fila.getString(3));
                BaseDeDatos.close();

            }else{
                Toast.makeText(getActivity().getApplicationContext(),"EL USUARIO NO EXISTE",Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
                limpiarCampos();
            }

        }

    }

    public void modificarUsuario(){

        AdmininistrasBdSqlite admininistrarBdSqlite = new AdmininistrasBdSqlite(getContext(),"administracion",null,1);
        SQLiteDatabase BaseDeDatos =admininistrarBdSqlite.getWritableDatabase();

        String nombre = editTextNombre.getText().toString();
        String apellido = editTextApellido.getText().toString();
        String cedula = editTextCedula.getText().toString();
        String telefono = editTextTelefono.getText().toString();
        String correo = editTextCorreo.getText().toString();

        if( nombre.equals("") || apellido.equals("") || cedula.equals("") || telefono.equals("")
                || correo.equals("") ){

            Toast.makeText(getActivity().getApplicationContext(),"PORFAVOR LLENAR TODOS LOS CAMPOS",Toast.LENGTH_SHORT).show();

        }else{

            ContentValues registro = new ContentValues();
            registro.put("cedula",cedula);
            registro.put("nombre",nombre);
            registro.put("apellido",apellido);
            registro.put("telefono",telefono);
            registro.put("correo",correo);

            int cedulaa = BaseDeDatos.update("usuarios", registro,"cedula="+cedula,null);
            BaseDeDatos.close();

            if ( cedulaa == 1 ){
                Toast.makeText(getActivity().getApplicationContext(),"Se modifico correctamente",Toast.LENGTH_SHORT).show();
                limpiarCampos();

            }else {
                Toast.makeText(getActivity().getApplicationContext(),"El usuario no existe",Toast.LENGTH_SHORT).show();

            }
        }
    }
    public void limpiarCampos(){
        editTextNombre.setText("");
        editTextApellido.setText("");
        editTextCedula.setText("");
        editTextCorreo.setText("");
        editTextTelefono.setText("");
    }
}