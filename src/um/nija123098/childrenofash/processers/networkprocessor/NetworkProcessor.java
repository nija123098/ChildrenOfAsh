package um.nija123098.childrenofash.processers.networkprocessor;

import um.nija123098.childrenofash.pipesystem.Liquid;
import um.nija123098.childrenofash.pipesystem.Process;
import um.nija123098.childrenofash.pipesystem.liquid.PlatformShutDownLiquid;
import um.nija123098.childrenofash.util.NNAList;

/**
 * Made by Dev on 8/7/2016
 */
public abstract class NetworkProcessor extends Process{
    protected boolean down;
    @Override
    public NNAList<Liquid> process(NNAList<Liquid> liquids, double deltaSyncTime){
        this.sendLiquids(liquids);
        for (int i = 0; i < liquids.size(); i++) {
            if (liquids.get(i) instanceof PlatformShutDownLiquid){
                this.down = true;
            }
        }
        return this.getLiquids();
    }
    protected abstract void sendLiquids(NNAList<Liquid> liquids);
    protected abstract NNAList<Liquid> getLiquids();
}
