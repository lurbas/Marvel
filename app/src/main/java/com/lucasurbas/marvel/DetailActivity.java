package com.lucasurbas.marvel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.lucasurbas.marvel.architecture.BaseActivity;
import com.lucasurbas.marvel.fragment.detail.DetailFragment;
import com.lucasurbas.marvel.model.ComicPresenterModel;

/**
 * Created by Lucas on 30/08/15.
 */
public class DetailActivity extends BaseActivity {

    private static final String KEY_COMIC = "key_comic";

    public static Intent getStartIntent(Context context, ComicPresenterModel comic){
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(KEY_COMIC, comic);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null){
            ComicPresenterModel comic = getIntent().getParcelableExtra(KEY_COMIC);
            Fragment fragment = DetailFragment.newInstance(comic);
            getSupportFragmentManager().beginTransaction().replace(android.R.id.content, fragment).commit();
        }
    }
}
