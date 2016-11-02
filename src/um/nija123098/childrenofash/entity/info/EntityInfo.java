package um.nija123098.childrenofash.entity.info;

import um.nija123098.childrenofash.Reference;

import java.io.Serializable;

/**
 * Created by Jack on 7/18/2016.
 */
public class EntityInfo implements Serializable{
    private static final long serialVersionUID = Reference.serialVersionUID;
    @Deprecated protected EntityInfo(){}
    private String name;
    // private String entityTypeName;// unimplemented this in favor of a state data package or entity with post transmitted constant data
    private String entityID;
    public EntityInfo(String name, String entityID) {
        this.name = name;
        this.entityID = entityID;
    }
    public String getName() {
        return this.name;
    }
    public String getEntityID() {
        return this.entityID;
    }
    public boolean isSameEntity(EntityInfo entityInfo){
        return this.entityID == entityInfo.entityID;
    }
    public boolean equals(Object o){
        return o instanceof EntityInfo && this.isSameEntity((EntityInfo) o);
    }
}
