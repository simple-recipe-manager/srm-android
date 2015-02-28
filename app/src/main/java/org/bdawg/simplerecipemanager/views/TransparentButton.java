package org.bdawg.simplerecipemanager.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import org.bdawg.simplerecipemanager.R;

/**
 * Created by breland on 2/28/2015.
 */
public class TransparentButton extends RelativeLayout {
    public TransparentButton(Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.transparent_button, this);
    }
}
