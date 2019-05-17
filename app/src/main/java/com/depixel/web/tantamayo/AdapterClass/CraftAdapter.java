package com.depixel.web.tantamayo.AdapterClass;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.depixel.web.tantamayo.Entity.CraftClass;
import com.depixel.web.tantamayo.R;

import java.util.List;

public class CraftAdapter extends ArrayAdapter<CraftClass> {

    private Context contextx;

    public CraftAdapter(@NonNull Context context, List<CraftClass> objects) {
        super(context, 0,objects);
        this.contextx = context;
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
                    R.layout.list_item_craft,
                    parent,
                    false);
        }

        // Referencias UI.
        ImageView avatar = (ImageView) convertView.findViewById(R.id.craft_imagen);
        TextView nameCraft = (TextView) convertView.findViewById(R.id.name_craft_text);
        TextView scheduleCraft = (TextView) convertView.findViewById(R.id.schedule_craft_text);
        ImageView numberWast = (ImageView) convertView.findViewById(R.id.image_wasa);

        // Lead actual.
        final CraftClass miclass = getItem(position);
        // Setup.
        //  Glide.with(getContext()).load(miclass.getImagenRoute()).into(avatar);
        avatar.setImageResource(miclass.getPhotoCraft());
        nameCraft.setText(miclass.getNameCraft());
              //  nameRoute.setBackgroundColor(Color.DKGRAY);
              //  nameRnameCraftoute.setTextColor(Color.WHITE);
        scheduleCraft.setText(miclass.getScheduleraft());
        numberWast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.setAction(Intent.ACTION_VIEW);
                sendIntent.setPackage("com.whatsapp");
                String url = "https://api.whatsapp.com/send?phone=" + miclass.getNumberCraft() + "&text=" + "your message";
                sendIntent.setData(Uri.parse(url));
                contextx.startActivity(sendIntent);

            }
        });

        return convertView;
        //return super.getView(position, convertView, parent);
    }
}
