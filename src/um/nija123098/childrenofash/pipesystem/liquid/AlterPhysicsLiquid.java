package um.nija123098.childrenofash.pipesystem.liquid;

import org.lwjgl.util.vector.Vector3f;
import um.nija123098.childrenofash.Reference;
import um.nija123098.childrenofash.entity.Entity;
import um.nija123098.childrenofash.entity.info.EntityInfo;
import um.nija123098.childrenofash.pipesystem.Liquid;
import um.nija123098.childrenofash.util.NNAList;

/**
 * Created by Jack on 7/24/2016.
 */
public class AlterPhysicsLiquid extends EntityModifierBaseLiquid{
    private static final long serialVersionUID = Reference.serialVersionUID;
    @Deprecated protected AlterPhysicsLiquid(){}
    public Vector3f pos, rot, vel, angVel;
    public AlterPhysicsLiquid(EntityInfo entityInfo, Vector3f pos, Vector3f rot, Vector3f vel, Vector3f angVel){
        super(entityInfo);
        this.pos = pos;
        this.rot = rot;
        this.vel = vel;
        this.angVel = angVel;
    }
    public AlterPhysicsLiquid(EntityInfo entityInfo, Vector3f vel, Vector3f angVel){
        this(entityInfo, null, null, vel, angVel);
    }
    @Override
    protected NNAList<Liquid> modify(Entity entity, double deltaTime){
        if (this.pos != null){
            entity.physics.setPosition(this.pos);
        }
        if (this.rot != null){
            entity.physics.setRotation(this.rot);
        }
        if (this.vel != null){
            entity.physics.setVelocity(this.vel);
        }
        if (this.angVel != null){
            entity.physics.setAngularVelocity(this.angVel);
        }
        return null;
    }
}
