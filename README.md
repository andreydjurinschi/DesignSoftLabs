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

1) [Domain model](softLab/src/main/java/lab01/softlab/entities/User.java):
2) [Role enum class](softLab/src/main/java/lab01/softlab/entities/Role.java);
3) [Db conf class](softLab/src/main/java/lab01/softlab/configs/dbConfig.java);
4) [Field Mask class](softLab/src/main/java/lab01/softlab/mask/UserFieldMask.java)
5) [User repository](softLab/src/main/java/lab01/softlab/repo/UserRepository.java)

Репозиторий содержит доп. методы для поиска пользователей по имени и выборку пользователь по роли и возрасту
```java
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    @Query("select u from User u where u.name = :name")
    List<User> findByName(String name);
    
    @Query("select u from User u where u.role = :role and u.age > 60")
    List<User> findRetiredUsersByRole(Role role);
}
```

- Реализация двух кастомных методов [User service](softLab/src/main/java/lab01/softlab/service/UserService.java) | [User controller](softLab/src/main/java/lab01/softlab/controllers/UserController.java)


```java
    // сервис
    public List<User> getAllByName(String name){
        return repo.findByName(name);
    }
    public List<User> getRetired(Role role){
        return repo.findRetiredUsersByRole(role);
    }
```

```java
// контроллер
@GetMapping("/find")
    public ResponseEntity<List<User>> findByName(@RequestParam String name){
        return ResponseEntity.status(HttpStatus.OK).body(serv.getAllByName(name));
    }
    
    @GetMapping("/retired")
    public ResponseEntity<List<User>> getRetiredUsers(@RequestParam Role role){
        return ResponseEntity.status(HttpStatus.OK).body(serv.getRetired(role));
    }
```

6) [Printer class](softLab/src/main/java/lab01/softlab/printer/Printer.java)

```java
public class Printer {
    public static Map<String, Object> print(User user, UserFieldMask mask){
        Map<String, Object> res = new HashMap<>();

        if(mask.isId()){
            res.put("id", user.getId());
        }
        if(mask.isName()){
            res.put("name", user.getName());
        }
        if(mask.isAge()){
            res.put("age", user.getAge());
        }
        if(mask.isRating()){
            res.put("rating", user.getRating());
        }
        if(mask.isRole()){
            res.put("role", user.getRole());
        }
        return res;
    }
}
```

метод на основе полученных значений из маски добавляет в результирующий словарь ключи на основе полей класса пользователя и их значений

[Service](softLab/src/main/java/lab01/softlab/service/UserService.java)

```java
    public List<Object> getAllRefToMask(UserFieldMask mask){
        List<User> allUsers = repo.findAll();
        List<Object> res = new ArrayList<>();
        for(var user : allUsers){
           res.add(Printer.print(user, mask));
        }
        return res;
    }
```

на основе данных из маски возвращается список в виде словаря из данных пользователей

[User controller](softLab/src/main/java/lab01/softlab/controllers/UserController.java)

```java
    @GetMapping("/all")
    public ResponseEntity<List<Object>> getAll(@RequestBody UserFieldMask mask){
        return ResponseEntity.status(HttpStatus.OK).body(serv.getAllRefToMask(mask));
    }
```




