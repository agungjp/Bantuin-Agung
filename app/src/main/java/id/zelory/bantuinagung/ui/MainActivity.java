package id.zelory.bantuinagung.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.Snackbar;
import android.view.View;

import java.util.List;

import butterknife.Bind;
import id.zelory.bantuinagung.R;
import id.zelory.bantuinagung.controller.MahasiswaController;
import id.zelory.bantuinagung.data.model.Mahasiswa;
import id.zelory.bantuinagung.ui.adapter.MahasiswaAdapter;
import id.zelory.benih.BenihActivity;
import id.zelory.benih.adapter.BenihRecyclerAdapter;
import id.zelory.benih.view.BenihRecyclerView;

public class MainActivity extends BenihActivity implements MahasiswaController.Presenter,
        BenihRecyclerAdapter.OnItemClickListener {

    private MahasiswaController mahasiswaController;
    private ProgressDialog progressDialog;
    private MahasiswaAdapter mahasiswaAdapter;

    @Bind(R.id.recycler_view) BenihRecyclerView recyclerView;

    @Override
    protected int getActivityView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        mahasiswaAdapter = new MahasiswaAdapter(this);
        mahasiswaAdapter.setOnItemClickListener(this);

        recyclerView.setUpAsList();
        recyclerView.setAdapter(mahasiswaAdapter);

        setupController(savedInstanceState);
    }

    private void setupController(Bundle savedInstanceState) {
        if (mahasiswaController == null) {
            mahasiswaController = new MahasiswaController(this);
        }

        if (savedInstanceState != null) {
            mahasiswaController.loadState(savedInstanceState);
        } else {
            mahasiswaController.loadMahasiswa();
        }
    }

    @Override
    public void showMahasiswa(List<Mahasiswa> mahasiswaList) {
        mahasiswaAdapter.clear();
        mahasiswaAdapter.add(mahasiswaList);
    }

    @Override
    public void showError(Throwable throwable) {
        Snackbar.make(recyclerView, "Terjadi kesalahan: " + throwable.getMessage(), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Silahkan tunggu...");
        progressDialog.show();
    }

    @Override
    public void dismissLoading() {
        progressDialog.dismiss();
    }

    @Override
    public void onItemClick(View view, int position) {
        Mahasiswa mahasiswa = mahasiswaAdapter.getData().get(position);

        Snackbar.make(recyclerView, "Item click: " + mahasiswa.getNama(), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        mahasiswaController.saveState(outState);
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
