package net.kyubey.engine.graphics;

import net.kyubey.engine.allocation.ComponentFactory;

/**
 *
 * @author kyubey
 */
public class RigidTransformComponentFactory implements ComponentFactory<RigidTransformComponent> {
	@Override
	public Object[] createSilo(int siloSize) {
		return new RigidTransformComponent[siloSize];
	}

	@Override
	public RigidTransformComponent createComponent() {
		return new RigidTransformComponent();
	}

	@Override
	public Class<RigidTransformComponent> getComponentType() {
		return RigidTransformComponent.class;
	}
}
