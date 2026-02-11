/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package net.kyubey.engine.allocation;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kyubey
 */
public class EntityPool {
	private final List<Integer> inUse;
	private int rightmost = 0;
	
	// TODO: things that would be nice: 
	// - available list to prevent fragementation
	// - yeah that's it, just some measure to prevent `rightmost` from becoming too large.

	public EntityPool() {
		inUse = new ArrayList<>();
	}

	public Integer createEntity() {
		Integer nextAvailable;
		nextAvailable = ++rightmost;
		inUse.add(nextAvailable);
		return nextAvailable;
	}

	public void destroyEntity(int e) {
		inUse.remove(e);
	}

	public void iterateActive(System system) {
		for (var e : inUse) {
			system.consumeEntity(e);
		}
	}
}
