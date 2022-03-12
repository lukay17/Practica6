package com.lega.practica6;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.lega.practica6.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.lega.practica6.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private String welcome = "";
    private String name = "";
    private String surname = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadComponents();

        if(getIntent().getExtras() != null){
            name = getIntent().getExtras().getString("name", "No hay Nombre");
            surname = getIntent().getExtras().getString("surname", "No hay Apellido");
            Log.e("lega",name);
            Log.e("lega",surname);
            welcome = "Hi, " + name + " " + surname;
            binding.AHUser.setText(welcome);
        }

        binding.toolbar.setNavigationIcon(R.drawable.home);

        setSupportActionBar(binding.toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.euro) {
            openWeb("https://developers.google.com/ml-kit/terms");
            return true;
        }

        if (item.getItemId() == R.id.rent) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new IconFragment()).commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadComponents() {
        binding.AHTitle.setText(R.string.home_title);

        binding.AHCardImage.setImageResource(R.drawable.calendar);

        binding.AHCardTitle1.setText(R.string.title1);

        binding.AHCardSubtitle1.setText(R.string.subtitle1);

        binding.AHCardImage2.setImageResource(R.drawable.tree);

        binding.AHCardTitle2.setText(R.string.title2);

        binding.AHCardSubtitle2.setText(R.string.subtitle2);
    }

    public void openWeb(String url){
        Uri web= Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW,web);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }
}