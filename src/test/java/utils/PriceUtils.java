package utils;

import java.util.Collections;
import java.util.List;

public class PriceUtils {

    public static boolean isSortedAscending(List<Integer> prices) {
        return prices.equals(prices.stream().sorted().toList());
    }

    public static boolean isSortedDescending(List<Integer> prices) {
        return prices.equals(
                prices.stream().sorted(Collections.reverseOrder()).toList()
        );
    }
}

