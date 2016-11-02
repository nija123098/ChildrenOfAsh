package um.nija123098.childrenofash.util.shape;

import org.lwjgl.util.vector.Vector3f;

/**
 * Made by Dev on 8/6/2016
 */
public interface Location3F {
    boolean canSee(Vector3f vector3f);
    default boolean canSee(PhysicsShape3F shape3F){
        Vector3f[] v = shape3F.bindPoints();
        for (int i = 0; i < v.length; i++) {
            if (this.canSee(v[i])){
                return true;
            }
        }
        return false;
    }
}
