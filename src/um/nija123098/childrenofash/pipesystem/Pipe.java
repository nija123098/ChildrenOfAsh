package um.nija123098.childrenofash.pipesystem;

import um.nija123098.childrenofash.Reference;
import um.nija123098.childrenofash.util.NNAList;

/**
 * Created by Jack on 7/18/2016.
 */
public final class Pipe extends Process{
    private double syncTime;
    private double deltaSyncTime;
    private NNAList<Liquid> liquids;
    public Pipe() {
        super();
        this.liquids = new NNAList<Liquid>();
    }
    public double getDeltaSyncTime(){
        return this.deltaSyncTime;
    }
    public double getSyncTime() {
        return this.syncTime;
    }
    public void poor(Liquid liquid){
        this.liquids.add(liquid);
    }
    public void poor(NNAList<Liquid> liquids){
        for (int i = 0; i < liquids.size(); i++) {
            this.poor(liquids.get(i));
        }
    }
    public NNAList<Liquid> getLiquids(){
        NNAList<Liquid> l = new NNAList<Liquid>();
        for (int i = 0; i < this.liquids.size(); i++) {
            if (this.liquids.get(i).getSyncTime() == null || this.liquids.get(i).getSyncTime() <= this.syncTime){
                l.add(this.liquids.get(i));
            }
        }
        return l;
    }
    /*public void drain(){
        this.liquids = new NNAList<Liquid>();
    }
    private void let(NNAList<Liquid> liquids){
        this.liquids.removeAll(liquids);
    }*/// both should co-exist
    private void let(){
        for (int i = 0; i < this.liquids.size(); i++) {
            if (this.liquids.get(i).getSyncTime() > this.syncTime){
                this.liquids.remove(i);
            }
        }
    }/*
    public NNAList<Liquid> transfer(){
        NNAList<Liquid> l = new NNAList<Liquid>();
        for (int i = 0; i < this.liquids.size(); i++) {
            if (this.liquids.get(i).crossPlatformInterest){
                l.add(this.liquids.get(i));
            }
        }
        return l;
    }
    public void poorInterPlatform(NNAList<Liquid> liquids){
        if (liquids != null){
            for (int i = 0; i < liquids.size(); i++) {
                this.liquids.get(i).crossPlatformInterest = false;
            }
            this.poor(liquids);
        }
    }*/
    @Override
    public NNAList<Liquid> process(NNAList<Liquid> liquids, double deltaSyncTime) {
        // network handling
        /*NNAList<Liquid> liq = new NNAList<Liquid>();
        for (int i = 0; i < this.liquids.size(); i++) {
            if (this.liquids.get(i).crossPlatformInterest){
                liq.add(this.liquids.get(i));
            }
        }
        this.networkPlug.sendLiquids(liq);
        this.poorInterPlatform(this.networkPlug.getLiquids());*/
        // inner pipe handling
        this.deltaSyncTime = 0;
        for (int i = 0; i < liquids.size(); i++) {
            if (liquids.get(i) instanceof PipeLiquid){
                this.deltaSyncTime += ((PipeLiquid) liquids.get(i)).syncTimeUpdate;
            }
        }
        this.syncTime += this.deltaSyncTime;
        return null;
    }
    public static class PipeLiquid extends Liquid{
        private static final long serialVersionUID = Reference.serialVersionUID;
        @Deprecated protected PipeLiquid(){}
        public double syncTimeUpdate;
        public PipeLiquid(double syncTimeUpdate) {
            this.syncTimeUpdate = syncTimeUpdate;
        }
    }
}
