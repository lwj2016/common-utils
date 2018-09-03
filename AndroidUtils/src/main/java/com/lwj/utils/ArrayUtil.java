package com.lwj.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.IntFunction;

/**
 * Created by lwj on 2018/8/2.
 * lwjfork@gmail.com
 */

public class ArrayUtil {


    public static void main(final String[] args) {
        Integer[] array = newArrayByDefault(10, 1);
        for (Integer s : array) {
            System.out.println(s);
        }
        convertIntElements(new ElementsConvert<Integer>() {
            @Override
            public Integer apply(int value, Integer... array) {
                return value;
            }
        }, array);

        for (Integer s : array) {
            System.out.println(s);
        }

        String[] result1 = convertElements2String(new ElementsConverts<Integer, String>() {
            @Override
            public String apply(int value, Integer... array) {
                return array[value] + "ddd";
            }
        }, array);
        for (String s : result1) {
            System.out.println(s);
        }

    }


    public static <T> List<T> array2List(T... objects) {
        return CollectionUtil.array2List(objects);
    }

    public static <T> ArrayList<T> array2ArrayList(T... objects) {
        return CollectionUtil.array2ArrayList(objects);
    }


    public interface ElementsConvert<R> extends ElementsConverts<R, R> {
    }


    public interface ElementsConverts<R, T> {
        /**
         * Applies this function to the given argument.
         *
         * @param value the function argument
         * @return the function result
         */
        @SuppressWarnings("all")
        T apply(int value, R... array);
    }


    @SafeVarargs
    public static <T> T[] convertElements(final ElementsConvert<T> generator, final T... array) {
        if (OSUtils.hasN_24()) {
            Arrays.setAll(array, new IntFunction<T>() {
                @Override
                public T apply(int value) {
                    return generator.apply(value, array);
                }
            });
        } else if (OSUtils.hasKitKat_19()) {
            Objects.requireNonNull(generator);
            for (int i = 0; i < array.length; i++)
                array[i] = generator.apply(i, array);
        } else {
            if (generator == null)
                throw new NullPointerException();
            for (int i = 0; i < array.length; i++)
                array[i] = generator.apply(i, array);
        }

        return array;
    }


    public static Integer[] convertIntElements(final ElementsConvert<Integer> generator, final Integer... array) {
        if (OSUtils.hasN_24()) {
            Arrays.setAll(array, new IntFunction<Integer>() {
                @Override
                public Integer apply(int value) {
                    return generator.apply(value, array);
                }
            });
        } else if (OSUtils.hasKitKat_19()) {
            Objects.requireNonNull(generator);
            for (int i = 0; i < array.length; i++)
                array[i] = generator.apply(i, array);
        } else {
            if (generator == null)
                throw new NullPointerException();
            for (int i = 0; i < array.length; i++)
                array[i] = generator.apply(i, array);
        }

        return array;
    }


    public static <Double> Double[] convertDoubleElements(final ElementsConvert<Double> generator, final Double... array) {
        if (OSUtils.hasN_24()) {
            Arrays.setAll(array, new IntFunction<Double>() {
                @Override
                public Double apply(int value) {
                    return generator.apply(value, array);
                }
            });
        } else if (OSUtils.hasKitKat_19()) {
            Objects.requireNonNull(generator);
            for (int i = 0; i < array.length; i++)
                array[i] = generator.apply(i, array);
        } else {
            if (generator == null)
                throw new NullPointerException();
            for (int i = 0; i < array.length; i++)
                array[i] = generator.apply(i, array);
        }

        return array;
    }


    public static <String> String[] convertStringElements(final ElementsConvert<String> generator, final String... array) {
        if (OSUtils.hasN_24()) {
            Arrays.setAll(array, new IntFunction<String>() {
                @Override
                public String apply(int value) {
                    return generator.apply(value, array);
                }
            });
        } else if (OSUtils.hasKitKat_19()) {
            Objects.requireNonNull(generator);
            for (int i = 0; i < array.length; i++)
                array[i] = generator.apply(i, array);
        } else {
            if (generator == null)
                throw new NullPointerException();
            for (int i = 0; i < array.length; i++)
                array[i] = generator.apply(i, array);
        }

        return array;
    }


    public static <Float> Float[] convertFloatElements(final ElementsConvert<Float> generator, final Float... array) {
        if (OSUtils.hasN_24()) {
            Arrays.setAll(array, new IntFunction<Float>() {
                @Override
                public Float apply(int value) {
                    return generator.apply(value, array);
                }
            });
        } else if (OSUtils.hasKitKat_19()) {
            Objects.requireNonNull(generator);
            for (int i = 0; i < array.length; i++)
                array[i] = generator.apply(i, array);
        } else {
            if (generator == null)
                throw new NullPointerException();
            for (int i = 0; i < array.length; i++)
                array[i] = generator.apply(i, array);
        }

        return array;
    }


    @SafeVarargs
    @SuppressWarnings("unchecked")
    public static <R, T> T[] convertElements2Obj(final ElementsConverts<R, T> generator, Class<T> targetClass, final R... array) {
        if (generator == null)
            throw new NullPointerException();
        int length = array.length;
        T[] resultArray = (T[]) Array.newInstance(targetClass, length);
        for (int i = 0; i < length; i++)
            resultArray[i] = generator.apply(i, array);
        return resultArray;
    }


    @SafeVarargs
    @SuppressWarnings("unchecked")
    public static <R> Integer[] convertElements2Int(final ElementsConverts<R, Integer> generator, final R... array) {

        return convertElements2Obj(generator, Integer.class, array);
    }


