package com.example.will.exp6;

/**
 * Created by Will on 2018/6/25.
 */

public class People {
        public int ID = -1;
        public String Name;
        public String Class;
        public String Number;

        @Override
        public String toString(){
            String result="";
            result+="ID：" + this.ID + "，";
            result+="姓名：" + this.Name + "，";
            result+="班级：" + this.Class + "，";
            result+="学号：" + this.Number;
            return result;
        }
}
