package com.common.lib.utils.log;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by lwj on 2016/3/8.
 * lwjfork@gmail.com
 * Copy from https://github.com/JakeWharton/timber
 */
public class Timber {
    private static final List<Tree> FOREST = new CopyOnWriteArrayList<>();
    private static final Tree TREE_OF_SOULS = new Tree() {


        public void v(String message, Object... args) {
            List forest = Timber.FOREST;
            int i = 0;

            for (int count = forest.size(); i < count; ++i) {
                ((Tree) forest.get(i)).v(message, args);
            }

        }

        public void vT(String tag, String message, Object... args) {
            List forest = Timber.FOREST;
            int i = 0;

            for (int count = forest.size(); i < count; ++i) {
                ((Tree) forest.get(i)).vT(tag, message, args);
            }

        }


        public void v(Throwable t, String message, Object... args) {
            List forest = Timber.FOREST;
            int i = 0;

            for (int count = forest.size(); i < count; ++i) {
                ((Tree) forest.get(i)).v(t, message, args);
            }

        }


        public void vT(String tag, Throwable t, String message, Object... args) {
            List forest = Timber.FOREST;
            int i = 0;

            for (int count = forest.size(); i < count; ++i) {
                ((Tree) forest.get(i)).vT(tag, t, message, args);
            }

        }


        public void d(String message, Object... args) {
            List forest = Timber.FOREST;
            int i = 0;

            for (int count = forest.size(); i < count; ++i) {
                ((Tree) forest.get(i)).d(message, args);
            }

        }

        public void dT(String tag, String message, Object... args) {
            List forest = Timber.FOREST;
            int i = 0;

            for (int count = forest.size(); i < count; ++i) {
                ((Tree) forest.get(i)).dT(tag, message, args);
            }

        }

        public void d(Throwable t, String message, Object... args) {
            List forest = Timber.FOREST;
            int i = 0;

            for (int count = forest.size(); i < count; ++i) {
                ((Tree) forest.get(i)).d(t, message, args);
            }

        }

        public void dT(String tag, Throwable t, String message, Object... args) {
            List forest = Timber.FOREST;
            int i = 0;

            for (int count = forest.size(); i < count; ++i) {
                ((Tree) forest.get(i)).dT(tag, t, message, args);
            }

        }


        public void i(String message, Object... args) {
            List forest = Timber.FOREST;
            int i = 0;

            for (int count = forest.size(); i < count; ++i) {
                ((Tree) forest.get(i)).i(message, args);
            }

        }

        public void iT(String tag, String message, Object... args) {
            List forest = Timber.FOREST;
            int i = 0;

            for (int count = forest.size(); i < count; ++i) {
                ((Tree) forest.get(i)).iT(tag, message, args);
            }

        }

        public void i(Throwable t, String message, Object... args) {
            List forest = Timber.FOREST;
            int i = 0;

            for (int count = forest.size(); i < count; ++i) {
                ((Tree) forest.get(i)).i(t, message, args);
            }

        }

        public void iT(String tag, Throwable t, String message, Object... args) {
            List forest = Timber.FOREST;
            int i = 0;

            for (int count = forest.size(); i < count; ++i) {
                ((Tree) forest.get(i)).iT(tag, t, message, args);
            }

        }

        public void w(String message, Object... args) {
            List forest = Timber.FOREST;
            int i = 0;

            for (int count = forest.size(); i < count; ++i) {
                ((Tree) forest.get(i)).w(message, args);
            }

        }

        public void wT(String tag, String message, Object... args) {
            List forest = Timber.FOREST;
            int i = 0;

            for (int count = forest.size(); i < count; ++i) {
                ((Tree) forest.get(i)).wT(tag, message, args);
            }

        }

        public void w(Throwable t, String message, Object... args) {
            List forest = Timber.FOREST;
            int i = 0;

            for (int count = forest.size(); i < count; ++i) {
                ((Tree) forest.get(i)).w(t, message, args);
            }

        }

        public void wT(String tag, Throwable t, String message, Object... args) {
            List forest = Timber.FOREST;
            int i = 0;

            for (int count = forest.size(); i < count; ++i) {
                ((Tree) forest.get(i)).wT(tag, t, message, args);
            }

        }


        public void e(String message, Object... args) {
            List forest = Timber.FOREST;
            int i = 0;

            for (int count = forest.size(); i < count; ++i) {
                ((Tree) forest.get(i)).e(message, args);
            }

        }


        public void eT(String tag, String message, Object... args) {
            List forest = Timber.FOREST;
            int i = 0;

            for (int count = forest.size(); i < count; ++i) {
                ((Tree) forest.get(i)).eT(tag, message, args);
            }

        }

        public void e(Throwable t, String message, Object... args) {
            List forest = Timber.FOREST;
            int i = 0;

            for (int count = forest.size(); i < count; ++i) {
                ((Tree) forest.get(i)).e(t, message, args);
            }

        }

        public void eT(String tag, Throwable t, String message, Object... args) {
            List forest = Timber.FOREST;
            int i = 0;

            for (int count = forest.size(); i < count; ++i) {
                ((Tree) forest.get(i)).eT(tag, t, message, args);
            }

        }

        public void wtf(String message, Object... args) {
            List forest = Timber.FOREST;
            int i = 0;

            for (int count = forest.size(); i < count; ++i) {
                ((Tree) forest.get(i)).wtf(message, args);
            }

        }


        public void wtfT(String tag, String message, Object... args) {
            List forest = Timber.FOREST;
            int i = 0;

            for (int count = forest.size(); i < count; ++i) {
                ((Tree) forest.get(i)).wtfT(tag, message, args);
            }

        }

        public void wtf(Throwable t, String message, Object... args) {
            List forest = Timber.FOREST;
            int i = 0;

            for (int count = forest.size(); i < count; ++i) {
                ((Tree) forest.get(i)).wtf(t, message, args);
            }

        }

        public void wtfT(String tag, Throwable t, String message, Object... args) {
            List forest = Timber.FOREST;
            int i = 0;

            for (int count = forest.size(); i < count; ++i) {
                ((Tree) forest.get(i)).wtfT(tag, t, message, args);
            }

        }


        protected void log(int priority, String tag, String message, Throwable t) {
            throw new AssertionError("Missing override for log method.");
        }


    };

