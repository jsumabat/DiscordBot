package Utils;

import java.util.HashMap;

public class RateLimiter {

    private static HashMap<Long, Long> normalRateLimitMap = new HashMap<>();
    private static HashMap<Long, Pair<Long, Integer>> exponentialRateLimitMap = new HashMap<>();

    public enum RateLimitLevel {
        /**
         * 1s cooldown for normal commands
         */
        RATE_LIMIT_LEVEL_NORMAL,
        /**
         * 1s -> 64s cooldown for costly commands
         */
        RATE_LIMIT_LEVEL_EXPONENTIAL
    }

    /**
     * Check if the user should be rate limited based on limiting level
     * @param uid User Id of the executor of the command
     * @param commandLimitLevel The limit level of the command its self
     * @return returns 0 if the user is not rate limited, else it returns the remaining time limited in milliseconds.
     */
    public static int CheckUserRateLimit(long uid, RateLimitLevel commandLimitLevel) {
        if(commandLimitLevel == RateLimitLevel.RATE_LIMIT_LEVEL_NORMAL) {
            if(normalRateLimitMap.containsKey(uid)){
                long tDiff = System.currentTimeMillis() - normalRateLimitMap.get(uid);
                if(tDiff <= 1000) {
                    return (int)(1000 - tDiff);
                } else {
                    normalRateLimitMap.put(uid, System.currentTimeMillis());
                }
            } else {
                normalRateLimitMap.put(uid, System.currentTimeMillis());
            }
        } else {
            if(exponentialRateLimitMap.containsKey(uid)) {
                long tDiff = System.currentTimeMillis() - exponentialRateLimitMap.get(uid).getFirst();
                int punishment = exponentialRateLimitMap.get(uid).getSecond();
                if(tDiff <= Math.pow(2, punishment) * 1000) {
                    if(punishment < 6) {
                        exponentialRateLimitMap.put(uid, new Pair<>(System.currentTimeMillis(), punishment+1));
                        return (int)(Math.pow(2, punishment) * 1000);
                    } else {
                        exponentialRateLimitMap.put(uid, new Pair<>(exponentialRateLimitMap.get(uid).getFirst(), punishment));
                        return (int)(Math.pow(2, punishment) * 1000 - tDiff);
                    }
                } else {
                    exponentialRateLimitMap.put(uid, new Pair<>(System.currentTimeMillis(), 0));
                }
            } else {
                exponentialRateLimitMap.put(uid, new Pair<>(System.currentTimeMillis(), 0));
            }
        }
        return 0;
    }
}
