package com.ass2.project_smd;


import static androidx.core.text.SpannableStringBuilderKt.color;

import static com.ass2.project_smd.R.*;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ass2.Drawer.DrawerAdapter;
import com.ass2.Drawer.DrawerItem;
import com.ass2.Drawer.SimpleItem;
import com.ass2.Drawer.SpaceItem;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.Arrays;

import android.view.View;
import android.view.WindowManager;

public class nav_bar extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener {

  private static final int POS_CLOSE = 0;
  private static final int POS_DASHBOARD = 1;
  private static final int POS_MY_PROFILE = 2;
  private static final int POS_NEARBY_RES = 3;
  private static final int POS_SETTINGS = 4;
  private static final int POS_ABOUT_US = 5;
  private static final int POS_LOGOUT = 6;

  private String[] screenTitles;
  private Drawable[] screenIcons;

  private SlidingRootNav  slidingRootNav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(layout.activity_nav_bar);

        Toolbar toolbar = findViewById(id.toolbar);
        setSupportActionBar(toolbar);

        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(layout.drawer_menu)
                .inject();
        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_CLOSE),
                createItemFor(POS_DASHBOARD).setChecked(true),
                createItemFor(POS_MY_PROFILE),
                createItemFor(POS_NEARBY_RES),
                createItemFor(POS_SETTINGS),
                createItemFor(POS_ABOUT_US),
                new SpaceItem(10),
                createItemFor(POS_LOGOUT)));
        adapter.setListener(this);


        //View otherLayout = getLayoutInflater().inflate(layout.drawer_menu, null);//"Fixed here"

        // Find the RecyclerView from the inflated layout
        RecyclerView list = findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        adapter.setSelected(POS_DASHBOARD);



    }

    @SuppressWarnings("rawtypes")
    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withIconTint(color(color.gray))
                .withTextTint(color(color.black))
                .withSelectedIconTint(color(color.gray))
                .withSelectedTextTint(color(color.black));
    }

    @ColorInt
    private int color(@ColorRes int res) {
        return ContextCompat.getColor(this, res);
    }
  private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.id_activityScreenTitles);
  }

  private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.id_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++){
            int id = ta.getResourceId(i, 0);
            if(id != 0){
                icons[i] = ContextCompat.getDrawable(this,id);
            }
        }
        ta.recycle();
        return icons;
  }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    @Override
    public void onItemSelected(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if(position == POS_DASHBOARD){
            DashboardFragment dashboardFragment = new DashboardFragment();
            transaction.replace(R.id.container, dashboardFragment);
        }
        else if(position == POS_MY_PROFILE){
            MyProfileFragment myProfileFragment = new MyProfileFragment();
            transaction.replace(R.id.container, myProfileFragment);
        }
        else if(position == POS_NEARBY_RES){
            NearbyResFragment nearbyResFragment = new NearbyResFragment();
            transaction.replace(R.id.container, nearbyResFragment);
        }
        else if(position == POS_SETTINGS){
            SettingsFragment settingsFragment = new SettingsFragment();
            transaction.replace(R.id.container, settingsFragment);
        }
        else if(position == POS_ABOUT_US){
            AboutUsFragment aboutUsFragment = new AboutUsFragment();
            transaction.replace(R.id.container, aboutUsFragment);
        }
        else if(position == POS_LOGOUT){
            finish();
        }
        slidingRootNav.closeMenu();
        transaction.addToBackStack(null);
        transaction.commit();
    }
}