# Курс - ООП (Java)

## Урок 1
```
Проанализировать персонажей "Крестьянин, Разбойник, Снайпер, Колдун, Копейщик, Арбалетчик, Монах".
Для каждого определит 8 полей данных(здоровье, сила итд) 3-4 поля поведения(методов атаковать, вылечить итд).
Создать абстрактный класс и иерархию наследников.
Расположить классы в пакет так, чтобы в основной программе небыло видно их полей.
В не абстрактных классах переопределить метод toString() так чтобы он возвращал название класса или имя.
Создать в основной программе по одному обьекту каждого не абстрактного класса и вывести в консоль его имя.
```

Предварительно, о "физике" игры (пошаговая).

Общие характеристики:
- **name** - имя персонажа
- **priority** - приоритет хода
- **health** - уровень здоровья (с погрешностью +- 10% от базового)
- **power** - сила (с погрешностью +- 10% от базового), от которой зависят атака, носимый груз и колдовство
- **agility** - ловкость (с погрешностью +- 10% от базового), влияет на вероятность критического удара и вероятность увернуться от вражеской атаки
- **defence** - снижает урон от вражеской атаки (выражается в % к урону)
- **distance** - дистанция действия (боя, магии, снабжения)
- **curX, curY** - местоположение в декартовых координатах


Помимо этого у каждого типа есть свои характеристики:

- **Снайпер** и **Арбалетчик** имеют немного разную силу, ловкость и эффективную дальность стрельбы
- **Разбойник**, **Копейщик**, **Снайпер** и **Арбалетчик** так же имеют поле **level**, т.е. опыт (увеличивается с каждым убитым противником и прибавляет + 10% к урону)
- **Колдун** и **Монах** имеют запас маны, которую могут тратить на лечение других юнитов, причем эффективность лечения зависит от их силы, а так же могут воскрешать павших (колдун - полностью за 75% маны, а монах с малым здоровьем, но за 20% маны)
- **Крестьянин** подносит стрелы стрелкам, количество стрел зависит от силы.

В классах используются константы, поскольку с ними будет проще подобрать баланс характеристик персонажей.


## Урок 2

Добавлен метод генерации двух команд, по 10 человек в каждой.

Метод генерации имён для персонажей заменил с **enum** на **class**, поскольку
JIDEA мало-мало сердилась на русские символы.

Класс **PointXY** переименовал в **CoordXY**, для исключения конфликтов со всякими awt и тд.
Плюс создал отдельный пакет **behavoir** для таких вот классов.
Поля **width** и **height** (размеры нашего "мира") у нас общие для всех экземпляров
этого класса, поэтому сделал их статичными и убрал инициализацию из
конструктора класса. Это позволит легко менять размер "мира",
независимо от количества уже созданных экземпляров класса **CoordXY**.

Метод поиска ближайшей к арбалетчику цели перенёс в базовый класс, поскольку он
не привязан к особенностям персонажей и впоследствии будет нужен всем персонажам.
Теперь это **PersonBase findNearestPerson(ArrayList<PersonBase> persons)**

Передачу координат в конструктор базового класса переделал на передачу ссылки на CoordXY,
поскольку это более логично, раз мы всё равно храним координаты в этом классе.


## Урок 3

Задание:

```
Добавить в файл интерфейса метод void step() это будет основной метод взаимодействыия 
с объектами. Добавить интерфейс в базовый абстрактный класс. Реализовать этот метод во 
всех классах наследниках. Для начала просто заглушкой.
Доработать классы лучников. Лучник должен во первых проверить жив ли он и есть ли у 
него стрелы, если нет то завершить метод. Есль всё да, то найти ближайшего противника 
и выстрелить по немы и, соответственно потратить одну стрелу. 
Реализовать весь функционал лучников в методе step().
Добавить в абстрактный класс int поле инициатива. В классах наследников инициализировать 
это поле. Крестьянин = 0, маги=1, пехота=2, лучники=3. 
В мэйне сделать так, чтобы сначала делали ход персонажи с наивысшей инициативой из обеих 
комманд а с наименьшей в конце.
```

Исправил систему сообщений, теперь действия персонажей выводятся в консоль более наглядно и лаконично.
```shell
[Арбалетчик] Андрей 0:4 ходит.  Стреляет по [Крестьянин] Ахиллес 9:4, но Ахиллес увернулся!
[Арбалетчик] Лариса 0:0 ходит.  Стреляет по [Монах] Артём 9:0 и наносит 17 повреждений.
[Снайпер] Андрей 9:7 ходит.  Стреляет по [Копейщик] Артём 0:7, но Артём увернулся!
[Снайпер] Андрей 9:2 ходит.  Стреляет по [Колдун] Ахиллес 0:2 и наносит критический удар в 34 повреждений!
```

Доработал метод findNearestPerson() из базового класса, чтобы не вернул "покойника".


## Урок 4
```
Реализовать метод step() пехоты. Первое проверяем живы ли мы, потом ищем ближайшего 
противника. Если противник в соседней клетке, наносим повреждение. Иначе двигаемся в 
сторну противника. Алгоритм движения, если dX>dY двигаемся по x иначе по y. dX и dY 
(разница наших координат и ближайшего противника) знаковые, от знака зависит направление.
```

Ход реализовал как в старых 8-ми битных играх: вначале оцениваем ход во всех возможных направлениях,
затем выбираем лучший вариант. В данной реализации оценка - это просто расстояние до выбранной цели.
Поскольку нам нужно лишь сравнение расстояний, а не их точное значение, то в класс CoordXY ввёл функцию
вычисления приблизительного расстояния:

dist = x * x + y * y;

То есть без вычисления квадратного корня - в данном случае он нам просто не нужен.
Такой алгоритм позволяет обходить простые препятствия. Чтобы обходить более сложные препятствия,
нужно запоминать направление предыдущего движения, а у нас это не предусмотрено.
Или переходить на более совершенные алгоритмы.


## Урок 5
```
Добавить в проект классы View и AnsiColors. Доработать проект так, чтобы выводилось состаяние 
в консоль. Если в одной из комманд погибли все, приложение закрывается с поздравлением победившей 
команды. Добавить в интерфейс метод getInfo() в котором хранится название персонажа, что он сделал 
в этот ход(выстрелил, вылечил, сходил), и с ком он это сделал!)
```

***Пофиксил баг*** в View: при вызове getChar(x, y) в коде были перепутаны
местами **x** и **y**, из-за чего код:
```
if (pos.getX() == x && pos.getY() == y)
```
работал неправильно, а вот:
```
if (pos.getX() == y && pos.getY() == x)
```
давал в итоге верный результат. Теперь приведено в более правильный вид.


***Пофиксил баг*** в View.tabSetter(), где для выравнивания строк применялся модификатор
%s с заданной длиной. Проблема только в том, что если в строку сразу поставить tab,
то он может не сработать, если конечная координата по X будет кратна 4-м. Связано
это наверное с тем, что при расчете конечной длины строки printf заранее учитывает и
символ табуляции. В итоге, часть надписей после ':' выводилась с отступом, а часть
без него.
Сделал вывод tab отдельно, теперь всё ровно.

Так же немного поменял вывод строк "Blue side" и "Green side", сделав через
тот же tabSetter(), чтобы надписи начинались там же, где и информация по командам.

Перевел начало координат в View к (0, 0), так привычнее.

Подкорректировал вывод информации о командах: теперь информация об игроке выводится в той позиции, на которой он и находился.
То есть, **blue** и **green** теперь действительно синие и зелёные.


Добавил в **toString()** персонажей вывод их основных параметров, обозначаемых эмодзи.