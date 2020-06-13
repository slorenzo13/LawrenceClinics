package com.example.lawrenceclinics;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lawrenceclinics.api.respuestas.CitaPendiente;
import com.example.lawrenceclinics.api.respuestas.CitasPendientes;
import com.example.lawrenceclinics.api.respuestas.DatosEspecialidad;

import java.util.List;

public class CitasPendientesAdapter extends RecyclerView.Adapter<CitasPendientesAdapter.VH> {

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    interface Listener {
        void onCitasPendientesClick(CitaPendiente cp);
    }

    private List<CitaPendiente> items;
    private Listener listener;

    public CitasPendientesAdapter(List<CitaPendiente> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cita_pendiente_item,parent,false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        CitaPendiente item = items.get(position);

        holder.fechaPendiente.setText(item.getFecha());
        holder.horaPendiente.setText(item.getHora());
        holder.areaPendiente.setText(item.getAreaDoctor());
        holder.doctorPendiente.setText(item.getNombreDoctor());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class VH extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView fechaPendiente, horaPendiente, areaPendiente, doctorPendiente;
        private Button cancelar;

        public VH(@NonNull View itemView) {
            super(itemView);

            this.fechaPendiente = itemView.findViewById(R.id.fechaTv);
            this.horaPendiente = itemView.findViewById(R.id.horaTv);
            this.areaPendiente = itemView.findViewById(R.id.areaTv);
            this.cancelar = itemView.findViewById(R.id.cancelarBt);
            this.doctorPendiente = itemView.findViewById(R.id.doctorTv);
            this.cancelar.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onCitasPendientesClick(items.get(getAdapterPosition()));
        }
    }

}
