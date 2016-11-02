package um.nija123098.childrenofash.platform;

import um.nija123098.childrenofash.entity.info.UserInfo;
import um.nija123098.childrenofash.pipesystem.Liquid;
import um.nija123098.childrenofash.pipesystem.Pipe;
import um.nija123098.childrenofash.pipesystem.Process;
import um.nija123098.childrenofash.util.NNAList;

/**
 * Created by Jack on 7/20/2016.
 */
public abstract class Platform extends Process{
    private NNAList<Process> processes = new NNAList<Process>();
    protected boolean run;
    public Platform() {
        this.addProcess(new Pipe());
    }
    public void run(UserInfo userInfo){
        System.out.println(this.getClass().getName() + " Started");
        this.run = true;
        this.addProcess(this);
        for (Process process : this.getProcesses()){
            this.addProcess(process);
        }
        for (Process process : this.processes) {
            process.start();
        }
        NNAList<Liquid> liquids;
        while (true){// core logic
            liquids = new NNAList<Liquid>();
            for (int i = 0; i < this.processes.size(); i++) {
                liquids.addAll(this.processes.get(i).process());
            }// this.process(((Pipe) this.processes.get(0)).getLiquids(), 0)
            for (int i = 0; i < liquids.size(); i++) {
                liquids.get(i).sync(userInfo, ((Pipe) this.processes.get(0)).getSyncTime());
            }
            ((Pipe) this.processes.get(0)).poor(liquids);
            if (!this.run){
                break;
            }
        }
        for (Process process : this.processes) {
            process.stop();
        }
        System.out.println(this.getClass().getName() + " Closed");
    }
    public void addProcess(Process process){
        if (this.processes.size() == 0){
            process.addPipe((Pipe) process);
        }else{
            process.addPipe((Pipe) this.processes.get(0));
        }
        this.processes.add(process);
    }
    public abstract Process[] getProcesses();
}
