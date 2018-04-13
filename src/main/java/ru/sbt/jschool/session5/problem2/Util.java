package ru.sbt.jschool.session5.problem2;

public class Util {
    static public String erase(String json){
        if (json.charAt(json.length()-2)==','){
            json = json.substring(0,json.length()-2)+"\n";
        }
        if (json.charAt(json.length()-3)==','){
            json = json.substring(0,json.length()-3)+"\n";
        }
        if (json.charAt(json.length()-2)=='\n'){
            json = json.substring(0,json.length()-1);
        }
        return json;
    }
}
