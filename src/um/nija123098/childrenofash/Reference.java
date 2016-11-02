package um.nija123098.childrenofash;

import um.nija123098.childrenofash.entity.render.RenderData;
import um.nija123098.childrenofash.pipesystem.Liquid;

/**
 * Created by Jack on 7/20/2016.
 */
public class Reference {
    public static final long serialVersionUID = -6849794470754667710L;
    // private static final long serialVersionUID = Reference.serialVersionUID;
    // @Deprecated protected Object(){}
    public static RenderData getRenderData(String entityTypeName) {
        return null;
    }
    public static boolean isClientApproved(Liquid liquid){
        return true;
    }
    public static boolean isServerApproved(Liquid liquid){
        return true;
    }
    public static final int PORT = 9001;
    public static final String HOST = "";
    public static boolean mustClose(){
        return close;
    }
    public static void close(){
        close = true;
    }
    public static boolean close = false;
}
