package um.nija123098.childrenofash.entity.game.attribute;

import um.nija123098.childrenofash.entity.Entity;

/**
 * Made by Dev on 8/5/2016
 */
public abstract class Trait {
    private Entity entity;
    public void apply(Entity entity){
        this.bind(entity);
        this.applyTrait();
    }
    private void bind(Entity entity){
        this.entity = entity;
    }
    protected abstract void applyTrait();
    public abstract void unapplyTrait();
}
