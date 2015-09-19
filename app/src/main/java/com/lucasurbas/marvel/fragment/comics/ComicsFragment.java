package com.lucasurbas.marvel.fragment.comics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;
import com.lucasurbas.marvel.R;
import com.lucasurbas.marvel.architecture.BaseFragment;
import com.lucasurbas.marvel.fragment.comics.presenter.ComicsPresenterForView;
import com.lucasurbas.marvel.fragment.comics.presenter.ComicsPresenterImpl;
import com.lucasurbas.marvel.fragment.comics.view.ComicsView;
import com.lucasurbas.marvel.fragment.comics.view.ComicsViewState;
import com.lucasurbas.marvel.model.ComicPresenterModel;
import com.lucasurbas.marvel.util.Util;
import com.lucasurbas.marvel.widget.GridSpacingDecoration;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Lucas on 19/09/15.
 */
public class ComicsFragment extends BaseFragment<ComicsView, ComicsPresenterForView> implements ComicsView {

    private static final String TAG = ComicsFragment.class.getSimpleName();

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.pi_loading)
    View piLoading;

    @Bind(R.id.rv_comics)
    RecyclerView rvComics;

    @Bind(R.id.tv_info)
    TextView tvInfo;

    private ComicsAdapter adapter;

    public static Fragment newInstance() {
        return new ComicsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_comics, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setupToolbar();
        setupRecyclerView();
    }

    private void setupToolbar() {
        ViewCompat.setElevation(toolbar, Util.pxFromDp(getActivity(), 4));
        toolbar.setTitle(R.string.app_name);
        toolbar.inflateMenu(R.menu.menu_comics);
    }

    private void setupRecyclerView() {
        final GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        final int columnWidth = (int) Util.pxFromDp(getActivity(), 150);
        int padding = (int) Util.pxFromDp(getActivity(), 2);
        rvComics.setPadding(padding, 0, padding, 0);
        rvComics.setLayoutManager(manager);
        rvComics.setItemAnimator(new DefaultItemAnimator());
        rvComics.addItemDecoration(new GridSpacingDecoration());

        rvComics.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (columnWidth > 0) {
                    int spanCount = Math.max(1, (rvComics.getMeasuredWidth() - rvComics.getPaddingLeft() - rvComics.getPaddingRight()) / columnWidth);
                    manager.setSpanCount(spanCount);
                }
            }
        });

        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return adapter.isFooter(position) ? manager.getSpanCount() : 1;
            }
        });

        adapter = new ComicsAdapter(getActivity(), new ComicsAdapter.NextPageListener() {
            @Override
            public void loadNextPage() {
                getPresenter().loadMoreComics();
            }
        });
        rvComics.setAdapter(adapter);
    }

    @Override
    public ViewState createViewState() {
        return new ComicsViewState();
    }

    @Override
    public void onNewViewStateInstance() {
        getPresenter().loadComics();
    }

    @Override
    public ComicsPresenterForView createPresenter() {
        return new ComicsPresenterImpl(getActivity());
    }

    @Override
    public void showComicList(List<ComicPresenterModel> comicList) {
        ((ComicsViewState) getViewState()).setComicList(comicList);
        adapter.setComics(comicList);
    }

    @Override
    public void showProgressIndicator(boolean show) {
        ((ComicsViewState) getViewState()).setLoading(show);
        piLoading.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setInfoText(String text) {
        ((ComicsViewState) getViewState()).setInfoText(text);
        tvInfo.setText(text);
    }
}
