package um.nija123098.childrenofash.processers.gameprocessor.game;

import um.nija123098.childrenofash.Reference;
import um.nija123098.childrenofash.entity.Entity;
import um.nija123098.childrenofash.pipesystem.Liquid;
import um.nija123098.childrenofash.pipesystem.Processed;
import um.nija123098.childrenofash.pipesystem.liquid.LevelCompleteLiquid;
import um.nija123098.childrenofash.util.NNAList;

import java.io.Serializable;

/**
 * Created by Jack on 7/20/2016.
 */
public abstract class Game implements Processed, Serializable{
    private static final long serialVersionUID = Reference.serialVersionUID;
    @Deprecated protected Game(){}
    public NNAList<Entity> heros;
    public Level level;
    public NNAList<Liquid> process(NNAList<Liquid> liquids, double deltaSyncTime){
        NNAList<Liquid> l = this.level.process(liquids, deltaSyncTime);
        for (int i = 0; i < liquids.size(); i++){
            if (liquids.get(i) instanceof LevelCompleteLiquid){
                this.setNextLevel();
            }
        }
        return l;
    }
    public void setNextLevel(){
        this.level = this.getLevel(this.level.level + 1);
        this.level.entities.addAll(this.heros);
        for (int i = 0; i < this.level.entities.size(); i++) {
            this.level.entities.get(i).ai.latch(this);
        }
        this.level.setForLevel();
    }
    public void connectPlayer(boolean connecting, Entity player){
        if (connecting){
            this.heros.add(player);
            if (this.level != null){
                this.level.entities.add(player);
            }
        }else{
            this.heros.remove(player);
            if (this.level != null){
                this.level.entities.remove(player);
            }
        }
    }
    public abstract Level getLevel(int level);
}
