package net.kyubey.engine.graphics;

/**
 *
 * @author kyubey
 */
public class Vector2 {
	public float x;
	public float y;

	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Vector2() {
		this(0);
	}

	public Vector2(float a) {
		this(a, a);
	}

	public final static Vector2 zero = new Vector2(0);
}
