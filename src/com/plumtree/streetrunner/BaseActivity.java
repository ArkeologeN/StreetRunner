package com.plumtree.streetrunner;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.AutoParallaxBackground;
import org.anddev.andengine.entity.scene.background.ParallaxBackground.ParallaxEntity;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.view.Display;

public class BaseActivity extends BaseGameActivity {
	
	private Camera mCamera;
	private Scene mMainScene;
	private BitmapTextureAtlas mBitmapAtlas, mBackgroundAtlas;
	private TextureRegion mPlayerTextureRegion, mSceneBgTextureRegion;
	private Sprite mCharacterSprite, mBackgroundSprite;

	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Engine onLoadEngine() {		
		
		final Display display = getWindowManager().getDefaultDisplay();
		int camWidth = display.getWidth();
		int camHeight = display.getHeight();
		
		this.mCamera = new Camera(0, 0, camWidth, camHeight);
		
		return new Engine(new EngineOptions(
								Configuration.CisFullScreen, 
								Configuration.COrientation, 
								new RatioResolutionPolicy(camWidth, camHeight), 
								this.mCamera));
	}

	@Override
	public void onLoadResources() {
		// TODO Auto-generated method stub
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		this.mBackgroundAtlas = new BitmapTextureAtlas(1024,1024, TextureOptions.DEFAULT);
		this.mSceneBgTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBackgroundAtlas, this, "background.png", 0, 0);
		mEngine.getTextureManager().loadTexture(mBackgroundAtlas);
		
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		this.mBitmapAtlas = new BitmapTextureAtlas(1024,1024,Configuration.CTextureOptions);
		this.mPlayerTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapAtlas, this, "Player.png", 0, 0);
		mEngine.getTextureManager().loadTexture(mBitmapAtlas);
	}

	@Override
	public Scene onLoadScene() {
		// TODO Auto-generated method stub
		
		mEngine.registerUpdateHandler(new FPSLogger());
		this.mMainScene = new Scene();
		this.mBackgroundSprite = new Sprite(0, mCamera.getHeight() - this.mSceneBgTextureRegion.getHeight(), this.mSceneBgTextureRegion);
		
		final AutoParallaxBackground mParallaxBackground = new AutoParallaxBackground(0, 0, 0, 10);
		mParallaxBackground.attachParallaxEntity(
				new ParallaxEntity(-25.0f, this.mBackgroundSprite));
		
		this.mMainScene.setBackground(mParallaxBackground);
		
		final int characterX = (int) (mCamera.getWidth() / 2);
		final int characterY = (int) (mCamera.getHeight() - (mPlayerTextureRegion.getHeight() * 1.3));
		
		this.mCharacterSprite = new Sprite(characterX, characterY, this.mPlayerTextureRegion);
		this.mCharacterSprite.setScale(2);
		this.mMainScene.attachChild(mCharacterSprite);
		
		return this.mMainScene;
	}
}