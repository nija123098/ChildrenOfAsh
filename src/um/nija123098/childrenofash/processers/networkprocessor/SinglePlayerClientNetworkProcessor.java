package um.nija123098.childrenofash.processers.networkprocessor;

import um.nija123098.childrenofash.pipesystem.Liquid;
import um.nija123098.childrenofash.util.NNAList;

/**
 * Made by Dev on 8/10/2016
 */
public class SinglePlayerClientNetworkProcessor extends NetworkProcessor{
    public NNAList<Liquid> liquids;
    private SinglePlayerServerNetworkProcessor server;
    public void bindClient(SinglePlayerServerNetworkProcessor server){
        this.server = server;
        this.liquids = new NNAList<Liquid>();
    }
    @Override
    protected void sendLiquids(NNAList<Liquid> liquids) {
        this.liquids.addAll(liquids);
    }
    @Override
    protected NNAList<Liquid> getLiquids(){
        NNAList<Liquid> l = this.server.liquids;
        this.server.liquids = new NNAList<Liquid>();
        return l;
    }
}
