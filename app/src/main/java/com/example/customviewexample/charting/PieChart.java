package com.example.customviewexample.charting;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;
import com.example.customviewexample.R;


class PieChart extends View {
    private boolean mShowText;
    private int textPos;
    private Paint textPaint;
    private float textHeight;
    private Paint piePaint;
    private Paint shadowPaint;
    private int textColor;
    private float mTextWidth;
    private RectF shadowBounds;

    public PieChart(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.PieChart,
                0, 0);
        try {
            mShowText = a.getBoolean(R.styleable.PieChart_showText, false);
            textPos = a.getInteger(R.styleable.PieChart_labelPosition, 0);
        } finally {
            a.recycle();
        }
        init();
    }

    public boolean isShowText() {
        return mShowText;
    }

    public void setShowText(boolean showText) {
        mShowText = showText;
        invalidate();
        requestLayout();
    }

    private void init() {
        piePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        piePaint.setColor(Color.GREEN);
        piePaint.setStyle(Paint.Style.FILL);
        piePaint.setTextSize(textHeight);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, 50, piePaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int minw = getPaddingLeft() + getPaddingRight() + getSuggestedMinimumWidth();
        int w = resolveSizeAndState(minw, widthMeasureSpec, 1);
        int minh = MeasureSpec.getSize(w) - (int)mTextWidth + getPaddingBottom() + getPaddingTop();
        int h = resolveSizeAndState(MeasureSpec.getSize(w) - (int)mTextWidth, heightMeasureSpec, 0);
        setMeasuredDimension(w, h);
    }
}
