package um.nija123098.childrenofash.platform;

import um.nija123098.childrenofash.pipesystem.Liquid;
import um.nija123098.childrenofash.pipesystem.Process;
import um.nija123098.childrenofash.pipesystem.liquid.PlatformShutDownLiquid;
import um.nija123098.childrenofash.processers.gameprocessor.GameProcessor;
import um.nija123098.childrenofash.processers.serverspecialprocess.ServerSpecialProcessor;
import um.nija123098.childrenofash.util.NNAList;

/**
 * Created by Jack on 7/20/2016.
 */
public class Server extends Platform{
    @Override
    public Process[] getProcesses() {
        return new Process[]{new GameProcessor(), new ServerSpecialProcessor()};
    }
    @Override
    public NNAList<Liquid> process(NNAList<Liquid> liquids, double deltaSyncTime) {
        for (Liquid l : liquids) {
            if (l instanceof PlatformShutDownLiquid) {
                this.run = false;
            }// CAN NOT MOVE THIS, NO ACCESS TO RUN IN PROCESS
        }
        return null;
    }
}
