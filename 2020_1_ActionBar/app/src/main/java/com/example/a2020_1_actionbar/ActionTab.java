package com.example.a2020_1_actionbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ActionTab extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_tab);

        ActionBar ab = getSupportActionBar();
        ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        for(int i=0; i<3; i++) {
            ActionBar.Tab tab = ab.newTab();
            String Cap = "Tab" + (i + 1);
            tab.setText(Cap);
            TabFragment frag = TabFragment.newInstance(Cap);
            tab.setTabListener(new TabListener(frag));
            ab.addTab(tab);
        }

        if (savedInstanceState != null) {
            int seltab = savedInstanceState.getInt("seltab");
            ab.setSelectedNavigationItem(seltab);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seltab", getActionBar().getSelectedNavigationIndex());
    }

    private class TabListener implements ActionBar.TabListener {
        private Fragment mFragment;

        public TabListener(Fragment fragment) {
            mFragment = fragment;
        }

        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            ft.add(R.id.tabparent, mFragment, "tag");
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
            ft.remove(mFragment);
        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        }
    }

    public static class TabFragment extends Fragment {
        public static TabFragment newInstance(String text) {
            TabFragment frag = new TabFragment();

            Bundle args = new Bundle();
            args.putString("text", text);
            frag.setArguments(args);

            return frag;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            String text = "";
            Bundle args = getArguments();

            if (args != null) {
                text = args.getString("text");
            }

            View linear = inflater.inflate(R.layout.actiontabfragment, container, false);
            TextView textview = (TextView)linear.findViewById(R.id.content);
            textview.setText(text);

            return linear;
        }
    }
}
