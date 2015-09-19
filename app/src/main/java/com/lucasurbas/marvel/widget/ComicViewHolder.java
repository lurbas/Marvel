package com.lucasurbas.marvel.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lucasurbas.marvel.App;
import com.lucasurbas.marvel.R;
import com.lucasurbas.marvel.event.OpenDetailScreenEvent;
import com.lucasurbas.marvel.model.ComicPresenterModel;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by Lucas on 19/09/15.
 */
public class ComicViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = ComicViewHolder.class.getSimpleName();

    @Inject
    Picasso picasso;

    @Bind(R.id.item_container)
    View itemContainer;
    @Bind(R.id.iv_image)
    ImageView ivImage;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    public ComicViewHolder(View view, boolean bind) {
        super(view);
        if(bind) {
            ButterKnife.bind(this, view);
            App.getObjectGraph().inject(this);
        }
    }

    public static int getLayoutResId() {
        return R.layout.row_comic;
    }

    public void presentComic(final Context context, final ComicPresenterModel comic) {

        picasso.load(comic.getThumbnailUrl())
                .fit()
                .into(ivImage);

        tvTitle.setText(comic.getTitle());

        itemContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new OpenDetailScreenEvent(comic));
            }
        });
    }
}
