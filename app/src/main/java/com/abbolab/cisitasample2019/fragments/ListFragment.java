package com.abbolab.cisitasample2019.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.abbolab.cisitasample2019.R;
import com.abbolab.cisitasample2019.adapters.UserAdapters;
import com.abbolab.cisitasample2019.model.User;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class ListFragment extends Fragment  {

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ottengo recycler view dal layout (view) tramite ID
        recyclerView = (RecyclerView) view.findViewById(R.id.userRecyclerView);
        // definisco la tipologia di layout manager che mi serve per la renderizzazione
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        // mostro lista statica
        renderStaticList();


        // eseguo download dei dati
        //executeDownloadData();
    }

    /***
     * Avvio procedura di donwload dati interrogando WebService
     */
    private void executeDownloadData() {

        Log.d("CISITA", ">>>> executeDownloadData()");

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://private-241152-cisitatest.apiary-mock.com/users", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                Log.d("CISITA", "AsyncHTTPClient - onSuccess :-) !!!");

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                Log.d("CISITA", "AsyncHTTPClient - onFailure :-(");

            }
        });
    }

    private void renderStaticList() {

        // creo data set statico
        ArrayList<User> users = new ArrayList<>();
        for(int index = 0; index < 20; index++) {
            users.add(new User());
        }

        // istanzio adapter per gestire il bind tra dati e renderizzazione
        UserAdapters userAdapters = new UserAdapters(getContext(), users);
        // setto alla recycler view l'adpater da usare
        recyclerView.setAdapter(userAdapters);

    }
}
