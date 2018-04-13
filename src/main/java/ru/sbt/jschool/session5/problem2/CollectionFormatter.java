package ru.sbt.jschool.session5.problem2;



import java.util.*;

public class CollectionFormatter implements JSONTypeFormatter<Collection>{
    @Override public String format(Collection collection, JSONFormatter formatter, Map<String, Object> ctx) {
        String json = "";


        if(ctx.get("name")!=null){
            json+= ctx.get("level")+"\"" + ctx.get("name") +"\": [\n";
        } else {
            json+= ctx.get("level")+ "[\n";
        }

        Map<String, Object> ctxMap = new HashMap<>();
        ctxMap.put("level", ctx.get("level")+"\t");


        for (Object entry: collection) {
            if (entry == null)
                continue;
            json += formatter.marshall(entry, ctxMap) + ",\n";

        }

        json = Util.erase(json);

        return json+ ctx.get("level")+ "],\n";
    }
}
