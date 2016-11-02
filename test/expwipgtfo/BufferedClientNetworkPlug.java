package expwipgtfo;

import um.engi11.Reference;
import um.engi11.networkplug.NetworkPlug;
import um.engi11.pipesystem.Liquid;
import um.engi11.util.NNAList;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Made by Dev on 8/7/2016
 */
public class BufferedClientNetworkPlug implements NetworkPlug {
    private Socket s;
    private ObjectOutputStream imp;
    private ObjectOutputStream oup;
    public BufferedClientNetworkPlug() {
        try {
            this.s = new Socket(Reference.HOST, Reference.PORT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Thread thread = new Thread(){
        @Override
        public void run(){

        }
    };
    @Override
    public void sendLiquids(NNAList<Liquid> liquids) {
        // BufferedWriter out = new BufferedWriter(serverSicket.accept());
        BufferedOutputStream o;
        // ObjectOutputStream s = new ObjectOutputStream(serverSicket.accept().getOutputStream());
        // s.writeObject();
    }

    @Override
    public NNAList<Liquid> getLiquids() {
        return null;
    }
}
