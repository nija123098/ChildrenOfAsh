package um.engi11.pipesystem.liquid;

import um.engi11.Reference;
import um.engi11.entity.Entity;
import um.engi11.entity.info.EntityInfo;
import um.engi11.pipesystem.Liquid;
import um.engi11.util.NNAList;

/**
 * Created by Jack on 7/24/2016.
 */
public class StandardMovementLiquid extends EntityModifierBaseLiquid implements MovementLiquid{
    private static final long serialVersionUID = Reference.serialVersionUID;
    @Deprecated protected StandardMovementLiquid(){}
    public StandardMovementLiquid(EntityInfo entityInfo) {
        super(entityInfo);
        this.crossPlatformInterest = false;
    }
    @Override
    protected NNAList<Liquid> modify(Entity entity, double deltaTime){
        entity.physics.moveAsRegular(deltaTime);
        return null;
    }
}
