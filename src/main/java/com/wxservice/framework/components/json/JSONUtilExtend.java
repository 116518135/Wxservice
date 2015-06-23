package com.wxservice.framework.components.json;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.ezmorph.MorphUtils;
import net.sf.ezmorph.MorpherRegistry;
import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.ezmorph.bean.MorphDynaClass;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONFunction;
import net.sf.json.JSONNull;
import net.sf.json.JSONString;
import net.sf.json.JsonConfig;
import net.sf.json.regexp.RegexpMatcher;
import net.sf.json.regexp.RegexpUtils;

import org.apache.commons.beanutils.DynaBean;

final class JSONUtilExtend {
   /** Constant for char " */
   public static final String DOUBLE_QUOTE = "\"";
   /** Constant for char ' */
   public static final String SINGLE_QUOTE = "'";

   private static RegexpMatcher FUNCTION_HEADER_MATCHER;
   private static final String FUNCTION_HEADER_PATTERN = "^function[ ]?\\(.*\\)$";
   private static RegexpMatcher FUNCTION_MACTHER;
   private static RegexpMatcher FUNCTION_PARAMS_MATCHER;
   private static final String FUNCTION_PARAMS_PATTERN = "^function[ ]?\\((.*?)\\)$";
   private static final String FUNCTION_PATTERN = "^function[ ]?\\(.*\\)[ ]?\\{.*\\}$";

   private static final MorpherRegistry morpherRegistry = new MorpherRegistry();

   static{
      FUNCTION_HEADER_MATCHER = RegexpUtils.getMatcher( FUNCTION_HEADER_PATTERN );
      FUNCTION_PARAMS_MATCHER = RegexpUtils.getMatcher( FUNCTION_PARAMS_PATTERN );
      FUNCTION_MACTHER = RegexpUtils.getMatcher( FUNCTION_PATTERN );

      // register standard morphers
      MorphUtils.registerStandardMorphers( morpherRegistry );
   }

   /**
    * Transformes the string into a valid Java Identifier.<br>
    * The default strategy is JavaIdentifierTransformer.NOOP
    *
    * @throws JSONException if the string can not be transformed.
    */
   public static String convertToJavaIdentifier( String key ) {
      return convertToJavaIdentifier( key, new JsonConfig() );
   }

   /**
    * Transformes the string into a valid Java Identifier.<br>
    * The default strategy is JavaIdentifierTransformer.NOOP
    *
    * @throws JSONException if the string can not be transformed.
    */
   public static String convertToJavaIdentifier( String key, JsonConfig jsonConfig ) {
      try{
         return jsonConfig.getJavaIdentifierTransformer()
               .transformToJavaIdentifier( key );
      }catch( JSONException jsone ){
         throw jsone;
      }catch( Exception e ){
         throw new JSONException( e );
      }
   }

   /**
    * Produce a string from a double. The string "null" will be returned if the
    * number is not finite.
    *
    * @param d A double.
    * @return A String.
    */
   public static String doubleToString( double d ) {
      if( Double.isInfinite( d ) || Double.isNaN( d ) ){
         return "null";
      }

      // Shave off trailing zeros and decimal point, if possible.

      String s = Double.toString( d );
      if( s.indexOf( '.' ) > 0 && s.indexOf( 'e' ) < 0 && s.indexOf( 'E' ) < 0 ){
         while( s.endsWith( "0" ) ){
            s = s.substring( 0, s.length() - 1 );
         }
         if( s.endsWith( "." ) ){
            s = s.substring( 0, s.length() - 1 );
         }
      }
      return s;
   }

   /**
    * Returns the params of a function literal.
    */
   public static String getFunctionParams( String function ) {
      return FUNCTION_PARAMS_MATCHER.getGroupIfMatches( function, 1 );
   }

   /**
    * Returns the inner-most component type of an Array.
    */
   public static Class getInnerComponentType( Class type ) {
      if( !type.isArray() ){
         return type;
      }
      return getInnerComponentType( type.getComponentType() );
   }

