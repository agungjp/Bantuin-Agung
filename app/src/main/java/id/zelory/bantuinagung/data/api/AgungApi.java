package id.zelory.bantuinagung.data.api;


import id.zelory.benih.network.BenihServiceGenerator;
import retrofit.http.GET;
import rx.Observable;

public enum AgungApi {

    HARVEST;
    private final API api;

    AgungApi() {
        api = BenihServiceGenerator.createService(API.class, API.ENDPOINT);
    }

    public static AgungApi pluck() {
        return HARVEST;
    }

    public API getApi() {
        return api;
    }

    public interface API {
        String ENDPOINT = "http://makeso.esy.es";

        @GET("/showMahasiswa.php")
        Observable<MahasiswaReponse> getMahasiswa();
    }
}
