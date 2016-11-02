package um.nija123098.childrenofash.processers.gameprocessor.game;

import um.nija123098.childrenofash.entity.Entity;
import um.nija123098.childrenofash.pipesystem.Liquid;
import um.nija123098.childrenofash.pipesystem.Processed;
import um.nija123098.childrenofash.pipesystem.liquid.LevelCompleteLiquid;
import um.nija123098.childrenofash.util.NNAList;

/**
 * Created by Jack on 7/20/2016.
 */
public abstract class Level implements Processed{
    public NNAList<Entity> entities;
    public int level;
    public void setForLevel(){
        for (int i = 0; i < this.entities.size(); i++) {
            this.entities.get(i).prepForLevel(this);
        }
    }
    @Override
    public NNAList<Liquid> process(NNAList<Liquid> liquids, double deltaSyncTime) {
        NNAList<Liquid> l = new NNAList<Liquid>();
        /*for (Liquid liq : liquids) {
            if (liq instanceof EntityModifierBaseLiquid) {
                ((EntityModifierBaseLiquid) liq).modify(this.entities, deltaSyncTime);
            }
        }*/
        for (int i = 0; i < this.entities.size(); i++){// todo add internal liquids like entity making liquids
            l.addAll(this.entities.get(i).process(liquids, deltaSyncTime));
            if (this.entities.get(i).removeMe()){
                this.entities.remove(i);
            }
        }
        if (this.levelCompleted()){
            l.add(this.getLevelCompleteLiquid());
        }
        return l;// todo
    }
    public LevelCompleteLiquid getLevelCompleteLiquid(){
        return new LevelCompleteLiquid();
    }
    protected abstract boolean levelCompleted();
}
