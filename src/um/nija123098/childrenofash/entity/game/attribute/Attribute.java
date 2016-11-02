package um.nija123098.childrenofash.entity.game.attribute;

import um.nija123098.childrenofash.entity.Entity;

/**
 * Made by Dev on 7/11/2016
 */
public class Attribute {// todo clean before implementing
    public enum Sex{// on the other hand they could just be genderless
        MALE(StatID.BRAWN), FEMALE(StatID.UNITY), NONE(null),;
        Sex(StatID trait){
            this.trait = new Trait(.25, .5, trait);
        }
        Trait trait;
    }
    public enum PhysicalBuild{
        ENDOMORPH(StatID.RESOLVE), MESOMORPH(StatID.BRAWN), ECTOMORPH(StatID.UNITY), NONE(null),;
        PhysicalBuild(StatID stat){
            this.trait = new Trait(.2, 0, stat);
        }
        Trait trait;
    }
    public enum Occupation{
        ORGINIZER(null, StatID.ATTUNE), SCAVENGER(null, StatID.PHY_RESIST), WARIOR(null, StatID.ATTACK), SCOUT(null, StatID.STEALTH), NONE(null, null);
        Occupation(Entity item, StatID stat){
            this.item = item;
            this.trait = new Trait(0, .5, stat);
        }
        public Trait trait;
        public Entity item;
        public Entity getItem(){
            return null;// TODO WORK ON THIS
        }
    }
    public static class Trait{// making this static fixes it?
        public double mult, add;
        public StatID stat;
        public Trait(double mult, double add, StatID stat) {
            this.mult = mult;
            this.add = add;
            this.stat = stat;
        }
        public void applyTrait(Entity object){
            if (this.stat != null){
                object.gameLogic.stats.getStat(this.stat).add += this.add;
                object.gameLogic.stats.getStat(this.stat).mult += this.mult;
            }
        }
    }
}