   /**
    * Returns the singleton MorpherRegistry.
    */
   public static MorpherRegistry getMorpherRegistry() {
      return morpherRegistry;
   }

   /**
    * Creates a Map with all the properties of the JSONObject.
    */
   public static Map getProperties( JSONObjectExtend JSONObjectExtend ) {
      Map properties = new HashMap();
      for( Iterator keys = JSONObjectExtend.keys(); keys.hasNext(); ){
         String key = (String) keys.next();
         /*
          * String parsedKey = key; if( !JSONUtils.isJavaIdentifier( parsedKey ) ){
          * parsedKey = JSONUtils.convertToJavaIdentifier( key ); }
          */
         properties.put( key, getTypeClass( JSONObjectExtend.get( key ) ) );
      }
      return properties;
   }

   /**
    * Returns the JSON type.<br>
    * Values are Object, String, Boolean, Number(subclasses) &amp; JSONFunction.
    */
   public static Class getTypeClass( Object obj ) {
      if( isNull( obj ) ){
         return Object.class;
      }else if( isArray( obj ) ){
         return List.class;
      }else if( isFunction( obj ) ){
         return JSONFunction.class;
      }else if( isBoolean( obj ) ){
         return Boolean.class;
      }else if( isNumber( obj ) ){
         Number n = (Number) obj;
         if( isInteger( n ) ){
            return Integer.class;
         }else if( isLong( n ) ){
            return Long.class;
         }else if( isFloat( n ) ){
            return Float.class;
         }else if( isBigInteger( n ) ){
            return BigInteger.class;
         }else if( isBigDecimal( n ) ){
            return BigDecimal.class;
         }else if( isDouble( n ) ){
            return Double.class;
         }else{
            throw new JSONException( "Unsupported type" );
         }
      }else if( isString( obj ) ){
         return String.class;
      }else if( isObject( obj ) ){
         return Object.class;
      }else{
         throw new JSONException( "Unsupported type" );
      }
   }

   /**
    * Returns the hashcode of value.<br>
    * If null it will return JSONNull.getInstance().hashCode().<br>
    * If value is JSON, JSONFunction or String, value.hashCode is returned,
    * otherwise the value is transformed to a String an its hashcode is
    * returned.
    */
   public static int hashCode( Object value ) {
      if( value == null ){
         return JSONNull.getInstance()
               .hashCode();
      }else if( value instanceof JSON || value instanceof String || value instanceof JSONFunction ){
         return value.hashCode();
      }else{
         return String.valueOf( value )
               .hashCode();
      }
   }

   /**
    * Tests if a Class represents an array or Collection.
    */
   public static boolean isArray( Class clazz ) {
      return clazz != null
            && (clazz.isArray() || Collection.class.isAssignableFrom( clazz ) || (JSONArray.class.isAssignableFrom( clazz )));
   }

   /**
    * Tests if obj is an array or Collection.
    */
   public static boolean isArray( Object obj ) {
      if( (obj != null && obj.getClass()
            .isArray()) || (obj instanceof Collection) || (obj instanceof JSONArray) ){
         return true;
      }
      return false;
   }

   /**
    * Tests if Class represents a Boolean or primitive boolean
    */
   public static boolean isBoolean( Class clazz ) {
      return clazz != null
            && (Boolean.TYPE.isAssignableFrom( clazz ) || Boolean.class.isAssignableFrom( clazz ));
   }

   /**
    * Tests if obj is a Boolean or primitive boolean
    */
   public static boolean isBoolean( Object obj ) {
      if( (obj instanceof Boolean) || (obj != null && obj.getClass() == Boolean.TYPE) ){
         return true;
      }
      return false;
   }

   /**
    * Tests if Class represents a primitive double or wrapper.<br>
    */
   public static boolean isDouble( Class clazz ) {
      return clazz != null
            && (Double.TYPE.isAssignableFrom( clazz ) || Double.class.isAssignableFrom( clazz ));
   }

