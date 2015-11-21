package id.zelory.bantuinagung;

import android.app.Application;

import timber.log.Timber;

public class BantuinAgungApp extends Application {

    private static BantuinAgungApp bantuinAgungApp;

    @Override
    public void onCreate() {
        super.onCreate();
        bantuinAgungApp = this;

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Timber.tag(getClass().getSimpleName());
        }
    }

    public static BantuinAgungApp pluck() {
        return bantuinAgungApp;
    }
}
