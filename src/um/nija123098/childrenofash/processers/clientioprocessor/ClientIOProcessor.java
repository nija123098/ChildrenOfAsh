package um.nija123098.childrenofash.processers.clientioprocessor;

import um.nija123098.childrenofash.pipesystem.Liquid;
import um.nija123098.childrenofash.pipesystem.Process;
import um.nija123098.childrenofash.processers.clientsimulator.ClientSimulatorProcessor;
import um.nija123098.childrenofash.util.NNAList;

/**
 * Created by Jack on 7/20/2016.
 */
public class ClientIOProcessor extends Process{
    private ClientSimulatorProcessor clientSimulatorProcessor;
    public ClientIOProcessor(){
    }
    public ClientIOProcessor attachSim(ClientSimulatorProcessor clientSimulatorProcessor){
        this.clientSimulatorProcessor = clientSimulatorProcessor;
        return this;
    }
    @Override
    public void start(){
    }
    @Override
    public NNAList<Liquid> process(NNAList<Liquid> liquids, double deltaSyncTime){
        return null;
    }
    @Override
    public void stop(){
    }
}
