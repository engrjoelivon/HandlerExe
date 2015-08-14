package com.example.joel.handler_exe;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class RunnableActivity extends Activity {
private TextView tv;
    private Handler handle;
    private final String  KEY_FOR_ANOTHER_THREAD="key_for_another_thread";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runnable);

        tv = (TextView) findViewById(R.id.textview_Runnable);

        handle = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                int a = message.what;
                switch (a) {
                    case 1: {
                        Log.d("tag", "case1");
                        tv.setText("The first thread completed in approximately 10s");
                        break;
                    }
                    case 2: {
                        Log.d("tag", "case2");
                        tv.setText("The sec thread completed in approximately 15s");
                        break;
                    }
                    case 3: {
                        tv.setText("The sec thread completed in approximately 7s");
                    }

                }

                return false;
            }
        });

        //Thread thread = new Thread(new ThreadRun());
        //thread.start();
        final Runnable r = new Runnable() {
            @Override
            public void run() {
                Long endTime = System.currentTimeMillis() + 5000;
                while (endTime > System.currentTimeMillis()) {

                }

                Log.d("tag", "executor service");
            }
        };

        ExecutorService exS= Executors.newFixedThreadPool(2);
        exS.submit(r);

        exS.shutdown();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_runnable, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private class ThreadRun implements Runnable{
        @Override
        public void run() {



                            Long endTime=  System.currentTimeMillis()+15000;
                            while(endTime>System.currentTimeMillis())
                            {

                            }
                           handle.sendEmptyMessage(2);





            }

    }

}
