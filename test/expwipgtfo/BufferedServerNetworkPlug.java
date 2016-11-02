package expwipgtfo;

import um.engi11.networkplug.NetworkPlug;
import um.engi11.pipesystem.Liquid;
import um.engi11.util.NNAList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Made by Dev on 8/7/2016
 */
public class BufferedServerNetworkPlug implements NetworkPlug, Runnable{
    private Thread thread;
    private ServerSocket serverSocket;
    private NNAList<Client> clients;
    public BufferedServerNetworkPlug(){
        this.thread.start();
    }
    @Override
    public void sendLiquids(NNAList<Liquid> liquids) {

    }
    @Override
    public NNAList<Liquid> getLiquids(){
        return null;
    }
    @Override
    public void run() {
        while (true){
            // serverSocket.accept();
        }
    }
    class Client implements Runnable{
        private NNAList<Liquid> outL, impL;
        private ObjectOutputStream oup;
        private ObjectInputStream imp;
        private Socket socket;
        private boolean foulty;
        public Client(Socket socket) {
            this.socket = socket;
            try {
                this.imp = new ObjectInputStream(this.socket.getInputStream());
                this.oup = new ObjectOutputStream(this.socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void run() {

        }
    }
    // these two methods are responsible for removeing any liquids that the server or client should not have, or that would be dangerous
    private static Liquid cleanOut(Liquid liquid){
        return null;
    }
    private static Liquid cleanIn(Liquid liquid){
        return null;
    }
}
