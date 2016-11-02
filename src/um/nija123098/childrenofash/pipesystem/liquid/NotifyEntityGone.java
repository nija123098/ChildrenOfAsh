package um.nija123098.childrenofash.pipesystem.liquid;

import um.nija123098.childrenofash.entity.info.EntityInfo;
import um.nija123098.childrenofash.pipesystem.Liquid;

/**
 * Created by Jack on 7/21/2016.
 */
// @Liquid.Meta(behavior = "Sent by server")
public class NotifyEntityGone extends Liquid{
    public EntityInfo entityInfo;
    public NotifyEntityGone(EntityInfo entityInfo) {
        this.entityInfo = entityInfo;
    }
}
