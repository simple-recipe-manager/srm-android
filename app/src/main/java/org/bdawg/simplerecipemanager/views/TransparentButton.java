package org.bdawg.simplerecipemanager.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.bdawg.simplerecipemanager.R;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by breland on 2/28/2015.
 */
public class TransparentButton extends RelativeLayout {
    ImageView iconView;
    TextView textView;

    String text;
    int drawable;

    public TransparentButton(Context context) {
        this(context,null);
    }

    public TransparentButton(Context context, AttributeSet attrs){
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TransparentButton);

        final int N = a.getIndexCount();
        for (int i = 0; i < N; ++i)
        {
            int attr = a.getIndex(i);
            switch (attr)
            {
                case R.styleable.TransparentButton_text:
                    this.text = a.getString(attr);
                    break;
                case R.styleable.TransparentButton_icon:
                    this.drawable = a.getResourceId(attr, -1);
                    break;
            }
        }
        a.recycle();

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.transparent_button, this, true);
        RelativeLayout rl = (RelativeLayout)getChildAt(0);
        this.iconView = (ImageView) rl.getChildAt(0);
        this.textView = (TextView) rl.getChildAt(2);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.setText(text);
        this.setIcon(drawable);
    }

    public void setText(String text){
        this.textView.setText(text);
    }

    public void setIcon(int iconId){
        this.iconView.setImageResource(iconId);
    }

}
