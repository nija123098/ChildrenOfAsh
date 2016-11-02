package singleplayer;

import um.engi11.entity.info.UserInfo;
import um.engi11.platform.Client;
import um.engi11.platform.Platform;
import um.engi11.platform.Server;

/**
 * Made by Dev on 7/29/2016
 */
public class SinglePlayerMain {
    public static void main(String[] args) {
        Platform server = new Server();
        SingleServerPlug serverPlug = new SingleServerPlug();
        Thread s = new Thread(){
            @Override
            public void run(){
                server.run(new UserInfo("SER.VER", new char[]{'O', 'S'}, -0));
            }
        };
        Platform client = new Client();
        SingleClientPlug clientPlug = new SingleClientPlug(serverPlug);
        serverPlug.bindToPlug(clientPlug);
        Thread c = new Thread(){
            @Override
            public void run(){
                client.run(new UserInfo("USER", new char[]{'e', 'p'}, -50));
            }
        };
        s.start();
        c.start();
    }
}