   /**
    * Tests if obj is javaScript function.<br>
    * Obj must be a non-null String and match <nowrap>"^function[ ]?\\(.*\\)[
    * ]?\\{.*\\}$"</nowrap>
    */
   public static boolean isFunction( Object obj ) {
      if( obj instanceof String ){
         String str = (String) obj;
         return FUNCTION_MACTHER.matches( str );
      }
      if( obj instanceof JSONFunction ){
         return true;
      }
      return false;
   }

   /**
    * Tests if obj is javaScript function header.<br>
    * Obj must be a non-null String and match "^function[ ]?\\(.*\\)$"
    */
   public static boolean isFunctionHeader( Object obj ) {
      if( obj instanceof String ){
         String str = (String) obj;
         return FUNCTION_HEADER_MATCHER.matches( str );
      }
      return false;
   }

   /**
    * Returns trus if str represents a valid Java identifier.
    */
   public static boolean isJavaIdentifier( String str ) {
      if( str.length() == 0 || !Character.isJavaIdentifierStart( str.charAt( 0 ) ) ){
         return false;
      }
      for( int i = 1; i < str.length(); i++ ){
         if( !Character.isJavaIdentifierPart( str.charAt( i ) ) ){
            return false;
         }
      }
      return true;
   }

   /**
    * Tests if the obj is a javaScript null.
    */
   public static boolean isNull( Object obj ) {
      if( obj instanceof JSONObjectExtend ){
         return ((JSONObjectExtend) obj).isNullObject();
      }
      return JSONNull.getInstance()
            .equals( obj );
   }

   /**
    * Tests if Class represents a primitive number or wrapper.<br>
    */
   public static boolean isNumber( Class clazz ) {
      return clazz != null
            && (Byte.TYPE.isAssignableFrom( clazz ) || Short.TYPE.isAssignableFrom( clazz )
                  || Integer.TYPE.isAssignableFrom( clazz ) || Long.TYPE.isAssignableFrom( clazz )
                  || Float.TYPE.isAssignableFrom( clazz ) || Double.TYPE.isAssignableFrom( clazz ) || Number.class.isAssignableFrom( clazz ));
   }

   /**
    * Tests if obj is a primitive number or wrapper.<br>
    */
   public static boolean isNumber( Object obj ) {
      if( (obj != null && obj.getClass() == Byte.TYPE)
            || (obj != null && obj.getClass() == Short.TYPE)
            || (obj != null && obj.getClass() == Integer.TYPE)
            || (obj != null && obj.getClass() == Long.TYPE)
            || (obj != null && obj.getClass() == Float.TYPE)
            || (obj != null && obj.getClass() == Double.TYPE) ){
         return true;
      }

      return obj instanceof Number;
   }

   /**
    * Tests if obj is not a boolean, number, string or array.
    */
   public static boolean isObject( Object obj ) {
      return !isNumber( obj ) && !isString( obj ) && !isBoolean( obj ) && !isArray( obj )
            && !isFunction( obj ) || isNull( obj );
   }

   /**
    * Tests if Class represents a String or a char
    */
   public static boolean isString( Class clazz ) {
      return clazz != null
            && (String.class.isAssignableFrom( clazz ) || (Character.TYPE.isAssignableFrom( clazz ) || Character.class.isAssignableFrom( clazz )));
   }

   /**
    * Tests if obj is a String or a char
    */
   public static boolean isString( Object obj ) {
      if( (obj instanceof String)
            || (obj instanceof Character)
            || (obj != null && (obj.getClass() == Character.TYPE || String.class.isAssignableFrom( obj.getClass() ))) ){
         return true;
      }
      return false;
   }

