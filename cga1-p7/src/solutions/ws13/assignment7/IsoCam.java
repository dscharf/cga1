package solutions.ws13.assignment7;

import org.amcgala.camera.AbstractCamera;
import org.amcgala.math.Matrix;
import org.amcgala.math.Vector3d;
import org.amcgala.renderer.Pixel;

/**
 * Kamera, die die isometrische Projektion implementiert.
 */
public class IsoCam extends AbstractCamera {


    /**
     *
     * @param vup Das Oben der Kamera
     * @param direction Der Punkt, zu dem die Kamera blickt
     */
	Matrix proj = null;
	
    public IsoCam(Vector3d vup,Vector3d direction) {
        this.up = vup;
        this.direction = direction;
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
        return new Pixel(v.x, v.y);
    }

    private Matrix translate(Vector3d t){
    	return new Matrix(new double[][] {
   			 {1, 0, 0, t.x},
   			 {0, 1, 0, t.y},
   			 {0, 0, 1, t.z},
   			 {0, 0, 0, 1}
    	});
    }
    @Override
    public void update() {
        // TODO Aktualisieren der Projektionsmatrix, nachdem Parameter der Kamera geaendert wurden.
    	projectionMatrix = new Matrix(new double[][]{
    			{1./2.*Math.sqrt(3),	0,	-1./2.*Math.sqrt(3), 0},
    			{-1./2.,	1,	-1./2.,	0},
    			{0,			0,	0,		0},
    			{0,			0,	0,		1}
			});
    	
    	projectionMatrix = projectionMatrix
    			.times(translate(new Vector3d(600,600,0)));
    
    }
}
