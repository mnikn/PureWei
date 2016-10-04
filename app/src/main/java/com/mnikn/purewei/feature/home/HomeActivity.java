package com.mnikn.purewei.feature.home;

import android.content.Intent;
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
import com.mnikn.mylibrary.listener.RecyclerScrollListener;
import com.mnikn.mylibrary.mvp.IListPresenter;
import com.mnikn.mylibrary.util.GlideUtil;
import com.mnikn.mylibrary.util.ToastUtil;
import com.mnikn.purewei.R;
import com.mnikn.purewei.feature.setting.SettingsActivity;
import com.mnikn.purewei.support.Constant;
import com.mnikn.purewei.support.callback.HomeLoaderCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IHomeView{

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.rv_home) RecyclerView rvHome;
    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.refresh_layout) SwipeRefreshLayout refreshLayout;

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
        ButterKnife.bind(this);

        mPresenter = (IHomePresenter) getPresenter();

        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(!mPresenter.isLoading()){
                    mPresenter.refresh();
                }
            }
        });
        refreshLayout.setColorSchemeResources(android.R.color.holo_red_dark,
            android.R.color.holo_green_dark,
            android.R.color.holo_blue_dark);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvHome.setLayoutManager(layoutManager);
        rvHome.addItemDecoration(
                new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL, R.drawable.item_divider));
        rvHome.addOnScrollListener(new RecyclerScrollListener(mAdapter,mPresenter,layoutManager));

        rvHome.setAdapter(mAdapter);
    }

    private void initVariables(){
        mAdapter = (HomeAdapter) getAdapter();
        getSupportLoaderManager().initLoader(
                Constant.LOADER_HOME,
                null,
                new HomeLoaderCallback(this, mAdapter));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initVariables();
        setupViews(null);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.cancelLoading();
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

        switch (id){
            case R.id.nav_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
            case R.id.nav_home:
                mPresenter.setWeiboType(Constant.HOME);
                mPresenter.refresh();
                break;
            case R.id.nav_hot:
                mPresenter.setWeiboType(Constant.HOT);
                mPresenter.refresh();
                break;
            case R.id.nav_around:
                mPresenter.setWeiboType(Constant.HOME);
                mPresenter.refresh();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
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
        mPresenter.setIsLoading(false);
        rvHome.scrollToPosition(0);
        ToastUtil.makeToastShort(this, "刷新完成");
    }

    @Override
    public void onLoadMore() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void onLoadMoreFinish() {
        refreshLayout.setRefreshing(false);
        mPresenter.setIsLoading(false);
        ToastUtil.makeToastShort(this, "加载完成");
    }

    @Override
    public IListPresenter getPresenter() {
        return new HomePresenter(this);
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return new HomeAdapter(this);
    }
}
