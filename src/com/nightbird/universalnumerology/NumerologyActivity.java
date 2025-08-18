package com.nightbird.universalnumerology;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.*;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.nightbird.universalnumerology.Calculation.DateCalculator;
import com.nightbird.universalnumerology.Data.BaseNumerologyData;
import com.nightbird.universalnumerology.Data.DataExtractor;
import com.nightbird.universalnumerology.Data.DateNumerologyData;
import com.nightbird.universalnumerology.Data.Event;
import com.nightbird.universalnumerology.Data.Type.CalculationMethod;
import com.nightbird.universalnumerology.Data.Type.EventType;
import com.nightbird.universalnumerology.Fragments.DetailsFragment;
import com.nightbird.universalnumerology.Fragments.GridFragment;
import com.nightbird.universalnumerology.Fragments.Headless.DataHolderFragment;
import com.nightbird.universalnumerology.Interfaces.DataRequestor;
import com.nightbird.universalnumerology.Mediator.DataMediator;
import com.nightbird.universalnumerology.Mediator.Mediator;
import com.nightbird.universalnumerology.Mediator.Receiver;
import com.nightbird.universalnumerology.NumerologyGrid.BaseGrid;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class NumerologyActivity extends ActionBarActivity implements BaseGrid.OnTransformationRequestListener, DataRequestor {

    private final int NUM_OF_PAGES = 2;
    private ViewPager mViewPager;
    private FragmentPagerAdapter mPagerAdapter;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView mDrawerList;

    /**
     * Called when the activity is first created.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Event<BaseNumerologyData> e = new Event<BaseNumerologyData>(initData());

        BaseNumerologyData data = e.getData();

        DataExtractor.getSuccess(data, this);

        FragmentManager fm = getSupportFragmentManager();

        DataHolderFragment dataFragment = (DataHolderFragment) fm.findFragmentByTag("data");
        if (dataFragment == null) {
            dataFragment = new DataHolderFragment();
            dataFragment.addEventData(e);

            fm.beginTransaction().add(dataFragment, "data").commit();
            fm.executePendingTransactions();

        }

        //  GridFragment frag = new GridFragment();
        DataMediator mediator = new DataMediator();
        //  mediator.addFragment(frag);
        dataFragment.addMediator(mediator);

        ///
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setupViewPager();
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setupFragmentsForLandscape();
        }

        setupActionBar();
        setupNavigationDrawer();


    }

    private void setupFragmentsForLandscape() {

        GridFragment gridFragment = new GridFragment();
        DetailsFragment detailsFragment = new DetailsFragment();

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.grid_fragment, gridFragment, "grid_fragment").replace(R.id.details_fragment, detailsFragment, "details_fragment").commit();

        Mediator mediator = getMediator();
        mediator.addFragment(gridFragment);
        mediator.addFragment(detailsFragment);

    }

    private Mediator getMediator() {
        FragmentManager fm = getSupportFragmentManager();
        DataHolderFragment dataHolderFragment = (DataHolderFragment)fm.findFragmentByTag("data");
        return  dataHolderFragment.getMediator();
    }

    private void setupViewPager() {

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
    }

    private void setupActionBar() {

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
    }

    private void setupNavigationDrawer() {

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        // mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.END);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.drawable.ic_drawer,
                0,
                0) {

            @Override
            public boolean onOptionsItemSelected(MenuItem item) {

                if (item != null && item.getItemId() == android.R.id.home) {
                    if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                        mDrawerLayout.closeDrawer(Gravity.LEFT);
                    } else {
                        mDrawerLayout.openDrawer(Gravity.LEFT);
                    }
                    return true;
                }
                return false;
            }

            public void onDrawerClosed(View view) {

                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(getTitle());
                supportInvalidateOptionsMenu();
            }

            public void onDrawerOpened(View view) {

                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(getTitle());
                supportInvalidateOptionsMenu();
            }

        };


        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerList = (ListView) findViewById(R.id.drawer);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_new).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_action_menu, menu);

        //MenuItem shareItem = menu.findItem(R.id.action_share);
        //shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    private BaseNumerologyData initData() {

        GregorianCalendar calendar = new GregorianCalendar();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DateCalculator dateCalculator = new DateCalculator(year, month, day, CalculationMethod.NORMAL_DATE);
        DateNumerologyData data = dateCalculator.calculate();

        String name = "";
        data.setDateEvent(new DateNumerologyData.DateEvent(year, month, day, name, EventType.EVENT, CalculationMethod.NORMAL_DATE));

        return data;
    }

    @Override
    public void onTransformationRequest(int numberIndex, int gridIndex) {

    }

    @Override
    public void requestData(Fragment fragment) {

        FragmentManager fm = getSupportFragmentManager();
        DataHolderFragment dataHolderFragment = (DataHolderFragment) fm.findFragmentByTag("data");
        Event<BaseNumerologyData> event = dataHolderFragment.getEventData();

        Receiver receiver = (Receiver) fragment;

        if (event.getLastHistoryItem() != null) {
            receiver.receive(event.getLastHistoryItem());
        } else if (event.getLastHistoryItem() == null) {
            receiver.receive(event.getData());
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private class ScreenSlidePagerAdapter extends FragmentPagerAdapter {

        DataHolderFragment dataHolderFragment;

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0: {
                    Mediator mediator = getMediator();
                    GridFragment gridFragment = new GridFragment();
                    mediator.addFragment(gridFragment);

                    return gridFragment;
                }

                case 1: {
                    Mediator mediator = getMediator();
                    DetailsFragment detailsFragment = new DetailsFragment();
                    mediator.addFragment(detailsFragment);

                    return detailsFragment;
                }
            }

            return null;
        }

        @Override
        public int getCount() {
            return NUM_OF_PAGES;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            String[] pageTitles = getResources().getStringArray(R.array.main_pager_strip);

            return pageTitles[position];
        }
    }

    private class DrawerItemClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        }
    }
}
