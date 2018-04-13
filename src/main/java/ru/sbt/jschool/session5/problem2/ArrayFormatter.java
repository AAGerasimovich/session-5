package ru.sbt.jschool.session5.problem2;


import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public class ArrayFormatter implements JSONTypeFormatter<Object>{
    @Override public String format(Object arr, JSONFormatter formatter, Map<String, Object> ctx) {
        String json = "";

        if(ctx.get("name")!=null){
            json+= ctx.get("level")+"\"" + ctx.get("name") +"\": [\n";
        } else {
            json+= ctx.get("level")+ "[\n";
        }

        Map<String, Object> ctxMap = new HashMap<>();
        ctxMap.put("level", ctx.get("level")+"\t");

        for (int i = 0; i < (int)ctx.get("length"); ++i) {
            json += formatter.marshall(Array.get(arr, i), ctxMap) + ",\n";

        }

        json = Util.erase(json);

        return json+ ctx.get("level")+ "],\n";
    }
}
