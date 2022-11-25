package com.chenwenrao.cdp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    private File dir = Environment.getExternalStorageDirectory();
    private File dataFile = new File(dir,"data.txt");
    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //写道外部
        Button button1=(Button) findViewById(R.id.bnt1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED) {
                    System.out.println("ok");
                }else {
                    ActivityCompat.requestPermissions(MainActivity.this,new String[] {
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    },1);
                }
                System.out.println(dir);//到这里面去寻找你的写的文件 /storage/sdcard0
                //执行写
                write();


                Toast.makeText(MainActivity.this,"我是小陈BUTTON--1,外部文件存储完毕",Toast.LENGTH_SHORT).show();
            }
        });

        Button button2=(Button) findViewById(R.id.bnt2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filename = "test2.txt";

                try {
                    //用MODE_PRIVAT模式，创建文件
                    FileOutputStream outputStream = openFileOutput(filename, MODE_PRIVATE);

                    outputStream.write("是小陈BUTTON--2,外部文件存储完毕".getBytes());
                    outputStream.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this,"我是小陈BUTTON--2,内部文件存储完毕",Toast.LENGTH_SHORT).show();
            }
        });

        button3=findViewById(R.id.bnt3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"我是小陈BUTTON--3,我还没有功能",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void write() {

        try {
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(dataFile);
            fos.write(new String("是小陈BUTTON--1,外部文件存储完毕").getBytes("utf-8"));
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}