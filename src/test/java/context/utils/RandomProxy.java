package context.utils;

import java.util.Arrays;
import java.util.List;

public class RandomProxy
{
    public Integer getRandomProxy()
    {
        List<Integer> proxies = Arrays.asList(
                23, 80, 81, 443, 1337, 1859, 3000, 3002,
                3030, 3128, 3306, 3333, 3621, 4000, 4502,
                5000, 5757, 5790, 7774, 8000, 8001, 8080,
                8081, 8082, 8083, 8084, 8085, 8086, 8443,
                8760, 8888, 8899, 9000, 9100, 9200, 9400,
                9876, 9877, 9880, 9900, 9999, 10002, 13260,
                14357, 38946, 49772, 50208, 54134, 54136,
                60778, 63342, 64000
        );

        Integer proxy = proxies.get(new java.util.Random().nextInt(proxies.size()));

        return proxy;

    }
}
