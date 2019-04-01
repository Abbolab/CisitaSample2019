package com.abbolab.cisitasample2019.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.abbolab.cisitasample2019.MainActivity;
import com.abbolab.cisitasample2019.R;

public class HomeFragment extends Fragment {

    public HomeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ottengo pulsante dalla view tramite ID
        final Button showList = (Button)view.findViewById(R.id.btnShowList);
        // resto in "ascolto" del click per l'evento a cui associare un'azione
        showList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(MainActivity.K_TAG, "onClick pulsante ShowList");

                // invoce il metodo per mostrare nuovo fragment
                showListFragmet();
            }
        });

    }

    private void showListFragmet() {

        // creo nuova instanza di fragment
        ListFragment listFragment = new ListFragment();

        // ottengo dall'Activity un Fragment Manager per poter aggiungere il fragment instanziato sopra
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        // aggiungo il fragment al fragment Transaction tramite ID FrameLayout, istanza fragment e TAG per referenziarlo
        transaction.replace(R.id.frameLaoutMain, listFragment, "LIST_FRAGMENT");

        // aggiunto fragment al backstack
        transaction.addToBackStack(listFragment.getTag());

        // conferma ed esegui l'operazione
        transaction.commit();
    }
}
