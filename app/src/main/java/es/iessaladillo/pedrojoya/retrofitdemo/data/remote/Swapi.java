package es.iessaladillo.pedrojoya.retrofitdemo.data.remote;

import es.iessaladillo.pedrojoya.retrofitdemo.data.remote.entity.PlanetDTO;
import es.iessaladillo.pedrojoya.retrofitdemo.data.remote.entity.PlanetsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Swapi {

    @GET("planets/{planetId}")
    Call<PlanetDTO> getPlanet(@Path("planetId") String planetId);

    @GET("planets/")
    Call<PlanetsResponse> searchPlanets(@Query("search") String search);

}
