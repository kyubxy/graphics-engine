package net.kyubey.engine;

import net.kyubey.engine.allocation.EntityPool;
import net.kyubey.engine.graphics.RigidTransformComponent;
import net.kyubey.engine.graphics.Vector2;
import org.lwjgl.glfw.*;
import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import org.lwjgl.opengl.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class HelloWorld {

    private long window;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

	private EntityPool pool = new EntityPool();

	private RigidTransformComponent[] rigidbodies = new RigidTransformComponent[100];

	private Integer entity1;

	private RigidTransformComponent attachRigidTransformComponent(Integer entity) {
		var rtc = new RigidTransformComponent();
		this.rigidbodies[entity] = rtc;
		return rtc;
	}

	private void makeNewEntity(float x, float y){
		var entity = pool.createEntity();
		var rtc = attachRigidTransformComponent(entity);
		rtc.position = new Vector2(x, y);
	}

	public HelloWorld() {
		// configure factories in component store

		int entityCount = 8;

		for (int i = 0; i < entityCount; i++){
			makeNewEntity(80 + i * (i % 2 == 0 ? 40 : 50), 80);
		}
	}

    public void run() {
        init();
        loop();

        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void init() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialise GLFW");
        }

        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

        window = glfwCreateWindow(WIDTH, HEIGHT, "LWJGL Square", NULL, NULL);
        if (window == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1); // vsync
        glfwShowWindow(window);

        GL.createCapabilities();

        // --- OpenGL setup for screen coordinates ---
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();

        // left, right, bottom, top, near, far
        glOrtho(0, WIDTH, HEIGHT, 0, -1, 1);

        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
    }

    private void loop() {
        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT);

            // Background colour
            glClearColor(0.1f, 0.1f, 0.15f, 1.0f);

            draw();

            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }

    private void draw() {
        glColor3f(1.0f, 0.2f, 0.2f);

		pool.iterateActive(entityId -> {
			var pos = rigidbodies[entityId].position;
			drawSquare2(pos.x, pos.y, 30);
		});
    }

	private static void drawSquare2(float x, float y, float size) {
		glBegin(GL_QUADS);
		glVertex2f(x, y);
		glVertex2f(x + size, y);
		glVertex2f(x + size, y + size);
		glVertex2f(x, y + size);
		glEnd();
	}

    public static void main(String[] args) {
        new HelloWorld().run();
    }
}


