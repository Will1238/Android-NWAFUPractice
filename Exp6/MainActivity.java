package com.example.will.exp6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DBAdapter dbAdapter = new DBAdapter(this);
        final EditText editName = (EditText)findViewById(R.id.editText);
        final EditText editClass = (EditText)findViewById(R.id.editText2);
        final EditText editNumber = (EditText)findViewById(R.id.editText3);
        final EditText editID = (EditText)findViewById(R.id.editText4);
        final ListView listView = (ListView)findViewById(R.id.listView);
        final List<String> list = new ArrayList<String>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        //添加数据
        Button btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                People people = new People();
                people.Name = editName.getText().toString();
                people.Class = editClass.getText().toString();
                people.Number = editNumber.getText().toString();

                dbAdapter.open();
                dbAdapter.insert(people);
                dbAdapter.close();
            }
        });

        //全部显示
        btn = (Button)findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbAdapter.open();
                People[] people = dbAdapter.queryAllData();
                dbAdapter.close();

                list.clear();
                if(people != null){
                    for (People p : people){
                        list.add(p.toString());
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });

        //清除显示
        btn = (Button)findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.clear();
                adapter.notifyDataSetChanged();
            }
        });

        //全部删除
        btn = (Button)findViewById(R.id.button4);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbAdapter.open();
                dbAdapter.deleteAllData();
                dbAdapter.close();
            }
        });

        //ID删除
        btn = (Button)findViewById(R.id.button5);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbAdapter.open();
                dbAdapter.deleteOneData(Long.parseLong(editID.getText().toString()));
                dbAdapter.close();
            }
        });

        //ID查询
        btn = (Button)findViewById(R.id.button6);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbAdapter.open();
                People[] people = dbAdapter.queryOneData(Long.parseLong(editID.getText().toString()));
                dbAdapter.close();

                list.clear();
                if(people != null){
                    for(People p : people){
                        list.add(p.toString());
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });

        //ID更新
        btn = (Button)findViewById(R.id.button7);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                People people = new People();
                people.Name = editName.getText().toString();
                people.Class = editClass.getText().toString();
                people.Number = editNumber.getText().toString();

                dbAdapter.open();
                dbAdapter.updateOneData(Long.parseLong(editID.getText().toString()), people);
                dbAdapter.close();
            }
        });
    }
}
