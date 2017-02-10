package com.example.myapplication1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import static android.R.attr.id;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String scheme = intent.getScheme();
        Uri uri = intent.getData();
        System.out.println("scheme:"+scheme);
        if (uri != null) {
            String host = uri.getHost();
            String dataString = intent.getDataString();
            String key1 = uri.getQueryParameter("key1");
            String path = uri.getPath();
            String path1 = uri.getEncodedPath();
            String queryString = uri.getQuery();
            System.out.println("host:"+host);
            System.out.println("dataString:"+dataString);
            System.out.println("id:"+id);
            System.out.println("path:"+path);
            System.out.println("path1:"+path1);
            System.out.println("queryString:"+queryString);
            ((TextView)findViewById(R.id.text)).setText(key1);
        }


    }


    public void go(View view){
        startActivity(new Intent(this,LoginActivity.class));
    }
}
