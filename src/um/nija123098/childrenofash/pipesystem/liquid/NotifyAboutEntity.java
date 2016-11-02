package um.nija123098.childrenofash.pipesystem.liquid;

import um.nija123098.childrenofash.Reference;
import um.nija123098.childrenofash.entity.Entity;
import um.nija123098.childrenofash.pipesystem.Liquid;

/**
 * Created by Jack on 7/21/2016.
 */
public class NotifyAboutEntity extends Liquid{
    private static final long serialVersionUID = Reference.serialVersionUID;
    @Deprecated protected NotifyAboutEntity(){}// TODO WORKING ON
    public Entity entity;
    public NotifyAboutEntity(Entity entity) {
        this.entity = entity;
        this.entity.ai = null;
    }
}
