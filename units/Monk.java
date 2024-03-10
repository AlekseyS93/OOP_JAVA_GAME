package units;

public class Monk extends Person{
    private int mana;
    private int healVal;
    private int healPrice;
    private int attackPrice;

    public Monk(String name, int age) {
        super(name, 80, 30, age, 5, 30, "StickMonk", 30);
        this.mana = 10;
        this.healPrice = 2;
        this.attackPrice = 1;
    }

    public void attack(Person person) {
        person.health -= this.power;
        this.mana -= price("attack");
    }

    public void heal(Person person) {
        person.health += this.healVal;
        this.mana -= price("heal");
    }

    public void heal() {
        super.health += this.healVal;
        this.mana -= price("heal");
    }

    private int price(String action) {
        if (action == "heal") return healPrice;
        else if (action == "attacl") return attackPrice;
        return 0;

    }
}
