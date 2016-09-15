package com.mnikn.purewei.ui.activity;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.mnikn.mylibrary.customview.RecyclerViewDivider;
import com.mnikn.mylibrary.util.GlideUtil;
import com.mnikn.mylibrary.util.ToastUtil;
import com.mnikn.purewei.R;
import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.mvp.IHomeView;
import com.mnikn.purewei.mvp.presenter.HomePresenter;
import com.mnikn.purewei.mvp.presenter.IHomePresenter;
import com.mnikn.purewei.support.adapter.HomeAdapter;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        IHomeView,
        LoaderManager.LoaderCallbacks<Cursor>{

    private static final int LOADER_WEIBO = 1;

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private SwipeRefreshLayout refreshLayout;
    private NavigationView navigationView;
    private FloatingActionButton fab;
    private RecyclerView rvHome;

    private HomeAdapter mAdapter;
    private IHomePresenter mPresenter;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.authorizeCallBack(requestCode,resultCode,data);
    }

    @Override
    public void setupViews(View parent) {
        setContentView(R.layout.activity_home);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.sl_home);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.refresh();
            }
        });

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        rvHome = (RecyclerView) findViewById(R.id.rv_home);
        rvHome.setLayoutManager(layoutManager);
        rvHome.addItemDecoration(
                new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL, R.drawable.item_divider));
        rvHome.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount()) {
                    mPresenter.loadMore();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            }
        });

        rvHome.setAdapter(mAdapter);
    }

    @Override
    public void setupPresenter() {
        mPresenter = new HomePresenter(this);
    }

    private void initVariables(){
        mAdapter = new HomeAdapter(this);
        getLoaderManager().initLoader(LOADER_WEIBO, null, this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initVariables();
        setupViews(null);
        setupPresenter();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_notification) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public android.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(
                this,
                WeiboContract.WeiboEntry.buildWeiboUriWithUser("user"),
                null,
                null,
                null,
                WeiboContract.WeiboEntry.COLUMN_CREATED_TIME + " DESC");
    }

    @Override
    public void onLoadFinished(android.content.Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(android.content.Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    @Override
    public void setUserView(String url,String name) {
        CircleImageView cvUserIcon = (CircleImageView) navigationView.findViewById(R.id.cv_nav_user_icon);
        GlideUtil.setCircleImage(this,url,cvUserIcon);

        TextView tvAccountName = (TextView) navigationView.findViewById(R.id.tv_nav_user_name);
        tvAccountName.setText(name);
    }

    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void onRefreshFinish() {
        refreshLayout.setRefreshing(false);
        ToastUtil.makeToastShort(this, "刷新完成");
    }

    @Override
    public void onLoadMoreFinish() {
        refreshLayout.setRefreshing(false);
        ToastUtil.makeToastShort(this, "加载完成");
    }
}
