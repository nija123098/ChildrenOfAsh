package um.nija123098.childrenofash.processers.clientsimulator;

import um.nija123098.childrenofash.entity.Entity;
import um.nija123098.childrenofash.entity.info.EntityInfo;
import um.nija123098.childrenofash.pipesystem.Liquid;
import um.nija123098.childrenofash.pipesystem.Process;
import um.nija123098.childrenofash.pipesystem.liquid.*;
import um.nija123098.childrenofash.util.NNAList;

import java.lang.annotation.Annotation;

/**
 * Made by Dev on 8/6/2016
 */
public class ClientSimulatorProcessor extends Process{
    public NNAList<Entity> entities;
    public ClientSimulatorProcessor() {
        this.entities = new NNAList<Entity>();
    }
    @Override
    public NNAList<Liquid> process(NNAList<Liquid> liquids, double deltaSyncTime){// TODO REDO THIS
        boolean[] hasMovementLiquid = new boolean[this.entities.size()];
        for (int i = 0; i < liquids.size(); i++) {
            Annotation[] a = liquids.get(i).getClass().getAnnotations();
            for (int j = 0; j < a.length; j++) {
                //if (a[j] instanceof MovementLiquid){
                //    hasMovementLiquid[i] = true;
                //    break;
                //}
            }
        }
        NNAList<Liquid> lqCollective = new NNAList<Liquid>(liquids);
        NNAList<Liquid> returnLiquids = new NNAList<Liquid>();
        for (Liquid l: lqCollective) {// beginning of process
            if (l instanceof NotifyAboutEntity){
                this.entities.add(((NotifyAboutEntity) l).entity);
            }else if (l instanceof NotifyEntityGone){
                EntityInfo ef = ((NotifyEntityGone) l).entityInfo;
                for (int j = 0; j < this.entities.size(); j++) {
                    if (this.entities.get(j).entityInfo.isSameEntity(ef)){
                        this.entities.remove(j);
                    }
                }
            }else if (l instanceof EntityModifierBaseLiquid){
                returnLiquids.addAll(((EntityModifierBaseLiquid) l).modify(this.entities, deltaSyncTime));
            }
        }
        for (Entity entity : this.entities){
            entity.physics.moveAsRegular(deltaSyncTime);
        }
        return returnLiquids;
    }
}
