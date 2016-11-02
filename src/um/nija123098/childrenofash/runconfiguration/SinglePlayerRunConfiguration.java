package um.nija123098.childrenofash.runconfiguration;

import um.nija123098.childrenofash.entity.info.UserInfo;
import um.nija123098.childrenofash.platform.Client;
import um.nija123098.childrenofash.platform.Server;
import um.nija123098.childrenofash.processers.networkprocessor.SinglePlayerClientNetworkProcessor;
import um.nija123098.childrenofash.processers.networkprocessor.SinglePlayerServerNetworkProcessor;

/**
 * Made by Dev on 8/10/2016
 */
public class SinglePlayerRunConfiguration extends RunConfiguration{
    public Server server;
    public Client client;
    public Thread s, c;
    public SinglePlayerRunConfiguration(){
        this.server = new Server();
        this.client = new Client();
        SinglePlayerClientNetworkProcessor cn = new SinglePlayerClientNetworkProcessor();
        SinglePlayerServerNetworkProcessor sn = new SinglePlayerServerNetworkProcessor();
        cn.bindClient(sn);
        sn.bindClient(cn);
        this.server.addProcess(sn);
        this.client.addProcess(cn);
    }
    @Override
    public void start(UserInfo userInfo) {
        this.s = new Thread(){
            @Override
            public void run(){
                server.run(SERVER_ARTIFICIAL_USER_INFO);
            }
        };
        this.c = new Thread(){
            @Override
            public void run(){
                client.run(userInfo);
            }
        };
        this.s.start();
        this.c.start();
    }
    private static final UserInfo SERVER_ARTIFICIAL_USER_INFO = new UserInfo("SERVE YOUR RUMBA OVERLORDS", "SEND ME YOUR KIDNEYS", "~SERVER");
}
