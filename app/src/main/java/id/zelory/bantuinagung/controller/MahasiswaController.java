package id.zelory.bantuinagung.controller;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import id.zelory.bantuinagung.data.api.AgungApi;
import id.zelory.bantuinagung.data.api.MahasiswaReponse;
import id.zelory.bantuinagung.data.model.Mahasiswa;
import id.zelory.benih.controller.BenihController;
import id.zelory.benih.util.BenihScheduler;
import timber.log.Timber;

public class MahasiswaController extends BenihController<MahasiswaController.Presenter> {

    private List<Mahasiswa> mahasiswaList;

    public MahasiswaController(Presenter presenter) {
        super(presenter);
    }

    public void loadMahasiswa() {
        presenter.showLoading();
        AgungApi.pluck()
                .getApi()
                .getMahasiswa()
                .compose(BenihScheduler.pluck().applySchedulers(BenihScheduler.Type.IO))
                .map(MahasiswaReponse::getMahasiswa)
                .subscribe(mahasiswaList -> {
                    this.mahasiswaList = mahasiswaList;
                    if (presenter != null) {
                        presenter.showMahasiswa(this.mahasiswaList);
                        presenter.dismissLoading();
                    }
                }, throwable -> {
                    Timber.e(throwable.getMessage());
                    if (presenter != null) {
                        presenter.showError(throwable);
                        presenter.dismissLoading();
                    }
                });
    }

    @Override
    public void saveState(Bundle bundle) {
        bundle.putParcelableArrayList("list_mahasiswa", (ArrayList<Mahasiswa>) mahasiswaList);
    }

    @Override
    public void loadState(Bundle bundle) {
        mahasiswaList = bundle.getParcelableArrayList("list_mahasiswa");

        if (mahasiswaList != null) {
            presenter.showMahasiswa(mahasiswaList);
        } else {
            loadMahasiswa();
        }
    }

    public interface Presenter extends BenihController.Presenter {
        void showMahasiswa(List<Mahasiswa> mahasiswaList);
    }
}
