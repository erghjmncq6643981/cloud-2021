/*
 * cloud-2021
 * 6/20/21 10:12 PM
 *
 * Please contact chandler
 * if you need additional information or have any questions.
 * Please contact chandler Corporation or visit:
 * https://www.jianshu.com/u/117796446366
 * @author 钱丁君-chandler
 * @version 1.0
 */
package com.chandler.instance.client.example.core;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 类功能描述
 *
 * @author 钱丁君-chandler 6/20/21 10:12 PM
 * @version 1.0
 * @since 1.8
 */
public class ClassLoaderTest {
  public static void main(String[] args) throws Exception {
    String path="/Users/qiandingjun/Downloads/class/";
    File file = new File(path);
    URL url = file.toURI().toURL();
    ClassLoader classLoader=new URLClassLoader(new URL[]{url});
    Class<?> clazz = classLoader.loadClass("Person");
    System.out.println("当前类加载器"+clazz.getClassLoader());
    System.out.println("父类加载器"+clazz.getClassLoader().getParent());
//    Person person= (Person) clazz.newInstance();

//    person.setName("chandler");
//    person.setSex("man");
//    person.setAge(20);
    System.out.println(clazz.newInstance().toString());
  }
}
