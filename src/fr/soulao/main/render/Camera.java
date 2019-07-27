package fr.soulao.main.render;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.glu.GLU;

import fr.soulao.main.maths.Matrix;
import fr.soulao.main.maths.Vector3f;

public class Camera {
	private float fov;
	private float zNear;
	private float zFar;
	private float speed = 0.1f;

	private Vector3f position;
	private Vector3f rotation;

	public Camera(Vector3f position) {
		this.setPosition(position);
		rotation = new Vector3f();
	}

	public Camera setPespectiveProjection(float fov, float zNear, float zFar) {
		this.fov = fov;
		this.zNear = zNear;
		this.zFar = zFar;
		return this;
	}

	public Vector3f getForward() {
		Vector3f r = new Vector3f();
		Vector3f rot = new Vector3f(rotation);

		float cosY = (float) Math.cos(Math.toRadians(rot.getY() + 90));
		float sinY = (float) Math.sin(Math.toRadians(rot.getY() + 90));
		float cosP = (float) Math.cos(Math.toRadians(rot.getX()));
		float sinP = (float) Math.sin(Math.toRadians(rot.getX()));

		// Euler Angles
		r.setX(cosY * cosP);
		r.setY(sinP);
		r.setZ(sinY * cosP);

		r.normalize();

		return new Vector3f(r);
	}

	public Vector3f getRight() {
		Vector3f r = new Vector3f();
		Vector3f rot = new Vector3f(rotation);

		float cosY = (float) Math.cos(Math.toRadians(rot.getY()));
		float sinY = (float) Math.sin(Math.toRadians(rot.getY()));
		// Euler Angles
		r.setX(cosY);
		r.setZ(sinY);

		r.normalize();

		return new Vector3f(r);
	}

	public void getPespectiveProjection() {
		glEnable(GL_PROJECTION);
		glLoadIdentity();
		GLU.gluPerspective(fov, (float) (Display.getWidth()) / (float) (Display.getHeight()), zNear, zFar);
		glEnable(GL_MODELVIEW);

	}

	public void update() {
		glPushAttrib(GL_TRANSFORM_BIT);
		glRotatef(rotation.getX(), 1, 0, 0);
		glRotatef(rotation.getY(), 0, 1, 0);
		glRotatef(rotation.getZ(), 0, 0, 1);
		glTranslatef(position.getX(), position.getY(), position.getZ());
		glPopMatrix();

	}

	public void input() {
		/*
		 * float[] pos = {position.getX(), position.getY(), position.getZ()};
		 * float[][] passMatrix = Matrix.passMatrix(rotation.getX(),
		 * rotation.getY(), rotation.getZ()); float[] pos2 =
		 * Matrix.produitMatriceVecteur(passMatrix, pos); for(int i =0; i <= 2;
		 * i++){ System.out.println(pos2[i]); }
		 */

		rotation.addX(-Mouse.getDY());
		rotation.addY(Mouse.getDX());
		rotation.addZ(speed * (float) (Mouse.getDWheel()));

		// Z est vers l'arrière
		if (Keyboard.isKeyDown(Keyboard.KEY_Z)) {
			// position.add(getForward());
			position.add(getForward().mul(speed));
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			// position.addZ(-0.01f);
			position.add(getForward().mul(-speed));
		}
		// X est vers la gauche
		if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			// position.addX(0.01f);
			position.add(getRight().mul(speed));
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			// position.addX(-0.01f);
			position.add(getRight().mul(-speed));
		}
		// Y est vers le bas
		if (Keyboard.isKeyDown(Keyboard.KEY_H)) {
			position.addY(speed);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_Y)) {
			position.addY(-speed);
		}
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
}
