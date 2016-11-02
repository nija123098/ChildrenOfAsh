package um.nija123098.childrenofash.util.shape;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import um.nija123098.childrenofash.util.MathHelper;
import um.nija123098.childrenofash.util.sobjects.SMat;
import um.nija123098.childrenofash.util.sobjects.SVec;

/**
 * Created by Jack on 7/23/2016.
 */
@Deprecated
public enum  RegularShape3D {// all comments depict a unscaled unit regular concave shape
    Sphere(){// one higher dimension circle
        @Override
        public float distanceToFace(Vector3f v, Vector3f r, Vector3f scale){
            return new SVec(scale.x, scale.y, scale.z).magnitude();
        }
        @Override
        public SMat tensor(Vector3f scale, float mass){
            return new SMat(2f/5f*mass*scale.x * scale.x, 2f/5f*mass*scale.y * scale.y, 2f/5f*mass*scale.z * scale.z);
        }
    }, Cuboid(){// one higher dimension square
        @Override
        public float distanceToFace(Vector3f v, Vector3f r, Vector3f scale){// had to figure out my self
            Matrix4f in = new Matrix4f();// makes direction vector into matrix
            in.m00 = v.x;
            in.m01 = v.y;
            in.m02 = v.z;
            in.m03 = 1;
            Matrix4f res = new Matrix4f();
            Matrix4f.mul(MathHelper.makeTransformationMatrix(new Vector3f(), r, new Vector3f(1, 1, 1)), in, res);// makes transformation matrix for rotate and multiplys that with direction vector
            v = new Vector3f(res.m00, res.m10, res.m20);// forms new direction vector
            Vector3f iv = new Vector3f(1 - v.x, 1 - v.y, 1 - v.z);// forms remaining component vector
            return (float) (Math.sqrt(v.x * scale.x * v.x * scale.x + v.y * scale.y * v.y * scale.y + v.z * scale.z * v.z * scale.z) + scale.length() - Math.sqrt(iv.x * scale.x * iv.x * scale.x + iv.y * scale.y * iv.y * scale.y + iv.z * scale.z * iv.z * scale.z));// does the math
        }// perhaps possibly right
        @Override
        public SMat tensor(Vector3f scale, float mass){
            return new SMat((scale.y * scale.y + scale.z * scale.z) / 3, (scale.x * scale.x + scale.z * scale.z) / 3, (scale.x * scale.x + scale.y * scale.y) / 3);
        }
    }, Cylinder(){// standard cylinder with circle perspective at either y perspective
        @Override
        public float distanceToFace(Vector3f v, Vector3f r, Vector3f scale){
            Matrix4f in = new Matrix4f();
            in.m00 = v.x;
            in.m01 = v.y;
            in.m02 = v.z;
            in.m03 = 1;
            Matrix4f res = new Matrix4f();
            Matrix4f.mul(MathHelper.makeTransformationMatrix(new Vector3f(), r, new Vector3f(1, 1, 1)), in, res);
            v = new Vector3f(res.m00, res.m10, res.m20);
            return 0;// TODO distance to face Cylinder, compound cuboid and sphere
        }
        @Override
        public SMat tensor(Vector3f scale, float mass){
            float artificialRadius = scale.x + scale.z / 2;
            return new SMat((mass * scale.y * scale.y) / 12 + (mass * artificialRadius * artificialRadius) / 4, mass * artificialRadius * artificialRadius / 2, (mass * scale.y * scale.y) / 12 + (mass * artificialRadius * artificialRadius) / 4);
        }
    };
    public abstract float distanceToFace(Vector3f v, Vector3f r, Vector3f scale);// TODO IMPLEMENT ROTATION
    public abstract SMat tensor(Vector3f scale, float mass);
}
