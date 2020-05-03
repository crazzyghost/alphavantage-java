package com.crazzyghost.alphavantage;

import java.lang.reflect.Field;


public class UrlExtractor{

    private UrlExtractor(){
        
    }
    
    public static String extract(Object object){

        StringBuilder stringBuilder = new StringBuilder();

        Class<?> cls = object.getClass();
        while(cls != null){
            Field[] fields = cls.getDeclaredFields();
            for(Field field : fields){
                field.setAccessible(true);
                try {
                    if (!field.isSynthetic() && field.get(object) != null){
                        stringBuilder.append(field.getName().toLowerCase())
                                .append("=");
                        String value = (field.get(object)).toString();
                        stringBuilder.append(value).append("&");
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