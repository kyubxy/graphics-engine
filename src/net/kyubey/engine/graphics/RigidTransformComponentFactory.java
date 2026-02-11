package net.kyubey.engine.graphics;

import net.kyubey.engine.allocation.ComponentFactory;
import net.kyubey.engine.allocation.Components;

/**
 *
 * @author kyubey
 */
public class RigidTransformComponentFactory implements ComponentFactory<RigidTransformComponent> {
	@Override
	public Object[] allocateSilo(int siloSize) {
		return new RigidTransformComponent[siloSize];
	}

	@Override
	public RigidTransformComponent allocateComponent() {
		return new RigidTransformComponent();
	}

	@Override
	public Class<RigidTransformComponent> getComponentType() {
		return RigidTransformComponent.class;
	}
}
