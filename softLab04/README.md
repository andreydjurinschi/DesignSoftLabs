## Статическое задание св-в

`классы:`

(x -> e.ex, e.g) 


```txt
Entity
 ├── Player
 └── Weapon
        └── MagicWeapon
```

есть абстрактный класс Entity, являющийся родителем для всех остальных представленных классов выше 

каждый последующий класс добавляет свои фиксированные, необходимые ему св-ва.  каждая сущность имеет фиксированные поля (их нельзя добавить, мы не можем их заменить). они представляют из себя тот набор
, который просто необходим этой сущности. **плюсы** такого подхода это лаконичный и понятный вид, хорошо работает в IDE (автокомплит, автоподстановка полей) и это удобно, когда мы знаем все сущности и их структуру заранее.

**минусы**: библиотека не знает зараннее какие св-ва нужны обычному пользователю, с чем он хочет работать и с чем нет.
конечно, мы можем в сущность пользователя подставить 20-40 всевозможных полей, однако они будут засорять значительный объем памяти, даже когда не будут использоваться. плюс с ростом функционала иерархия классов будет увеличиваться, что приведет к непонятной архитектуре проекта

___

## Использование интерфейсов

`классы:`
 
```txt
Entity
 ├── Player -> HasHp, HasMana
 └── Weapon -> 
        └── MagicWeapon
```

данный алгоритм действий обходит ограничения множественного наследования. теперь под каждую сущность можно создавать необходимые функционалу интерфейсы и имплементировать кол-во в рамках необходимого.

данный подход не усложняет иерархию классов и позволяет гибко настраивать последующую необходимую логику, однако точно так же, как и в прошлом примере, нам приходится опять в ручную добавлять каждое св-во для объекта.

____________

если мы говорим про создание класса, в котором есть неогрониченное кол-во полей, которые связывают сущность, к примеру: `Player: mana, health, gun, speed, ...`, то шанс того, что в такой сущности есть необходимые
для логики поля очень велик, однако не все поля могут быть нужны, а это означает потребление сущностью больших ресурсов. как по мне такой подход является одним из самых неудобных и нежелательных, потому что такой подход просто неоптимизирован

____________

## Решение

нам нужен проект, который реализует систему динамических свойств — универсальный механизм, который позволяет добавлять к сущности любые поля в рантайме, даже если библиотека заранее не знает, что это будут за свойства.

Эта же идея используется в:

- ASP.NET HttpContext.Items

- Unity ScriptableObject metadata

___________

#### 1) PropertyKey<T> — типизированный ключ свойства

Это обёртка над уникальным целочисленным ID свойства.

#####  Каждый ключ:

- имеет тип (<T>), чтобы сохранить типобезопасность
- имеет уникальный ID, который используется как индекс в Object[]

##### Поля

- private final int id;
- Хранит числовой идентификатор свойства.

##### Конструктор

- public PropertyKey(int id)

- Получает номер свойства (индекс в массиве значений сущности).

##### Методы

- public int getId()

Позволяет сущности узнать, куда сохранять значение (в какой слот массива).

##### КОД [link](https://github.com/andreydjurinschi/DesignSoftLabs/blob/a62e2d93b7e4ed830d340446c7bd3f3eeac6f698/softLab04/library/src/main/java/PropertyKey.java)

```java
public class PropertyKey <T>{
    private final int id;

    public PropertyKey(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
```

#### 2) PropertyRegistry — глобальный реестр ключей

##### Это глобальный менеджер, который:

- гарантирует, что ключи будут уникальны

- следит, чтобы два разных модуля не создали одинаковый ключ под разными типами

- выдаёт уникальные ID для свойств

##### Поля

- private static int counter = 0;

- private static Map<String, Integer> keys = new HashMap<>();

`counter` — генерирует новые ID

`keys` — словарь имён свойств → ID. гарантирует, что имя не повторится

##### Метод регистрации:

public static <T> PropertyKey<T> registerNew(String name)

Проверяет, существует ли ключ с таким названием. Если существует — выдаёт ошибку. Регистрирует имя и выдаёт новый PropertyKey.

##### КОД

```java
import java.util.HashMap;
import java.util.Map;

public final class PropertyRegistry {
    private static int counter = 0;
    private static Map<String, Integer> keys = new HashMap<>();

    public static <T>PropertyKey<T> registerNew(String name){
        if(keys.containsKey(name)){
            throw new IllegalArgumentException("key " + name + " already exists");
        }
        keys.put(name, counter);
        return new PropertyKey<>(counter++);
    }

    public static Integer getKeyByName(String name){
        return keys.get(name);
    }
}
```

#### 3) Entity — сущность с динамическими свойствами [link](https://github.com/andreydjurinschi/DesignSoftLabs/blob/a62e2d93b7e4ed830d340446c7bd3f3eeac6f698/softLab04/library/src/main/java/Entity.java)

##### Назначение

Сущность хранит свойства, но не жёстко прописанные поля, а через массив Object[].
Это позволяет: хранить любые свойства, не использовать наследование, добавлять свойства из других проектов

##### Поле:

private final Object[] values = new Object[128]; Это хранилище всех свойств.

Элемент массива → одно свойство, индекс — ID key.

##### Метод set
public <T> void set(PropertyKey<T> key, T value). Сохраняет значение свойства в массив:

таким образом мыузнаём ID свойства: key.getId(). сохраняем: values[id] = value

это дает очень быстрое сохранение и извлечение данных.

##### Метод getOrDefault

public <T> T getOrDefault(PropertyKey<T> key, T defaultValue)

Если значение отсутствует → возвращается `defaultValue`.

Используется, если значение может быть необязательным.

