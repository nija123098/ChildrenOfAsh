package um.nija123098.childrenofash.util.shape;

import org.lwjgl.util.vector.Matrix3f;
import org.lwjgl.util.vector.Vector3f;
import um.nija123098.childrenofash.util.MathHelper;

/**
 * Made by Dev on 7/31/2016
 */
public abstract class PhysicsShape3F {
    private Vector3f[] bindPoints;
    public V3 position, rotation, scale;
    public float mass;
    private boolean changed;
    public PhysicsShape3F(V3 position, V3 rotation, V3 scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }
    public PhysicsShape3F(){
        this.bindPoints();
        this.position = new V3(new Vector3f());
        this.rotation = new V3(new Vector3f());
        this.scale = new V3(new Vector3f());
    }
    protected Vector3f[] bindPoints(){
        this.checkAndUpdate();
        return this.bindPoints;
    }
    private void checkAndUpdate(){
        if (this.position.wasChanged() || this.rotation.wasChanged() || this.scale.wasChanged()){
            this.bindPoints = MathHelper.transform(this.position.value, this.rotation.value, this.scale.value, this.getIdentity());
        }
    }
    public boolean clashes(PhysicsShape3F shape){
        Vector3f[] oBoundsPoints = shape.bindPoints();
        Vector3f[] boundsPoints = this.bindPoints();
        for (int i = 0; i < oBoundsPoints.length; i++) {
            for (int j = 0; j < boundsPoints.length; j++) {
                for (int k = j + 1; k < boundsPoints.length; k++) {
                    if (!MathHelper.inside(boundsPoints[j], boundsPoints[k], boundsPoints[i])){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public abstract Vector3f[] getIdentity();
    public abstract Matrix3f getInertalTensor();
    public static final float PRECISION = 20;
    public class V3{
        public Vector3f value;
        private Vector3f lastValue;
        private boolean changed;
        public V3(Vector3f value) {
            this.value = value;
            this.lastValue = new Vector3f(value);
        }
        private void update(){
            if (!(this.value.x == this.lastValue.x && this.value.y == this.lastValue.y && this.value.z == this.lastValue.z)){
                this.changed = true;
            }
        }
        private boolean wasChanged(){
            this.update();
            if (this.changed){
                this.reset();
                return true;
            }
            return false;
        }
        private void reset(){
            this.lastValue = new Vector3f(this.value);
            this.changed = false;
        }
        private Vector3f getValueCopy(){
            return new Vector3f(this.value);
        }
    }
}
