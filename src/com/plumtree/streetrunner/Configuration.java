package com.plumtree.streetrunner;

import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.opengl.texture.TextureOptions;

public class Configuration {
	
	/**
	 *  Whether the screen should be on full pixels?
	 */
	public static final boolean CisFullScreen = true;
	
	public static final ScreenOrientation COrientation = ScreenOrientation.LANDSCAPE;
	
	public static final TextureOptions CTextureOptions = TextureOptions.BILINEAR_PREMULTIPLYALPHA;

}