    public Timber() {
    }

    public static void v(String message, Object... args) {
        TREE_OF_SOULS.v(message, args);
    }

    public static void vT(String tag, String message, Object... args) {
        TREE_OF_SOULS.vT(tag, message, args);
    }

    public static void v(Throwable t, String message, Object... args) {
        TREE_OF_SOULS.v(t, message, args);
    }

    public static void vT(String tag, Throwable t, String message, Object... args) {
        TREE_OF_SOULS.vT(tag, t, message, args);
    }

    public static void d(String message, Object... args) {
        TREE_OF_SOULS.d(message, args);
    }

    public static void dT(String tag, String message, Object... args) {
        TREE_OF_SOULS.dT(tag, message, args);
    }

    public static void d(Throwable t, String message, Object... args) {
        TREE_OF_SOULS.d(t, message, args);
    }

    public static void dT(String tag, Throwable t, String message, Object... args) {
        TREE_OF_SOULS.dT(tag, t, message, args);
    }

    public static void i(String message, Object... args) {
        TREE_OF_SOULS.i(message, args);
    }

    public static void iT(String tag, String message, Object... args) {
        TREE_OF_SOULS.iT(tag, message, args);
    }

    public static void i(Throwable t, String message, Object... args) {
        TREE_OF_SOULS.i(t, message, args);
    }

    public static void iT(String tag, Throwable t, String message, Object... args) {
        TREE_OF_SOULS.iT(tag, t, message, args);
    }


    public static void w(String message, Object... args) {
        TREE_OF_SOULS.w(message, args);
    }

    public static void wT(String tag, String message, Object... args) {
        TREE_OF_SOULS.wT(tag, message, args);
    }

    public static void w(Throwable t, String message, Object... args) {
        TREE_OF_SOULS.w(t, message, args);
    }

    public static void wT(String tag, Throwable t, String message, Object... args) {
        TREE_OF_SOULS.wT(tag, t, message, args);
    }

    public static void e(String message, Object... args) {
        TREE_OF_SOULS.e(message, args);
    }

    public static void eT(String tag, String message, Object... args) {
        TREE_OF_SOULS.eT(tag, message, args);
    }

    public static void e(Throwable t, String message, Object... args) {
        TREE_OF_SOULS.e(t, message, args);
    }

    public static void eT(String tag, Throwable t, String message, Object... args) {
        TREE_OF_SOULS.eT(tag, t, message, args);
    }

    public static void wtf(String message, Object... args) {
        TREE_OF_SOULS.wtf(message, args);
    }

    public static void wtfT(String tag, String message, Object... args) {
        TREE_OF_SOULS.wtfT(tag, message, args);
    }

    public static void wtf(Throwable t, String message, Object... args) {
        TREE_OF_SOULS.wtf(t, message, args);
    }

    public static void wtfT(String tag, Throwable t, String message, Object... args) {
        TREE_OF_SOULS.wtfT(tag, t, message, args);
    }

    public static Tree asTree() {
        return TREE_OF_SOULS;
    }

    public static Tree tag(String tag) {
        List forest = FOREST;
        int i = 0;

        for (int count = forest.size(); i < count; ++i) {
            ((Tree) forest.get(i)).explicitTag.set(tag);
        }

        return TREE_OF_SOULS;
    }

    public static void plant(Tree tree) {
        if (tree == null) {
            throw new NullPointerException("tree == null");
        } else if (tree == TREE_OF_SOULS) {
            throw new IllegalArgumentException("Cannot plant Timber into itself.");
        } else {
            FOREST.add(tree);
        }
    }

    public static void uproot(Tree tree) {
        if (!FOREST.remove(tree)) {
            throw new IllegalArgumentException("Cannot uproot tree which is not planted: " + tree);
        }
    }

    public static void uprootAll() {
        FOREST.clear();
    }
}
