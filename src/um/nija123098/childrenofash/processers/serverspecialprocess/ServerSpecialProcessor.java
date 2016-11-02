package um.nija123098.childrenofash.processers.serverspecialprocess;

import um.nija123098.childrenofash.entity.info.UserInfo;
import um.nija123098.childrenofash.pipesystem.Liquid;
import um.nija123098.childrenofash.pipesystem.Pipe;
import um.nija123098.childrenofash.pipesystem.Process;
import um.nija123098.childrenofash.pipesystem.liquid.PlatformShutDownLiquid;
import um.nija123098.childrenofash.pipesystem.liquid.ServerConnectionLiquid;
import um.nija123098.childrenofash.util.NNAList;

/**
 * Created by Jack on 7/20/2016.
 */
public class ServerSpecialProcessor extends Process{
    private NNAList<UserInfo> userInfos;
    private double lastTime;
    private boolean justStarted;
    public ServerSpecialProcessor(){
        this.userInfos = new NNAList<UserInfo>();
        this.justStarted = true;
    }
    @Override
    public NNAList<Liquid> process(NNAList<Liquid> liquids, double deltaSyncTime) {
        NNAList<Liquid> liqu = new NNAList<Liquid>(2);
        double currentTime = System.currentTimeMillis();
        liqu.add(new Pipe.PipeLiquid(this.lastTime - currentTime));
        this.lastTime = currentTime;
        //
        for (Liquid l : liquids) {// todo use a better method
            if (l instanceof ServerConnectionLiquid){
                if (((ServerConnectionLiquid)l).connecting){
                    this.userInfos.add(l.getUserInfo());
                }else{
                    this.userInfos.remove(l.getUserInfo());
                }
            }
        }
        //
        if (!this.justStarted && this.userInfos.size() == 0){
            liqu.add(new PlatformShutDownLiquid());
        }
        if (this.userInfos.size() != 0){
            this.justStarted = false;
        }
        //
        return liqu;
    }
}
