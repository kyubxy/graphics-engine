/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package net.kyubey.engine.allocation;

/**
 *
 * @author kyubey
 */
public enum ComponentSilo {
	// put the silos here
	RIGID_TRANSFORM("rigidtransform");

	String key;
	ComponentSilo(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}
}
