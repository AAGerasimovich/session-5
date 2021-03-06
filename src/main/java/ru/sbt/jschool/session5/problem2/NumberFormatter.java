package ru.sbt.jschool.session5.problem2;



import java.util.Map;

public class NumberFormatter implements JSONTypeFormatter<Object>{
    @Override public String format(Object obj, JSONFormatter formatter, Map<String, Object> ctx) {

        if(ctx.get("name")!=null){
            return ctx.get("level")+ formatter.marshall(ctx.get("name")) +": " + obj + ",\n";
        }
        return  ctx.get("level")+ obj.toString() ;


    }
}
