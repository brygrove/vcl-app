package com.vcl.util;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vcl.borrower.Borrower;

public class GenericsClassUtil<T> {

	private Class<T> genericClass;
	
	@SuppressWarnings("unchecked")
	public Class<T> getGenericClass() {
		
		if (genericClass == null) {
			genericClass = (Class<T>) getTypeArguments(GenericsClassUtil.class, getClass()).get(0);
		}
		
		return genericClass;
	}
	
	/**
	 * Get the underlying class for a type, or null if the type is a variable
	 * type.
	 * 
	 * @param type
	 *            the type
	 * @return the underlying class
	 */
	public static Class<?> getClass(Type type) {
		if (type instanceof Class) {
			return (Class) type;
		} else if (type instanceof ParameterizedType) {
			return getClass(((ParameterizedType) type).getRawType());
		} else if (type instanceof GenericArrayType) {
			Type componentType = ((GenericArrayType) type).getGenericComponentType();
			Class<?> componentClass = getClass(componentType);
			if (componentClass != null) {
				return Array.newInstance(componentClass, 0).getClass();
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	/**
	 * Get the actual type arguments a child class has used to extend a generic
	 * base class.
	 * 
	 * @param baseClass
	 *            the base class
	 * @param childClass
	 *            the child class
	 * @return a list of the raw classes for the actual type arguments.
	 */
	public static <T> List<Class<?>> getTypeArguments(Class<T> baseClass, Class<? extends T> childClass) {
		Map<Type, Type> resolvedTypes = new HashMap<Type, Type>();
		Type type = childClass;
		// start walking up the inheritance hierarchy until we hit baseClass
		while (!getClass(type).equals(baseClass)) {
			if (type instanceof Class) {
				// there is no useful information for us in raw types, so just
				// keep going.
				type = ((Class) type).getGenericSuperclass();
			} else {
				ParameterizedType parameterizedType = (ParameterizedType) type;
				Class<?> rawType = (Class) parameterizedType.getRawType();

				Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
				TypeVariable<?>[] typeParameters = rawType.getTypeParameters();
				for (int i = 0; i < actualTypeArguments.length; i++) {
					resolvedTypes.put(typeParameters[i], actualTypeArguments[i]);
				}

				if (!rawType.equals(baseClass)) {
					type = rawType.getGenericSuperclass();
				}
			}
		}

		// finally, for each actual type argument provided to baseClass,
		// determine (if possible)
		// the raw class for that type argument.
		Type[] actualTypeArguments;
		if (type instanceof Class) {
			actualTypeArguments = ((Class) type).getTypeParameters();
		} else {
			actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
		}
		List<Class<?>> typeArgumentsAsClasses = new ArrayList<Class<?>>();
		// resolve types by chasing down type variables.
		for (Type baseType : actualTypeArguments) {
			while (resolvedTypes.containsKey(baseType)) {
				baseType = resolvedTypes.get(baseType);
			}
			typeArgumentsAsClasses.add(getClass(baseType));
		}
		return typeArgumentsAsClasses;
	}
	
	interface Model<M> {
		
	}
	
	static abstract class A<M> implements Model<M> {
		public Class<?> getModelClass() {
			/*Type superclass = getClass().getGenericSuperclass();

			// make sure it's a parameterized type (could be a proxy)
			if (superclass instanceof ParameterizedType) {
				return (Class<?>) ((ParameterizedType) superclass).getActualTypeArguments()[0];
			}

			return null;*/
			
			return getTypeArguments(A.class, getClass()).get(0);
		}
	}
	
	static class B<T> extends A<T> {
		
	}
	

	public static void main(String[] args){
		B<Borrower> b = new B<Borrower>();
		System.out.println(b.getModelClass());
		
		Type mySuperClass = b.getClass().getGenericSuperclass();
		Type tType =  ((ParameterizedType)mySuperClass).getActualTypeArguments()[0];
		System.out.println(tType);
		
		
	}
}
