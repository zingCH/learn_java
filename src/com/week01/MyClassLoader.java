/**
 * <b>工程名：</b>learn_java<br/>
 * <b>包  名：</b>com.week01<br/>
 * <b>文件名：</b>MyClassLoader.java<br/>
 * <b>日  期：</b>2021/05/06<br/>
 */
package com.week01;

import sun.misc.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <b>类  名：</b>MyClassLoader<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>qchang<br/>
 * <b>创建时间：</b>2021/05/06<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0 <br/>
 */

public class MyClassLoader extends ClassLoader{

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        Class aClass = new MyClassLoader().findClass("Hello");
        Method hello = aClass.getMethod("hello", null);
        hello.invoke(aClass.newInstance(),null);
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("com/week01/Hello.xlass");
        byte[] bytesSrc = new byte[0];
        try {
            bytesSrc = IOUtils.readAllBytes(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = new byte[bytesSrc.length];
        for (int i = 0; i < bytesSrc.length; i++) {
            bytes[i] = (byte) (255-bytesSrc[i]);
        }
        return defineClass(name,bytes,0,bytes.length);
    }
}
