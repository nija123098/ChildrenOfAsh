package um.nija123098.childrenofash.util;

import java.util.Random;

/**
 * Created by Jack on 7/24/2016.
 */
public class UniqueIDHelper {
    private static final NNAList<Integer> INTEGERS = new NNAList<Integer>();
    private static final Random random = new Random();
    public static int getUniqeEntityId(){
        int i;
        while (true){
            i = Math.abs(random.nextInt());
            if (!INTEGERS.contains(i)){
                break;
            }
        }
        INTEGERS.add(i);
        return i;
    }
}
