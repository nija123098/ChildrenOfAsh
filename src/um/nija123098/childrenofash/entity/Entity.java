package um.nija123098.childrenofash.entity;

import um.nija123098.childrenofash.Reference;
import um.nija123098.childrenofash.entity.ai.AI;
import um.nija123098.childrenofash.entity.game.GameLogic;
import um.nija123098.childrenofash.entity.info.EntityInfo;
import um.nija123098.childrenofash.entity.physics.Physics;
import um.nija123098.childrenofash.entity.render.RenderData;
import um.nija123098.childrenofash.pipesystem.Liquid;
import um.nija123098.childrenofash.pipesystem.Processed;
import um.nija123098.childrenofash.processers.gameprocessor.game.Level;
import um.nija123098.childrenofash.util.NNAList;

import java.io.Serializable;

/**
 * Created by Jack on 7/19/2016.
 */
public class Entity implements Processed, Serializable{
    private static final long serialVersionUID = Reference.serialVersionUID;
    @Deprecated protected Entity(){
        this.physics.latch(this);
        this.ai.latch(this);
    }
    public transient boolean visible;
    public transient Level level;
    public transient AI ai;
    public EntityInfo entityInfo;
    public RenderData renderData;
    public GameLogic gameLogic;
    public Physics physics;
    public Entity(EntityInfo entityInfo, RenderData renderData, GameLogic gameLogic, Physics physics, AI ai){
        this.entityInfo = entityInfo;
        this.renderData = renderData;
        this.gameLogic = gameLogic;
        this.physics = physics;
        this.ai = ai;
        this.physics.latch(this);
        this.ai.latch(this);
    }
    public void prepForLevel(Level level){
        this.latch(level);
    }
    public void latch(Level level){
        this.level = level;
    }
    // WARNING NON-PHYSICS ONLY
    public NNAList<Liquid> process(NNAList<Liquid> liquids, double deltaSyncTime){
        if (!this.visible){
            return null;
        }
        NNAList<Liquid> l = new NNAList<Liquid>();
        l.addAll(this.gameLogic.process(liquids, deltaSyncTime));
        l.addAll(this.ai.process(liquids, deltaSyncTime));
        l.addAll(this.physics.process(liquids, deltaSyncTime));
        return l;
    }
    public boolean removeMe(){
        return false;
    }
}