##### Метод tryGet

public <T> boolean tryGet(PropertyKey<T> key, Holder<T> out)

Если значение есть — помещает его в out.value и возвращает true

Если нет — false

#####  Holder<T>

Java не позволяет вернуть 2 значения, поэтому используется контейнер.

##### КОД

```java
public class Entity {
    private final Object[] values = new Object[128];

    public <T> void set(PropertyKey<T> key, T value){
        values[key.getId()] = value;
    }

    public <T> T getOrDefault(PropertyKey<T> key, T defaultValue){
        Object value = values[key.getId()];
        return value == null ? defaultValue : (T) value;
    }

    public <T> boolean tryGet(PropertyKey<T> key, Holder<T> out) {
        Object value = values[key.getId()];
        if (value != null) {
            out.value = (T) value;
            return true;
        }
        return false;
    }

    public static class Holder<T> {
        public T value;
    }

}
```

##### 4. UserAppKeys — ключи, определённые в пользовательском проекте [link](https://github.com/andreydjurinschi/DesignSoftLabs/blob/a62e2d93b7e4ed830d340446c7bd3f3eeac6f698/softLab04/user_app/src/main/java/UserAppKeys.java)

Показывает, что пользователь библиотеки может определять новые свойства сам.

Библиотека ничего не знает о предоставленных полях, но система динамических свойств позволяет им появляться.

##### КОД

```java
    import java.util.List;

    /**
     * The type User app keys.
     */
    public class UserAppKeys {
        /**
         * The constant Health.
         */
        public static final PropertyKey<Integer> Health =
                PropertyRegistry.registerNew("HEALTH");

        /**
         * The constant NAME.
         */
        public static final PropertyKey<String> NAME =
                PropertyRegistry.registerNew("NAME");

        /**
         * The constant Speed.
         */
        public static final PropertyKey<Float> Speed =
                PropertyRegistry.registerNew("SPEED");
    }

```

сущность `Entity` никак не изменяется, что дает ей универсальность. пользователь может сам в виде ключа для `PropertyKey` подставить тип данных логически необходимый и все, функионал сущности доступен просто добавлением св-ва

#### 5) Main — пример использования [link](https://github.com/andreydjurinschi/DesignSoftLabs/blob/62a75e32507ea73c7aea689af048d1862a44db28/softLab04/user_app/src/main/java/Main.java)

###### КОД

```java
    // создаем два игрока
    Entity player1 = new Entity();
    player1.set(UserAppKeys.NAME, "Player1");
    player1.set(UserAppKeys.Speed, 12.5f);
    player1.set(UserAppKeys.Health, 150);

    Entity player2 = new Entity();
    player1.set(UserAppKeys.NAME, "Player2");
    player1.set(UserAppKeys.Health, 250);

```

```java
        /*get or default methods*/
        Float playerOneSpeed = player1.getOrDefault(UserAppKeys.Speed, 0.0f);
        //Float playerTwoSpeed = player2.getOrDefault(UserAppKeys.Speed, 0.0f);

        System.out.println(
                "Player 1 speed: " + playerOneSpeed + "\n" 
                        //"Player 2 speed: " + playerTwoSpeed
        );
```

если у игрока не будет скорости, то дефолт значение - 0

```java
/*
    * custom speed changing method
    */
    public static void  changeSpeed(Entity e, float value, char way){
        Entity.Holder<Float> speedHolder = new Entity.Holder<>();
        if(e.tryGet(UserAppKeys.Speed, speedHolder)){
            switch (way){
                case '-' ->{
                    e.set(UserAppKeys.Speed, speedHolder.value - value);
                }
                case '+' -> {
                    e.set(UserAppKeys.Speed, speedHolder.value + value);
                }
                default -> {
                    System.out.println("problem");
                }
            }
        }else{
            System.out.println("prop Speed not selected");
        }

    }
```

можем добавлять кастомные методы для работы с св-вами объекта. выше представлен метод для смены скорости игрока

```java
/*checking for property*/
        Entity.Holder<Float> speedHolder = new Entity.Holder<>();
        if(player1.tryGet(UserAppKeys.Speed, speedHolder)){
            System.out.println("Speed=" + speedHolder.value);
        }

        /*custom changing property*/
        changeSpeed(player1, 15, '+');

        if(player1.tryGet(UserAppKeys.Speed, speedHolder)){
            System.out.println("Speed=" + speedHolder.value);
        }
```

получаем значение ключа и меняем его (до кастомного метода / после)

```bash
Speed=12.5
Speed=27.5
```

```java
/*checking non-existence property*/ 
Entity.Holder<Float> playerTwoSpeedHolder = new Entity.Holder(); 
if(!player2.tryGet(UserAppKeys.Speed, playerTwoSpeedHolder)){     
    playerTwoSpeedHolder.value = player2.getOrDefault(UserAppKeys.Speed, 0.0f);
    player2.set(UserAppKeys.Speed, playerTwoSpeedHolder.value);
    System.out.println("Current speed of second player is 0!");
}
changeSpeed(player2, 8, '+'); 
System.out.println("Changed speed - " + playerTwoSpeedHolder.value);
```

пытаемся получить значение несуществующего поля и поменять его

```bash
Current speed of second player is 0!
Changed speed - 0.0
```

видим 0, тоесть дефолтное значение. такой принцип не выбрасывает исключения по типу `null pointer` и другие, что дает гибкость и надежность программы

#### ИТОГ

данное решение помогло создать такую библиотеку, которая позволяет динамически добавлять объекту св-ва по необходимости. одна из самых интеренсых лабораторных работ, тк раньше я даже не задавался вопросом о том, что такие решения впринципе существуют

