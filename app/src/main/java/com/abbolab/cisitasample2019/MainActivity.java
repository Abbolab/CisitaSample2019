package com.abbolab.cisitasample2019;

import android.Manifest;
import android.os.Debug;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.abbolab.cisitasample2019.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity  {

    // TAG globale
    public static String K_TAG = "CISITA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(K_TAG, "OnCreate MainActivity");

        instantiateHomeFragment();
    }

    private void instantiateHomeFragment() {

        // creo nuova instanza di fragment
        HomeFragment homeFragment = new HomeFragment();

        // ottengo dall'Activity un Fragment Manager per poter aggiungere il fragment instanziato sopra
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // aggiungo il fragment al fragment Transaction tramite ID FrameLayout, istanza fragment e TAG per referenziarlo
        transaction.add(R.id.frameLaoutMain, homeFragment, "HOME_FRAGMENT");

        // conferma ed esegui l'operazione
        transaction.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(K_TAG, "onResume MainActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(K_TAG, "onDestroy MainActivity");
    }
}
