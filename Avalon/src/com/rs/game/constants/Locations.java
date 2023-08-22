package com.rs.game.constants;

import com.rs.game.WorldTile;
import com.rs.game.constants.objects.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Anaxarchus
 * This enum class is used for referencing WorldTile locations and the id's of objects which can exist on them.
 * This is being implemented to solve issues with multiple plugins having conflicting keys from the ObjectPlugin.getKeys method.
 * <p>
 * There is a conflict involving the ID 29355 in at least two classes:
 * - {@link com.rs.game.objects.plugins.GlobalStairs}
 * - {@link com.rs.game.objects.plugins.LumbridgeBasement}
 * This conflict is preventing some(all?) ladder objects from being processed by GlobalStairs.
 */


public enum Locations {
    // TODO: parse points of interest and WorldObjects into enum
    EXAMPLE_LOCATION(100, 100, 0, -1, "This is a location description"),
    EXAMPLE_LADDER(100, 100, 0, 29355, "This is a description for a ladder");

    private final int x;
    private final int y;
    private final int plane;
    private final int objectId;
    private final String description;

    Locations(int x, int y, int plane, int objectId, String description) {
        this.x = x;
        this.y = y;
        this.plane = plane;
        this.objectId = objectId;
        this.description = description;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPlane() {
        return plane;
    }

    public int getObjectId(){
        return objectId;
    }

    public WorldTile getWorldTile() {
        return new WorldTile(x, y, plane);
    }

    public String getDescription() {
        return description;
    }

    /**
     * Check if an object with the specified objectId exists at a given location.
     *
     * @param x        The x-coordinate of the location.
     * @param y        The y-coordinate of the location.
     * @param plane    The plane (or level) of the location.
     * @param objectId The ID of the object to check for.
     * @return true if an object with the specified objectId exists at the location, false otherwise.
     */
    public boolean hasObject(int x, int y, int plane, int objectId) {
        for (Locations location : Locations.values()) {
            if (location.getX() == x && location.getY() == y && location.getPlane() == plane && location.getObjectId() == objectId) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get a list of Locations that have a specific objectId.
     *
     * @param objectId The ID of the object to search for.
     * @return An array of Locations that have the specified objectId.
     */
    public Locations[] getLocationsWithObjectId(int objectId) {
        List<Locations> locationsWithObjectId = new ArrayList<>();
        for (Locations location : Locations.values()) {
            if (location.getObjectId() == objectId) {
                locationsWithObjectId.add(location);
            }
        }
        return locationsWithObjectId.toArray(new Locations[0]);
    }

    /**
     * Find the location for the given parameters.
     *
     * @param x        The x-coordinate of the location.
     * @param y        The y-coordinate of the location.
     * @param plane    The plane (or level) of the location.
     * @return Location enum if one exists for the given coordinates or null if none is found.
     */
    public Locations find(int x, int y, int plane) {
        for (Locations location : Locations.values()) {
            if (location.getX() == x && location.getY() == y && location.getPlane() == plane) {
                return location;
            }
        }
        return null;
    }
}
