package net.kyubey.engine.allocation;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kyubey
 */
public class ComponentSiloStore {
	private static final int SILO_ITEM_COUNT = 100; // TODO: this should be much larger

	private static class MissingComponentException extends IllegalArgumentException {
		public MissingComponentException(String missingKey) {
			super("No such component registered: '"  + missingKey +  "'."
				    + "Ensure it is registered at initialisation or that the "
					+ "correct ComponentSiloStore is being referenced at this point.");
		}
	}

	private final Map<Class<?>, ComponentFactory> factories;
	private final Map<Class<?>, Object[]> componentSilos;

	public ComponentSiloStore(ComponentFactory[] cfs) {
		this.componentSilos = new HashMap<>();
		this.factories = new HashMap<>();
		this.registerFactories(cfs);
		this.allocateSilos(cfs);
	}

	private void registerFactories(ComponentFactory[] factoryArray) {
		for (ComponentFactory cf : factoryArray) {
			var key = cf.getComponentType();
			factories.put(key, cf);
		}
	}

	private void allocateSilos(ComponentFactory[] factoryArray) {
		for (var factory : factoryArray) {
			// TODO: the silo store should have a mitigation against full silos.
			var silo = factory.createSilo(SILO_ITEM_COUNT); 
			componentSilos.put(factory.getComponentType(), silo);
		}
	}

	public <T> T attachComponentToEntity(Integer entity, Class<T> componentType) {
		ComponentFactory factory = getFactory(componentType);
		T component = componentType.cast(factory.createComponent());

		var silo = getSilo(componentType);
		silo[entity] = component;

		return component;
	}

	public <T> T getComponentFromEntity(Class<T> componentKey, Integer entity) {
		var silo = getSilo(componentKey);
		Object component = silo[entity];
		if (component == null) {
			return null;
		} else {
			return componentKey.cast(component);
		}
	}

	private <T> ComponentFactory<?> getFactory(Class<T> type) {
		var f = factories.get(type);
		if (f == null) throw new MissingComponentException(type.getName());
		return f;
	}

	private Object[] getSilo(Class<?> type) {
		var silo = componentSilos.get(type);
		if (silo == null) throw new MissingComponentException(type.getName());
		return silo;
	}
}