   /**
    * Tests if the String possibly represents a valid JSON String.<br>
    * Valid JSON strings are:
    * <ul>
    * <li>"null"</li>
    * <li>starts with "[" and ends with "]"</li>
    * <li>starts with "{" and ends with "}"</li>
    * </ul>
    */
   public static boolean mayBeJSON( String string ) {
      return string != null
            && ("null".equalsIgnoreCase( string )
                  || (string.startsWith( "[" ) && string.endsWith( "]" )) || (string.startsWith( "{" ) && string.endsWith( "}" )));
   }

   /**
    * Creates a new MorphDynaBean from a JSONObjectExtend. The MorphDynaBean will have
    * all the properties of the original JSONObjectExtend with the most accurate type.
    * Values of properties are not copied.
    */
   public static DynaBean newDynaBean( JSONObjectExtend JSONObjectExtend ) {
      return newDynaBean( JSONObjectExtend, new JsonConfig() );
   }

   /**
    * Creates a new MorphDynaBean from a JSONObjectExtend. The MorphDynaBean will have
    * all the properties of the original JSONObjectExtend with the most accurate type.
    * Values of properties are not copied.
    */
   public static DynaBean newDynaBean( JSONObjectExtend JSONObjectExtend, JsonConfig jsonConfig ) {
      Map props = getProperties( JSONObjectExtend );
      for( Iterator entries = props.entrySet()
            .iterator(); entries.hasNext(); ){
         Map.Entry entry = (Map.Entry) entries.next();
         String key = (String) entry.getKey();
         if( !JSONUtilExtend.isJavaIdentifier( key ) ){
            String parsedKey = JSONUtilExtend.convertToJavaIdentifier( key, jsonConfig );
            if( parsedKey.compareTo( key ) != 0 ){
               props.put( parsedKey, props.remove( key ) );
            }
         }
      }
      MorphDynaClass dynaClass = new MorphDynaClass( props );
      MorphDynaBean dynaBean = null;
      try{
         dynaBean = (MorphDynaBean) dynaClass.newInstance();
         dynaBean.setDynaBeanClass( dynaClass );
      }catch( Exception e ){
         throw new JSONException( e );
      }
      return dynaBean;
   }

   /**
    * Produce a string from a Number.
    *
    * @param n A Number
    * @return A String.
    * @throws JSONException If n is a non-finite number.
    */
   public static String numberToString( Number n ) {
      if( n == null ){
         throw new JSONException( "Null pointer" );
      }
      testValidity( n );

      // Shave off trailing zeros and decimal point, if possible.

      String s = n.toString();
      if( s.indexOf( '.' ) > 0 && s.indexOf( 'e' ) < 0 && s.indexOf( 'E' ) < 0 ){
         while( s.endsWith( "0" ) ){
            s = s.substring( 0, s.length() - 1 );
         }
         if( s.endsWith( "." ) ){
            s = s.substring( 0, s.length() - 1 );
         }
      }
      return s;
   }

   /**
    * Produce a string in double quotes with backslash sequences in all the
    * right places. A backslash will be inserted within </, allowing JSON text
    * to be delivered in HTML. In JSON text, a string cannot contain a control
    * character or an unescaped quote or backslash.<br>
    * <strong>CAUTION:</strong> if <code>string</code> represents a
    * javascript function, translation of characters will not take place. This
    * will produce a non-conformant JSON text.
    *
    * @param string A String
    * @return A String correctly formatted for insertion in a JSON text.
    */
   public static String quote( String string ) {
      if( isFunction( string ) ){
         return string;
      }
      if( string == null || string.length() == 0 ){
         return "\"\"";
      }

      char b;
      char c = 0;
      int i;
      int len = string.length();
      StringBuffer sb = new StringBuffer( len + 4 );
      String t;

      sb.append( '"' );
      for( i = 0; i < len; i += 1 ){
         b = c;
         c = string.charAt( i );
         switch( c ){
            case '\\':
            case '"':
               sb.append( '\\' );
               sb.append( c );
               break;
            case '/':
               if( b == '<' ){
                  sb.append( '\\' );
               }
               sb.append( c );
               break;
            case '\b':
               sb.append( "\\b" );
               break;
            case '\t':
               sb.append( "\\t" );
               break;
            case '\n':
               sb.append( "\\n" );
               break;
            case '\f':
               sb.append( "\\f" );
               break;
            case '\r':
               sb.append( "\\r" );
               break;
            default:
               if( c < ' ' ){
                  t = "000" + Integer.toHexString( c );
                  sb.append( "\\u" )
                        .append( t.substring( t.length() - 4 ) );
               }else{
                  sb.append( c );
               }
         }
      }
      sb.append( '"' );
      return sb.toString();
   }

