import org.lwjgl.util.vector.Matrix3f;
import um.engi11.util.MAVec3f;
import um.engi11.util.shape.PhysicsSphere;
import um.engi11.util.sobjects.SMat;
import um.engi11.util.sobjects.SVec;

/**
 * Made by Dev on 7/29/2016
 */
public class MathTest {
    public static void main(String[] args) {
        sphereVertexCountTest();
    }
    private static void matvecmut(){
        SMat a = new SMat(1, 1, 1, 0, 0, 0, 0, 0, 0), b = new SMat(1, 2, 3, 4, 5, 6, 7, 8, 9), c = new SMat();
        Matrix3f.mul(b, a, c);
        System.out.println(c.toString());
    }
    private static void damTriangles(){
        System.out.println(Math.sin(Math.toRadians(30)));
    }
    private static void maVec3fText(){
        MAVec3f v = new MAVec3f(new SVec(0, 0, 1));
        // v.z += Math.PI * 1f;
        SVec v3 = v.getVec();
        System.out.println(v3.toString());
    }
    private static void magChecker(){
        System.out.println(new SVec(1, 1, 1).magnitude());
        System.out.println(Math.sqrt(3));
    }
    private static void sphereVertexCountTest(){
        System.out.println(new PhysicsSphere().getIdentity().length);
    }
}
