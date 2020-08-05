/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;

/**
 *
 * @author joalg
 */
public class Clonador {
    public static <T> T clone(T object){
        T clone = null;
        try{
            clone = (T) object.getClass().newInstance();
        }
        catch(InstantiationException e){
            e.printStackTrace();
        }
        catch(IllegalAccessException e){
            e.printStackTrace();
        }
        
        for( Class obj = object.getClass(); !obj.equals(Object.class); obj = obj.getSuperclass()){
            for( Field field : obj.getDeclaredFields()){
                field.setAccessible(true);
                try {
                    field.set(clone, field.get(object));
                } catch (Throwable t) {
                }
            }
        }
        return clone;
    }

    public static Object deepCopy(Object object) {
        try {
          ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
          ObjectOutputStream outputStrm = new ObjectOutputStream(outputStream);
          outputStrm.writeObject(object);
          ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
          ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
          return objInputStream.readObject();
        }
        catch (Exception e) {
          e.printStackTrace();
          return null;
        }
    }
}