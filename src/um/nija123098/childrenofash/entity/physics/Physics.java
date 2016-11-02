package um.nija123098.childrenofash.entity.physics;

import org.lwjgl.util.vector.Vector3f;
import um.nija123098.childrenofash.Reference;
import um.nija123098.childrenofash.entity.Entity;
import um.nija123098.childrenofash.pipesystem.Liquid;
import um.nija123098.childrenofash.pipesystem.Processed;
import um.nija123098.childrenofash.pipesystem.liquid.AlterPhysicsLiquid;
import um.nija123098.childrenofash.util.NNAList;
import um.nija123098.childrenofash.util.shape.PhysicsShape3F;
import um.nija123098.childrenofash.util.sobjects.SMat;
import um.nija123098.childrenofash.util.sobjects.SVec;

import java.io.Serializable;

/**
 * Created by Jack on 7/19/2016.
 */
public class Physics implements Serializable, Processed{
    private static final long serialVersionUID = Reference.serialVersionUID;
    @Deprecated protected Physics(){}
    private transient Entity entity;
    private Float mass;// mass == null == immovable;
    private Vector3f dimensions;
    private PhysicsShape3F shape;
    private Vector3f position;
    private Vector3f rotation;
    private Vector3f velocity;
    private Vector3f angularVelocity;
    private AlterPhysicsLiquid alterPhysicsLiquid;
    public Physics(Vector3f dimensions, Vector3f position, Vector3f rotation, PhysicsShape3F shape, Float mass){
        this.dimensions = dimensions;
        this.position = position;
        this.rotation = rotation;
        this.shape = shape;
        this.mass = mass;
    }
    @Override
    public NNAList<Liquid> process(NNAList<Liquid> liquids, double deltaSyncTime) {
        if (alterPhysicsLiquid != null){
            NNAList<Liquid> liquid = new NNAList<Liquid>(1);
            liquid.add(this.alterPhysicsLiquid);
            this.alterPhysicsLiquid = null;
            return liquid;
        }
        return null;
    }
    public void latch(Entity entity){
        this.entity = entity;
    }
    public void moveAsRegular(double time){
        if (this.mass != null){
            this.position.x += this.velocity.x * time;
            this.position.y += this.velocity.y * time;
            this.position.z += this.velocity.z * time;
            this.rotation.x += this.angularVelocity.x * time;
            this.rotation.y += this.angularVelocity.y * time;
            this.rotation.z += this.angularVelocity.z * time;
        }
    }
    public void setPosition(Vector3f position) {
        this.position = position;
    }
    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }
    public void setVelocity(Vector3f velocity) {
        this.velocity = velocity;
    }
    public void setAngularVelocity(Vector3f angularVelocity){
        this.angularVelocity = angularVelocity;
    }
    private Float getMass(){
        if (this.mass != null){
            float m = 0;
            for (int i = 0; i < this.entity.gameLogic.equipped.size(); i++) {
                Float mo = this.entity.gameLogic.equipped.get(i).physics.getMass();
                if (mo != null){
                    m += mo;
                }// this could cause problems
            }
            return this.mass + m;
        }
        return null;
    }
    /**
     This function calulates the velocities after a 3D collision vaf, vbf, waf and wbf from information about the colliding bodies
     double e coefficient of restitution which depends on the nature of the two colliding materials
     double ma total mass of body a
     double mb total mass of body b
     matrix Ia inertia tensor for body a in absolute coordinates (if this is known in local body coordinates it must be converted before this is called).
     matrix Ib inertia tensor for body b in absolute coordinates (if this is known in local body coordinates it must be converted before this is called).
     vector ra position of collision point relative to centre of mass of body a in absolute coordinates (if this is known in local body coordinates it must be converted before this is called).
     vector rb position of collision point relative to centre of mass of body b in absolute coordinates (if this is known in local body coordinates it must be converted before this is called).
     vector n normal to collision point, the line along which the impulse acts.
     vector vai initial velocity of centre of mass on object a
     vector vbi initial velocity of centre of mass on object b
     vector wai initial angular velocity of object a
     vector wbi initial angular velocity of object b
     vector vaf final velocity of centre of mass on object a
     vector vbf final velocity of centre of mass on object a
     vector waf final angular velocity of object a
     vector wbf final angular velocity of object b
     */
    // WARNING THIS MAY NOT CONFORM TO JAVA STANDARDS, THIS HAS BEEN DONE TO PRESERVE GIVEN CODE
    public static NNAList<Liquid> collide(Entity a, Entity b, double deltaTime){// processes collision if it collides and returns true, or it does not - does not calculate the position or rotation
        if (a.physics.getMass() == null && b.physics.getMass() == null){
            return null;
        }
        Vector3f nor = (Vector3f) new SVec(a.physics.position).negate(b.physics.position).normalise();// TODO WORKING ON INSERT ROTATIONAL ADJUSTMENT
        if (a.physics.shape.clashes(b.physics.shape) || b.physics.shape.clashes(a.physics.shape)){
            return null;
        }
        SVec aFP = new SVec((float) (a.physics.position.x + a.physics.velocity.x * deltaTime), (float) (a.physics.position.y + a.physics.velocity.y * deltaTime), (float) (a.physics.position.z + a.physics.velocity.z * deltaTime));
        SVec bFP = new SVec((float) (b.physics.position.x + b.physics.velocity.x * deltaTime), (float) (b.physics.position.y + b.physics.velocity.y * deltaTime), (float) (b.physics.position.z + b.physics.velocity.z * deltaTime));
        // SVec aFR = new SVec((float) (a.physics.rotation.x + a.physics.angularVelocity.x * deltaTime), (float) (a.physics.rotation.y + a.physics.angularVelocity.y * deltaTime), (float) (a.physics.rotation.z + a.physics.angularVelocity.z * deltaTime));
        // SVec bFR = new SVec((float) (b.physics.rotation.x + b.physics.angularVelocity.x * deltaTime), (float) (b.physics.rotation.y + b.physics.angularVelocity.y * deltaTime), (float) (b.physics.rotation.z + b.physics.angularVelocity.z * deltaTime));
        SVec unv = aFP.copy().minus(bFP);
        SVec collisionPoint = new SVec(unv.x / 2 + aFP.x, unv.y / 2 + aFP.y, unv.z / 2 + aFP.z);
        double e = 0;
        double ma = a.physics.getMass() == null ? MASSIVE_IMMOVABLE_MASS_CONSTANT : a.physics.getMass();// todo make this more efficient
        double mb = b.physics.getMass() == null ? MASSIVE_IMMOVABLE_MASS_CONSTANT : b.physics.getMass();
        SMat Ia = (SMat) a.physics.shape.getInertalTensor();
        SMat Ib = (SMat) b.physics.shape.getInertalTensor();
        SVec ra = collisionPoint;// copy nor required until modification
        SVec rb = collisionPoint.copy();
        SVec n = new SVec(aFP).minus(new SVec(bFP));// normalized in algorithm anyway
        SVec vai = ((SVec) a.physics.velocity).copy();// perhaps just making a new SVec is more efficient
        SVec vbi = ((SVec) b.physics.velocity).copy();
        SVec wai = ((SVec) a.physics.angularVelocity).copy();// copying this might not be necessary
        SVec wbi = ((SVec) b.physics.angularVelocity).copy();
        SVec vaf;
        SVec vbf;
        SVec waf;
        SVec wbf;
        SMat IaInverse = Ia.inverse();
        SVec normal = (SVec) n.normalise();
        SVec angularVelChangea  = normal.copy(); // start calculating the change in abgular rotation of a
        angularVelChangea.cross(ra);
        IaInverse.transform(angularVelChangea);
        SVec vaLinDueToR = angularVelChangea.copy().cross(ra);  // calculate the linear velocity of collision point on a due to rotation of a
        double scalar = 1/ma + vaLinDueToR.dot(normal);
        SMat IbInverse = Ib.inverse();
        SVec angularVelChangeb = normal.copy(); // start calculating the change in abgular rotation of b
        angularVelChangeb.cross(rb);
        IbInverse.transform(angularVelChangeb);
        SVec vbLinDueToR = angularVelChangeb.copy().cross(rb);  // calculate the linear velocity of collision point on b due to rotation of b
        scalar += 1/mb + vbLinDueToR.dot(normal);
        double Jmod = (e+1)*(vai.minus(vbi)).magnitude()/scalar;
        SVec J = normal.mul(Jmod);
        vaf = vai.minus(J.mul(1/ma));
        vbf = vbi.minus(J.mul(1/mb));
        waf = wai.minus(angularVelChangea);
        wbf = wbi.minus(angularVelChangeb);
        a.physics.velocity = vaf;
        b.physics.velocity = vbf;
        a.physics.angularVelocity = waf;
        b.physics.angularVelocity = wbf;
        NNAList<Liquid> ret = new NNAList<>(2);
        a.physics.velocity = vaf;
        a.physics.angularVelocity = waf;
        b.physics.velocity = vbf;
        b.physics.angularVelocity = wbf;
        ret.add(new AlterPhysicsLiquid(a.entityInfo, vaf, waf));
        ret.add(new AlterPhysicsLiquid(b.entityInfo, vbf, wbf));
        return ret;
    }
    /**
     * Largest possible value without overflow to simulate an immovable object.
     * The object will not move, because of null mass detection in normal movement,
     * but still requires a mass for collision.  This is a round-a-bout way for not
     * needing a massive (ha) equation for collision.
     */
    private static final float MASSIVE_IMMOVABLE_MASS_CONSTANT = Float.MAX_VALUE;
    public PhysicsShape3F getShape() {
        return shape;
    }
}
