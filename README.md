## Lab 1
## Djurinschi Andrei I2302

### Цели:

Сделайте domain model класс с хотя бы 5 полями, среди которых int, string, float, enum.
Сделайте абстракцию под базу данных, хранящую эти объекты. Имплементация не принципиальна, можно хранить их просто в массиве под капотом.
Создайте класс маски полей (сначала как bool).
Реализуйте метод в абстракции базы данных, которая совершает поиск всех объектов по одному из полей (например, FindByName(string)).
Реализуйте статическую функцию, которая печатает в консоль значения полей, согласно переданной маски.
Протестируйте программу.

Дальнейшие задания (как минимум 3 на выбор):
Реализуйте это в виде JSON REST API (возврат объекта не со всеми полями) (ASP.NET Core).
Создайте метод для merge всех объектов в базе данных, с равными значениями полей, согласно переданной маске.
Создайте метод копирования данных для всех объектов, с равными значениями полей, согласно переданной маске, с другого объекта, переданным параметром.
Выполните unit test-ы используй фреймворк (xunit).
Переделайте маску полей на основе битов.
Сделайте 3 метода, работающие на основе масок, комбинирующих их тем или иным полезным образом.

### Рабочий процесс

1) Domain model [User class](softLab/src/main/java/lab01/softlab/entities/User.java):

```java
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    private int age;

    private float rating;

    @Enumerated(EnumType.ORDINAL)
    private Role role;

// ниже геттеры 
```

2) Enum class [Role class](softLab/src/main/java/lab01/softlab/entities/Role.java)

```java
public enum Role {
    ADMINISTRATOR, MANAGER, TEACHER
}
```

3) 
