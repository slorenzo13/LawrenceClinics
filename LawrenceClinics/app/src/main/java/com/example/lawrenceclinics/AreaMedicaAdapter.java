package com.example.lawrenceclinics;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lawrenceclinics.api.respuestas.DatosEspecialidad;

import java.util.List;

public class AreaMedicaAdapter extends RecyclerView.Adapter<AreaMedicaAdapter.VH> {

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    interface Listener {
        void onAreaMedicaClick(DatosEspecialidad d);
    }

    private List<DatosEspecialidad> items;
    private Listener listener;

    public AreaMedicaAdapter(List<DatosEspecialidad> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.area_medica_item,parent,false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        DatosEspecialidad item = items.get(position);

        holder.nombreArea.setText(item.getNombreArea());
        holder.imagen.setImageResource(DatosEspecialidad.obtenerImagen(item));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class VH extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView nombreArea;
        private ImageView imagen;

        public VH(@NonNull View itemView) {
            super(itemView);
            this.imagen = itemView.findViewById(R.id.areaIv);
            this.nombreArea = itemView.findViewById(R.id.nombreAreaTv);
        }

        @Override
        public void onClick(View v) {
            listener.onAreaMedicaClick(items.get(getAdapterPosition()));
        }
    }

}
