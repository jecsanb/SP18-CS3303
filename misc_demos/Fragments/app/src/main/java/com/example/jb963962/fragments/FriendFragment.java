package com.example.jb963962.fragments;

import android.app.FragmentTransaction;
import android.app.ListFragment;

/**
 * Created by jb963962 on 2/21/18.
 */

public class FriendFragment extends ListFragment {
    boolean mDualPane;
    int position = 0;





    public void showDetails(int index){
        position = index;
        if(mDualPane){
            getListView().setItemChecked(index,true);
            DetailsFragment detailsFragment = (DetailsFragment)getFragmentManager().findFragmentById(R.id.details);
            if(detailsFragment == null || detailsFragment.getShowIndex() != index){
                detailsFragment = DetailsFragment.newInstance(index);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.details,detailsFragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }


        }
    }
}
