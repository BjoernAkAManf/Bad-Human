package link.mcseu.badhuman.util;

import java.security.SecureRandom;
import java.util.Random;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RandomUtil {
    private final Random SECURE = new SecureRandom();
    private final Random RANDOM = new Random();

    public float nextSecureFloat(float offset) {
        return newSecure().nextFloat() + offset;
    }

    public Random newSimple() {
        return RANDOM;
    }

    public Random newSecure() {
        return SECURE;
    }
}