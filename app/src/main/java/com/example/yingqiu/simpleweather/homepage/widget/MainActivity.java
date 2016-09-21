package com.example.yingqiu.simpleweather.homepage.widget;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yingqiu.simpleweather.R;
import com.example.yingqiu.simpleweather.homepage.bean.Province;
import com.example.yingqiu.simpleweather.homepage.presenter.IMainPresenter;
import com.example.yingqiu.simpleweather.homepage.presenter.MainPresenterImp;
import com.example.yingqiu.simpleweather.homepage.view.IMainView;
import com.example.yingqiu.simpleweather.homepage.widget.adapter.CitiesAdapter;
import com.example.yingqiu.simpleweather.homepage.widget.adapter.ProvincesAdapter;
import com.example.yingqiu.simpleweather.listener.OnRecyclerViewClickListener;
import com.example.yingqiu.simpleweather.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IMainView {


    @BindView(R.id.linear_dot_gray)
    LinearLayout linearDotGray;
    @BindView(R.id.iv_white_dot)
    ImageView ivWhiteDot;
    @BindView(R.id.tv_city_name)
    TextView tvCityName;
    @BindView(R.id.iv_add_city)
    ImageView ivAddCity;
    @BindView(R.id.vp_container)
    ViewPager vpContainer;
    @BindView(R.id.root_layout)
    LinearLayout rootLayout;
    @BindView(R.id.pop_root_layout)
    RelativeLayout popRootLayout;


    private List<String> mSavedCities;
    private FragmentPagerAdapter mPagerAdapter;
    private IMainPresenter mPresenter;
    private List<Fragment> mFragments;
    private int mDotWidth;

    private List<Province> mProvinces;
    private List<String> mCities;
    private boolean popIsInit;
    private PopupWindow pw;
    private CitiesAdapter citiesAdapter;
    private ProvincesAdapter provincesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenter = new MainPresenterImp(this);

        //与savedInstanceState结合 待会写 判断是否为空
        mSavedCities = new ArrayList<String>();
        mFragments = new ArrayList<Fragment>();
        mProvinces = new ArrayList<Province>();
        mCities = new ArrayList<String>();


        initPagerEvent();
        loadSavedCities();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPagerAdapter = null;
        mPresenter.detachView();
    }


    //-----------------initDot-----------------------------------------
    private void initDotEvent() {
        //点与点之间的距离
        int leftMargin = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
        Log.e("tag", "leftMargin>>>>>>>>>>>>>" + leftMargin);

        if (linearDotGray.getChildCount() > 0)
            linearDotGray.removeAllViews();

        for (int i = 0; i < mFragments.size(); i++)
            initCreateDot(leftMargin, i);

        initDotWidth();
    }

    private void initCreateDot(int leftMargin, int pos) {
        ImageView iv = new ImageView(MainActivity.this);
        iv.setImageResource(R.drawable.shape_dot_gray);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (pos > 0)
            params.leftMargin = leftMargin;
        iv.setLayoutParams(params);
        linearDotGray.addView(iv);
    }

    private void initDotWidth() {
        ivWhiteDot.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            ivWhiteDot.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        } else {
                            ivWhiteDot.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        }

                        if (linearDotGray.getChildCount() > 1) {
                            mDotWidth = linearDotGray.getChildAt(1).getLeft() - linearDotGray.getChildAt(0).getLeft();
                            LogUtil.e("tag", "mDotWidth>>>>>>>>>>>" + mDotWidth);
                        }
                    }
                });
    }


