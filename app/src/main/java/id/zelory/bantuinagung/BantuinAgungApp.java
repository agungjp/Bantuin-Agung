package id.zelory.bantuinagung;

import android.app.Application;

import timber.log.Timber;

/**
 * Application nya sih aplikasi ini
 */
public class BantuinAgungApp extends Application {

    //instance nya disimpan disini nantinya
    private static BantuinAgungApp bantuinAgungApp;

    @Override
    public void onCreate() {
        super.onCreate();
        //simpan instance
        bantuinAgungApp = this;

        //Init timber, untuk log kalo kagi mode debug doang
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Timber.tag(getClass().getSimpleName());
        }
    }

    /**
     * kalo butuh instance aplikasi bisa panggil method ini
     *
     * @return instance aplikasi
     */
    public static BantuinAgungApp pluck() {
        return bantuinAgungApp;
    }
}
