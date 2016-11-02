package um.nija123098.childrenofash.entity.info;

import um.nija123098.childrenofash.Reference;

/**
 * Created by Jack on 7/18/2016.
 */
public class UserInfo extends EntityInfo{
    private static final long serialVersionUID = Reference.serialVersionUID;
    @Deprecated protected UserInfo(){}
    private String key;
    public UserInfo(String userName, String key, String playerID) {
        super(userName, "-" + playerID);// todo temp when using alternate heros
        this.key = key;
    }
}
