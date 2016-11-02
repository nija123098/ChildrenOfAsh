package um.nija123098.childrenofash.util.shape;

import org.lwjgl.util.vector.Matrix3f;
import org.lwjgl.util.vector.Vector3f;
import um.nija123098.childrenofash.util.MathHelper;
import um.nija123098.childrenofash.util.NNAList;
import um.nija123098.childrenofash.util.sobjects.SMat;

/**
 * Made by Dev on 8/1/2016
 */
public class PhysicsSphere extends PhysicsShape3F {
    private static final float precision = 0.19634954084936207f;// too much precision for a float?  The compiler does not complain
    @Override
    public Vector3f[] getIdentity() {
        NNAList<Vector3f> v = new NNAList<Vector3f>();
        for (float x = 0; x < Math.PI; x++) {
            for (float y = 0; y < Math.PI; y++) {
                for (float z = 0; z < Math.PI; z++) {
                    v.add(MathHelper.transform(new Vector3f(), new Vector3f(x, y, z), new Vector3f(), new Vector3f())[0]);
                }
            }
        }
        return v.toArray(new Vector3f[v.size()]);
    }
    @Override
    public Matrix3f getInertalTensor() {
        return new SMat(2f / 5f * this.mass * this.scale.value.x * this.scale.value.x, 2f / 5f * this.mass * this.scale.value.y * this.scale.value.y, 2f / 5f * this.mass * this.scale.value.z * this.scale.value.z);
    }
}
