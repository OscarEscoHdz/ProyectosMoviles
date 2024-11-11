package com.example.proyectofinalescobedo;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;


public class Citas extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_citas);


        tabLayout = findViewById(R.id.tableLayout);
        viewPager = findViewById(R.id.viewrPager);

        tabLayout.setupWithViewPager(viewPager);

        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new CitaFragment(), "Agendar cita");
        vpAdapter.addFragment(new ConsultaCitaFragment(), "Consulta");
        viewPager.setAdapter(vpAdapter);

    }
}