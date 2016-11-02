package um.nija123098.childrenofash.processers.networkprocessor;

import um.nija123098.childrenofash.Reference;
import um.nija123098.childrenofash.pipesystem.Liquid;
import um.nija123098.childrenofash.util.NNAList;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Made by Dev on 8/10/2016
 */
public class ServerSidedNetworkProcessor extends NetworkProcessor{
    private ServerSocket serverSocket;
    private NNAList<Client> clients;
    private NNAList<Liquid> in, out;// TODO atomize all asynchronous lists
    private Thread thread;
    public ServerSidedNetworkProcessor(){
        this.clients = new NNAList<Client>(2);
        this.down = false;
        try{
            this.serverSocket = new ServerSocket(Reference.PORT);
        }catch(IOException e){
            e.printStackTrace();
        }
        this.thread = new Thread(){
            @Override
            public void run(){
                while (true){
                    if (down || Reference.mustClose()){
                        break;
                    }
                    Socket s = null;
                    try {
                        s = serverSocket.accept();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (s != null){
                        clients.add(new Client(s));
                    }
                    for (int c = 0; c < clients.size(); c++) {
                        clients.get(c).sendLiquids(in);
                        out.addAll(clients.get(c).getLiquids());
                    }
                    in = new NNAList<Liquid>();
                }
            }
        };
    }
    @Override
    protected void sendLiquids(NNAList<Liquid> liquids){
        this.in.addAll(liquids);
    }
    @Override
    protected NNAList<Liquid> getLiquids(){
        NNAList<Liquid> l = this.out;
        this.out = new NNAList<Liquid>();
        return l;
    }
    private class Client extends ClientSidedNetworkProcessor{
        public Client(Socket socket){
            this.setupSocket(socket);
        }
        @Override
        protected boolean isServerApproved(Liquid liquid){
            return super.isClientApproved(liquid);
        }// required due to flip for reuse
        @Override
        protected boolean isClientApproved(Liquid liquid){
            return super.isServerApproved(liquid);
        }
    }
}