   /**
    * Strips any single-quotes or double-quotes from boths sides of the string.
    */
   public static String stripQuotes( String input ) {
      if( input.length() < 2 ){
         return input;
      }else if( input.startsWith( SINGLE_QUOTE ) && input.endsWith( SINGLE_QUOTE ) ){
         return input.substring( 1, input.length() - 1 );
      }else if( input.startsWith( DOUBLE_QUOTE ) && input.endsWith( DOUBLE_QUOTE ) ){
         return input.substring( 1, input.length() - 1 );
      }else{
         return input;
      }
   }

   /**
    * Throw an exception if the object is an NaN or infinite number.
    *
    * @param o The object to test.
    * @throws JSONException If o is a non-finite number.
    */
   public static void testValidity( Object o ) {
      if( o != null ){
         if( o instanceof Double ){
            if( ((Double) o).isInfinite() || ((Double) o).isNaN() ){
               throw new JSONException( "JSON does not allow non-finite numbers" );
            }
         }else if( o instanceof Float ){
            if( ((Float) o).isInfinite() || ((Float) o).isNaN() ){
               throw new JSONException( "JSON does not allow non-finite numbers." );
            }
         }else if( o instanceof BigDecimal || o instanceof BigInteger ){
            // ok
            return;
         }
      }
   }

   /**
    * Transforms a Number into a valid javascript number.<br>
    * Float gets promoted to Double.<br>
    * Byte and Short get promoted to Integer.<br>
    * Long gets downgraded to Integer if possible.<br>
    */
   public static Number transformNumber( Number input ) {
      if( input instanceof Float ){
         return new Double( input.doubleValue() );
      }else if( input instanceof Short ){
         return new Integer( input.intValue() );
      }else if( input instanceof Byte ){
         return new Integer( input.intValue() );
      }else if( input instanceof Long ){
         Long max = new Long( Integer.MAX_VALUE );
         if( input.longValue() <= max.longValue() && input.longValue() >= Integer.MIN_VALUE ){
            return new Integer( input.intValue() );
         }
      }

      return input;
   }

   /**
    * Make a JSON text of an Object value. If the object has an
    * value.toJSONString() method, then that method will be used to produce the
    * JSON text. The method is required to produce a strictly conforming text.
    * If the object does not contain a toJSONString method (which is the most
    * common case), then a text will be produced by the rules.
    * <p>
    * Warning: This method assumes that the data structure is acyclical.
    *
    * @param value The value to be serialized.
    * @return a printable, displayable, transmittable representation of the
    *         object, beginning with <code>{</code>&nbsp;<small>(left brace)</small>
    *         and ending with <code>}</code>&nbsp;<small>(right brace)</small>.
    * @throws JSONException If the value is or contains an invalid number.
    */
   public static String valueToString( Object value ) {
      if( value == null || isNull( value ) ){
         return "null";
      }
      if( value instanceof JSONFunction ){
         return ((JSONFunction) value).toString();
      }
      if( value instanceof JSONString ){
         Object o;
         try{
            o = ((JSONString) value).toJSONString();
         }catch( Exception e ){
            throw new JSONException( e );
         }
         if( o instanceof String ){
            return (String) o;
         }
         throw new JSONException( "Bad value from toJSONString: " + o );
      }
      if( value instanceof Number ){
         return numberToString( (Number) value );
      }
      if( value instanceof Boolean || value instanceof JSONObjectExtend || value instanceof JSONArray ){
         return value.toString();
      }
      return quote( value.toString() );
   }

