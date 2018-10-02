package com.android.mh.telstra.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.mh.telstra.R;
import com.android.mh.telstra.model.Row;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Adapter to refresh the list and add the item to recycler view
 */
public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.AboutViewHolder> {

    private final LayoutInflater mInflater;
    private List<Row> mRows; // Cached copy of rows
    private final Context context;

    UserListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public AboutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new AboutViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AboutViewHolder aboutViewHolder, int i) {
        if (mRows != null) {
            Row currentRow = mRows.get(i);
            aboutViewHolder.txtTitele.setText(currentRow.getTitle());
            if (!TextUtils.isEmpty(currentRow.getDescription()))
                aboutViewHolder.txtDesc.setText(currentRow.getDescription());
            else
                aboutViewHolder.txtDesc.setText(context.getString(R.string.desc_not_available));

            if (!TextUtils.isEmpty(currentRow.getImageHref())) {
                Picasso.with(context).load(currentRow.getImageHref()).placeholder(R.drawable.ic_launcher_foreground).into(aboutViewHolder.img);
            }
        }

    }

    /**
     * add the list of rows to adapter
     * @param rows
     */
    void setWords(List<Row> rows) {
        mRows = rows;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mRows != null) {
            return mRows.size();
        }
        return 0;
    }

    /**
     * View holder to hold the item view
     */
    class AboutViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtTitele;
        private final TextView txtDesc;
        private final ImageView img;

        private AboutViewHolder(View itemView) {
            super(itemView);
            txtTitele = itemView.findViewById(R.id.textTitle);
            txtDesc = itemView.findViewById(R.id.txtDesc);
            img = itemView.findViewById(R.id.imageView);
        }

    }
}
