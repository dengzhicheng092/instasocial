/* * *  Company : Bsetec *  Product: Instasocial *  Email : support@bsetec.com *  Copyright © 2018 BSEtec. All rights reserved. * */package project.android.imageprocessing.filter.processing;import project.android.imageprocessing.filter.GroupFilter;/** * This is the same as the {@link OpeningFilter}, except that this acts on all color channels, not just the red channel. * @author Chris Batt */public class OpeningRGBFilter extends GroupFilter {	public OpeningRGBFilter(int radius) {		ErosionRGBFilter erosion = new ErosionRGBFilter(radius);		DilationRGBFilter dilation = new DilationRGBFilter(radius);		erosion.addTarget(dilation);		dilation.addTarget(this);				registerInitialFilter(erosion);		registerTerminalFilter(dilation);	}}