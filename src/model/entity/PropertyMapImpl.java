package model.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Impl of property map.
 *
 */
public final class PropertyMapImpl implements PropertyMap {

    private final Map<String, Object> m = new HashMap<>();
    private static final String NOT_PROPERTY = "Requested inexistent property: ";

    @Override
    public int getIntegerProperty(final String property) {
        if (m.containsKey(property)) {
            return (int) m.get(property);
        } else {
            throw new IllegalArgumentException(NOT_PROPERTY + " [" + property + "]");
        }

    }

    @Override
    public double getDoubleProperty(final String property) {
        if (m.containsKey(property)) {
            return (double) m.get(property);
        } else {
            throw new IllegalArgumentException(NOT_PROPERTY + " [" + property + "]");
        }
    }

    @Override
    public boolean getBooleanProperty(final String property) {
        if (m.containsKey(property)) {
            return (boolean) m.get(property);
        } else {
            throw new IllegalArgumentException(NOT_PROPERTY + " [" + property + "]");
        }
    }

    @Override
    public void putProperty(final String property, final int value) {
        m.put(property, value);

    }

    @Override
    public void putProperty(final String property, final double value) {
        m.put(property, value);
    }

    @Override
    public void putProperty(final String property, final boolean value) {
        m.put(property, value);
    }

    @Override
    public void deleteProperty(final String property) {
        if (m.containsKey(property)) {
            m.remove(property);
        } else {
            throw new IllegalArgumentException(NOT_PROPERTY);
        }

    }

    @Override
    public Object getObjectProperty(final String property) {
        if (m.containsKey(property)) {
            return m.get(property);
        } else {
            throw new IllegalArgumentException(NOT_PROPERTY);
        }
    }

    @Override
    public void putProperty(final String property, final Object value) {
        m.put(property, value);

    }

    @Override
    public void changeProperty(final String property, final int value) {
        if (m.containsKey(property)) {
            m.put(property, value);
        } else {
            throw new IllegalArgumentException(NOT_PROPERTY);
        }

    }

    @Override
    public void changeProperty(final String property, final double value) {
        if (m.containsKey(property)) {
            m.put(property, value);
        } else {
            throw new IllegalArgumentException(NOT_PROPERTY);
        }

    }

    @Override
    public void changeProperty(final String property, final boolean value) {
        if (m.containsKey(property)) {
            m.put(property, value);
        } else {
            throw new IllegalArgumentException(NOT_PROPERTY);
        }

    }

    @Override
    public void changeProperty(final String property, final Object value) {
        if (m.containsKey(property)) {
            m.put(property, value);
        } else {
            throw new IllegalArgumentException(NOT_PROPERTY);
        }

    }

}
