package com.lucasurbas.marvel.fragment.detail;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;
import com.lucasurbas.marvel.App;
import com.lucasurbas.marvel.R;
import com.lucasurbas.marvel.architecture.BaseFragment;
import com.lucasurbas.marvel.fragment.detail.presenter.DetailPresenterForView;
import com.lucasurbas.marvel.fragment.detail.presenter.DetailPresenterImpl;
import com.lucasurbas.marvel.fragment.detail.view.DetailView;
import com.lucasurbas.marvel.fragment.detail.view.DetailViewState;
import com.lucasurbas.marvel.model.ComicPresenterModel;
import com.lucasurbas.marvel.util.Util;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Lucas on 19/09/15.
 */
public class DetailFragment extends BaseFragment<DetailView, DetailPresenterForView> implements DetailView {

    private static final String TAG = DetailFragment.class.getSimpleName();
    private static final String KEY_COMIC = "key_comic";

    @Inject
    Picasso picasso;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.iv_image)
    ImageView ivImage;

    @Bind(R.id.tv_title)
    TextView tvTitle;

    @Bind(R.id.tv_description)
    TextView tvDescription;

    @Bind(R.id.fab_favourite)
    FloatingActionButton fabFavourite;


    public static Fragment newInstance(ComicPresenterModel comic) {
        Fragment fragment = new DetailFragment();
        Bundle b = new Bundle();
        b.putParcelable(KEY_COMIC, comic);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        App.getObjectGraph().inject(this);
        super.onViewCreated(view, savedInstanceState);
        setupToolbar();
        setupFab();
    }

    private void setupToolbar() {
        ViewCompat.setElevation(toolbar, Util.pxFromDp(getActivity(), 4));
        toolbar.setTitle(R.string.t_screen_details);
    }

    private void setupFab() {
        fabFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().shuffleImage();
            }
        });
    }

    @Override
    public ViewState createViewState() {
        return new DetailViewState();
    }

    @Override
    public void onNewViewStateInstance() {
        ComicPresenterModel comic = getArguments().getParcelable(KEY_COMIC);
        getPresenter().initComic(comic);
    }

    @Override
    public DetailPresenterForView createPresenter() {
        return new DetailPresenterImpl(getActivity());
    }


    @Override
    public void setComicData(ComicPresenterModel comic) {
        ((DetailViewState) getViewState()).setComic(comic);

        tvTitle.setText(comic.getTitle());
        tvDescription.setText(comic.getDescription());
    }

    @Override
    public void setImage(String imageUrl) {
        ((DetailViewState) getViewState()).setCurrentImageUrl(imageUrl);

        picasso.load(imageUrl)
                .fit()
                .centerInside()
                .into(ivImage);
    }
}
