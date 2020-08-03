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

/**
 *
 * @author USUARIO
 */
public class Clonador {
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
