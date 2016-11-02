package um.nija123098.childrenofash.processers.gameprocessor;

import um.nija123098.childrenofash.entity.Entity;
import um.nija123098.childrenofash.pipesystem.Liquid;
import um.nija123098.childrenofash.pipesystem.Process;
import um.nija123098.childrenofash.pipesystem.liquid.EntityModifierBaseLiquid;
import um.nija123098.childrenofash.pipesystem.liquid.GameModifierBaseLiquid;
import um.nija123098.childrenofash.pipesystem.liquid.NotifyAboutEntity;
import um.nija123098.childrenofash.pipesystem.liquid.NotifyEntityGone;
import um.nija123098.childrenofash.processers.gameprocessor.game.Game;
import um.nija123098.childrenofash.util.NNAList;

/**
 * Created by Jack on 7/19/2016.
 */
public class GameProcessor extends Process {// should not be used in client
    private Game game;
    private NNAList<Entity> visables;
    public void attachGame(Game game){
        this.game = game;
        this.visables = new NNAList<Entity>();
    }
    @Override
    public NNAList<Liquid> process(NNAList<Liquid> liquids, double deltaSyncTime) {
        if (this.game != null){/*
            boolean[] hasMovementLiquid = new boolean[this.game.level.entities.size()];
            for (int i = 0; i < liquids.size(); i++) {
                Annotation[] a = liquids.get(i).getClass().getAnnotations();
                for (int j = 0; j < a.length; j++) {
                    if (a[j] instanceof MovementLiquid){
                        hasMovementLiquid[i] = true;
                        break;
                    }
                }
            }
            for (int i = 0; i < this.game.level.entities.size(); i++) {
                for (int j = 0; j < this.game.level.entities.size(); j++) {
                    if (i != j){
                        ret.addAll(Physics.collide(this.game.level.entities.get(i), this.game.level.entities.get(j), deltaSyncTime));
                    }// will continue to send all collisions regardless if client is aware of either object
                }// both for fluid efficiency and for anti-cheat
            }*/
            NNAList<Liquid> ret = new NNAList<Liquid>(liquids);
            for (int i = 0; i < this.game.level.entities.size(); i++) {
                for (int j = 0; j < this.game.heros.size(); j++) {
                    if (this.game.heros.get(j).gameLogic.canSee(this.game.level.entities.get(i)) && !this.visables.contains(this.game.level.entities.get(i))){
                        this.visables.add(this.game.level.entities.get(i));
                        ret.add(new NotifyAboutEntity(this.game.level.entities.get(i)));
                        this.game.level.entities.get(i).visible = true;
                    }else if (this.visables.contains(this.game.level.entities.get(i))){
                        this.visables.add(this.game.level.entities.get(i));
                        ret.add(new NotifyEntityGone(this.game.level.entities.get(i).entityInfo));
                        this.game.level.entities.get(i).visible = false;
                    }
                }
            }
            for (Liquid l : liquids) {
                if (l instanceof EntityModifierBaseLiquid){
                    ((EntityModifierBaseLiquid) l).modify(this.game.level.entities, deltaSyncTime);
                }else if (l instanceof GameModifierBaseLiquid){// probably want to just modify the game processor object itself
                    ((GameModifierBaseLiquid) l).process(this.game, deltaSyncTime);// granted this may make it more cheat
                }// game modifier perhaps too powerful, perhaps make a game variable class, though you could make use of final
            }// todo figure this out
            ret.addAll(this.game.process(liquids, deltaSyncTime));// though should be careful of pre-mature optimization
            for (Entity entity : this.game.level.entities){
                ret.addAll(entity.process(liquids, deltaSyncTime));
            }
            for (int i = 0; i < this.game.heros.size(); i++) {
                ret.addAll(this.game.heros.get(i).process(liquids, deltaSyncTime));
            }
            return ret;
        }
        return null;
    }
}
