package id.zelory.bantuinagung.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import id.zelory.bantuinagung.R;
import id.zelory.bantuinagung.data.model.Mahasiswa;
import id.zelory.bantuinagung.ui.adapter.viewholder.MahasiswaViewHolder;
import id.zelory.benih.adapter.BenihRecyclerAdapter;

public class MahasiswaAdapter extends BenihRecyclerAdapter<Mahasiswa, MahasiswaViewHolder> {

    public MahasiswaAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemView(int viewType) {
        return R.layout.item_mahasiswa;
    }

    @Override
    public MahasiswaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MahasiswaViewHolder(getView(parent, viewType), itemClickListener, longItemClickListener);
    }
}
