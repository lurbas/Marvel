package com.lucasurbas.marvel.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.lucasurbas.marvel.R;

/**
 * Created by Lucas on 19/09/15.
 */
public class FixedRatioImageView extends ImageView {

    private int aspectRatioWidth;
    private int aspectRatioHeight;

    public FixedRatioImageView(Context context) {
        super(context);
    }

    public FixedRatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public FixedRatioImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FixedRatioImageView);

        aspectRatioWidth = a.getInt(R.styleable.FixedRatioImageView_ratioWidth, 4);
        aspectRatioHeight = a.getInt(R.styleable.FixedRatioImageView_ratioHeight, 3);

        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int originalWidth = MeasureSpec.getSize(widthMeasureSpec);
        int originalHeight = MeasureSpec.getSize(heightMeasureSpec);

        int calculatedHeight = originalWidth * aspectRatioHeight / aspectRatioWidth;

        int finalWidth, finalHeight;

        finalWidth = originalWidth;
        finalHeight = calculatedHeight;


        super.onMeasure(
                MeasureSpec.makeMeasureSpec(finalWidth, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(finalHeight, MeasureSpec.EXACTLY));
    }
}