   /**
    * Make a prettyprinted JSON text of an object value.
    * <p>
    * Warning: This method assumes that the data structure is acyclical.
    *
    * @param value The value to be serialized.
    * @param indentFactor The number of spaces to add to each level of
    *        indentation.
    * @param indent The indentation of the top level.
    * @return a printable, displayable, transmittable representation of the
    *         object, beginning with <code>{</code>&nbsp;<small>(left brace)</small>
    *         and ending with <code>}</code>&nbsp;<small>(right brace)</small>.
    * @throws JSONException If the object contains an invalid number.
    */
   public static String valueToString( Object value, int indentFactor, int indent ) {
      if( value == null || isNull( value ) ){
         return "null";
      }
      if( value instanceof JSONFunction ){
         return ((JSONFunction) value).toString();
      }
      if( value instanceof JSONString ){
         return ((JSONString) value).toJSONString();
      }
      if( value instanceof Number ){
         return numberToString( (Number) value );
      }
      if( value instanceof Boolean ){
         return value.toString();
      }
      if( value instanceof JSONObjectExtend ){
         return ((JSONObjectExtend) value).toString( indentFactor, indent );
      }
      if( value instanceof JSONArray ){
         return ((JSONArray) value).toString( indentFactor, indent );
      }
      return quote( value.toString() );
   }

   /**
    * Finds out if n represents a BigInteger
    *
    * @return true if n is instanceOf BigInteger or the literal value can be
    *         evaluated as a BigInteger
    */
   private static boolean isBigDecimal( Number n ) {
      if( n instanceof BigDecimal ){
         return true;
      }
      try{
         new BigDecimal( String.valueOf( n ) );
         return true;
      }catch( NumberFormatException e ){
         return false;
      }
   }

   /**
    * Finds out if n represents a BigInteger
    *
    * @return true if n is instanceOf BigInteger or the literal value can be
    *         evaluated as a BigInteger
    */
   private static boolean isBigInteger( Number n ) {
      if( n instanceof BigInteger ){
         return true;
      }
      try{
         new BigInteger( String.valueOf( n ) );
         return true;
      }catch( NumberFormatException e ){
         return false;
      }
   }

   /**
    * Finds out if n represents a Double.
    *
    * @return true if n is instanceOf Double or the literal value can be
    *         evaluated as a Double.
    */
   private static boolean isDouble( Number n ) {
      if( n instanceof Double ){
         return true;
      }
      try{
         double d = Double.parseDouble( String.valueOf( n ) );
         return !Double.isInfinite( d );
      }catch( NumberFormatException e ){
         return false;
      }
   }

   /**
    * Finds out if n represents a Float.
    *
    * @return true if n is instanceOf Float or the literal value can be
    *         evaluated as a Float.
    */
   private static boolean isFloat( Number n ) {
      if( n instanceof Float ){
         return true;
      }
      try{
         float f = Float.parseFloat( String.valueOf( n ) );
         return !Float.isInfinite( f );
      }catch( NumberFormatException e ){
         return false;
      }
   }

   /**
    * Finds out if n represents an Integer.
    *
    * @return true if n is instanceOf Integer or the literal value can be
    *         evaluated as an Integer.
    */
   private static boolean isInteger( Number n ) {
      if( n instanceof Integer ){
         return true;
      }
      try{
         Integer.parseInt( String.valueOf( n ) );
         return true;
      }catch( NumberFormatException e ){
         return false;
      }
   }

   /**
    * Finds out if n represents a Long.
    *
    * @return true if n is instanceOf Long or the literal value can be evaluated
    *         as a Long.
    */
   private static boolean isLong( Number n ) {
      if( n instanceof Long ){
         return true;
      }
      try{
         Long.parseLong( String.valueOf( n ) );
         return true;
      }catch( NumberFormatException e ){
         return false;
      }
   }

   private JSONUtilExtend() {
      super();
   }
}