    @SafeVarargs
    @SuppressWarnings("unchecked")
    public static <R> Byte[] convertElements2Byte(final ElementsConverts<R, Byte> generator, final R... array) {

        return convertElements2Obj(generator, Byte.class, array);
    }


    @SafeVarargs
    @SuppressWarnings("unchecked")
    public static <R> Double[] convertElements2Double(final ElementsConverts<R, Double> generator, final R... array) {

        return convertElements2Obj(generator, Double.class, array);
    }


    @SafeVarargs
    @SuppressWarnings("unchecked")
    public static <R> Float[] convertElements2Float(final ElementsConverts<R, Float> generator, final R... array) {

        return convertElements2Obj(generator, Float.class, array);
    }


    @SafeVarargs
    @SuppressWarnings("unchecked")
    public static <R> Long[] convertElements2Long(final ElementsConverts<R, Long> generator, final R... array) {

        return convertElements2Obj(generator, Long.class, array);
    }


    @SafeVarargs
    @SuppressWarnings("unchecked")
    public static <R> Short[] convertElements2Short(final ElementsConverts<R, Short> generator, final R... array) {

        return convertElements2Obj(generator, Short.class, array);
    }


    @SafeVarargs
    @SuppressWarnings("unchecked")
    public static <R> String[] convertElements2String(final ElementsConverts<R, String> generator, final R... array) {

        return convertElements2Obj(generator, String.class, array);
    }


    public static int[] sort(int... array) {
        Arrays.sort(array);
        return array;
    }


    public static int[] sort(int fromIndex, int toIndex, int... array) {
        Arrays.sort(array, fromIndex, toIndex);
        return array;
    }

    public static double[] sort(double... array) {
        Arrays.sort(array);
        return array;
    }

    public static double[] sort(int fromIndex, int toIndex, double... array) {
        Arrays.sort(array, fromIndex, toIndex);
        return array;
    }

    public static float[] sort(float... array) {
        Arrays.sort(array);
        return array;
    }

    public static float[] sort(int fromIndex, int toIndex, float... array) {
        Arrays.sort(array, fromIndex, toIndex);
        return array;
    }

    public static long[] sort(long... array) {
        Arrays.sort(array);
        return array;
    }

    public static long[] sort(int fromIndex, int toIndex, long... array) {
        Arrays.sort(array, fromIndex, toIndex);
        return array;
    }

    public static short[] sort(short... array) {
        Arrays.sort(array);
        return array;
    }

    public static short[] sort(int fromIndex, int toIndex, short... array) {
        Arrays.sort(array, fromIndex, toIndex);
        return array;
    }


    public static byte[] sort(byte... array) {
        Arrays.sort(array);
        return array;
    }

    public static byte[] sort(int fromIndex, int toIndex, byte... array) {
        Arrays.sort(array, fromIndex, toIndex);
        return array;
    }


    public static <T> T[] sort(Comparator<? super T> c, T... array) {
        Arrays.sort(array, c);
        return array;
    }


    public static <T> T[] sort(Comparator<? super T> c, int fromIndex, int toIndex, T... array) {
        Arrays.sort(array, fromIndex, toIndex, c);
        return array;
    }

    public static <T> int binarySearch(T key, Comparator<? super T> c, T... array) {
        return Arrays.binarySearch(array, key, c);
    }


    public static <T> T[] fill(T value, T... array) {
        for (int i = 0, len = array.length; i < len; i++)
            array[i] = value;
        return array;
    }


    public static <T> T[] fill(T value, int fromIndex, int endIndex, T... array) {
        int realEndIndex = Math.min(endIndex, array.length - 1);
        for (int i = fromIndex; i <= realEndIndex; i++)
            array[i] = value;
        return array;
    }

    /**
     * create a Array
     *
     * @param <T>    the type of element
     * @param length the length of array
     * @param value  the default value
     * @return the t [ ]
     * @author Created by liuwenjie on 2018/08/03 14:56
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] newArrayByDefault(int length, T value) {
        return fill(value, newArray(length, (Class<T>) value.getClass()));
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] newArray(int length, Class<T> clazz) {
        return (T[]) Array.newInstance(clazz, length);
    }

    @SuppressWarnings("unchecked")
    public static String[] newStrArray(int length) {
        return newArray(length, String.class);
    }


    @SuppressWarnings("unchecked")
    public static Integer[] newIntegeArray(int length) {
        return newArray(length, Integer.class);
    }


    @SuppressWarnings("unchecked")
    public static Double[] newDoubleStrArray(int length) {
        return newArray(length, Double.class);
    }


    @SuppressWarnings("unchecked")
    public static Float[] newFloatArray(int length) {
        return newArray(length, Float.class);
    }


    @SuppressWarnings("unchecked")
    public static Short[] newShortArray(int length) {
        return newArray(length, Short.class);
    }


    @SuppressWarnings("unchecked")
    public static Byte[] newByteStrArray(int length) {
        return newArray(length, Byte.class);
    }


    public static boolean contains(Object[] array, Object object) {
        return Arrays.binarySearch(array, object) >= 0;
    }

    public static boolean contains(int[] array, int object) {
        return Arrays.binarySearch(array, object) >= 0;
    }

    public static boolean contains(String[] array, String object) {
        return Arrays.binarySearch(array, object) >= 0;
    }

    public static int indexOf(Object[] array, Object object) {
        return Arrays.binarySearch(array, object);
    }

    public static int indexOf(int[] array, int object) {
        return Arrays.binarySearch(array, object);
    }

    public static int indexOf(String[] array, String object) {
        return Arrays.binarySearch(array, object);
    }


}
