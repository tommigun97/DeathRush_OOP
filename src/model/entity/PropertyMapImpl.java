package model.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Impl of property map.
 *
 */
public final class PropertyMapImpl implements PropertyMap {

    private final Map<String, Object> m = new HashMap<>();

    @Override
    public int getIntegerProperty(final String property) {
        return (int) m.get(property);
    }

    @Override
    public double getDoubleProperty(final String property) {
        return (double) m.get(property);
    }

    @Override
    public boolean getBooleanProperty(final String property) {
        return (boolean) m.get(property);
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
        m.remove(property);

    }

    @Override
    public Object getObjectProperty(final String property) {
        return m.get(property);
    }

    @Override
    public void putProperty(final String property, final Object value) {
        m.put(property, value);

    }

}
