package com.example.djnig.tantamayo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class PromotionFragment extends Fragment {
    Button reservar ;
    private TextView textHistoryJustify;
    String itinerariDetails="1° Día, Viernes Santo; 19 de abril." +
            "\n2:00 am Salida del Jr. San Martin N° 558 rumbo a Tantamayo" +
            "\n8:00 am Desayuno en Pampa Florida, bienvenida a la fiesta de Semana Santa 2019." +
            "\n9:00 am Trekking Ruta # 2 de la Hoja Sagrada, Susupillo. Isog y Jipango." +
            "\n2:00 pm Almuerzo en Pampa Florida." +
            "\n3:00 pm La vía crucis en la plaza e Iglesia de Pampa Florida" +
            "\n6:30 pm Cena y pernoctar en Pampa Florida." +
            "\n2° Día, Sábado Santo; 20 de abril" +
            "\n4:00 am Procesión de la cruz para recibir el alba del amanecer." +
            "\n7:30 am Desayuno" +
            "\n11:45 am Canticos de Salmos y la muerte de Judas Iscariote en la Iglesia." +
            "\n1:30 pm Almuerzo en casa del mayordomo." +
            "\n3:00 pm Visita a las excavaciones arqueológicas del Tambo Inca – Pariash" +
            "\n6:30 pm Cena y pernoctar." +
            "\n3°Día, Domingo Santo; 21 de abril" +
            "\n8:00 am Desayuno" +
            "\n9:00 am Visita a la zona arqueológica de Piruro." +
            "\n1:00 pm Almuerzo y retorno a la ciudad de Húanuco" +
            "\nCosto: S/.150.00" +
            "\nIncluye: Pasaje ida y vuelta, 3 desayunos, 3 almuerzos, 2 cenas, 2 noches de alojamiento rural, entrada y orientador turístico (Opcional Acémila)." +
            "\nRecomendaciones: Traer consigo ropa ligera, jean, abrigo, zapatillas, bloqueador, lente de sol, gorra, cámara fotográfica y/o filmadora.\n";

    public PromotionFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_promotion, container, false);
        textHistoryJustify=(TextView) root.findViewById(R.id.texItinerario);
        textHistoryJustify.setText(itinerariDetails);
        reservar = (Button) root.findViewById(R.id.buttonReserva);
        reservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.setAction(Intent.ACTION_VIEW);
                sendIntent.setPackage("com.whatsapp");
                int numero = (int) (Math.random() * 50) + 1;
                String url = "https://api.whatsapp.com/send?phone=+51971444147&text=" + "Hola, me interesa esta promoción,mi código de promocion es PRO"+numero;
                sendIntent.setData(Uri.parse(url));
                startActivity(sendIntent);
            }
        });
        return root;
    }


}
