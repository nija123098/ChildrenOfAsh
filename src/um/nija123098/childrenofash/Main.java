package um.nija123098.childrenofash;

import um.nija123098.childrenofash.entity.info.UserInfo;
import um.nija123098.childrenofash.runconfiguration.SinglePlayerRunConfiguration;

public class Main {
    public static void main(String[] args) {
        new SinglePlayerRunConfiguration().start(new UserInfo("NAME", "S", "S"));
    }
}
