package net.kyubey.engine.graphics;

import net.kyubey.engine.allocation.ComponentFactory;
import net.kyubey.engine.allocation.ComponentSilo;

/**
 *
 * @author kyubey
 */
public class RigidTransformComponentFactory implements ComponentFactory<RigidTransformComponent> {
	@Override
	public String getSiloIdentifier() {
		return ComponentSilo.RIGID_TRANSFORM.getKey();
	}

	@Override
	public Object[] allocateSilo(int siloSize) {
		return new RigidTransformComponent[siloSize];
	}

	@Override
	public RigidTransformComponent allocateComponent() {
		return new RigidTransformComponent();
	}
}
