package com.example.masterservices;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static com.example.masterservices.R.id;
import static com.example.masterservices.R.id.nav_service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.masterservices.presentation.auth.LoginFragment;
import com.example.masterservices.presentation.main.ApplicationFragment;
import com.example.masterservices.presentation.main.MasterFragment;
import com.example.masterservices.presentation.main.ServiceFragment;
import com.example.masterservices.presentation.main.ProfileFragment;
import com.example.masterservices.util.Constants;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;
    private TextView toolbarText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SharedPreferences sharedPreferences = this.getSharedPreferences(new Constants().USER_KEY_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        toolbar = findViewById(id.toolbar);
        toolbarText = findViewById(id.toolbar_text);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                if (item.getItemId() == nav_service) {
                    selectedFragment = new ServiceFragment();
                    toolbarText.setText("Услуги");
                } else if (item.getItemId() == id.nav_master) {
                    selectedFragment = new MasterFragment(bottomNavigationView);
                    toolbarText.setText("Мастера");
                } else if (item.getItemId() == id.nav_application) {
                    selectedFragment = new ApplicationFragment();
                    toolbarText.setText("Заявки");
                } else if (item.getItemId() == id.nav_profile) {
                    selectedFragment = new ProfileFragment();
                    toolbarText.setText("Профиль");
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                }
                return true;
            }
        });
        String value = sharedPreferences.getString(new Constants().USER_AUTH_KEY_PREFERENCE, new Constants().Unauthorized);
        if (value.equals(new Constants().Unauthorized)) {
            LoginFragment loginFragment = new LoginFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, loginFragment)
                    .commit();
        } else {
            onAuthSuccess();
        }
    }

    public void onAuthSuccess() {
        bottomNavigationView.setVisibility(VISIBLE);
        toolbar.setVisibility(VISIBLE);
        bottomNavigationView.setSelectedItemId(R.id.nav_service); // Показать HomeFragment по умолчанию
    }
    public void onAuthGo() {
        bottomNavigationView.setVisibility(INVISIBLE);
        toolbar.setVisibility(INVISIBLE);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

    }
}