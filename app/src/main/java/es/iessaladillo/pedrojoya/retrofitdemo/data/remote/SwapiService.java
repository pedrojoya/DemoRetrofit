package es.iessaladillo.pedrojoya.retrofitdemo.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SwapiService {

    private static SwapiService INSTANCE;
    private final Swapi swapi;

    public static SwapiService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SwapiService(buildInstance());
        }
        return INSTANCE;
    }

    private static Swapi buildInstance() {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://swapi.co/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        return retrofit.create(Swapi.class);
    }

    private SwapiService(Swapi swapi) {
        this.swapi = swapi;
    }

    public Swapi getSwapi() {
        return swapi;
    }

}
