import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Test2 {
    // InvocationHandler stuHandler = new StuInvocationHandler<String>("111");
    // Class<?> stuProxyClass = Proxy.getProxyClass(IPerson.class.getClassLoader(), IPerson.class);
    // Constructor<?> constructor;

    {
        // Proxy类的newProxyInstance方法创建了一个动态代理对象
        // Proxy.newProxyInstance(IPerson.class.getClassLoader(),new Class[]{IPerson.class},stuHandler);
        // try {
        //     constructor = stuProxyClass.getConstructor(InvocationHandler.class);
        //     IPerson stuProxy = (IPerson) constructor.newInstance(stuHandler);
        //     System.out.println(stuProxy.giveMoney());
        // } catch (Exception e) {
        //     throw new RuntimeException(e);
        // }

        // byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0",new Class[]{IPerson.class});//系统默认是以$Proxy0，数字0开始
        byte[] classFile = ProxyGenerator.generateProxyClass("StuProxy",new Class[]{IPerson.class});//我们可以自己改生成的代理类名字
        String path = "C:/Users/jun/IdeaProjects/Test/out/StuProxy.class";
        try(FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(classFile);
            fos.flush();
            System.out.println("代理类class文件写入成功");
        } catch (Exception e) {
            System.out.println("写文件错误");
        }
    }

     class StuInvocationHandler<T> implements InvocationHandler {
        //invocationHandler持有的被代理对象
        T target;

        public StuInvocationHandler(T target) {
            this.target = target;
        }

        /**
         * proxy:代表动态代理对象
         * method：代表正在执行的方法
         * args：代表调用目标方法时传入的实参
         */
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("代理执行" +method.getName() + "方法");
            //代理过程中插入监测方法,计算该方法耗时
            MonitorUtil.start();
            // Object result = method.invoke(target, args);
            MonitorUtil.finish(method.getName());
            return "result";
        }
    }

    public static void main(String[] args) {
        new Test2();
    }
}
