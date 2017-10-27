package com.lwj.utils.log;

/**
 * Created by lwj on 2016/3/8.
 * liuwenjie@goumin.com
 */

import android.util.Log;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Timber {
    private static final List<Tree> FOREST = new CopyOnWriteArrayList();
    private static final Timber.Tree TREE_OF_SOULS = new Timber.Tree() {
        public void v(String message, Object... args) {
            List forest = Timber.FOREST;
            int i = 0;

            for (int count = forest.size(); i < count; ++i) {
                ((Timber.Tree) forest.get(i)).v(message, args);
            }

        }

        public void v(Throwable t, String message, Object... args) {
            List forest = Timber.FOREST;
            int i = 0;

            for (int count = forest.size(); i < count; ++i) {
                ((Timber.Tree) forest.get(i)).v(t, message, args);
            }

        }

        public void d(String message, Object... args) {
            List forest = Timber.FOREST;
            int i = 0;

            for (int count = forest.size(); i < count; ++i) {
                ((Timber.Tree) forest.get(i)).d(message, args);
            }

        }

        public void d(Throwable t, String message, Object... args) {
            List forest = Timber.FOREST;
            int i = 0;

            for (int count = forest.size(); i < count; ++i) {
                ((Timber.Tree) forest.get(i)).d(t, message, args);
            }

        }

        public void i(String message, Object... args) {
            List forest = Timber.FOREST;
            int i = 0;

            for (int count = forest.size(); i < count; ++i) {
                ((Timber.Tree) forest.get(i)).i(message, args);
            }

        }

        public void i(Throwable t, String message, Object... args) {
            List forest = Timber.FOREST;
            int i = 0;

            for (int count = forest.size(); i < count; ++i) {
                ((Timber.Tree) forest.get(i)).i(t, message, args);
            }

        }

        public void w(String message, Object... args) {
            List forest = Timber.FOREST;
            int i = 0;

            for (int count = forest.size(); i < count; ++i) {
                ((Timber.Tree) forest.get(i)).w(message, args);
            }

        }

        public void w(Throwable t, String message, Object... args) {
            List forest = Timber.FOREST;
            int i = 0;

            for (int count = forest.size(); i < count; ++i) {
                ((Timber.Tree) forest.get(i)).w(t, message, args);
            }

        }

        public void e(String message, Object... args) {
            List forest = Timber.FOREST;
            int i = 0;

            for (int count = forest.size(); i < count; ++i) {
                ((Timber.Tree) forest.get(i)).e(message, args);
            }

        }

        public void e(Throwable t, String message, Object... args) {
            List forest = Timber.FOREST;
            int i = 0;

            for (int count = forest.size(); i < count; ++i) {
                ((Timber.Tree) forest.get(i)).e(t, message, args);
            }

        }

        public void wtf(String message, Object... args) {
            List forest = Timber.FOREST;
            int i = 0;

            for (int count = forest.size(); i < count; ++i) {
                ((Timber.Tree) forest.get(i)).wtf(message, args);
            }

        }

        public void wtf(Throwable t, String message, Object... args) {
            List forest = Timber.FOREST;
            int i = 0;

            for (int count = forest.size(); i < count; ++i) {
                ((Timber.Tree) forest.get(i)).wtf(t, message, args);
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

    public static void v(Throwable t, String message, Object... args) {
        TREE_OF_SOULS.v(t, message, args);
    }

    public static void d(String message, Object... args) {
        TREE_OF_SOULS.d(message, args);
    }

    public static void d(Throwable t, String message, Object... args) {
        TREE_OF_SOULS.d(t, message, args);
    }

    public static void i(String message, Object... args) {
        TREE_OF_SOULS.i(message, args);
    }

    public static void i(Throwable t, String message, Object... args) {
        TREE_OF_SOULS.i(t, message, args);
    }

    public static void w(String message, Object... args) {
        TREE_OF_SOULS.w(message, args);
    }

    public static void w(Throwable t, String message, Object... args) {
        TREE_OF_SOULS.w(t, message, args);
    }

    public static void e(String message, Object... args) {
        TREE_OF_SOULS.e(message, args);
    }

    public static void e(Throwable t, String message, Object... args) {
        TREE_OF_SOULS.e(t, message, args);
    }

    public static void wtf(String message, Object... args) {
        TREE_OF_SOULS.wtf(message, args);
    }

    public static void wtf(Throwable t, String message, Object... args) {
        TREE_OF_SOULS.wtf(t, message, args);
    }

    public static Timber.Tree asTree() {
        return TREE_OF_SOULS;
    }

    public static Timber.Tree tag(String tag) {
        List forest = FOREST;
        int i = 0;

        for (int count = forest.size(); i < count; ++i) {
            ((Timber.Tree) forest.get(i)).explicitTag.set(tag);
        }

        return TREE_OF_SOULS;
    }

    public static void plant(Timber.Tree tree) {
        if (tree == null) {
            throw new NullPointerException("tree == null");
        } else if (tree == TREE_OF_SOULS) {
            throw new IllegalArgumentException("Cannot plant Timber into itself.");
        } else {
            FOREST.add(tree);
        }
    }

    public static void uproot(Timber.Tree tree) {
        if (!FOREST.remove(tree)) {
            throw new IllegalArgumentException("Cannot uproot tree which is not planted: " + tree);
        }
    }

    public static void uprootAll() {
        FOREST.clear();
    }

    public static class DebugTree extends Timber.Tree {
        private static final int MAX_LOG_LENGTH = 4000;
        private static final int CALL_STACK_INDEX = 5;
        private static final Pattern ANONYMOUS_CLASS = Pattern.compile("\\$\\d+$");

        public DebugTree() {
        }

        protected String createStackElementTag(StackTraceElement element) {
            String tag = element.getClassName();
            Matcher m = ANONYMOUS_CLASS.matcher(tag);
            if (m.find()) {
                tag = m.replaceAll("");
            }

            return tag.substring(tag.lastIndexOf(46) + 1);
        }

        final String getTag() {
            String tag = super.getTag();
            if (tag != null) {
                return tag;
            } else {
                StackTraceElement[] stackTrace = (new Throwable()).getStackTrace();
                if (stackTrace.length <= 5) {
                    throw new IllegalStateException("Synthetic stacktrace didn\'t have enough elements: are you using proguard?");
                } else {
                    return this.createStackElementTag(stackTrace[5]);
                }
            }
        }

        protected void log(int priority, String tag, String message, Throwable t) {
            if (message.length() < 4000) {
                if (priority == Log.ASSERT) {
                    Log.wtf(tag, message);
                } else {
                    Log.println(priority, tag, message);
                }

            } else {
                int i = 0;

                int end;
                for (int length = message.length(); i < length; i = end + 1) {
                    int newline = message.indexOf(10, i);
                    newline = newline != -1 ? newline : length;

                    do {
                        end = Math.min(newline, i + 4000);
                        String part = message.substring(i, end);
                        if (priority == Log.ASSERT) {
                            Log.wtf(tag, part);
                        } else {
                            Log.println(priority, tag, part);
                        }

                        i = end;
                    } while (end < newline);
                }

            }
        }
    }

    public abstract static class Tree {
        private final ThreadLocal<String> explicitTag = new ThreadLocal();

        public Tree() {
        }

        String getTag() {
            String tag = (String) this.explicitTag.get();
            if (tag != null) {
                this.explicitTag.remove();
            }

            return tag;
        }

        public void v(String message, Object... args) {
            this.prepareLog(Log.VERBOSE, null, message, args);
        }

        public void v(Throwable t, String message, Object... args) {
            this.prepareLog(Log.VERBOSE, t, message, args);
        }

        public void d(String message, Object... args) {
            this.prepareLog(Log.DEBUG, null, message, args);
        }

        public void d(Throwable t, String message, Object... args) {
            this.prepareLog(Log.DEBUG, t, message, args);
        }

        public void i(String message, Object... args) {
            this.prepareLog(Log.INFO, null, message, args);
        }

        public void i(Throwable t, String message, Object... args) {
            this.prepareLog(Log.INFO, t, message, args);
        }

        public void w(String message, Object... args) {
            this.prepareLog(Log.WARN, null, message, args);
        }

        public void w(Throwable t, String message, Object... args) {
            this.prepareLog(Log.WARN, t, message, args);
        }

        public void e(String message, Object... args) {
            this.prepareLog(Log.ERROR, null, message, args);
        }

        public void e(Throwable t, String message, Object... args) {
            this.prepareLog(Log.ERROR, t, message, args);
        }

        public void wtf(String message, Object... args) {
            this.prepareLog(Log.ASSERT, null, message, args);
        }

        public void wtf(Throwable t, String message, Object... args) {
            this.prepareLog(Log.ASSERT, t, message, args);
        }

        private void prepareLog(int priority, Throwable t, String message, Object... args) {
            if (message != null && message.length() == 0) {
                message = null;
            }

            if (message == null) {
                if (t == null) {
                    return;
                }

                message = Log.getStackTraceString(t);
            } else {
                if (args.length > 0) {
                    message = String.format(message, args);
                }

                if (t != null) {
                    message = message + "\n" + Log.getStackTraceString(t);
                }
            }

            this.log(priority, this.getTag(), message, t);
        }

        protected abstract void log(int var1, String var2, String var3, Throwable var4);
    }
}
