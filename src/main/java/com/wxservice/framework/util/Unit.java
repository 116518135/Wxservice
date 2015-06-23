package com.wxservice.framework.util;

public class Unit {
	 public static final double PIXEL = 1.0;
	  public static final double INCHES = 72.0;
	  public static final double CENTIMETERS = 28.3464;
	  public static final double MILLIMETERS = 2.83464;


	  private String unitName;


	  private double conversionValue;

	  /** Creates a new instance of Unit */
	  public Unit(String unitName, double conversionValue) {
	    this.unitName = unitName;
	    this.conversionValue = conversionValue;
	  }

	  /** Getter for property unitName.
	   * @return Value of property unitName.
	   *
	   */
	  public String getUnitName() {
	    return this.unitName;
	  }

	  /** Setter for property unitName.
	   * @param unitName New value of property unitName.
	   *
	   */
	  public void setUnitName(String unitName) {
	    this.unitName = unitName;
	  }

	  /** Getter for property conversionValue.
	   * @return Value of property conversionValue.
	   *
	   */
	  public double getConversionValue() {
	    return this.conversionValue;
	  }

	  /** Setter for property conversionValue.
	   * @param conversionValue New value of property conversionValue.
	   *
	   */
	  public void setConversionValue(double conversionValue) {
	    this.conversionValue = conversionValue;
	  }

	  public static Unit[] getStandardUnits() {
	    Unit[] units = new Unit[4];

	    units[0] = new Unit("pixels", Unit.PIXEL);
	    units[1] = new Unit("inches", Unit.INCHES);
	    units[2] = new Unit("cm", Unit.CENTIMETERS);
	    units[3] = new Unit("mm", Unit.MILLIMETERS);

	    return units;
	  }

	  public String toString() {
	    return getUnitName();
	  }

	  static public double convertPixelsToInches(long pixels) {
	    return ( (double) pixels) / INCHES;
	  }

	  static public long convertInchesToPixels(double inches) {
	    return (long) (inches * INCHES);
	  }

	  static public double convertPixelsToCentimeters(long pixels) {
	    return ( (double) pixels) / CENTIMETERS;
	  }

	  static public long convertCentimetersToPixels(double centimeters) {
	    return (long) (centimeters * CENTIMETERS);
	  }

	  static public double convertPixelsToMillimeters(long pixels) {
	    return ( (double) pixels) / MILLIMETERS;
	  }

	  static public long convertMillimetersToPixels(double millimeters) {
	    return (long) (millimeters * MILLIMETERS);
	  }

	  static public long convertToPixels(double value, double convert) {
	    return (long) (value * convert);
	  }
}

