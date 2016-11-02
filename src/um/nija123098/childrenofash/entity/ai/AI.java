package um.nija123098.childrenofash.entity.ai;

import um.nija123098.childrenofash.Reference;
import um.nija123098.childrenofash.entity.Entity;
import um.nija123098.childrenofash.pipesystem.Liquid;
import um.nija123098.childrenofash.pipesystem.Processed;
import um.nija123098.childrenofash.processers.gameprocessor.game.Game;
import um.nija123098.childrenofash.util.NNAList;

import java.io.Serializable;

/**
 * AIs are fully responsible for their own entity's
 * simulation through its AI simulation counter-part.
 * Standard non-specific AI components will not at
 * all assist in simulation.
 *
 * Made by Dev on 8/6/2016
 */
public abstract class AI implements Processed, Serializable{// abstracts probably don't have to be declared Serializable
    private static final long serialVersionUID = Reference.serialVersionUID;
    @Deprecated protected AI(){}
    public transient NNAList<Entity> canSee;
    public transient Entity entity;
    protected transient Game game;
    public void latch(Game game){// perhaps condense latching level and game, granted latching game (for now) is platform dependent
        this.game = game;
    }
    public void latch(Entity entity){
        this.entity = entity;
    }
    @Override
    public NNAList<Liquid> process(NNAList<Liquid> liquids, double deltaSyncTime){
        if (this.game != null){
            this.upkeepView();
        }
        return this.subProcess(liquids, deltaSyncTime);
    }
    private void upkeepView(){
        Entity e;
        for (int i = 0; i < this.game.level.entities.size(); i++) {
            if (this.entity.gameLogic.canSee(e = this.game.level.entities.get(i))){
                if (!this.canSee.contains(e)){
                    this.canSee.add(e);
                }
            }else{
                if (this.canSee.contains(e)){
                    this.canSee.remove(e);
                }
            }
        }
    }
    protected abstract NNAList<Liquid> subProcess(NNAList<Liquid> liquids, double deltaSyncTime);
}
