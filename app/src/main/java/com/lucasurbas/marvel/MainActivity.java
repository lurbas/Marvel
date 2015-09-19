package com.lucasurbas.marvel;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.lucasurbas.marvel.architecture.BaseActivity;
import com.lucasurbas.marvel.event.OpenDetailScreenEvent;
import com.lucasurbas.marvel.fragment.comics.ComicsFragment;
import com.lucasurbas.marvel.fragment.detail.DetailFragment;

import de.greenrobot.event.EventBus;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            Fragment fragment = ComicsFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.container_master, fragment).commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(OpenDetailScreenEvent event) {
        if (findViewById(R.id.container_detail) == null) {
            startActivity(DetailActivity.getStartIntent(this, event.getComic()));
        } else {
            Fragment fragment = DetailFragment.newInstance(event.getComic());
            getSupportFragmentManager().beginTransaction().replace(R.id.container_detail, fragment).commit();
        }
    }
}
