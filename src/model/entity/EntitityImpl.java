package model.entity;

import java.util.HashMap;
import java.util.Map;
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
    private final Map<String, Object> properties;

    private EntitityImpl(final String image, final Location loc, final Behavior behavior,
            final EntityType type, final Map<String, Object> properties) {
        super();
        this.image = image;
        this.loc = loc;
        this.behavior = Optional.ofNullable(behavior);
        this.type = type;
        this.properties = properties;
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
    public Object getProperty(final String property) {
        return this.properties.get(property);
    }

    @Override
    public Optional<Behavior> getBehaviour() {
        return this.behavior;
    }

    @Override
    public EntityType getType() {
        return this.type;
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
        private final Map<String, Object> properties = new HashMap<>();

        /**
         * @param image
         *            an image to represent the entity.
         */
        public EntitiesBuilder setImage(final String image) {
            this.image = image;
            return this;
        }

        /**
         * @param loc
         *            initial location
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
         */
        public EntitiesBuilder with(final String property, final Object value) {
            this.properties.put(property, value);
            return this;
        }

        public <T extends Behavior> EntitiesBuilder setBehaviour(T b) {
            this.behavior = b;
            return this;
        }

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
