package model.entity;

import java.util.Optional;
import model.Location;

/**
 * Implementation of entity interface.
 *
 */
public final class EntitityImpl implements Entity {
    private String image;
    private Location loc;
    private final Optional<Behavior> behavior;
    private final EntityType type;
    private final PropertyMap properties;

    private EntitityImpl(final String image, final Location loc, final Behavior behavior, final EntityType type,
            final PropertyMap properties) {
        super();
        this.image = image;
        this.loc = loc;
        this.behavior = Optional.ofNullable(behavior);
        this.type = type;
        this.properties = properties;
        if (this.behavior.isPresent()) {
            behavior.setEntity(this);
        }
    }

    @Override
    public String toString() {
        return "EntitityImpl [image=" + image + ", loc=" + loc + ", behavior=" + behavior + ", type=" + type
                + ", properties=" + properties + "]";
    }

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public void setImage(final String image) {
        this.image = image;
    }

    @Override
    public Location getLocation() {
        return loc;
    }

    @Override
    public void setLocation(final Location loc) {
        this.loc = loc;
    }

    @Override
    public Optional<Behavior> getBehaviour() {
        return this.behavior;
    }

    @Override
    public EntityType getType() {
        return this.type;
    }

    @Override
    public int getIntegerProperty(final String property) {
        return this.properties.getIntegerProperty(property);
    }

    @Override
    public double getDoubleProperty(final String property) {
        return this.properties.getDoubleProperty(property);
    }

    @Override
    public boolean getBooleanProperty(final String property) {
        return this.properties.getBooleanProperty(property);
    }

    @Override
    public Object getObjectProperty(final String property) {
        return this.properties.getObjectProperty(property);
    }

    @Override
    public void changeIntProperty(final String property, final int value) {
        this.properties.changeProperty(property, value);
    }

    @Override
    public void changeDoubleProperty(final String property, final double value) {
        this.properties.changeProperty(property, value);
    }

    @Override
    public void changeBooleanProperty(final String property, final boolean value) {
        this.properties.changeProperty(property, value);
    }

    @Override
    public void changeObjectProperty(final String property, final Object value) {
        this.properties.changeProperty(property, value);

    }

    /**
     * Builder for the entities.
     *
     */
    public static class EntitiesBuilder {
        private String image;
        private Location loc;
        private Behavior behavior;
        private EntityType type;
        private final PropertyMap properties = new PropertyMapImpl();

        /**
         * @param image
         *            an image to represent the entity.
         * @return the builder
         */
        public EntitiesBuilder setImage(final String image) {
            this.image = image;
            return this;
        }

        /**
         * @param loc
         *            initial location
         * @return the builder
         */
        public EntitiesBuilder setLocation(final Location loc) {
            this.loc = loc;
            return this;
        }

        /**
         * @param property
         *            the name of the property
         * @param value
         *            the value of the property
         * @return the builder
         */
        public EntitiesBuilder with(final String property, final double value) {
            this.properties.putProperty(property, value);
            return this;
        }
        /**
         * @param property
         *            the name of the property
         * @param value
         *            the value of the property
         * @return the builder
         */
        public EntitiesBuilder with(final String property, final Object value) {
            this.properties.putProperty(property, value);
            return this;
        }

        /**
         * @param property
         *            the name of the property
         * @param value
         *            the value of the property
         * @return the builder
         */
        public EntitiesBuilder with(final String property, final boolean value) {
            this.properties.putProperty(property, value);
            return this;
        }

        /**
         * @param property
         *            the name of the property
         * @param value
         *            the value of the property
         * @return the builder
         */
        public EntitiesBuilder with(final String property, final int value) {
            this.properties.putProperty(property, value);
            return this;
        }

        /**
         * @param b
         *            entity's behavior
         * @return the builder
         */
        public EntitiesBuilder setBehaviour(final Behavior b) {
            this.behavior = b;
            return this;
        }

        /**
         * @param t
         *            entity type
         * @return the builder
         */
        public EntitiesBuilder setType(final EntityType t) {
            this.type = t;
            return this;
        }

        /**
         * @return the entity builded
         */
        public EntitityImpl build() {
            return new EntitityImpl(image, loc, behavior, type, properties);
        }
    }
}
