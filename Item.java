// Item.java
// this class represents a single item, it could be an equippable
// thing like weapon or ring, or something generic
/**
 * The Item class represents a single item and if it could be equippable.
 */
public class Item {
    // what sort of item it is
    private ItemType type;

    // the name of the item as shown to the user
    private String name;

    // how much it weighs (player can only carry so much)
    private int weight;

    // how much the item is worth for buying/selling
    private int value;

    // the item's strength - this differs based on the type
    // for a weapon, it's damage
    // for armor, it's protection
    private int strength;
    /**
     * The Item constructor is used to instantiate an item.
     * @param type is the item type.
     * @param name is the item name.
     * @param weight is the item weight.
     * @param value is the item value.
     * @param strength is the item strenth.
     */
    public Item(ItemType type, String name, int weight, int value, int strength) {
        this.type = type;
        this.name = name;
        this.weight = weight;
        this.value = value;
        this.strength = strength;
    }
    /**
     * The getWeight method returns the weight of the item.
     * @return the weight of the item.
     */
    public int getWeight() {
        return weight;
    }
    /**
     * The getValue method returns the value of the item.
     * @return the value of the item.
     */
    public int getValue() {
        return value;
    }
    /**
     * The getStrenth method returns the strength of the item.
     * @return the strength of the item.
     */
    public int getStrength() {
        return strength;
    }
    /**
     * The getName method returns the name of the item.
     * @return the name of the item.
     */
    public String getName() {
        return name;
    }
    /**
     * The getType method returns the type of the method.
     * @return the type of the method.
     */
    public ItemType getType() {
        return type;
    }
    /**
     * The toString method is an override that converts variables to a string.
     */
    @Override
    public String toString() {
        return name + " " + weight + " " + value + " " + strength;
    }
}

