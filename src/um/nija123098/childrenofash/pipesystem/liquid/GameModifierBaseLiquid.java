package um.nija123098.childrenofash.pipesystem.liquid;

import um.nija123098.childrenofash.Reference;
import um.nija123098.childrenofash.pipesystem.Liquid;
import um.nija123098.childrenofash.processers.gameprocessor.game.Game;
import um.nija123098.childrenofash.util.NNAList;

/**
 * Created by Jack on 7/24/2016.
 */
public abstract class GameModifierBaseLiquid extends Liquid{
    private static final long serialVersionUID = Reference.serialVersionUID;
    // @Deprecated protected GameModifierBaseLiquid(){}
    public GameModifierBaseLiquid() {
    }
    public abstract NNAList<Liquid> process(Game game, double deltaTime);
}
