package com.example.djnig.tantamayo.AdapterClass;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.djnig.tantamayo.Entity.DetailRoute;
import com.example.djnig.tantamayo.R;

import java.util.List;

public class RouteAdapter extends ArrayAdapter<DetailRoute> {
    public RouteAdapter(@NonNull Context context, List<DetailRoute> objects) {
        super(context,0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Obtener inflater.
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // ¿Existe el view actual?
        if (null == convertView) {
            convertView = inflater.inflate(
                    R.layout.list_item_route,
                    parent,
                    false);
        }

        // Referencias UI.
        ImageView avatar = (ImageView) convertView.findViewById(R.id.route_imagen);
        TextView nameRoute = (TextView) convertView.findViewById(R.id.r_name);
        TextView typeRoute = (TextView) convertView.findViewById(R.id.r_type);
        TextView kmRoute = (TextView) convertView.findViewById(R.id.r_km);
        TextView stretchRoute = (TextView) convertView.findViewById(R.id.r_stretch);


        // Lead actual.
        DetailRoute miclass = getItem(position);

        // Setup.
      //  Glide.with(getContext()).load(miclass.getImagenRoute()).into(avatar);
        avatar.setImageResource(miclass.getImagenRoute());
        nameRoute.setText(miclass.getNameRoute());
        switch (position){
            case 0:
                nameRoute.setBackgroundColor(Color.MAGENTA);
                nameRoute.setTextColor(Color.WHITE);
                break;
            case 1:
                nameRoute.setBackgroundColor(Color.DKGRAY);
                nameRoute.setTextColor(Color.WHITE);
                break;
            case 2:
                nameRoute.setBackgroundColor(Color.rgb(35, 172, 16));
                nameRoute.setTextColor(Color.WHITE);
                break;
            case 3:
                nameRoute.setBackgroundColor(Color.RED);
                nameRoute.setTextColor(Color.WHITE);
                break;
            case 4:
                nameRoute.setBackgroundColor(Color.rgb(213, 158, 32));
                nameRoute.setTextColor(Color.WHITE);
                break;
            case 5:
                nameRoute.setBackgroundColor(Color.BLUE);
                nameRoute.setTextColor(Color.WHITE);
                break;
            case 6:
                nameRoute.setBackgroundColor(Color.rgb(247, 150, 9));
                nameRoute.setTextColor(Color.WHITE);
                break;
            case 7:
                nameRoute.setBackgroundColor(Color.rgb(204, 80, 178));
                nameRoute.setTextColor(Color.WHITE);
                break;
            default:
                nameRoute.setBackgroundColor(Color.rgb(204, 80, 178));
                nameRoute.setTextColor(Color.WHITE);
        }

        typeRoute.setText(miclass.getTypeRoute());
        kmRoute.setText(miclass.getKmRoute());
        stretchRoute.setText(miclass.getStretchRoute());

        return convertView;
        //return super.getView(position, convertView, parent);
    }
}
