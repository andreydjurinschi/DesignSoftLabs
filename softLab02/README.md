### lab 02 "PIPELINE"


> класс, описывающий пользователя:

пустой конструктор необходим для технологии `jackson` (сериализация и десериализация)

```java
    public User(String name, String surname, LocalDate dateOfBirth, String username) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.username = username;
    }

    public User(){}

    public User(String name, String surname, LocalDate dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
    }
```

> Generic Pipeline

### Классы для работы с json

```java
// класс для чтения и преобразования json объекта в список User
public class JsonRead {

    public static List<User> deserialize() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper.readValue(new File("softLab02/src/main/java/softLab/pipelineData/users.json"),
                new TypeReference<>() {});
    }
}

// метод для записи из класса utils/JsonRead
public static void addUser(String fileName, User user) {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());

    try {
        File file = new File(fileName);
        ArrayNode arrayNode;

        if (file.exists()) {
            arrayNode = (ArrayNode) mapper.readTree(file);
        } else {
            arrayNode = mapper.createArrayNode();
        }

        ObjectNode userNode = mapper.createObjectNode();
        userNode.put("NEW_USER", "NEW_USER");
        userNode.put("name", user.getName());
        userNode.put("surname", user.getSurname());
        userNode.put("dateOfBirth", user.getDateOfBirth().toString());
        userNode.put("username", user.getUsername());

        arrayNode.add(userNode);

        mapper.writerWithDefaultPrettyPrinter().writeValue(file, arrayNode);

    } catch (IOException e) {
        e.printStackTrace();
    }
}

```

### Интерфейс конвейера

```java
public interface IPipelineStep<I, O> {
    Context<O> Execute(Context<I> context);
}
```

### Интерфейс посетителя

```java
public interface IVisitor {

    /*User gmail */
    String visitInitDataStep(InitDataStep step, StringBuilder data);
    String visitUsersWithoutUsernamesStep(UsersWithoutUsernameStep step, StringBuilder data);
    String visitGenerateUsernamesStep(
            softLab.genericPipeline.steps.dependet.GenerateUsernameStep step, StringBuilder data
    );
    String visitPrintNewUsernamesStep(PrintNewUsernamesStep step, StringBuilder data);
    
    /*User creating steps*/
    String visitAddUser(AddUserToFileStep step, StringBuilder data);
    String visitCheckIfUsernameIsNotNull(CheckIfUsernameIsNotNull step, StringBuilder data);
    String visitGenerateUsernameStep(GenerateUsernameStep step, StringBuilder data);
    String visitNormalizeNameStep(NormalizeNameSurnameStep  step, StringBuilder data);

}
```

### Класс контекста

```java
public final class Context<T> {
    private List<T> data;

    private User user; // for creating steps

    private boolean isValid = true; // for creating steps

    private boolean isDone = false;

    public Context(List<T> data) {
        this.data = (data!=null) ? data : Collections.EMPTY_LIST;
    }

    public Context(User user) {
        this.user = user;
    }

    public List<T> getData(){
        return data;
    }
    
    public User getUser() {
        return user;
    }
}

// геттеры и сеттеры
```

### Пример класса "шагов", имплементирующего интерфейс IPipelineStep, для работы со списком значений

```java
    public class GenerateUsernameStep implements IPipelineStep<User, String> {

    private StringBuilder builder = new StringBuilder();

    @Override
    public Context<String> Execute(Context<User> context) {
        List<User> users = context.getData();
        builder.append("Generating usernames for all users above");
        return new Context<>(
                context.getData()
                        .stream().map(u -> u.getName().toLowerCase() + "." + u.getSurname().toLowerCase() + "@usm.md")
                        .toList()
        );
    }

    public String accept(IVisitor visitor){
        return visitor.visitGenerateUsernamesStep(this, builder);
    }
}
```

### класс Visitor, имплементирующий интерфейс IVisitor

```java
public class Visitor implements IVisitor {

    @Override
    public String visitInitDataStep(InitDataStep step, StringBuilder data) {

        return "\n_____________Init data step is called:_____________\n" +
                "\n"
                + data +
                "\n__________________________________________________\n";
    }
}
```

### класс Pipeline

```java
public class Pipeline{
    private List<IPipelineStep<?, ? >> steps = new ArrayList<>();

    public void addStep(IPipelineStep<?, ? > step){
        steps.add(step);
    }

    public <T> Context<T> executeSteps(Context<?> data){
        Context<?> intermediateData = data;
        for(IPipelineStep<?, ? > step : steps){
            if(intermediateData.isDone()){
                System.out.println("Context stopped at " + step.getClass());
                break;
            }
            intermediateData = ((IPipelineStep<Object, Object>) step).Execute((Context<Object>) intermediateData);
        }
        return (Context<T>) intermediateData;
    }
    public String printStepsLog(Visitor visitor, Context<?> context){
        StringBuilder builder = new StringBuilder();
        for(IPipelineStep<?, ?> step : steps){
            if(context.isDone()){
                builder.append("Visitor skipped for step: ").append(step.getClass().getSimpleName()).append("\n");
                break;
            }
            builder.append(stepDescription(step, visitor));
        }
        return builder.toString();
    }

    private String stepDescription(IPipelineStep<?, ?> step, Visitor visitor){
        /* step s for one entity */
        if(step instanceof NormalizeNameSurnameStep normalizeStep){
            return normalizeStep.accept(visitor);
        } else if(step instanceof CheckIfUsernameIsNotNull checkStep){
            return checkStep.accept(visitor);
        } else if(step instanceof GenerateUsernameStep genStep){
            return genStep.accept(visitor);
        } else if(step instanceof AddUserToFileStep addStep){
            return addStep.accept(visitor);
        /* step s for list of entities */
        } else if(step instanceof InitDataStep dataSTep){
            return dataSTep.accept(visitor);
        } else if(step instanceof UsersWithoutUsernameStep usersSTep){
            return usersSTep.accept(visitor);
        } else if(step instanceof softLab.genericPipeline.steps.dependet.GenerateUsernameStep generateStep){
            return generateStep.accept(visitor);
        } else if(step instanceof PrintNewUsernamesStep printStep){
            return printStep.accept(visitor);
        }
        return "";
    }
}
```


