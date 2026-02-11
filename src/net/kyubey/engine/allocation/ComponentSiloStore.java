package net.kyubey.engine.allocation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author kyubey
 */
public class ComponentSiloStore {
	private static int SILO_ITEM_COUNT = 100; // TODO: this should be much larger

	private static class MissingComponentException extends IllegalArgumentException {
		public MissingComponentException(String missingKey) {
			super("No such component registered: '"  + missingKey +  "'."
				    + "Ensure it is registered at initialisation or that the "
					+ "correct ComponentSiloStore is being referenced at this point.");
		}
	}

	private Map<String, ComponentFactory> factories;
	private Map<String, Object[]> componentSilos;

	private Set<String> validKeys;
	
	public ComponentSiloStore(ComponentFactory[] cfs) {
		componentSilos = new HashMap<>();

		// register factories into map
		this.factories = new HashMap<>();
		this.validKeys = new HashSet<>();
		for (ComponentFactory cf : cfs) {
			var key = cf.getSiloIdentifier();
			factories.put(key, cf);
			validKeys.add(key);
		}

		this.allocateSilos();
	}

	private void allocateSilos() {
		for (var key : validKeys) {
			ComponentFactory factory = factories.get(key);

			// TODO: the silo store should have a mitigation against full silos.
			var silo = factory.allocateSilo(SILO_ITEM_COUNT); 
			componentSilos.put(key, silo);
		}
	}

	public Object attachComponentToEntity(Integer entity, String componentKey) {
		// verify key is valid first
		if (!validKeys.contains(componentKey)) {
			throw new MissingComponentException(componentKey);
		}

		// create the component
		ComponentFactory factory = factories.get(componentKey);
		var component = factory.allocateComponent();

		// locate the silo
		var silo = componentSilos.get(componentKey);
		silo[entity] = component;

		// return the component for the client to mutate
		return component;
	}

	public Object getComponentFromEntity(String componentKey, Integer entity) {
		if (!validKeys.contains(componentKey)) {
			throw new MissingComponentException(componentKey);
		}
		var silo = componentSilos.get(componentKey);
		var component = silo[entity];
		return component;
	}
}
