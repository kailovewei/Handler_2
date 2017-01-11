package ykk.handler2.com.handler_2;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView textView;
    private Button button;
    private  static  Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button= (Button) findViewById(R.id.button_Id);
        textView= (TextView) findViewById(R.id.textView_Id);
        handler=new MyHandler();
        button.setOnClickListener(this);
        Log.d("MainActivity","ykk");
    }

    @Override
    public void onClick(View v) {
        Thread thread=new WorkerThread();
        thread.start();

    }
    class MyHandler extends Handler
    {

        @Override
        public void handleMessage(Message msg) {
            String s= (String) msg.obj;
            textView.setText(s);

        }
    }
    class WorkerThread extends  Thread
    {
        @Override

        public void run() {
            //模拟访问网络，所以说当线程运行时，首先休眠2秒钟
            try {
                Thread.sleep(2*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //变量s的值，模拟从网络当中获取的数据.
                String s="从网络中获取的数据";
                //textView.setText(s);这样的做法错误，因为在android系统当中，只有在main thread中才能操作UI.
                Message msg=handler.obtainMessage();
                msg.obj=s;
                handler.sendMessage(msg);
            }
        }
    }
