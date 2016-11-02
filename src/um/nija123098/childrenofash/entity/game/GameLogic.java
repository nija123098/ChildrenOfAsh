package um.nija123098.childrenofash.entity.game;

import um.nija123098.childrenofash.Reference;
import um.nija123098.childrenofash.entity.Entity;
import um.nija123098.childrenofash.entity.game.attribute.Stats;
import um.nija123098.childrenofash.pipesystem.Liquid;
import um.nija123098.childrenofash.pipesystem.Processed;
import um.nija123098.childrenofash.util.NNAList;
import um.nija123098.childrenofash.util.shape.Location3F;

import java.io.Serializable;

/**
 * Created by Jack on 7/20/2016.
 */
public class GameLogic implements Processed, Serializable{
    private static final long serialVersionUID = Reference.serialVersionUID;
    @Deprecated protected GameLogic(){}
    public Stats stats;
    public Location3F location3F;
    public NNAList<Entity> items;
    public NNAList<Entity> equipped;
    @Override
    public NNAList<Liquid> process(NNAList<Liquid> liquids, double deltaSyncTime){
        return null;// todo
    }
    public boolean canSee(Entity entity){
        return this.location3F.canSee(entity.physics.getShape());
    }
    public boolean canEquip(Entity entity){
        throw new RuntimeException();
    }
}
