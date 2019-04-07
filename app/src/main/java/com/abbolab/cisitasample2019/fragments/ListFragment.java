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

import com.abbolab.cisitasample2019.MainActivity;
import com.abbolab.cisitasample2019.R;
import com.abbolab.cisitasample2019.adapters.UserAdapters;
import com.abbolab.cisitasample2019.model.User;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
        executeDownloadData();
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

                try {
                    // converto array di bytes in stringa UTF-8
                    String jsonString = new String(responseBody, "UTF-8"); // for UTF-8 encoding
                    // converto stringa in oggetto JSONObject per la serializzazione
                    JSONObject jsonObject = new JSONObject(jsonString);
                    Log.d(MainActivity.K_TAG, "JSON Responde: " + jsonObject.toString());
                    //Ottengo array di utenti
                    JSONArray userArray = jsonObject.getJSONArray("users");

                    // creo array list per l'aggiunta di oggetti creati dal parsing
                    ArrayList<User> usersDataSet = new ArrayList<User>();

                    //ciclo anagrafiche contenuti in JSON array
                    for(int index=0; index < userArray.length(); index++) {
                        // ottengo utente dal array tramite indice
                        JSONObject jsonUser = userArray.getJSONObject(index);
                        // istanzio nuovo oggetto User
                        User user = new User();
                        // mappo proprietà JSON nell'oggetto User
                        user.name = jsonUser.getString("name");
                        user.surname = jsonUser.getString("surname");
                        //ottengo Date da stringa tramite formattazione ISO
                        DateFormat format = new SimpleDateFormat("yyyy-dd-mm HH:mm", Locale.ITALIAN);
                        Date date = format.parse(jsonUser.getString("dateRegistration"));
                        user.dateRegistration = date;

                        user.age = jsonUser.getInt("age");

                        // aggiungo oggetto User popolato al dataset
                        usersDataSet.add(user);
                    }

                    // ricarico adapter con nuovi contenuti
                    renderDinamicList(usersDataSet);

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }


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

    private void renderDinamicList(ArrayList<User> users) {

        // istanzio adapter per gestire il bind tra dati e renderizzazione
        UserAdapters userAdapters = new UserAdapters(getContext(), users);
        // setto alla recycler view l'adpater da usare
        recyclerView.setAdapter(userAdapters);

    }
}
