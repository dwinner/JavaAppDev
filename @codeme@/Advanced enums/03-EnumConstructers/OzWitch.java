//: OzWitch.java
// Волшебники страны Оз.

public enum OzWitch
{
    // Подобные инициализаторы перечислений должны быть определены раньше методов:
    WEST("Miss Gulch, aka the Wicked Witch of the West"),
    NORTH("Glinda, the Good Witch of the North"),
    EAST("Wicked Witch of the East, wearer of the Ruby " +
        "Slippers, crushed by Dorothy's house"),
    SOUTH("Good by inference, but missing");
  
    private String description;
  
    // Конструктор должен быть частным или "пакетным":
    private OzWitch(String description)
    {
        this.description = description;
    }
  
    public String getDescription()
    {
        return description;
    }
  
    public static void main(String[] args)
    {
        for (OzWitch witch : OzWitch.values())
            System.out.println(witch + ": " + witch.getDescription());
    }
}
// ---------------------------------------------------------------------------------------
/* Output:
WEST: Miss Gulch, aka the Wicked Witch of the West
NORTH: Glinda, the Good Witch of the North
EAST: Wicked Witch of the East, wearer of the Ruby Slippers, crushed by Dorothy's house
SOUTH: Good by inference, but missing
*///:~
