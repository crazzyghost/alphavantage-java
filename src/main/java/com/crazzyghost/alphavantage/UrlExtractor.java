package com.crazzyghost.alphavantage;

import java.lang.reflect.Field;


/**
 * Extracts a valid url from a request object. The request object should contain valid 
 * api endpoint parameters
 * @since 1.0.0
 * @author crazzyghost
 */
public class UrlExtractor{

    private UrlExtractor(){
        
    }
    
    /**
     * Get an API url from a request object
     * @param object a request object with the valid API parameters
     * @return valid API url
     */
    public static String extract(Object object){

        //url
        StringBuilder stringBuilder = new StringBuilder();

        Class<?> cls = object.getClass();
        while(cls != null){
            //access all fields in object
            Field[] fields = cls.getDeclaredFields();
            for(Field field : fields){
                field.setAccessible(true);
                try {
                    //extract non-null and non-synthetic fields
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