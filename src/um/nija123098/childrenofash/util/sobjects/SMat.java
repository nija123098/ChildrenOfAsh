package um.nija123098.childrenofash.util.sobjects;

import org.lwjgl.util.vector.Matrix3f;

/**
 * Created by Jack on 7/23/2016.
 */
public class SMat extends Matrix3f {
    public SMat(){
        super();
    }
    public SMat(float x, float y, float z){
        this.m00 = x;
        this.m11 = y;
        this.m22 = z;
    }
    public SMat(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22){
        this.m00 = m00;
        this.m01 = m01;
        this.m02 = m02;
        this.m10 = m10;
        this.m11 = m11;
        this.m12 = m12;
        this.m20 = m20;
        this.m21 = m21;
        this.m22 = m22;
    }
    public SMat(Matrix3f src){
        this(src.m00, src.m01, src.m02, src.m10, src.m11, src.m12, src.m20, src.m21, src.m22);
    }
    public SMat inverse() {
        return (SMat) this.invert();
    }
    public void transform(SVec angularVelChangea) {
        SMat a = new SMat(angularVelChangea.x, angularVelChangea.y, angularVelChangea.z, 0, 0, 0, 0, 0, 0), b = new SMat(this), c = new SMat();
        Matrix3f.mul(b, a, c);
        angularVelChangea.x = c.m00;
        angularVelChangea.y = c.m01;
        angularVelChangea.z = c.m02;
    }
}
