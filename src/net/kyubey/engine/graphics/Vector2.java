/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

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

	public Vector2(float a) {
		this.x = a;
		this.y = a;
	}

	public final static Vector2 zero = new Vector2(0);
}
