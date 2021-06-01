package com.example.menus_http.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.menus_http.R;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    TextView textViewUrl;
    RequestQueue requestQueue;

    private  static final String url = "https://www.etnassoft.com/api/v1/get/?id=589";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View v = inflater.inflate(R.layout.fragment_gallery, container, false);
        textViewUrl = v.findViewById(R.id.textViewUrl);
        final TextView textView = v.findViewById(R.id.text_gallery);

        requestQueue = Volley.newRequestQueue(getContext());
        stringRequest();
        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return v;
    }

    public void stringRequest(){

        StringRequest request =  new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        textViewUrl.setText(response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);


    }
}