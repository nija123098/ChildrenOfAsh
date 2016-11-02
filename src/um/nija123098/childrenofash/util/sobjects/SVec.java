package um.nija123098.childrenofash.util.sobjects;

import com.sun.javafx.geom.Vec3f;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by Jack on 7/23/2016.
 */
public class SVec extends Vector3f{
    public SVec(Vector3f v){
        super(v);
    }

    public SVec(float x, float y, float z) {
        super(x, y, z);
    }

    public SVec copy() {
        try {
            return (SVec) this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public SVec cross(SVec ra) {
        Vec3f f = new Vec3f(this.x, this.y, this.z);
        f.cross(f, new Vec3f(ra.x, ra.y, ra.z));
        this.x = f.x;
        this.y = f.y;
        this.z = f.z;
        return this;
    }

    public double dot(SVec normal) {
        Vec3f f = new Vec3f(this.x, this.y, this.z);
        return f.dot(new Vec3f(normal.x, normal.y, normal.z));
    }

    public SVec minus(SVec vbr) {
        this.x -= vbr.x;
        this.y -= vbr.y;
        this.z -= vbr.z;
        return this;

    }

    public SVec mul(double jmod) {
        Vec3f f = new Vec3f(this.x, this.y, this.z);
        f.mul((float) jmod);
        this.x = f.x;
        this.y = f.y;
        this.z = f.z;
        return this;
    }

    public float magnitude() {
        return this.length();
    }
}
