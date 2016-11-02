package um.nija123098.childrenofash.pipesystem.liquid;

import um.nija123098.childrenofash.entity.Entity;
import um.nija123098.childrenofash.entity.info.EntityInfo;
import um.nija123098.childrenofash.pipesystem.Liquid;
import um.nija123098.childrenofash.util.NNAList;

/**
 * Created by Jack on 7/21/2016.
 */
public abstract class EntityModifierBaseLiquid extends Liquid{
    @Deprecated protected EntityModifierBaseLiquid(){}
    private EntityInfo entityInfo;
    public EntityModifierBaseLiquid(EntityInfo entityInfo){
        this.entityInfo = entityInfo;
    }
    public final NNAList<Liquid> modify(NNAList<Entity> entities, double deltaTime){
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).entityInfo.isSameEntity(this.getEntityInfo())){
                return this.modify(entities.get(i), deltaTime);
            }
        }
        return null;
    }
    private EntityInfo getEntityInfo(){
        return this.entityInfo;
    }
    protected abstract NNAList<Liquid> modify(Entity entity, double deltaTime);
}
