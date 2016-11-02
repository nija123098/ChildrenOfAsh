package um.nija123098.childrenofash.util;

import org.lwjgl.util.vector.Vector3f;
import um.nija123098.childrenofash.util.sobjects.SVec;

/**
 * Made by Dev on 7/29/2016
 */
@Deprecated
public class MAVec3f {
    /**
     * @value mag magnitude of vector
     * @value x value of x axis in radians with z sin, y cos facing object left
     * @value y value of y axis in radians with x sin, z cos facing object up
     * @value z value of z axis in radians with y sin, x cos facing object forward
     */
    public float mag, x, y, z;
    public MAVec3f(SVec sVec){
        this.mag = sVec.magnitude();
        // this.x = (float) (Math.atan(sVec.y / sVec.z) + (sVec.y < 0 ? Math.PI : 0));
        this.x = (float) (sVec.y == 0 ? (sVec.z > 0 ? Math.PI * .5 : Math.PI * 1.5) : (Math.atan(sVec.z / sVec.y) + (sVec.z > 0 ? Math.PI : 0)));
        // this.y = (float) (Math.atan(sVec.z / sVec.x) + (sVec.z < 0 ? Math.PI : 0));
        this.y = (float) (sVec.z == 0 ? (sVec.x > 0 ? Math.PI * .5 : Math.PI * 1.5) : (Math.atan(sVec.x / sVec.z) + (sVec.x > 0 ? Math.PI : 0)));
        // this.z = (float) (Math.atan(sVec.y / sVec.x) + (sVec.y < 0 ? Math.PI : 0));
        this.z = (float) (sVec.x == 0 ? (sVec.y > 0 ? Math.PI * .5 : Math.PI * 1.5) : (Math.atan(sVec.y / sVec.x) + (sVec.y > 0 ? Math.PI : 0)));
        if (mag == 0);
    }
    public MAVec3f(Vector3f vector3f){
        this(new SVec(vector3f));
    }
    public SVec getVec(){
        return new SVec((float) Math.cos(this.z) * this.mag, (float) Math.sin(this.z) * this.mag, (float) Math.cos(this.y) * this.mag);
    }
}
