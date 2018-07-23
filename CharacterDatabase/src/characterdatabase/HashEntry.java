package characterdatabase;

public class HashEntry {
    private Character character;
    private String key;
    
    public HashEntry(String key, Character character){
        this.character = character;
        this.key = key;
    }
    
    public Character getCharacter(){
        return character;
    }

    public String getKey(){
        return key;
    }
}
