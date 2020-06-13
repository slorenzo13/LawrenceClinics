package com.example.lawrenceclinics;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lawrenceclinics.api.respuestas.DatosEspecialidad;
import com.example.lawrenceclinics.api.respuestas.HorarioDisponible;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HorariosDisponiblesAdapter extends RecyclerView.Adapter<HorariosDisponiblesAdapter.VH> {

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    interface Listener {
        void onHorarioDisponibleClick(HorarioDisponible h);
    }

    private List<HorarioDisponible> items;
    private Listener listener;

    public HorariosDisponiblesAdapter(List<HorarioDisponible> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horario_disponible_item,parent,false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        HorarioDisponible item = items.get(position);
        
        holder.horario.setText(item.horarioFormateado());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class VH extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView horario;

        public VH(@NonNull View itemView) {
            super(itemView);
            this.horario = itemView.findViewById(R.id.horarioTv);
            this.itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onHorarioDisponibleClick(items.get(getAdapterPosition()));
        }
    }

}
