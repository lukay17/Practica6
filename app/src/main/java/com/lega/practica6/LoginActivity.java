package com.lega.practica6;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lega.practica6.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private int REQUEST_CODE_OBJECT_ACTIVITY=100;
    private int contador = 0;
    private String name = "";
    private String surname = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.ALImage.setImageResource(R.drawable.city);

        listener();
    }

    private void listener() {
        binding.ALUsernameText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(binding.ALUsernameText.getText().toString() != null && binding.ALUsernameText.getText().toString().length() > 0) {
                    contador +=1 ;
                }else{
                    contador = 0 ;
                }

                if(contador > 1){
                    binding.ALButtonAccess.setEnabled(true);
                }else{
                    binding.ALButtonAccess.setEnabled(false);
                }
            }
        });

        binding.ALPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(binding.ALPasswordText.getText().toString() != null && binding.ALPasswordText.getText().toString().length() > 0) {
                    contador +=1 ;
                }else{
                    contador = 0 ;
                }

                if(contador > 1){
                    binding.ALButtonAccess.setEnabled(true);
                }else{
                    binding.ALButtonAccess.setEnabled(false);
                }
            }
        });

        binding.ALButtonAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.toString() ==null && name.toString().length()==0 && surname.toString() ==null && surname.toString().length()==0){
                    Toast.makeText(LoginActivity.this, "Debes Ingresar Usuario y Password", Toast.LENGTH_SHORT).show();
                }else{
                    if(name.toString().equals(binding.ALUsernameText.getText().toString())){
                        if(surname.toString().equals(binding.ALPasswordText.getText().toString())){
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            intent.putExtra("name", binding.ALUsernameText.getText().toString());
                            intent.putExtra("surname", binding.ALPasswordText.getText().toString());
                            startActivity(intent);
                        }else{
                            Toast.makeText(LoginActivity.this, "Password Incorrecto", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(LoginActivity.this, "Usuario Incorrecto", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binding.ALTxtCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, REQUEST_CODE_OBJECT_ACTIVITY);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_OBJECT_ACTIVITY && resultCode ==RESULT_OK){
            name = data.getExtras().getString("name");
            surname = data.getExtras().getString("surname");
            Log.e("lega",name);
            Log.e("lega",surname);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}