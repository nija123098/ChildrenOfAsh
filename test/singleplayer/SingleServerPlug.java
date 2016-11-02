package singleplayer;

import um.engi11.pipesystem.Liquid;
import um.engi11.networkplug.NetworkPlug;
import um.engi11.util.NNAList;

/**
 * Made by Dev on 7/29/2016
 */
public class SingleServerPlug implements NetworkPlug {
    public NNAList<Liquid> liquids;
    private SingleClientPlug singleClientPlug;
    public void bindToPlug(SingleClientPlug singleClientPlug){
        this.singleClientPlug = singleClientPlug;
        this.liquids = new NNAList<Liquid>();
    }
    @Override
    public void sendLiquids(NNAList<Liquid> liquids) {
        this.singleClientPlug.liquids.addAll(liquids);
    }
    @Override
    public NNAList<Liquid> getLiquids() {
        NNAList<Liquid> liquids = this.liquids;
        this.liquids = new NNAList<Liquid>();
        return liquids;
    }
}
