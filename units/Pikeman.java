package units;

public class Pikeman extends Person{
    public Pikeman(String name, int age){
        super(name,80,30,age,30,50,"Pike",20);
    }

    public void attack(Person person) {
        person.health -= this.power;
    }
}
