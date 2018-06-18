package com.example.will.exp4;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Will on 2018/6/18.
 */

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        final EditText editText=(EditText)findViewById(R.id.textIn);
        Button btn2=(Button)findViewById(R.id.btn2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uriString=editText.getText().toString();
                Uri data= Uri.parse(uriString);
                Intent result=new Intent(null, data);
                setResult(RESULT_OK, result);
                finish();
            }
        });
    }
}
