package com.example.carezone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private Intromanager intromanager;
    private ViewPagerAdapter viewPagerAdapter;
    private TextView[] dots;
    Button next,skip;
    private LinearLayout dotsLayout;
    private int[] layouts;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intromanager = new Intromanager(this);
        if(!intromanager.check())
        {
         intromanager.setFirst(false);
         Intent i = new Intent(MainActivity.this,Main2.class);
         startActivity(i);
         finish();
        }
        if(Build.VERSION.SDK_INT>=21)
        {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_FULLSCREEN);
        }

        setContentView(R.layout.activity_main);

        viewPager = (ViewPager)findViewById(R.id.view_pager);
        dotsLayout= (LinearLayout)findViewById(R.id.layoutDots);
        skip = (Button)findViewById(R.id.btn_skip);
        next = (Button)findViewById(R.id.btn_next);
        layouts = new int[]{R.layout.wc_screen1,R.layout.wc_screen2,R.layout.wc_screen3, R.layout.wc_screen4};
        addBottomDots(0);
        changeStatusBarColor();
        viewPagerAdapter = new ViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(viewListner);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Main2.class);
                startActivity(i);
                finish();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int current = getItem(+1);
                if(current<layouts.length)
                {
                    viewPager.setCurrentItem(current);

                }
                else
                {
                    Intent i = new Intent(MainActivity.this,Main2.class);
                    startActivity(i);
                    finish();

                }

            }
        });


    }

    private void addBottomDots(int position)
    {
        dots =  new TextView[layouts.length];
        int[] colorActive = getResources().getIntArray(R.array.dot_active);
        int[] colorInactive = getResources().getIntArray(R.array.dot_inactive);
        dotsLayout.removeAllViews();
        for(int i=0; i<dots.length; i++)
        {
            dots[i]= new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorInactive[position]);
            dotsLayout.addView(dots[i]);
        }
        if(dots.length>0)
            dots[position].setTextColor(colorActive[position]);

    }

    private int getItem(int i)
    {
        return viewPager.getCurrentItem() + i ;
    }

    ViewPager.OnPageChangeListener viewListner = new ViewPager.OnPageChangeListener()
    {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position)
        {
            addBottomDots(position);
            if(position==layouts.length-1)
            {
                next.setText("PROCEED");
                skip.setVisibility(View.GONE);

            }
            else
                {
                    next.setText("NEXT");
                    skip.setVisibility(View.VISIBLE);
                }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public void changeStatusBarColor()
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
        {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);

        }
    }

    public class ViewPagerAdapter extends PagerAdapter
    {
        private LayoutInflater layoutInflater;

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = layoutInflater.inflate(layouts[position],container,false);
            container.addView(v);

            return v;
        }

        @Override
        public int getCount() {

            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;

        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            View v  =(View)object;
            container.removeView(v);
        }
    }
}
