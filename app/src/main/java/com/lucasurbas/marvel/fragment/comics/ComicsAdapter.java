package com.lucasurbas.marvel.fragment.comics;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucasurbas.marvel.R;
import com.lucasurbas.marvel.model.ComicPresenterModel;
import com.lucasurbas.marvel.widget.ComicViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas on 19/09/15.
 */
public class ComicsAdapter extends RecyclerView.Adapter<ComicViewHolder> {

    private static final int ITEM_VIEW_TYPE_FOOTER = 0;
    private static final int ITEM_VIEW_TYPE_ITEM = 1;

    public interface NextPageListener {
        void loadNextPage();
    }

    private List<ComicPresenterModel> comicList;
    private Context context;
    private NextPageListener nextPageListener;

    public ComicsAdapter(Context context, NextPageListener nextPageListener) {
        this.comicList = new ArrayList<>();
        this.context = context;
        this.nextPageListener = nextPageListener;
    }

    @Override
    public ComicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_VIEW_TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_footer, parent, false);
            return new ComicViewHolder(view, false);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(ComicViewHolder.getLayoutResId(), parent, false);
            return new ComicViewHolder(view, true);
        }
    }

    @Override
    public void onBindViewHolder(final ComicViewHolder holder, final int position) {

        if (isFooter(position)) {
            if (nextPageListener != null) {
                nextPageListener.loadNextPage();
            }
            return;
        }

        final ComicPresenterModel comic = comicList.get(position);
        holder.presentComic(context, comic);
    }

    public void setComics(List<ComicPresenterModel> comicList) {
        this.comicList = comicList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return comicList.size() == 0 ? 0 : comicList.size() + 1;
    }

    public boolean isFooter(int position) {
        return position == comicList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return isFooter(position) ?
                ITEM_VIEW_TYPE_FOOTER : ITEM_VIEW_TYPE_ITEM;
    }
}