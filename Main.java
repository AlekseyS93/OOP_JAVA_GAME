/*
Проанализировать персонажей "Крестьянин, Разбойник, Снайпер, Колдун, Копейщик, Арбалетчик, Монах".
Для каждого определит 8 полей данных(здоровье, сила итд) 3-4 поля поведения(методов атаковать, вылечить итд).
Создать абстрактный класс и иерархию наследников.
Расположить классы в пакет так, чтобы в основной программе небыло видно их полей.
В не абстрактных классах переопределить метод toString() так чтобы он возвращал название класса или имя.
Создать в основной программе по одному обьекту каждого не абстрактного класса и вывести в консоль его имя.
*/

import behavior.CoordXY;
import behavior.HeroesNames;
import person.*;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    static ArrayList<PersonBase> red = new ArrayList<>();
    static ArrayList<PersonBase> blue = new ArrayList<>();

    public static void main(String[] args) {
        createTeam(red, 10, 0);
        createTeam(blue, 10, 3);
        System.out.println(red + "\n");
        System.out.println(blue);

        System.out.println("\nTest find nearest enemy");
        Sniper sniper = new Sniper(HeroesNames.getRandomName(), new CoordXY(7, 5));
        PersonBase target = sniper.findNearestPerson(blue);
        System.out.println(sniper + ": nearest blue target at " + target);
        target = sniper.findNearestPerson(red);
        System.out.println(sniper + ": nearest red target at " + target);
        System.out.println();
        Robber robber = new Robber(HeroesNames.getRandomName(), new CoordXY(3, 1));
        target = robber.findNearestPerson(blue);
        System.out.println(robber + ": nearest blue target at " + target);
        target = robber.findNearestPerson(red);
        System.out.println(robber + ": nearest red target at " + target);

    }


    public static void createTeam(ArrayList<PersonBase> team, int num, int start)
    {
        Random rnd = new Random();
        while (--num >= 0)
        {
            int n = start + rnd.nextInt(4);
            switch (n)
            {
                case 0:
                    team.add(new Crossbowman(HeroesNames.getRandomName(), new CoordXY(0, num)));
                    break;
                case 1:
                    team.add(new Spearman(HeroesNames.getRandomName(), new CoordXY(0, num)));
                    break;
                case 2:
                    team.add(new Wizard(HeroesNames.getRandomName(), new CoordXY(0, num)));
                    break;
                case 3:
                    team.add(new Peasant(HeroesNames.getRandomName(), new CoordXY(start*3, num)));
                    break;
                case 4:
                    team.add(new Sniper(HeroesNames.getRandomName(), new CoordXY(9, num)));
                    break;
                case 5:
                    team.add(new Monk(HeroesNames.getRandomName(), new CoordXY(9, num)));
                    break;
                case 6:
                    team.add(new Robber(HeroesNames.getRandomName(), new CoordXY(9, num)));
                    break;
                default:
                    System.out.println("ERROR! Пересмотри алгоритм, ламер!");
            }
        }
    }

}