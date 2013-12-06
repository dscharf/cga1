package solutions.ws13.assignment7;

import javax.vecmath.Vector4d;

import org.amcgala.camera.AbstractCamera;
import org.amcgala.math.Matrix;
import org.amcgala.math.Quaternion;
import org.amcgala.math.Vector3d;
import org.amcgala.renderer.Pixel;

/**
 * Klasse, die die perspektivische Projektion von Vektoren ermoeglicht.
 */
public class PCam extends AbstractCamera{

    private double d;

    /**
     * Erzeugt eine neue Kamera an einer Position mit einem bestimmten
     * Blickpunkt.
     *
     * @param vup       Das Oben der Kamera
     * @param position  Die Position der Kamera
     * @param direction Der Punkt, zu dem die Kamera blickt
     * @param d         der Abstand der Kamera zur Projektionsebene. Umso kleiner der
     *                  Wert desto größer die perspektivische Wirkung
     */
    
    
    public PCam(Vector3d vup, Vector3d position, Vector3d direction, double d) {
        this.up = vup;
        this.location = position;
        this.direction = direction;
        this.d = d;
        update();
    }


    @Override
    protected Matrix getProjectionMatrix() {
        // TODO Rueckgabe der Projektionsmatrix
        return projectionMatrix;
    }

    @Override
    public Pixel project(Vector3d v) {
        // TODO Anwenden der Projektionmatrix auf den uebergebenen Vektor.
    	v = v.transform(getProjectionMatrix());
        return new Pixel(v.x/v.z, v.y/v.z);
    }
    
    private Matrix perspective(double fov, double aspect, double near, double far){
		double e = 1. / Math.atan(fov / 2.0);
		double length = far - near;
		/*
		return new Matrix(new double[][] {
    			{e, 0, 0, 0},
    			{0, e / aspect, 0, 0},
    			{0, 0, -(far + near) / length, -(2 * near * far) / length},
    			{0, 0, -1, 0}
    	});
		
		
		return new Matrix(new double[][] {
    			{e, 0, 0, 0},
    			{0, e /aspect , 0, 0},
    			{0, 0, -1, -2 * d},
    			{0, 0, -1, 0}
    	});
		*/
		
		return new Matrix(new double[][] {
    			{e, 0, 0, 0},
    			{0, e / aspect, 0, 0},
    			{0, 0,  1/near, 1},
    			{0, 0, 0, 0}
    	});
    }
    
    private Matrix lookAt(Vector3d eye, Vector3d target, Vector3d up) {
		Vector3d zaxis = target.sub(eye).times(-1).normalize();
		Vector3d xaxis = up.cross(zaxis).normalize();
		Vector3d yaxis = zaxis.cross(xaxis).normalize();

		return new Matrix(new double[][]{
				{xaxis.x, xaxis.y, xaxis.z, eye.dot(xaxis)},
				{yaxis.x, yaxis.y, yaxis.z, eye.dot(yaxis)},
				{zaxis.x, zaxis.y, zaxis.z, eye.dot(zaxis)},
				{0, 0, 0, 1}
		});
    }
    

    @Override
    public void update() {
    	projectionMatrix = perspective(40, 600.0 / 600.0, d, d * 2)
    			.times(lookAt(location, direction, up));
    			//.times(translate(new Vector3d(100,350,100)));
    }
}
