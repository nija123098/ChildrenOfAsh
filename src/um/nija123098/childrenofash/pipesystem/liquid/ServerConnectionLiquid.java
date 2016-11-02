package um.nija123098.childrenofash.pipesystem.liquid;

import um.nija123098.childrenofash.Reference;
import um.nija123098.childrenofash.pipesystem.Liquid;

/**
 * Created by Jack on 7/20/2016.
 */
public class ServerConnectionLiquid extends Liquid{
    private static final long serialVersionUID = Reference.serialVersionUID;
    @Deprecated protected ServerConnectionLiquid(){}
    public boolean connecting;
    public ServerConnectionLiquid(boolean connecting) {
        this.connecting = connecting;
    }
}
