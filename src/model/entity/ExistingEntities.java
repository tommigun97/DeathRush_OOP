package model.entity;

/**
 * Enum for entities in the game. used for scan from file.
 *
 */
public enum ExistingEntities {

    /**
     * enemy 1.
     */
    ENEMY1,
    /**
     * enemy 2.
     */
    ENEMY2,
    /**
     * stopped.
     */
    STOPPED,
    /**
     * boss 1.
     */
    BOSS1,
    /**
     * boss 2.
     */
    BOSS2,
    /**
     * boss 3.
     */
    BOSS3,
    /**
     * gun.
     */
    GUN,
    /**
     * guitar.
     */
    GUITAR,
    /**
     * sugar.
     */
    SUGAR,
    /**
     * cigarettes.
     */
    CIGARETS;

    /**
     * Getter for entity.
     * 
     * @param id
     *            entity id
     * @return the correct enum object
     */
    public static ExistingEntities getPropieties(final String id) {
        return id
                .equals("1")
                        ? ExistingEntities.ENEMY1
                        : id.equals("2") ? ExistingEntities.ENEMY2
                                : id.equals("3") ? ExistingEntities.STOPPED
                                        : id.equals("4") ? ExistingEntities.BOSS1
                                                : id.equals("5") ? ExistingEntities.BOSS2
                                                        : id.equals("6") ? ExistingEntities.BOSS3
                                                                : id.equals("7") ? ExistingEntities.GUN
                                                                        : id.equals("8") ? ExistingEntities.GUITAR
                                                                                : id.equals("9")
                                                                                        ? ExistingEntities.SUGAR
                                                                                        : ExistingEntities.CIGARETS;
    }
}
