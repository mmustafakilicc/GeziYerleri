package com.klc.geziyerleri.ui.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;

public class TextViewTitle extends androidx.appcompat.widget.AppCompatTextView {

    private static final int SIZE = 18;
    private static final int LEFT_PADDING = 40;
    private static final float LETTER_SPACING = 0.08f;

    public TextViewTitle(Context context) {
        super(context);
        setTextSize(SIZE);
        setTypeface(null, Typeface.BOLD);
        setGravity(Gravity.CENTER_VERTICAL);
        setPadding(LEFT_PADDING, 0, 0, 0);
        setLetterSpacing(LETTER_SPACING);
    }

    public TextViewTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTextSize(SIZE);
        setTypeface(null, Typeface.BOLD);
        setGravity(Gravity.CENTER_VERTICAL);
        setPadding(LEFT_PADDING, 0, 0, 0);
        setLetterSpacing(LETTER_SPACING);
    }

    public TextViewTitle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTextSize(SIZE);
        setTypeface(null, Typeface.BOLD);
        setGravity(Gravity.CENTER_VERTICAL);
        setPadding(LEFT_PADDING, 0, 0, 0);
        setLetterSpacing(LETTER_SPACING);
    }
}
