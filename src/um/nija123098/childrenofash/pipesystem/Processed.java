package um.nija123098.childrenofash.pipesystem;

import um.nija123098.childrenofash.util.NNAList;

/**
 * Created by Jack on 7/20/2016.
 */
public interface Processed {
    NNAList<Liquid> process(NNAList<Liquid> liquids, double deltaSyncTime);
}
