package com.common.lib.utils;

/**
 * Created by lwj on 2017/11/2.
 * lwjfork@gmail.com
 */

public enum ByteUnit {
    KB {
        @Override
        public double toKbs(double num) {
            return num;
        }

        @Override
        public double toMbs(double num) {
            return num / COMMON_SPACE;
        }

        @Override
        public double toGbs(double num) {
            return num / COMMON_SPACE / COMMON_SPACE;
        }

        @Override
        public double toTbs(double num) {
            return num / COMMON_SPACE / COMMON_SPACE / COMMON_SPACE;
        }

        @Override
        public double convert(double num, ByteUnit byteUnit) {
            return byteUnit.toKbs(num);
        }
    },
    MB {
        @Override
        public double toKbs(double num) {
            return num * COMMON_SPACE;
        }

        @Override
        public double toMbs(double num) {
            return num;
        }

        @Override
        public double toGbs(double num) {
            return num / COMMON_SPACE;
        }

        @Override
        public double toTbs(double num) {
            return num / COMMON_SPACE / COMMON_SPACE;
        }

        @Override
        public double convert(double num, ByteUnit byteUnit) {
            return byteUnit.toMbs(num);
        }
    },
    GB {
        @Override
        public double toKbs(double num) {
            return num * COMMON_SPACE * COMMON_SPACE;
        }

        @Override
        public double toMbs(double num) {
            return num * COMMON_SPACE;
        }

        @Override
        public double toGbs(double num) {
            return num;
        }

        @Override
        public double toTbs(double num) {
            return num / COMMON_SPACE;
        }

        @Override
        public double convert(double num, ByteUnit byteUnit) {
            return byteUnit.toGbs(num);
        }
    },
    TB {
        @Override
        public double toKbs(double num) {
            return num * COMMON_SPACE * COMMON_SPACE * COMMON_SPACE;
        }

        @Override
        public double toMbs(double num) {
            return num * COMMON_SPACE * COMMON_SPACE;
        }

        @Override
        public double toGbs(double num) {
            return num * COMMON_SPACE;
        }

        @Override
        public double toTbs(double num) {
            return num;
        }

        @Override
        public double convert(double num, ByteUnit byteUnit) {
            return byteUnit.toTbs(num);
        }
    };


    public double toKbs(double num) {
        throw new AbstractMethodError();
    }

    public double toMbs(double num) {
        throw new AbstractMethodError();
    }

    public double toGbs(double num) {
        throw new AbstractMethodError();
    }

    public double toTbs(double num) {
        throw new AbstractMethodError();
    }

    public double convert(double num, ByteUnit byteUnit) {
        throw new AbstractMethodError();
    }

    public static final double COMMON_SPACE = 1024f;
    public static final double Kb = COMMON_SPACE;
    public static final double Mb = COMMON_SPACE * Kb;
    public static final double Gb = COMMON_SPACE * Mb;
    public static final double Tb = COMMON_SPACE * Gb;
}
