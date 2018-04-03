package ru.sbt.jschool.session5.problem1;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 */
public class SQLGenerator {


    private <T> List<Map<String,String>> getData(Class<T> clazz){


        Field[] fields = clazz.getDeclaredFields();

        List<Map<String,String>> data = new ArrayList<>();
        Map<String,String> table = new HashMap<>();
        table.put("table", clazz.getAnnotation(Table.class).name());
        data.add(table);


        for (Field f: fields){

            Map<String, String> tmp = new HashMap<>();
            try {
                Column column = f.getAnnotation(Column.class);
                tmp.put("column", column.name().equals("") ? f.getName().toLowerCase() : column.name().toLowerCase());
                data.add(tmp);

            } catch (NullPointerException e){
                System.err.println("Oops");
            }
            try {
                PrimaryKey primaryKey = f.getAnnotation(PrimaryKey.class);
                tmp.put("primaryKey", primaryKey.name().equals("") ? f.getName().toLowerCase() : primaryKey.name().toLowerCase());
                data.add(tmp);

            } catch (NullPointerException e){
                System.err.println("Oops");
            }
        }

        return data;
    }

    public <T> String insert(Class<T> clazz) {

        List<Map<String,String>> data = getData(clazz);

        StringBuilder sb = new StringBuilder("INSERT INTO ");
        StringBuilder brackets = new StringBuilder(") VALUES (");
        for(Map<String,String> d: data){
            if (d.get("table")!=null){
                sb.append(d.get("table"));
                sb.append("(");


            } else {
                sb.append(d.values().toArray()[0]);
                sb.append(", ");
                brackets.append("?, ");
            }
        }
        sb.setLength(Math.max(sb.length() - 2, 0));
        brackets.setLength(Math.max(brackets.length() - 2, 0));
        sb.append(brackets);

        return sb.toString() + ")";
    }

    public <T> String update(Class<T> clazz) {
        List<Map<String,String>> data = getData(clazz);

        StringBuilder update = new StringBuilder("UPDATE ");
        StringBuilder set = new StringBuilder("SET ");
        StringBuilder where = new StringBuilder(" WHERE ");
        for(Map<String,String> d: data){
            if (d.get("table")!=null){
                update.append(d.get("table"));
                update.append(" ");


            }
            if (d.get("column")!=null){
                set.append(d.get("column"));
                set.append(" = ?, ");

            }
            if (d.get("primaryKey")!=null){
                where.append(d.get("primaryKey"));
                where.append(" = ? AND ");

            }
        }
        set.setLength(Math.max(set.length() - 2, 0));
        where.setLength(Math.max(where.length() - 5, 0));
        update.append(set).append(where);

        return update.toString();
    }

    public <T> String delete(Class<T> clazz) {
        List<Map<String,String>> data = getData(clazz);

        StringBuilder delete = new StringBuilder("DELETE FROM ");
        StringBuilder where = new StringBuilder("WHERE ");
        for(Map<String,String> d: data){
            if (d.get("table")!=null){
                delete.append(d.get("table"));
                delete.append(" ");


            }
            if (d.get("primaryKey")!=null){
                where.append(d.get("primaryKey"));
                where.append(" = ? AND ");

            }
        }
        where.setLength(Math.max(where.length() - 5, 0));
        delete.append(where);

        return delete.toString();
    }

    public <T> String select(Class<T> clazz) {
        List<Map<String,String>> data = getData(clazz);

        StringBuilder select = new StringBuilder("SELECT ");
        StringBuilder from = new StringBuilder(" FROM ");
        StringBuilder where = new StringBuilder("WHERE ");
        for(Map<String,String> d: data){
            if (d.get("table")!=null){
                from.append(d.get("table"));
                from.append(" ");


            }
            if (d.get("column")!=null){
                select.append(d.get("column"));
                select.append(", ");

            }
            if (d.get("primaryKey")!=null){
                where.append(d.get("primaryKey"));
                where.append(" = ? AND ");

            }
        }
        select.setLength(Math.max(select.length() - 2, 0));
        where.setLength(Math.max(where.length() - 5, 0));
        select.append(from).append(where);

        return select.toString();
    }
}
