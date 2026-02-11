package net.kyubey.engine.graphics;

/**
 * Transform which preserve the shape of an object
 * @author kyubey
 */
public class RigidTransformComponent {
	public Vector2 position;
	public float rotation;

	public RigidTransformComponent() {
		position = new Vector2();
		rotation = 0;
	}
}
