package ru.sbt.jschool.session5.problem2;



import ru.sbt.jschool.session5.problem1.Currency;

import java.lang.reflect.*;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

public class Serializer {



    public static void main(String[] args){
        Serializer o = new Serializer();
        System.out.println(o.parse(new Account(34L, 345L, 4532L , Currency.RUR, 456456)));

    }


    public String parse(Object object) {

        Field[] declaredFields = object.getClass().getDeclaredFields();
        String json = "";

        for (Field field: declaredFields){
            json += "\""+field.getName() + "\": " ;

            try {
                field.setAccessible(true);
                Object fieldValue = field.get(object);

                if(!getJson(fieldValue).equals("")){
                    json+=getJson(fieldValue);
                }else {
                    if (fieldValue instanceof String) {
                        json += "\"" + fieldValue.toString() + "\"";
                    } else {
                        json +=  fieldValue.toString();
                    }
                }

            }catch (IllegalAccessException e){
                System.out.println("");
            }catch (NullPointerException e){

            }
            json+=",\n";
        }

        if (!json.equals("")) {

            json =   tab("{\n" + json);
        }
        return json.substring(0, json.length() - 1) + "}";
    }

    private  String parseMap(Map map){
        String json = "[\n";
        Set<Map.Entry > set = map.entrySet();

        for (Map.Entry entry: set) {
            if (getJson(entry.getKey()).equals("")) {
                if (entry.getKey() instanceof String) {
                    json += "\"" + entry.getKey().toString() + "\": ";
                } else {
                    json += entry.getKey().toString() + ": ";
                }
            }
            else {
                json +=getJson(entry.getKey()) + ": ";
            }
            if (getJson(entry.getValue()).equals("")) {
                if (entry.getValue() instanceof String) {
                    json += "\"" + entry.getValue().toString() + "\"\n";
                } else {
                    json += entry.getValue().toString() + "\n";
                }
            }
            else {
                json +=getJson(entry.getValue())+ "\n";
            }

        }


        return json+"]";
    }


    private  String parseArray(Object arr, int n){
        String json = "[\n";

        for (int i = 0; n>i; ++i){

            if(!getJson(Array.get(arr, i)).equals("")){

                json +=getJson(Array.get(arr, i)) + "\n";
            } else

            if (Array.get(arr, i) instanceof String) {
                json +=  "\"" + Array.get(arr, i)+ "\",\n";
            } else {
                json +=  Array.get(arr, i)+ ",\n";
            }
        }
        return json+"]";
    }

    private String tab(String json){
        return json.replace("\n", "\n\t");
    }

    private String getJson (Object fieldValue){
        String json = "";
        try {

            if (check(fieldValue)) {
                json +=  tab(parse(fieldValue));

            } else if (fieldValue.getClass().isArray()){
                json +=  tab(parseArray(fieldValue, Array.getLength(fieldValue)));

            } else if (fieldValue instanceof Map){
                json += tab(parseMap((Map)fieldValue));

            } else if (fieldValue instanceof Collection){
                json +=  tab(parseArray(((Collection) fieldValue).toArray(), ((Collection) fieldValue).size()));

            } else if (fieldValue instanceof Date){
                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                json += format.format((Date)fieldValue);

            }
            return json;

        }catch (NullPointerException e){

        }
        return json;
    }

    private boolean check(Object fieldValue){
        return !(fieldValue instanceof Integer) &&
                !(fieldValue instanceof String) &&
                !(fieldValue instanceof Double) &&
                !(fieldValue instanceof Long) &&
                !(fieldValue instanceof Short) &&
                !(fieldValue instanceof Float) &&
                !(fieldValue instanceof Boolean) &&
                !(fieldValue instanceof Character) &&
                !(fieldValue instanceof Date) &&
                !(fieldValue instanceof Byte) &&
                !(fieldValue instanceof Arrays) &&
                !(fieldValue instanceof Calendar) &&
                !(fieldValue instanceof Collection) &&
                !(fieldValue instanceof Map)&&
                !(fieldValue instanceof Enum) &&
                !fieldValue.getClass().isArray();
    }
}
