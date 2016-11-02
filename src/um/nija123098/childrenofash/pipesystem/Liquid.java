package um.nija123098.childrenofash.pipesystem;

import um.nija123098.childrenofash.Reference;
import um.nija123098.childrenofash.entity.info.UserInfo;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Jack on 7/18/2016.
 */
public abstract class Liquid implements Serializable{
    private static final long serialVersionUID = Reference.serialVersionUID;
    /*@Deprecated*/ protected Liquid(){}
    private UserInfo userInfo;
    private Double syncTime;
    public void sync(UserInfo entityInfo, double syncTime){
        if (this.userInfo == null){
            this.userInfo = entityInfo;
        }
        if (this.syncTime == null){
            this.syncTime = syncTime;
        }
    }
    public UserInfo getUserInfo() {
        return this.userInfo;
    }
    public Double getSyncTime() {
        return this.syncTime;
    }
    @Retention(RetentionPolicy.CLASS)
    @Target(ElementType.TYPE)
    protected @interface Meta{
        boolean isBaseType() default false;
        boolean isFullyImplemented() default false;
        String behavior();// should be replaced with optimized list of boolean behaviors
        String notes() default "";
    }
}
