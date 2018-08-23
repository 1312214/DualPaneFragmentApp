package com.duyhoang.dualpanefragmentapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;

/**
 * Created by rogerh on 3/25/2018.
 */

public class DetailFragment extends Fragment {


    public static DetailFragment newInstance(int index){

        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TitleFragment.ITEM_INDEX_CLICKED, index);
        detailFragment.setArguments(bundle);

        return detailFragment;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // if the screen is on Portrait then this function won't be passed. Because there no Frame container.
        if(container == null)
            return null;

        TextView detailInfo = new TextView(getActivity());
        ScrollView scrollView = new ScrollView(getActivity());

        //Converts an unpacked complex data value holding a dimension to its final floating point value.
        int padding = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4,getActivity().getResources().getDisplayMetrics());
        detailInfo.setPadding(padding,padding, padding, padding);
        scrollView.addView(detailInfo);
        detailInfo.setText(ShakePear.DETAIL[getArguments().getInt(TitleFragment.ITEM_INDEX_CLICKED, 0)] );

        return scrollView;
    }
}
