package id.zelory.bantuinagung.data.api;


import id.zelory.benih.network.BenihServiceGenerator;
import retrofit.http.GET;
import rx.Observable;

/**
 * Ini API nya aplikasi kita
 */
public enum AgungApi {
    //ini instance
    HARVEST;
    //ini API nya
    private final API api;

    /**
     * kosntruktornya ini API
     */
    AgungApi() {
        //kita generate API nya
        api = BenihServiceGenerator.createService(API.class, API.ENDPOINT);
    }

    /**
     * Singleton nya si API
     *
     * @return balikin si instance
     */
    public static AgungApi pluck() {
        return HARVEST;
    }

    /**
     * untuk akses API nya
     *
     * @return balikin si api
     */
    public API getApi() {
        return api;
    }

    /**
     * API nya bisa apa aja ada disini
     */
    public interface API {
        //Base URL alamat API
        String ENDPOINT = "http://makeso.esy.es";

        /**
         * Kalo mau ngambil data mahasiswa dari API
         *
         * @return datanya mahasiswa dalam bentuk MahasiswaResponse
         */
        @GET("/showMahasiswa.php")
        Observable<MahasiswaResponse> getMahasiswa();
    }
}
