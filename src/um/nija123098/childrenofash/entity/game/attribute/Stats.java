package um.nija123098.childrenofash.entity.game.attribute;

/**
 * Made by Dev on 7/9/2016
 */
public class Stats {
    public BaseStat unity, brawn, judgement, resolve;
    public ExtendedStat stealth, speed, attack, dodge, accuracy, attune, burstEnergy, health, nonPhyResist, phyResist;
    public Stats(){
        this(1, 1, 1, 1);
    }
    public Stats(int unity, int brawn, int judgement, int resolve) {
        this.unity = new BaseStat(unity);
        this.brawn = new BaseStat(brawn);
        this.judgement = new BaseStat(judgement);
        this.resolve = new BaseStat(resolve);
        this.stealth = new ExtendedStat(this.unity, this.unity);
        this.speed = new ExtendedStat(this.brawn, this.unity);
        this.attack = new ExtendedStat(this.brawn, this.brawn);
        this.dodge = new ExtendedStat(this.judgement, this.unity);
        this.accuracy = new ExtendedStat(this.judgement, this.brawn);
        this.attune = new ExtendedStat(this.judgement, this.judgement);
        this.burstEnergy = new ExtendedStat(this.resolve, this.unity);
        this.health = new ExtendedStat(this.resolve, this.brawn);
        this.nonPhyResist = new ExtendedStat(this.resolve, this.judgement);
        this.phyResist = new ExtendedStat(this.resolve, this.resolve);
    }
    public void tick(){
        this.unity.tick();
        this.brawn.tick();
        this.judgement.tick();
        this.resolve.tick();
    }
    public Stat getStat(StatID statID){
        switch (statID){
            case UNITY:
                return this.unity;
            case BRAWN:
                return this.brawn;
            case JUDGEMENT:
                return this.judgement;
            case RESOLVE:
                return this.resolve;
            case STEALTH:
                return this.stealth;
            case SPEED:
                return this.speed;
            case ATTACK:
                return this.attack;
            case DODGE:
                return this.dodge;
            case ACCURACY:
                return this.accuracy;
            case ATTUNE:
                return this.attune;
            case BURST_ENERGY:
                return this.burstEnergy;
            case HEALTH:
                return this.health;
            case NON_PHY_RESIST:
                return this.nonPhyResist;
            case PHY_RESIST:
                return this.phyResist;
        }
        return null;
    }
    //@BalenceConstant
    static double TICK_DETRACT = .0002;
    //@BalenceConstant
    static double USE_ADD = 1;
    abstract class Stat{
        public double mult = 1, add = 0;
        public abstract void use();
        public abstract double getValue();
    }
    public class BaseStat extends Stat{
        private int max;
        private double value;
        private BaseStat(int max){
            this.max = max;
            this.value = max;
        }
        void tick(){
            this.value -= TICK_DETRACT;
        }
        public void use(){
            this.value += USE_ADD;
            if (this.value > this.max){
                this.value = this.max;
            }
        }
        public void upgrade(){
            ++this.max;
            ++this.value;
        }
        public double getValue(){
           return this.value * this.mult + this.add;
        }
        public int getMax(){
            return (int) (this.max * this.mult + this.add);
        }
    }
    public class ExtendedStat extends Stat{
        private BaseStat stat1, stat2;
        public ExtendedStat(BaseStat stat1, BaseStat stat2) {
            this.stat1 = stat1;
            this.stat2 = stat2;
        }
        @Override
        public void use() {
            this.stat1.use();
            this.stat2.use();
        }
        @Override
        public double getValue() {
            return Math.pow(this.stat1.getValue() * this.stat1.getValue() + this.stat2.getValue() * this.stat2.getValue(), .5) * this.mult + this.add;
        }
    }
}
