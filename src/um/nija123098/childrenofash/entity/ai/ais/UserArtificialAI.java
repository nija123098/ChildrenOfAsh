package um.nija123098.childrenofash.entity.ai.ais;

import um.nija123098.childrenofash.entity.ai.AI;
import um.nija123098.childrenofash.pipesystem.Liquid;
import um.nija123098.childrenofash.pipesystem.liquid.UserRequestLiquid;
import um.nija123098.childrenofash.util.NNAList;

/**
 * Made by Dev on 8/24/2016
 */
public abstract class UserArtificialAI extends AI{
    @Override
    protected NNAList<Liquid> subProcess(NNAList<Liquid> liquids, double deltaSyncTime){
        NNAList<Liquid> liq = new NNAList<Liquid>();
        for (int i = 0; i < liquids.size(); i++) {
            if (liquids.get(i) instanceof UserRequestLiquid && liquids.get(i).getUserInfo().isSameEntity(this.entity.entityInfo)){
                liq.add(((UserRequestLiquid) liquids.get(i)).getModLiquid());
            }
        }
        return liq;
    }
}
