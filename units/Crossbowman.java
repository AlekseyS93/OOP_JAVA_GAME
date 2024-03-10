package units;

public class Crossbowman extends Person{
    private int arrowsNum;
    boolean inGame = true;
    public Crossbowman(String name, int age){
        super(name, 60, 40, age, 20, 40, "crossbow", 40);
    }
    public void attack(Person person) {
        person.health -= this.power;
    }

    public boolean isInGame() {
        return this.arrowsNum == 0 ? false : true;
    }
}
