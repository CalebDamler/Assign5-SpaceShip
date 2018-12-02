package com.example.caleb.pet;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/********************************************************
 * Caleb Damler
 * CSIC 428
 * 3/8/2018
 * Vitual pet
 *
 * this app launches you pet alien into space
 * earth image: https://www.huffingtonpost.com/kimberly-k-arcand/earth-photos-top-ten_b_4190273.html
 * alien image: https://www.iconfinder.com/icons/677452/alien_invasion_ship_space_transport_ufo_icon
 * background: http://wallpaper-gallery.net/gallery/space-background-wallpaper.html
 ********************************************************/
public class MainActivity extends AppCompatActivity {

    private Thread calcualteThread;
    private RelativeLayout relativeLayout;
    private ImageView ufo, Iearth;
    private Alien alien;
    private Earth earth;
    private int locX, locY;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        relativeLayout = findViewById(R.id.relativeLayout);
        textView = findViewById(R.id.textView);

        locX = 500;
        locY = 2200;
        createEarth();
        createAlien();
        calcualteThread = new Thread(calcualteAction);
    }

    /**********************************************************
     * createAlien()
     *
     * builds the alien and puts him on the earth
     ***********************************************************/
    private void createAlien() {
        LayoutInflater layoutInflater;
        layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int initalX = locX;
        int initalY = locY;
        int porpVel = 10;
        //build
        alien = new Alien();
        //start position
        alien.setmX(initalX);
        alien.setmY(initalY);
        alien.setVelocity(porpVel);
        //image
        ufo=(ImageView)layoutInflater.inflate(R.layout.ufo_image,null);
        ufo.setY(alien.getmY());
        ufo.setX(alien.getmX());
        relativeLayout.addView(ufo,1);
    }
    /**********************************************************
     *creatEarth()
     *
     * builds the earth at the bottom of the scoreen
     * for the alien to launch from
     *
     ***********************************************************/
    private void createEarth() {
        LayoutInflater layoutInflater;
        layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //start position
        int initalX = locX;
        int initalY = locY;
        //build earth
        earth = new Earth();
        earth.setmX(initalX);
        earth.setmY(initalY);
        //image
        Iearth = (ImageView)layoutInflater.inflate(R.layout.earth_image,null);
        //set position
        Iearth.setX(0);
        Iearth.setY(2000);
        relativeLayout.addView(Iearth, 0);

    }
    /**********************************************************
     *onTouch
     *
     * handles when the screen is touched
     * to send the alien off into space
     *
     ***********************************************************/
    @Override
    public boolean onTouchEvent(MotionEvent event){
        int touchAction = event.getActionMasked();
        switch (touchAction) {
            case MotionEvent.ACTION_DOWN:
                locX = 500;
                locY = 2500;
            case MotionEvent.ACTION_UP:
                locY = earth.getmY();
                locX = earth.getmX();
            case MotionEvent.ACTION_MOVE:
                locX = 500;
                locY = -1000;
        }
        textView.setText("BLAST OFF!");
        return true;
    }
    @Override
    protected void onResume(){
        calcualteThread.start();
        super.onResume();
    }

    @Override
    protected void onPause(){
        finish();
        super.onPause();
    }
    @Override
    protected void onDestroy(){
        finish();
        super.onDestroy();
    }
    /**********************************************************
     *runnable
     *
     * moving the alien and delaying the thread
     *
     *
     ***********************************************************/
    private Runnable calcualteAction = new Runnable() {
        private static final int DELAY = 200;
        @Override
        public void run() {
            try{
                while (true){
                    alien.move(locX,locY);
                    Thread.sleep(DELAY);
                    threadHandler.sendEmptyMessage(0);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    };

    public Handler threadHandler = new Handler(){
        public void handleMessage(android.os.Message msg){
            ufo.setX((float)alien.getmX());
            ufo.setY((float)alien.getmY());
        }
    };
}

