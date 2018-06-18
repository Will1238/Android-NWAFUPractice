package com.example.will.caculator;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            setContentView(R.layout.activity_main);
        else
            setContentView(R.layout.activity_land);
        final TextView textView=(TextView)findViewById(R.id.Show);
        textView.setMovementMethod(new ScrollingMovementMethod());
        final TextView textViewAnswer=(TextView)findViewById(R.id.Answer);

 //数字按钮事件和点按钮事件
        Button button=(Button)findViewById(R.id.bt05);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(textView.getText()+"1");
            }
        });
        button=(Button)findViewById(R.id.bt06);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(textView.getText()+"2");
            }
        });
        button=(Button)findViewById(R.id.bt07);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(textView.getText()+"3");
            }
        });
        button=(Button)findViewById(R.id.bt09);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(textView.getText()+"4");
            }
        });
        button=(Button)findViewById(R.id.bt10);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(textView.getText()+"5");
            }
        });
        button=(Button)findViewById(R.id.bt11);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(textView.getText()+"6");
            }
        });
        button=(Button)findViewById(R.id.bt13);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(textView.getText()+"7");
            }
        });
        button=(Button)findViewById(R.id.bt14);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(textView.getText()+"8");
            }
        });
        button=(Button)findViewById(R.id.bt15);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(textView.getText()+"9");
            }
        });
        button=(Button)findViewById(R.id.bt17);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(textView.getText()+".");
            }
        });
        button=(Button)findViewById(R.id.bt18);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(textView.getText()+"0");
            }
        });

 //清除按钮
        button=(Button)findViewById(R.id.bt01);     //C按钮
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("");
                textViewAnswer.setText("");
            }
        });
        button=(Button)findViewById(R.id.bt02);     //CE按钮
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=String.valueOf(textView.getText());
                int index=text.lastIndexOf("\n");
                if(index == -1){
                    textView.setText("");
                }
                else{
                    textView.setText(text.substring(0,index));
                }
            }
        });
        button=(Button)findViewById(R.id.bt03);     //DEL按钮
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=String.valueOf(textView.getText());
                if(text.length() != 0){            //检测条件避免下面的函数调用越界
                    text=text.substring(0,text.length()-1);
                    if(text.lastIndexOf("\n") == text.length()-1){    //处理多余的空行
                        text=text.substring(0,text.length()-1);
                    }
                    textView.setText(text);
                }
            }
        });

//四种运算按钮
        button=(Button)findViewById(R.id.bt04);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(textView.getText()+"\n+");
                int offset=textView.getLineCount()*textView.getLineHeight();    //超出屏幕显示范围时自动向下滚动（显示底部），getHeight的返回值是当前可显示的范围的高度
                if(offset>textView.getHeight()){
                    textView.scrollTo(0,offset-textView.getHeight());
                }
            }
        });
        button=(Button)findViewById(R.id.bt08);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(textView.getText()+"\n-");
                int offset=textView.getLineCount()*textView.getLineHeight();
                if(offset>textView.getHeight()){
                    textView.scrollTo(0,offset-textView.getHeight());
                }
            }
        });
        button=(Button)findViewById(R.id.bt12);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(textView.getText()+"\n*");
                int offset=textView.getLineCount()*textView.getLineHeight();
                if(offset>textView.getHeight()){
                    textView.scrollTo(0,offset-textView.getHeight());
                }
            }
        });
        button=(Button)findViewById(R.id.bt16);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(textView.getText()+"\n/");
                int offset=textView.getLineCount()*textView.getLineHeight();
                if(offset>textView.getHeight()){
                    textView.scrollTo(0,offset-textView.getHeight());
                }
            }
        });

//等于号
        button=(Button)findViewById(R.id.bt19);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text= String.valueOf(textView.getText());
                double ans=0;
                String content="",ansview;
                ArrayList<Double> numbers=new ArrayList<Double>();
                ArrayList<String> ops=new ArrayList<String>();
                int index=text.indexOf('\n');

                while(index != -1){             //去除'\n'
                    text=text.substring(0,index)+text.substring(index+1,text.length());
                    index=text.indexOf('\n');
                }
                ansview=text;
                while(text.length()>0){     //提取数字和操作符
                    while(text.length()>0 && text.charAt(0)>='0' && text.charAt(0)<='9'){       //条件一防止越界访问
                        content+=text.charAt(0);
                        text=text.substring(1,text.length());
                    }
                    numbers.add(Double.valueOf(Double.parseDouble(content)));

                    if(text.length()>0)         //防止越界
                    {
                        ops.add(text.charAt(0)+"");
                        text=text.substring(1,text.length());
                    }
                    content="";
                }
                ans=numbers.size();
                ans=numbers.get(0).doubleValue();
                for(int i=0;i<ops.size();i++){
                    switch (ops.get(i).charAt(0)){
                        case '+':
                            ans+=numbers.get(i+1);
                            break;
                        case '-':
                            ans-=numbers.get(i+1);
                            break;
                        case '*':
                            ans*=numbers.get(i+1);
                            break;
                        case '/':
                            ans/=numbers.get(i+1);
                            break;
                        default:
                            break;
                    }
                }/**/

                textViewAnswer.setText(ansview+"="+ans);
                textView.setText("");
            }
        });
    }

}
