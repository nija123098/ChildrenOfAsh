package um.nija123098.childrenofash.runconfiguration;

import um.nija123098.childrenofash.entity.info.UserInfo;
import um.nija123098.childrenofash.platform.Server;

/**
 * Made by Dev on 8/10/2016
 */
public class DedicatedServerRunConfiguration extends RunConfiguration{
    public Server server;
    public Thread t;
    @Override
    public void start(UserInfo userInfo) {
        this.server = new Server();
        this.t = new Thread(){
            @Override
            public void run(){
                server.run(userInfo);
            }
        };
        this.t.start();
    }
}
