package um.nija123098.childrenofash.runconfiguration;

import um.nija123098.childrenofash.entity.info.UserInfo;
import um.nija123098.childrenofash.platform.Client;

/**
 * Made by Dev on 8/10/2016
 */
public class DedicatedClientRunConfiguration extends RunConfiguration{
    public Client client;
    public Thread t;
    @Override
    public void start(UserInfo userInfo) {
        this.client = new Client();
        this.t = new Thread(){
            @Override
            public void run(){
                client.run(userInfo);
            }
        };
        this.t.start();
    }
}
