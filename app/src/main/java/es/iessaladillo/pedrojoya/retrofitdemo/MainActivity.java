package es.iessaladillo.pedrojoya.retrofitdemo;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import es.iessaladillo.pedrojoya.retrofitdemo.data.remote.SwapiService;
import es.iessaladillo.pedrojoya.retrofitdemo.data.remote.entity.PlanetDTO;
import es.iessaladillo.pedrojoya.retrofitdemo.data.remote.entity.PlanetsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView lblPlanet;
    private Button btnSearch;
    private EditText txtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
        Call<PlanetDTO> request = SwapiService.getInstance().getSwapi().getPlanet("1");
        request.enqueue(new Callback<PlanetDTO>() {
            @Override
            public void onResponse(Call<PlanetDTO> call, Response<PlanetDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    lblPlanet.setText(response.body().getName());
                }
            }

            @Override
            public void onFailure(Call<PlanetDTO> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupViews() {
        lblPlanet = ActivityCompat.requireViewById(this, R.id.lblPlanet);
        btnSearch = ActivityCompat.requireViewById(this, R.id.btnSearch);
        txtSearch = ActivityCompat.requireViewById(this, R.id.txtSearch);
        btnSearch.setOnClickListener(v -> search(txtSearch.getText().toString()));
    }

    private void search(String text) {
        Call<PlanetsResponse> call = SwapiService.getInstance().getSwapi().searchPlanets(text);
        call.enqueue(new Callback<PlanetsResponse>() {
            @Override
            public void onResponse(Call<PlanetsResponse> call, Response<PlanetsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ArrayList<String> planetNames = new ArrayList<>();
                    for (PlanetDTO planetDTO: response.body().getPlanets()) {
                        planetNames.add(planetDTO.getName());
                    }
                    lblPlanet.setText(TextUtils.join(",", planetNames));
                }
            }

            @Override
            public void onFailure(Call<PlanetsResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
