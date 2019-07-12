package com.tsa.nccapp.interfaces;

/**
 * Created by Akhil Tripathi on 27-12-2017.
 */

public interface DrawableClickListener {

    public static enum DrawablePosition { TOP, BOTTOM, LEFT, RIGHT };
    public void onClick(DrawablePosition target);
}
