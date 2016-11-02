package um.nija123098.childrenofash.processers.networkprocessor;

import um.nija123098.childrenofash.pipesystem.Liquid;
import um.nija123098.childrenofash.util.NNAList;

/**
 * Made by Dev on 8/10/2016
 */
public class SinglePlayerServerNetworkProcessor extends NetworkProcessor{
    public NNAList<Liquid> liquids;
    private SinglePlayerClientNetworkProcessor client;
    public void bindClient(SinglePlayerClientNetworkProcessor client){
        this.client = client;
        this.liquids = new NNAList<Liquid>();
    }
    @Override
    protected void sendLiquids(NNAList<Liquid> liquids) {
        this.liquids.addAll(liquids);
    }
    @Override
    protected NNAList<Liquid> getLiquids() {
        NNAList<Liquid> l = this.client.liquids;
        this.client.liquids = new NNAList<Liquid>();
        return l;
    }
}
