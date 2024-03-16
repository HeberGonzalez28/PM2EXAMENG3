package com.example.pm2exameng3;

import android.content.Context;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.pm2exameng3.Config.Personas;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Personas> {

    private List<Personas> mList;
    private Context mContext;
    private int resourceLayout;

    public CustomAdapter(@NonNull Context context, int resource, List<Personas> objects) {
        super(context, resource, objects);
        this.mList = objects;
        this.mContext = context;
        this.resourceLayout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(resourceLayout, parent, false);
            holder = new ViewHolder();
            holder.imagen = view.findViewById(R.id.imageView2);
            holder.nombres = view.findViewById(R.id.nombres);
            holder.telefono = view.findViewById(R.id.telefono);
            holder.latitud = view.findViewById(R.id.latitud);
            holder.longitud = view.findViewById(R.id.longitud);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Personas person = mList.get(position);

        // Establecer la imagen si tienes el recurso de imagen en tu clase Personas
        // Decodificar la cadena Base64 en un objeto Bitmap
        Bitmap bitmap = decodeBase64(person.getFoto());
        holder.imagen.setImageBitmap(bitmap);

        holder.nombres.setText(person.getNombres());
        holder.telefono.setText(person.getTelefono());
        holder.latitud.setText(person.getLatitud());
        holder.longitud.setText(person.getLongitud());

        return view;
    }

    // Método para decodificar una cadena Base64 en un objeto Bitmap
    private Bitmap decodeBase64(String base64String) {
        byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }

    static class ViewHolder {
        ImageView imagen;
        TextView nombres;
        TextView telefono;
        TextView latitud;
        TextView longitud;
    }

}
