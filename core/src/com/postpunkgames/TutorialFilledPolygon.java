package com.postpunkgames;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.ShortArray;

public class TutorialFilledPolygon extends ApplicationAdapter {
	ShapeRenderer sr;

	FloatArray vertices;
	Vector2 center;

	Texture texture;
	PolygonSprite polySprite;
	PolygonSpriteBatch polyBatch;

	@Override
	public void create() {

		sr = new ShapeRenderer();

		polyBatch = new PolygonSpriteBatch();

		Pixmap pix = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
		pix.setColor(1, 0, 0, 1);
		pix.fill();

		texture = new Texture(pix);

		TextureRegion textureRegion = new TextureRegion(texture);

		center = new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);

		vertices = new FloatArray(new float[] { center.x, center.y + 50, center.x + 100, center.y, center.x + 50,
				center.y - 50, center.x - 50, center.y - 50, center.x - 100, center.y });

		EarClippingTriangulator triangulator = new EarClippingTriangulator();
		ShortArray triangleIndices = triangulator.computeTriangles(vertices);

		PolygonRegion polyReg = new PolygonRegion(textureRegion, vertices.toArray(), triangleIndices.toArray());

		polySprite = new PolygonSprite(polyReg);

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// sr.begin(ShapeType.Line);
		// sr.setColor(0, 1, 0, 1);
		// sr.polygon(vertices.toArray());
		// sr.end();

		polyBatch.begin();
		polySprite.draw(polyBatch);
		polyBatch.end();

	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void dispose() {
	}
}
