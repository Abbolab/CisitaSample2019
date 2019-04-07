package com.abbolab.cisitasample2019.adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Debug;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.abbolab.cisitasample2019.MainActivity;
import com.abbolab.cisitasample2019.R;
import com.abbolab.cisitasample2019.model.User;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class UserAdapters extends RecyclerView.Adapter<UserAdapters.ViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<User> dataSet;

    public UserAdapters(Context context, ArrayList<User> dataSet) {

        this.inflater = LayoutInflater.from(context);
        this.dataSet = dataSet;
    }

    // inflate del layout per ogni row della lista
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.user_list_item, parent, false);
        return new ViewHolder(view);
    }

    // ottengo view holder relativo alla row per farne il bind dei dati
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // ottengo oggetto da dataset corrispondente all'indice della riga
        User currentUser = dataSet.get(position);

        holder.textViewName.setText(currentUser.name + " " + currentUser.surname);
        holder.textViewDate.setText(currentUser.dateRegistration.toString());
        holder.textViewAge.setText("Age: " + String.valueOf(currentUser.age)+ " - Pos: " + position);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    // creo una view holder per ogni row della lista da reciclare in base alla posizione dello scroll
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewName;
        TextView textViewDate;
        TextView textViewAge;

        ViewHolder(View itemView) {

            super(itemView);

            textViewName = (TextView)itemView.findViewById(R.id.userItemName);
            textViewDate = (TextView)itemView.findViewById(R.id.userItemDate);
            textViewAge = (TextView)itemView.findViewById(R.id.userItemAge);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            Log.d(MainActivity.K_TAG, "Row selezionata posizione: " + getAdapterPosition());
        }
    }

}
