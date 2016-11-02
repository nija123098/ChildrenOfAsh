package um.nija123098.childrenofash.processers.networkprocessor;

import um.nija123098.childrenofash.Reference;
import um.nija123098.childrenofash.pipesystem.Liquid;
import um.nija123098.childrenofash.util.NNAList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Made by Dev on 8/10/2016
 */
public class ClientSidedNetworkProcessor extends NetworkProcessor{
    protected Socket socket;
    protected ObjectOutputStream oup;
    protected ObjectInputStream imp;
    protected Thread thread;
    protected NNAList<Liquid> in, out, pec;
    protected boolean run;
    public ClientSidedNetworkProcessor(Socket socket){
        this();
        this.setupSocket(socket);
        this.run = true;
    }
    public ClientSidedNetworkProcessor(){
        this.thread = new Thread(){
            @Override
            public void run(){
                while (true){
                    if (run){
                        break;
                    }
                }
                while (true){
                    if (Reference.mustClose()){
                        break;
                    }
                    try{
                        Liquid l;
                        while ((l = (Liquid) imp.readObject()) != null){
                            if (isClientApproved(l)){
                                in.add(l);
                            }
                        }
                        for (int i = 0; i < out.size(); i++) {
                            oup.writeObject(out.get(i));
                            out.remove(i);
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    for (int i = 0; i < pec.size(); i++) {
                        if (isServerApproved(pec.get(i))){
                            in.add(pec.get(i));
                        }
                    }
                    pec = new NNAList<Liquid>();
                }
                try {
                    imp.close();
                    oup.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }
    protected void setupSocket(Socket socket){
        try{
            this.socket = socket;
            this.oup = new ObjectOutputStream(this.socket.getOutputStream());
            this.imp = new ObjectInputStream(this.socket.getInputStream());
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    protected boolean isServerApproved(Liquid liquid){
        return Reference.isServerApproved(liquid);
    }
    protected boolean isClientApproved(Liquid liquid){
        return Reference.isClientApproved(liquid);
    }
    @Override
    protected void sendLiquids(NNAList<Liquid> liquids) {
        this.pec.addAll(liquids);
    }
    @Override
    protected NNAList<Liquid> getLiquids(){
        return null;
    }
}
