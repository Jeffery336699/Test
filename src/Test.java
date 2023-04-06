public class Test {
    public static void main(String[] args) {
        HttpApi httpApi = new HttpApiImpl();
        HttpApi proxy = ProxyFactory.getProxy(httpApi);
        // HttpApi proxy = ProxyFactory.getProxy(HttpApi.class);
        proxy.get("www.baidu.com");
    }
}
