package ru.sbt.jschool.session5.problem2;

import java.util.Map;

public class StringFormatter implements JSONTypeFormatter<String>{
    @Override public String format(String str, JSONFormatter formatter, Map<String, Object> ctx) {

        if(ctx.get("name")!=null){
            return ctx.get("level") + formatter.marshall(ctx.get("name")) +": \""  + str +"\",\n";
        }
        return  ctx.get("level")+  "\"" + str+"\"" ;
    }
}
