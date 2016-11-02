package um.nija123098.childrenofash.platform;

import um.nija123098.childrenofash.pipesystem.Liquid;
import um.nija123098.childrenofash.pipesystem.Process;
import um.nija123098.childrenofash.processers.clientioprocessor.ClientIOProcessor;
import um.nija123098.childrenofash.processers.clientsimulator.ClientSimulatorProcessor;
import um.nija123098.childrenofash.util.NNAList;

/**
 * Created by Jack on 7/20/2016.
 */
public class Client extends Platform{
    @Override
    public Process[] getProcesses() {
        ClientSimulatorProcessor pi = new ClientSimulatorProcessor();
        return new Process[]{pi, new ClientIOProcessor().attachSim(pi)};
    }
    @Override
    public NNAList<Liquid> process(NNAList<Liquid> liquids, double deltaSyncTime) {
        return null;
    }
}
