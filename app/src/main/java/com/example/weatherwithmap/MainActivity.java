package com.example.weatherwithmap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    EditText city;
    Button btnParse;
    TextView resultText, shirota, dolgota;
    public String q;
    public String units;
    public String appid;
    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        btnParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapClear();
                parse();
            }
        });
    }

    private void initData() {
        city = findViewById(R.id.city);
        btnParse = findViewById(R.id.btnParse);
        resultText = findViewById(R.id.resultText);
        shirota = findViewById(R.id.shirota);
        dolgota = findViewById(R.id.dolgota);
    }

    private void parse() {
        q = (city.getText().toString());
        units = "metric";
        appid = "14694abc40a51e1b28c053fdea1faa06";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api service = retrofit.create(Api.class);

        Call<Get> call = service.Pogodka(q, units, appid);
        call.enqueue(new Callback<Get>() {
            @Override
            public void onResponse(Call<Get> call, Response<Get> response) {
                if (!response.isSuccessful()){
                    resultText.setText("" + response.code());
                    return;
                }

                Get get = response.body();
                String content = "";
                String name = "";
                double cityLong;
                double cityLat;
                content += ((int)get.getMain().getTemp() + " C°");
                cityLong = (get.getCoordObject().getLon());
                cityLat = (get.getCoordObject().getLat());
                name = get.getName();
                resultText.setText(content);

                LatLng Moscow = new LatLng(cityLat, cityLong);
                map.addMarker(new MarkerOptions().position(Moscow).title(name + ", " + content));
                map.moveCamera(CameraUpdateFactory.newLatLng(Moscow));
            }

            @Override
            public void onFailure(Call<Get> call, Throwable t) {
                resultText.setText(t.getMessage());
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
    }

    public void mapClear(){
        map.clear();
    }
}


