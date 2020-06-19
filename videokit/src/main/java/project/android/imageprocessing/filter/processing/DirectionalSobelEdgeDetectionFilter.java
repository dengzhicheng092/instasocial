/* * *  Company : Bsetec *  Product: Instasocial *  Email : support@bsetec.com *  Copyright © 2018 BSEtec. All rights reserved. * */package project.android.imageprocessing.filter.processing;/** * Currently only used as part of the CannyEdgeDetectionFilter * @author Chris Batt */public class DirectionalSobelEdgeDetectionFilter extends SobelEdgeDetectionFilter {	@Override	protected String getFragmentShader() {		return 				 "precision mediump float;\n" 				+"uniform sampler2D "+UNIFORM_TEXTURE0+";\n"  				+"varying vec2 "+VARYING_TEXCOORD+";\n"					+"uniform float "+UNIFORM_TEXELWIDTH+";\n"				+"uniform float "+UNIFORM_TEXELHEIGHT+";\n"												  		+"void main(){\n"		  		+"   vec2 up = vec2(0.0, "+UNIFORM_TEXELHEIGHT+");\n"		  		+"   vec2 right = vec2("+UNIFORM_TEXELWIDTH+", 0.0);\n"		  		+"   float bottomLeftIntensity = texture2D("+UNIFORM_TEXTURE0+", "+VARYING_TEXCOORD+" - up - right).r;\n"		  		+"   float topRightIntensity = texture2D("+UNIFORM_TEXTURE0+", "+VARYING_TEXCOORD+" + up + right).r;\n"			    +"   float topLeftIntensity = texture2D("+UNIFORM_TEXTURE0+", "+VARYING_TEXCOORD+" + up - right).r;\n"			    +"   float bottomRightIntensity = texture2D("+UNIFORM_TEXTURE0+", "+VARYING_TEXCOORD+" - up + right).r;\n"			    +"   float leftIntensity = texture2D("+UNIFORM_TEXTURE0+", "+VARYING_TEXCOORD+" - right).r;\n"			    +"   float rightIntensity = texture2D("+UNIFORM_TEXTURE0+", "+VARYING_TEXCOORD+" + right).r;\n"			    +"   float bottomIntensity = texture2D("+UNIFORM_TEXTURE0+", "+VARYING_TEXCOORD+" - up).r;\n"			    +"   float topIntensity = texture2D("+UNIFORM_TEXTURE0+", "+VARYING_TEXCOORD+" + up).r;\n"			    +"   float h = -topLeftIntensity - 2.0 * topIntensity - topRightIntensity + bottomLeftIntensity + 2.0 * bottomIntensity + bottomRightIntensity;\n"			    +"   float v = -bottomLeftIntensity - 2.0 * leftIntensity - topLeftIntensity + bottomRightIntensity + 2.0 * rightIntensity + topRightIntensity;\n"			    			    +"   vec2 gradientDirection;\n"			    +"   gradientDirection.x = -bottomLeftIntensity - 2.0 * leftIntensity - topLeftIntensity + bottomRightIntensity + 2.0 * rightIntensity + topRightIntensity;\n"			    +"   gradientDirection.y = -topLeftIntensity - 2.0 * topIntensity - topRightIntensity + bottomLeftIntensity + 2.0 * bottomIntensity + bottomRightIntensity;\n"			    			    +"   float gradientMagnitude = length(gradientDirection);\n"			    +"   vec2 normalizedDirection = normalize(gradientDirection);\n"			    +"   normalizedDirection = sign(normalizedDirection) * floor(abs(normalizedDirection) + 0.617316);\n" // Offset by 1-sin(pi/8) to set to 0 if near axis, 1 if away			    +"   normalizedDirection = (normalizedDirection + 1.0) * 0.5;\n" // Place -1.0 - 1.0 within 0 - 1.0			    			    +"   gl_FragColor = vec4(gradientMagnitude, normalizedDirection.x, normalizedDirection.y, 1.0);\n"		  		+"}\n";	}}