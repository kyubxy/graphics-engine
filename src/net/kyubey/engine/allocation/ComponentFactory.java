/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package net.kyubey.engine.allocation;

/**
 *
 * @author kyubey
 * @param <T>
 */
public interface ComponentFactory<T> {
	Class<T> getComponentType();
	Object[] createSilo(int siloSize);
	T createComponent();
}
