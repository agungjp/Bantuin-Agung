package id.zelory.bantuinagung.ui.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import id.zelory.bantuinagung.R;
import id.zelory.bantuinagung.data.model.Mahasiswa;
import id.zelory.benih.adapter.viewholder.BenihItemViewHolder;

import static id.zelory.benih.adapter.BenihRecyclerAdapter.OnItemClickListener;
import static id.zelory.benih.adapter.BenihRecyclerAdapter.OnLongItemClickListener;

public class MahasiswaViewHolder extends BenihItemViewHolder<Mahasiswa> {

    @Bind(R.id.nama) TextView nama;
    @Bind(R.id.nim) TextView nim;
    @Bind(R.id.alamat) TextView alamat;

    public MahasiswaViewHolder(View itemView, OnItemClickListener itemClickListener, OnLongItemClickListener longItemClickListener) {
        super(itemView, itemClickListener, longItemClickListener);
    }

    @Override
    public void bind(Mahasiswa mahasiswa) {
        nama.setText(mahasiswa.getNama());
        nim.setText(mahasiswa.getNim());
        alamat.setText(mahasiswa.getAlamat());
    }
}
