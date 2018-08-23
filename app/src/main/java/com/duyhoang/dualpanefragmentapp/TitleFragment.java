package com.duyhoang.dualpanefragmentapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.ListView;

/**
 * Created by rogerh on 3/25/2018.
 */

public class TitleFragment extends ListFragment {

    public static String ITEM_INDEX_CLICKED = "value";

    boolean isDualPane;
    int curItemClicked = 0;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        // Set arrayadapter for fragment
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, ShakePear.ARTICLES));

        // Check if Dual_Pane mode is visible by if Detail_Fragment is visible
        View detailFragment = getActivity().findViewById(R.id.detail_fragment);
        isDualPane = detailFragment != null && detailFragment.getVisibility() == View.VISIBLE;
        if(savedInstanceState != null)
            curItemClicked = savedInstanceState.getInt(ITEM_INDEX_CLICKED, 0);

        if(isDualPane)
        {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            showDetailItem(curItemClicked);
        }

    }



    void showDetailItem(int index){

        if(isDualPane){
            getListView().setItemChecked(index, true);
            FragmentManager fm = getActivity().getSupportFragmentManager();
            DetailFragment details = (DetailFragment)fm.findFragmentById(R.id.detail_fragment);
            if(details == null || details.getArguments().getInt(TitleFragment.ITEM_INDEX_CLICKED) != index){

                FragmentTransaction ft = fm.beginTransaction();
                DetailFragment newDetail = DetailFragment.newInstance(index);
                if(index == 0)
                    ft.add(R.id.detail_fragment, newDetail);
                else
                    ft.replace(R.id.detail_fragment, newDetail);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        }
        else{
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra(ITEM_INDEX_CLICKED, index);
            startActivity(intent);
        }


    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        showDetailItem(position);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ITEM_INDEX_CLICKED, curItemClicked);
    }
}
