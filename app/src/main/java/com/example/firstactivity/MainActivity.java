package com.example.firstactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.BreakIterator;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    String str="";
    TextView text;
    boolean isError=false;
    boolean isInt=false;
    public void sqrt() {
        if(isError==true){}
        else if (calculate() < 0) {
            Toast.makeText(MainActivity.this, "Input error", Toast.LENGTH_SHORT).show();
            str="";
        } else {
            str=""+Math.sqrt(calculate());
        }
        text.setText(str);
        isError=false;
    }
    public String change(String str){
        char[] chars=str.toCharArray();

        for(int i=0;i<chars.length-1;i++){
            if(i==0&&chars[i]=='-'&&chars[i+1]!='-')
                str='@'+str.substring(1);
            if(chars[i]=='*'&&chars[i+1]=='-'||chars[i]=='/'&&chars[i+1]=='-'
                    ||chars[i]=='+'&&chars[i+1]=='-'||chars[i]=='%'&&chars[i+1]=='-'
                    ||chars[i]=='-'&&chars[i+1]=='-'&&i!=0)
                str=str.substring(0,i+1)+'@'+str.substring(i+2);
        }
         return str;

    }
    public ArrayList getOps(String str){
        str=change(str);
        ArrayList<String> list=new ArrayList();
        String[] split=str.split("[0-9\\.\\@]");
        for(int i=0;i<split.length;i++)
            if(split[i].contains("+")||split[i].contains("-")||split[i].contains("*")||
                    split[i].contains("/")||split[i].contains("%"))
            {
                list.add(split[i]);
            }
        return list;
    }
    public ArrayList getNum(String str){
        str=change(str);
        ArrayList<Double> list=new ArrayList();
        String[] split=str.split("[-%\\+\\*\\/]");
        for(int i=0;i<split.length;i++)
        {
            String s=split[i];
            if(s.indexOf('.')!=s.lastIndexOf('.'))
            {
                isError=true;
                return list;
            }
            if(s.contains("@")){
                s='-'+s.substring(1);
            }
            list.add(Double.parseDouble(s));
        }
        return list;
    }
    public double calculate(){
        ArrayList<String> ops=getOps(str);
        for(int i=0;i<ops.size();i++) {
            if (ops.get(i).length() > 1) {
                Toast.makeText(MainActivity.this, "Input error", Toast.LENGTH_SHORT).show();
                str = "";
                isError = true;
                return -1;
            }
        }
        ArrayList<Double> num=getNum(str);
        if(ops.size()==0&&num.size()==0){
            Toast.makeText(MainActivity.this, "NULL", Toast.LENGTH_SHORT).show();
            str="";
            isError=true;
            return -1;
        }
        if(ops.size()!=num.size()-1||isError==true){
            Toast.makeText(MainActivity.this, "Input error", Toast.LENGTH_SHORT).show();
            str="";
            isError=true;
            return -1;
        }
        for(int i=0;i<ops.size();i++)
        {
                if(ops.get(i).equals("*"))
                {
                    double res=num.get(i)*num.get(i+1);
                    num.remove(i+1);
                    num.remove(i);
                    num.add(i,res);
                    ops.remove(i);
                    i--;
                }
                else if(ops.get(i).equals("/")){
                    if(num.get(i+1)==0) {
                        Toast.makeText(MainActivity.this, "Input error", Toast.LENGTH_SHORT).show();
                        str="";
                        isError=true;
                        return -1;
                    }
                    double res=num.get(i)/num.get(i+1);
                    num.remove(i+1);
                    num.remove(i);
                    num.add(i,res);
                    ops.remove(i);
                    i--;
                }
                else if(ops.get(i).equals("%"))
                {
                    if(num.get(i+1)==0) {
                        Toast.makeText(MainActivity.this, "Input error", Toast.LENGTH_SHORT).show();
                        str="";
                        isError=true;
                        return -1;
                    }
                    double res=num.get(i)%num.get(i+1);
                    num.remove(i+1);
                    num.remove(i);
                    num.add(i,res);
                    ops.remove(i);
                    i--;
                }

        }
        while(ops.size()!=0){
            String op=ops.remove(0);
            double num1=num.remove(0);
            double num2=num.remove(0);
            if(op.equals("+")){
                double res=num1+num2;
                num.add(0,res);
            }
            if(op.equals("-")){
                double res=num1-num2;
                num.add(0,res);
            }
        }
        if(!str.contains(".")&&num.get(0).intValue()-num.get(0)==0)
             str=""+num.get(0).intValue();
        else
            str=""+num.get(0);
        return num.get(0);
    }
    public void result(){
        calculate();
        text.setText(str);
        isError=false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        text=(TextView) findViewById(R.id.text);
        Button button_ac=(Button) findViewById(R.id.button_ac);
        button_ac.setOnClickListener(this);
        Button button_sqrt=(Button) findViewById(R.id.button_sqrt);
        button_sqrt.setOnClickListener(this);
        Button button_squ=(Button) findViewById(R.id.button_squ);
        button_squ.setOnClickListener(this);
        Button button_add=(Button) findViewById(R.id.button_add);
        button_add.setOnClickListener(this);
        Button button_sub=(Button) findViewById(R.id.button_sub);
        button_sub.setOnClickListener(this);
        Button button_div=(Button) findViewById(R.id.button_div);
        button_div.setOnClickListener(this);
        Button button_mul=(Button) findViewById(R.id.button_mul);
        button_mul.setOnClickListener(this);
        Button button_0=(Button) findViewById(R.id.button_0);
        button_0.setOnClickListener(this);
        Button button_1=(Button) findViewById(R.id.button_1);
        button_1.setOnClickListener(this);
        Button button_2=(Button) findViewById(R.id.button_2);
        button_2.setOnClickListener(this);
        Button button_3=(Button) findViewById(R.id.button_3);
        button_3.setOnClickListener(this);
        Button button_4=(Button) findViewById(R.id.button_4);
        button_4.setOnClickListener(this);
        Button button_5=(Button) findViewById(R.id.button_5);
        button_5.setOnClickListener(this);
        Button button_6=(Button) findViewById(R.id.button_6);
        button_6.setOnClickListener(this);
        Button button_7=(Button) findViewById(R.id.button_7);
        button_7.setOnClickListener(this);
        Button button_8=(Button) findViewById(R.id.button_8);
        button_8.setOnClickListener(this);
        Button button_9=(Button) findViewById(R.id.button_9);
        button_9.setOnClickListener(this);
        Button button_point=(Button) findViewById(R.id.button_point);
        button_point.setOnClickListener(this);
        Button button_mod=(Button) findViewById(R.id.button_mod);
        button_mod.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(str.length()>20)
        {
            Toast.makeText(MainActivity.this, "Too long", Toast.LENGTH_SHORT).show();
            if(view.getId()==R.id.button_ac)
            {
                str="";
                isError=false;
                text.setText(str);
            }
            else if(view.getId()==R.id.button_sqrt){
                sqrt();
            }
            else if(view.getId()==R.id.button_squ)
            {
                result();
            }
            return;
        }
        switch (view.getId()){
            case R.id.button_ac:
                str="";
                isError=false;
                text.setText(str);
                break;
            case R.id.button_sqrt:
                sqrt();
                break;
            case R.id.button_squ:
                result();
                break;
            case R.id.button_0:
                str+="0";
                text.setText(str);
                break;
            case R.id.button_1:
                str+="1";
                text.setText(str);
                break;
            case R.id.button_2:
                str+="2";
                text.setText(str);
                break;
            case R.id.button_3:
                str+="3";
                text.setText(str);
                break;
            case R.id.button_4:
                str+="4";
                text.setText(str);
                break;
            case R.id.button_5:
                str+="5";
                text.setText(str);
                break;
            case R.id.button_6:
                str+="6";
                text.setText(str);
                break;
            case R.id.button_7:
                str+="7";
                text.setText(str);
                break;
            case R.id.button_8:
                str+="8";
                text.setText(str);
                break;
            case R.id.button_9:
                str+="9";
                text.setText(str);
                break;
            case R.id.button_add:
                str+="+";
                text.setText(str);
                break;
            case R.id.button_sub:
                str+="-";
                text.setText(str);
                break;
            case R.id.button_div:
                str+="/";
                text.setText(str);
                break;
            case R.id.button_mul:
                str+="*";
                text.setText(str);
                break;
            case R.id.button_mod:
                str+="%";
                text.setText(str);
                break;
            case R.id.button_point:
                str+=".";
                text.setText(str);
                break;
            default:
                break;
        }
    }
}