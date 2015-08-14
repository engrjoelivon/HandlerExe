package com.example.joel.handler_exe;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {
private Handler handle;
    private TextView tv;
    TextView tv2;
    Thread thread2=null;
    Thread thread;

    private final String KEY_FOR_ANOTHERTHREAD="keyforanotherthread";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button start=(Button)findViewById(R.id.start);
        tv2=(TextView)findViewById(R.id.textView2);
        tv=(TextView)findViewById(R.id.textView);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 thread=new Thread(new AnotherThread());
                thread.setName("first thread");
                thread.start();


                tv2.setText("This text loading before the text above changes shows that the operation happend in another thread");

            }



        });
        handle=new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                int a=message.what;
                switch (a){
                    case 1:{ Log.d("tag","case1"); tv.setText("The first thread completed in approximately 10s");  break;}
                    case 2:{ Log.d("tag","case2");tv.setText("The sec thread completed in approximately 15s"); break;}


                }
               //Bundle bundle= message.getData();


                //tv.setText(bundle.getString(KEY_FOR_ANOTHERTHREAD));
                return false;
            }
        });







    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

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
        else if(id==R.id.Runnable)
        {
         Intent intent=new Intent();
            intent.setClass(this,RunnableActivity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }
    /////////////Creating an inner class to act as another thread////////////
    private class AnotherThread implements Runnable
    {

        @Override
        public void run() {
         Long endTime=  System.currentTimeMillis()+10000;
            while(endTime>System.currentTimeMillis())
            {

            }
            Log.d("tag",thread.getName());
            thread.getName();
           Message message= handle.obtainMessage();
            Bundle b=new Bundle();
            b.putString(KEY_FOR_ANOTHERTHREAD, "The other thread completed in approximately 10s");
            message.setData(b);
            //handle.sendMessage(message);
            handle.sendEmptyMessage(1);


        }

    }
}
