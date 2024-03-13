package person;

import behavior.CoordXY;

/**
 * Класс Снайпер (лучник)
 */
public class Sniper extends ShooterBase {

    private static final int HEALTH = 800;
    private static final int POWER = 30;
    private static final int AGILITY = 30;
    private static final int DEFENCE = 5;
    private static final int DISTANCE = 6;
    private static final int ARROWS = 12;
    private static final int EFFECTIVE_DISTANCE = 4;


    /**
     * Создание экзеспляра Снайпера
     *
     * @param name Имя
     * @param pos  Положение в прогстранстве
     */
    public Sniper(String name, CoordXY pos)
    {
        super(name, 0, HEALTH, POWER, AGILITY, DEFENCE, DISTANCE, ARROWS, EFFECTIVE_DISTANCE, pos);
    }

    @Override
    public String toString()
    {
        return String.format("[Снайпер] %s", name  + " " + position.toString());
    }

}