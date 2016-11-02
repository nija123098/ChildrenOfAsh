package expwipgtfo;

import um.engi11.pipesystem.Liquid;

/**
 * Created by Jack on 7/23/2016.
 */
public class SpeedReductionLiquid extends Liquid{// unimplemented until most other things are implemented
    public double reduction;
    public SpeedReductionLiquid(double reduction){
        super(true);
        this.reduction = reduction;
    }
}
