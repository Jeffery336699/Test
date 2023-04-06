import com.sun.istack.internal.Nullable;
import sun.rmi.runtime.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {
    public static HttpApi getProxy(HttpApi target) {
        return (HttpApi) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new LogHandler(target));
    }

    public static HttpApi getProxy(Class clazz) {
        return (HttpApi) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                clazz.getInterfaces(),
                new LogHandler(clazz));
    }

    private static class LogHandler implements InvocationHandler {
        private Object target;

        LogHandler(HttpApi target) {
            this.target = target;
        }

        LogHandler(Class clazz) {
            try {
                this.target = clazz.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        // method底层的方法无参数时，args为空或者长度为0
        @Override
        public Object invoke(Object proxy, Method method, @Nullable Object[] args)
                throws Throwable {
            // Constructor<?> constructor = proxy.getClass().getConstructor(InvocationHandler.class);
            // constructor.setAccessible(true);
            // Object o = constructor.newInstance(this);
            // method.invoke(o,args);
            // 扩展的功能
            System.out.println(proxy.getClass().getCanonicalName() + "---proxy1--" + args[0]);
            System.out.println(target.getClass().getSimpleName() + "---proxy2--" + args[0]);
            // 访问基础对象
            return method.invoke(target, args);
        }
    }
}

