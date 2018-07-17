package characterdatabase;


public class Character {
    private final String name;
    private final String race;
    private final String charClass;
    private final String alignment;
    
    public Character(String name, String race, String charClass, String alignment){
        this.name = name;
        this.race = race;
        this.charClass = charClass;
        this.alignment = alignment;
    }
    
    public String getName(){
        return name;
    }
}
