package um.nija123098.childrenofash.entity.render;

import um.nija123098.childrenofash.Reference;
import um.nija123098.childrenofash.entity.Entity;

import java.io.Serializable;

/**
 * Created by Jack on 7/19/2016.
 */
public class RenderData implements Serializable{// this should not have to be serialized, this should be loaded from a reference src
    private static final long serialVersionUID = Reference.serialVersionUID;
    public transient Entity entity;
}
