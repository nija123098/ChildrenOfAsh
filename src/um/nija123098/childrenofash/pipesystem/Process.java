package um.nija123098.childrenofash.pipesystem;

import um.nija123098.childrenofash.util.NNAList;

/**
 * Created by Jack on 7/18/2016.
 */
public abstract class Process implements Processed{
    private Pipe pipe;
    public void addPipe(Pipe pipe){
        this.pipe = pipe;
    }
    public NNAList<Liquid> process(){
        return this.process(this.pipe.getLiquids(), this.pipe.getDeltaSyncTime());
        // todo may want to check if there are unprocessed liquids
    }
    public void start(){
    }
    // protected abstract void process(List<Liquid> liquids, double deltaSyncTime);
    public void stop(){
    }
}
