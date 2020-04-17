package com.crazzyghost.alphavantage;

import java.lang.reflect.Field;


public class UrlExtractor{
    
    public static String extract(Object object){

        StringBuilder stringBuilder = new StringBuilder();

        Class<?> cls = object.getClass();
        while(cls != null){
            Field[] fields = cls.getDeclaredFields();
            for(Field field : fields){
                field.setAccessible(true);
                try {

                    if (field.get(object) != null){
                        stringBuilder.append(field.getName())
                                .append("=");
                        if(field.getType().isEnum()){
                            String value = (field.get(object)).toString();
                            stringBuilder.append(value).append("&");
                        }else{
                            stringBuilder.append((String)field.get(object)).append("&");
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            cls = cls.getSuperclass();
        }

        return stringBuilder.append("apikey=").toString();

    }
}
