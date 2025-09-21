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

1) [Domain model](src/main/java/lab01/softlab/entities/User.java):
2) [Role enum class](src/main/java/lab01/softlab/entities/Role.java);
3) [Db conf class](src/main/java/lab01/softlab/configs/dbConfig.java);
4) [Field Mask class](src/main/java/lab01/softlab/mask/UserFieldMask.java)
5) [User repository](src/main/java/lab01/softlab/repo/UserRepository.java)

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

- Реализация двух кастомных методов [User service](src/main/java/lab01/softlab/service/UserService.java) | [User controller](src/main/java/lab01/softlab/controllers/UserController.java)


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

6) [Printer class](src/main/java/lab01/softlab/printer/Printer.java)

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

[Service](src/main/java/lab01/softlab/service/UserService.java)

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

[User controller](src/main/java/lab01/softlab/controllers/UserController.java)

```java
    @GetMapping("/all")
    public ResponseEntity<List<Object>> getAll(@RequestBody UserFieldMask mask){
        return ResponseEntity.status(HttpStatus.OK).body(serv.getAllRefToMask(mask));
    }
```

7. [Byte mask](src/main/java/lab01/softlab/mask/UserByteFieldMask.java)

```java
    public static final byte ID     = 0b00001;
    public static final byte NAME   = 0b00010;
    public static final byte AGE    = 0b00100;
    public static final byte RATING = 0b01000;
    public static final byte ROLE   = 0b10000;
    
    private byte mask = 0;
    
    public byte getMask() {
        return mask;
    }
    
    public void setMask(byte mask) {
        this.mask = mask;
    }
    
    public void addField(byte field) {
        this.mask |= field;
    }
    
    public boolean hasField(byte field) {
        return (mask & field) != 0;
    }
    
    public boolean hasID()     { return hasField(ID); }
    public boolean hasNAME()   { return hasField(NAME); }
    public boolean hasAGE()    { return hasField(AGE); }
    public boolean hasRATING() { return hasField(RATING); }
    public boolean hasROLE()   { return hasField(ROLE); }
```

Метод вывода на основе битовой маски точно такой-же 

Метод в контроллере

```java
    @GetMapping("/allByByte")
    public ResponseEntity<List<Map<String, Object>>> getAll(){
        UserByteFieldMask mask = new UserByteFieldMask();
        mask.addField(UserByteFieldMask.ID);
        mask.addField(UserByteFieldMask.NAME);
        mask.addField(UserByteFieldMask.ROLE);
        return ResponseEntity.status(HttpStatus.OK).body(serv.getAllRefToMask(mask));
    }
```

8. [Merge method](src/main/java/lab01/softlab/mask/MaskMethods.java)

```java
    // Метод для сравнения значений по маске
    private boolean equalByMask(User u1, User u2, UserFieldMask mask){
        if(mask.isAge() && u1.getAge() != u2.getAge()){
            return false;
        }
        if(mask.isName() && !u1.getName().equals(u2.getName())){
            return false;
        }
        if(mask.isRating() && u1.getRating() != u2.getRating()){
            return false;
        }
        if(mask.isRole() && !u1.getRole().equals(u2.getRole())){
            return false;
        }
        return true;
    }
```

```java
// Метод merge для всех пользователей из БД
    public List<User> merge(UserFieldMask mask){
        List<User> allUsers = repo.findAll();
        List<User> merged = new ArrayList<>();
        for(var u : allUsers){
            User exists = merged.stream().filter(u1 -> equalByMask(u1, u, mask)).
                    findFirst().orElse(null);
            if(exists == null){
                exists = new User();
                exists.setName(u.getName());
                exists.setAge(u.getAge());
                exists.setRole(u.getRole());
                exists.setRating(u.getRating());
                merged.add(exists);
            }else{
                float avg = (float) allUsers.stream().mapToDouble(User::getRating).average().getAsDouble();
                exists.setRating(avg);
            }
        }
        return merged;
    }
```

9. [Unit tests](src/test/java/lab01/user_mask_tests/UserMaskTests.java)

```java
    @Mock
    private UserRepository repo;

    @InjectMocks
    private UserService service;
```

```java
@Test
    void getAll(){

        UserFieldMask mask = new UserFieldMask();
        mask.setAge(true);
        mask.setName(true);
        mask.setId(false);
        mask.setRating(false);
        mask.setRole(true);

        User user1 = new User();
        user1.setAge(10);
        user1.setName("test1");
        user1.setRating(1.5F);
        user1.setRole(Role.ADMINISTRATOR);

        User user2 = new User();
        user2.setAge(11);
        user2.setName("test2");
        user2.setRating(5.5F);
        user2.setRole(Role.TEACHER);

        Mockito.when(repo.findAll()).thenReturn(List.of(user1, user2));
        List<Map<String, Object>> users = service.getAllRefToMask(mask);

        Assertions.assertNotNull(users);
        Assertions.assertEquals(2, users.size());

        Map<String, Object> u1 = users.get(0);
        Assertions.assertEquals("test1", u1.get("name"));
        Assertions.assertEquals(10, u1.get("age"));
        Assertions.assertEquals(Role.ADMINISTRATOR, u1.get("role"));
        Assertions.assertFalse(u1.containsKey("id"));
        Assertions.assertFalse(u1.containsKey("rating"));

        Map<String, Object> u2 = users.get(1);
        Assertions.assertEquals("test2", u2.get("name"));
        Assertions.assertEquals(11, u2.get("age"));
        Assertions.assertEquals(Role.TEACHER, u2.get("role"));
    }
```







