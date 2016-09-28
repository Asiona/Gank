package com.asion.gank.activities;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.asion.gank.R;
import com.asion.gank.utils.SelectPicPopupWindow;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolbar;
    private NavigationView mNavView;
    private DrawerLayout mDrawerLayout;
    private Fragment lastFragment;
    private SelectPicPopupWindow menuWindow;
    private FloatingActionButton mFloadingBtn;
    private SearchView searchView;
    private FloatingActionButton mRefreshBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setSupportActionBar(mToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, 0, 0);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mNavView.setNavigationItemSelectedListener(this);

        disableNavigationViewScrollbars(mNavView);
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mNavView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mFloadingBtn = ((FloatingActionButton) findViewById(R.id.smile_btn));
        mRefreshBtn = ((FloatingActionButton) findViewById(R.id.refresh_btn));

        mFloadingBtn.setOnClickListener(v -> {
                Intent i = new Intent(MainActivity.this, SendActivity.class);
                int centerX = (v.getLeft() + v.getRight()) / 2;
                int centerY = (v.getTop() + v.getBottom()) / 2;
                i.putExtra("x", centerX);
                i.putExtra("y", centerY);
                startActivityForResult(i,100);}
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.main_menu_search).getActionView();
        ComponentName sa = new ComponentName(this, SearchResultActivity.class);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(sa));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_menu_more:
                showPopWindow();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mFloadingBtn.setVisibility(View.INVISIBLE);
        mRefreshBtn.setVisibility(View.VISIBLE);
        mRefreshBtn.animate().translationY(0);
        switch (item.getItemId()) {
            case R.id.main_home:
                break;
            case R.id.main_android:
                break;
            case R.id.main_ios:
                break;
            case R.id.main_web:
                break;
            case R.id.main_video:
                break;
            case R.id.main_benefit:
                break;
            case R.id.main_res:
                mRefreshBtn.animate().translationY(-mFloadingBtn.getWidth()-20);
                mFloadingBtn.setVisibility(View.VISIBLE);
                break;
            case R.id.main_details:
                Snackbar.make(mDrawerLayout, "Details", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.main_exit:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("You want to leave me?");
                builder.setNegativeButton("SURE", (a, b) -> finish());
                builder.setPositiveButton("CANCLE", null);
                builder.create().show();
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showPopWindow() {
        menuWindow = new SelectPicPopupWindow(MainActivity.this);
        menuWindow.showAtLocation(MainActivity.this.findViewById(R.id.main_menu_more), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    private void changeFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (!fragment.isAdded()) {
            if (lastFragment != null) {
                fragmentTransaction.hide(lastFragment);
            }
            fragmentTransaction.add(R.id.container, fragment).commit();
        } else {
            fragmentTransaction.hide(lastFragment).show(fragment).commit();
        }
        lastFragment = fragment;
    }

    private void disableNavigationViewScrollbars(NavigationView navigationView) {
        if (navigationView != null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
            if (navigationMenuView != null) {
                navigationMenuView.setVerticalScrollBarEnabled(false);
            }
        }
    }

}