//---------------------------------------------------------------------------

    /**
     * 初始化ViewPage相关
     */
    private void initPagerEvent() {


        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

        };

        vpContainer.setAdapter(mPagerAdapter);

        vpContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int offset = (int) (mDotWidth * (positionOffset + position));
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivWhiteDot.getLayoutParams();
                params.leftMargin = offset;
                ivWhiteDot.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                tvCityName.setText(mSavedCities.get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


    /**
     * 初始化与popupWindow相关的一些数据
     */
    private void initProvinceAndCities() {

        LinearLayoutManager citiesManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        LinearLayoutManager provincesManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);

        citiesAdapter = new CitiesAdapter(mCities);
        provincesAdapter = new ProvincesAdapter(mProvinces);

        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.layout_china_cities,null);


        RecyclerView recyclerCities = (RecyclerView) contentView.findViewById(R.id.recycler_cities);
        RecyclerView recyclerProvinces = (RecyclerView) contentView.findViewById(R.id.recycler_province);

        recyclerCities.setLayoutManager(citiesManager);
        recyclerCities.setItemAnimator(new DefaultItemAnimator());
        recyclerCities.setAdapter(citiesAdapter);

        recyclerProvinces.setLayoutManager(provincesManager);
        recyclerProvinces.setItemAnimator(new DefaultItemAnimator());
        recyclerProvinces.setAdapter(provincesAdapter);

        setRecyclerViewClickEvent();

        pw = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        pw.setAnimationStyle(R.style.PopupAnim);
        pw.setTouchable(true);
        pw.setFocusable(false);
        pw.setOutsideTouchable(true);
        popIsInit = true;

        showPopupWindow();
    }

    private void setRecyclerViewClickEvent() {
        provincesAdapter.setOnClickListener(new OnRecyclerViewClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                loadCitiesByProvinceId(mProvinces.get(position).getProvinceId());
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });



        citiesAdapter.setOnClickListener(new OnRecyclerViewClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                addCity(mCities.get(position));
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (pw != null && pw.isShowing()){
            pw.dismiss();
            return;
        }
        super.onBackPressed();
    }

    @OnClick(R.id.iv_add_city)
    public void onClick() {
        showPopupWindow();
    }

    private void showPopupWindow() {

        if (mProvinces.size() == 0) {
            loadProvinces();
        }

        if (mCities.size() == 0 && mProvinces.size() != 0) {
            loadCitiesByProvinceId(mProvinces.get(0).getProvinceId());
        }

        if (!popIsInit)
            initProvinceAndCities();
        else{
            pw.showAsDropDown(popRootLayout);
        }

    }


//-------------------------------------------------------------------------

    @Override
    public void addCity(String city) {
        mPresenter.addCity(city);
    }

    @Override
    public void onAddCitySuccess(String city) {
        if(pw.isShowing())
            pw.dismiss();

        mSavedCities.add(city);
        mFragments.add(WeatherFragment.newInstance(city));
        notifyPageChanged();
    }


    @Override
    public void loadSavedCities() {
        mPresenter.loadSavedCities();
    }

    @Override
    public void onLoadSavedCitiesSuccess(List<String> cities) {
        if (this.mSavedCities == null)
            this.mSavedCities = new ArrayList<>();
        this.mSavedCities.clear();
        this.mSavedCities.addAll(cities);

        if (mSavedCities.size() > 0)
            updateViewPager();


    }

    @Override
    public void loadProvinces() {
        mPresenter.loadProvinces();
    }

    @Override
    public void onLoadProvincesSuccess(List<Province> provinces) {
        mProvinces.clear();
        mProvinces.addAll(provinces);
        //notify
        if (provincesAdapter != null)
            provincesAdapter.notifyDataSetChanged();

    }

    @Override
    public void loadCitiesByProvinceId(int provinceId) {
        mPresenter.loadCities(provinceId);
    }

    @Override
    public void onLoadCitiesByProvinceIdSuccess(List<String> cities) {
        mCities.clear();
        mCities.addAll(cities);
        //notify
        if (citiesAdapter != null)
            citiesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(String msg) {
        showMsg(msg);
    }


    //---------------------------------------------------------

    private void updateViewPager() {
        int length = mSavedCities.size();
        for (int i = 0; i < length; i++) {
            mFragments.add(WeatherFragment.newInstance(mSavedCities.get(i)));
        }

        tvCityName.setText(mSavedCities.get(vpContainer.getCurrentItem()));

        notifyPageChanged();
    }

    private void notifyPageChanged() {
        mPagerAdapter.notifyDataSetChanged();
        initDotEvent();
    }

    private void showMsg(String msg) {
        Snackbar.make(rootLayout, msg, Snackbar.LENGTH_SHORT)
                .show();
    }


}
