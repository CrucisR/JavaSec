package com.Crucis.Vuldemo;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class newClass {
    public void getClassLoader() throws ClassNotFoundException {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        ClassLoader cl1 = File.class.getClassLoader();

        // ClassLoader 方法
        Class<?> Class0 = cl.loadClass("");
        Class<?> Class1 = cl.findClass("");
        Class<?> Class2 = cl.findLoadedClass("");
        Class<?> Class3 = cl.defineClass("", byte[] b, int i, int j);
        Class<?> Class4 = cl.loadClaresolveClass("");

    }

    // 类动态加载  显式 JAVA反射 ClassLoader

    // 隐式指的是类名.方法名()或new类实例

    //
    // Class.forName("类名")默认会初始化被加载类的静态属性和方法
    // 如果不希望初始化类可以使用Class.forName("类名", 是否初始化类, 类加载器)
    // 而ClassLoader.loadClass默认不会初始化类方法
    public void getClassLoader1() throws ClassNotFoundException {
        // 反射加载TestHelloWorld示例
        Class.forName("");

        // ClassLoader加载TestHelloWorld示例
        this.getClass().getClassLoader().loadClass("");
    }


    public void getClass3() {
        String cla = "com.";
        try {
            Class<?> Class0 = java.io.File.class;
            Class<?> Class1 = Class.forName("com.");
            Class<?> Class2 = ClassLoader.getSystemClassLoader().loadClass("com.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void getInstance2(Class runtimeClass1) throws Exception {

        // 获取实例01

        // 获取构造方法
        // 使用Runtime类的Class对象获取Runtime类的无参数构造方法(getDeclaredConstructor())
        // 因为Runtime的构造方法是private的我们无法直接调用
        // 所以我们需要通过反射去修改方法的访问权限(constructor.setAccessible(true))
        Constructor constructor = runtimeClass1.getDeclaredConstructor();
        constructor.setAccessible(true);

        // 创建Runtime类示例，等价于 Runtime rt = new Runtime();
        Object runtimeInstance = constructor.newInstance();

// 后续调用
        // 获取Runtime的exec(String cmd)方法
        Method runtimeMethod = runtimeClass1.getMethod("exec", String.class);
        // 调用exec方法，等价于 rt.exec(cmd);
        Process process = (Process) runtimeMethod.invoke(runtimeInstance, cmd);
        // 获取命令执行结果
        InputStream in = process.getInputStream();
        // 输出命令执行结果
        System.out.println(org.apache.commons.io.IOUtils.toString(in, "UTF-8"));


        // 获取实例02
        // classstr -> classloader -> class -> instance
        // 使用自定义的类加载器加载TestHelloWorld类
        String testClassName = "";
        Class testClass = ClassLoader.getSystemClassLoader().loadClass(testClassName);

        // 反射创建TestHelloWorld类，等价于 TestHelloWorld t = new TestHelloWorld();
        Object testInstance = testClass.newInstance();

    }

    public void classUse(Class clazz, Object Instance) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        // 获取某个类的所有的成员方法的方法

        // getMethod只能获取到当前类和父类的所有有权限的方法(如：public)
        // 而getDeclaredMethod能获取到当前类的所有成员方法(不包含父类)
        Method[] methods = clazz.getDeclaredMethods();
        Method method0 = clazz.getDeclaredMethod("方法名");
        Method method1 = clazz.getDeclaredMethod("方法名", String.class, int.class);

        String param1 = null;
        int param2 = 0;

        // 调用类方法
        method1.invoke(Instance, param1, param2);


        // 调用成员变量
        Field[] fields = clazz.getDeclaredFields();
        Field field  = clazz.getDeclaredField("变量名");
        // 获取成员变量值：
        Object obj = field.get(Instance);
        // 修改成员变量值
        field.set(Instance, newInstance);

        // 反射获取Field类的modifiers
        Field modifiers = field.getClass().getDeclaredField("modifiers");

        // 设置modifiers修改权限
        modifiers.setAccessible(true);

        // 修改成员变量的Field对象的modifiers值
        modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        // 修改成员变量值
        field.set(类实例对象, 修改后的值);
    }

}