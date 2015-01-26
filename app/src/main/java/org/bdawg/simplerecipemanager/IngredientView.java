package org.bdawg.simplerecipemanager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by breland on 1/25/2015.
 */
public class IngredientView extends RelativeLayout {

    private CheckBox checkBox;
    private TextView textView;

    public IngredientView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.ingr_view, this);
        textView = (TextView) findViewById(R.id.ingr_view_text);
        checkBox = (CheckBox) findViewById(R.id.ingr_view_checkbox);
    }

    public void setText(String text){
        this.textView.setText(text);
    }




}
