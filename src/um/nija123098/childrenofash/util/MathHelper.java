package um.nija123098.childrenofash.util;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

/**
 * Made by Dev on 7/29/2016
 */
public class MathHelper {
    public static Vector3f[] transform(Vector3f translation, Vector3f rotation, Vector3f scale, Vector3f...vecs){
        Matrix4f tm = makeTransformationMatrix(translation, rotation, scale);
        Vector3f[] out = new Vector3f[vecs.length];
        int l;
        for (int i = 0; i < vecs.length; i += 4) {
            l = vecs.length % 4;
            M4 in = new M4();
            for (int j = 0; j < l; j++) {
                in.set(j, 0, vecs[i + j].x);
                in.set(j, 1, vecs[i + j].y);
                in.set(j, 2, vecs[i + j].z);
                in.set(j, 3, 0);
            }
            Matrix4f res = new Matrix4f();
            Matrix4f.mul(tm, in, res);
            for (int j = 0; j < l; j++) {
                out[i] = new Vector3f(in.get(j, 0), in.get(j, 1), in.get(j, 2));
            }
        }
        return out;// TODO check this, it may not be correct
    }
    public static Matrix4f makeTransformationMatrix(Vector3f translation, Vector3f rotation, Vector3f scale){
        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();
        Matrix4f.translate(translation, matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rotation.x), new Vector3f(1, 0, 0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rotation.y), new Vector3f(0, 1, 0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rotation.z), new Vector3f(0, 0, 1), matrix, matrix);
        Matrix4f.scale(new Vector3f(scale), matrix, matrix);
        return matrix;
    }
    public static float mag(float...axis){
        float v = 0;
        for (int i = 0; i < axis.length; i++) {
            v += axis[i] * axis[i];
        }
        return (float) Math.sqrt(v);
    }
    public static boolean inside(Vector3f ina, Vector3f inb, Vector3f point){
        return (ina.x - inb.x > 0 ? (point.x > ina.x || point.x < inb.x) : (point.x < ina.x || point.x > inb.x)) || (ina.y - inb.y > 0 ? (point.y > ina.y || point.y < inb.y) : (point.y < ina.y || point.y > inb.y)) || (ina.z - inb.z > 0 ? (point.z > ina.z || point.z < inb.z) : (point.z < ina.z || point.z > inb.z));
    }
    private static class M4 extends Matrix4f{
        public M4() {
        }
        public M4(Matrix4f src) {
            super(src);
        }
        private void set(int x, int y, float v){
            B:
            switch (x){
                case 0: switch (y){
                        case 0: this.m00 = v; break B;
                        case 1: this.m01 = v; break B;
                        case 2: this.m02 = v; break B;
                        case 3: this.m03 = v; break B;}
                case 1: switch (y){
                        case 0: this.m10 = v; break B;
                        case 1: this.m11 = v; break B;
                        case 2: this.m12 = v; break B;
                        case 3: this.m13 = v; break B;}
                case 2: switch (y){
                        case 0: this.m20 = v; break B;
                        case 1: this.m21 = v; break B;
                        case 2: this.m22 = v; break B;
                        case 3: this.m23 = v; break B;}
                case 3: switch (y){
                        case 0: this.m30 = v; break B;
                        case 1: this.m31 = v; break B;
                        case 2: this.m32 = v; break B;
                        case 3: this.m33 = v; break B;}
            }
        }
        private float get(int x, int y){
            switch (x){
                case 0: switch (y){
                    case 0: return this.m00;
                    case 1: return this.m01;
                    case 2: return this.m02;
                    case 3: return this.m03;}
                case 1: switch (y){
                    case 0: return this.m10;
                    case 1: return this.m11;
                    case 2: return this.m12;
                    case 3: return this.m13;}
                case 2: switch (y){
                    case 0: return this.m20;
                    case 1: return this.m21;
                    case 2: return this.m22;
                    case 3: return this.m23;}
                case 3: switch (y){
                    case 0: return this.m30;
                    case 1: return this.m31;
                    case 2: return this.m32;
                    case 3: return this.m33;}
            }
            throw new RuntimeException("Non-Existent matrix position");
        }
    }
}
