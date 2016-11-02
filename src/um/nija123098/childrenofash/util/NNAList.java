package um.nija123098.childrenofash.util;

import um.nija123098.childrenofash.Reference;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Jack on 7/21/2016.
 */
public class NNAList<E> extends ArrayList<E>{
    private static final long serialVersionUID = Reference.serialVersionUID;
    public NNAList() {
    }
    public NNAList(Collection<? extends E> c) {
        super(c);
    }
    public NNAList(int initialCapacity) {
        super(initialCapacity);
    }
    @Override// only overrides used methods, not a general util
    public boolean add(E e){
        if (e != null){
            return super.add(e);
        }else{
            return false;
        }
    }
    @Override
    public boolean addAll(Collection collection){
        if (collection != null){// collection should be able to be null
            return super.addAll(collection);
        }else{
            return false;
        }
    }
}
