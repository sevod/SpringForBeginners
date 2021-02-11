#Курс Spring для начинающих от Заур Трегулов.

Используем комьюнити идею. Будем использовать maven. 

Создаем новый maven проект с архитипом maven-archetype-quickstart и добавляем с сайта maven три зависимости   

`spring-context spring-core spring-beans`

#Inversion of Control (инверсия управления) и Dependency Injection (внедрение зависимости)

#Inversion of Control (инверсия управления)

###Spring Container
Является ответственным за создание и управление объектами.

В этом контейнере будут находиться созданные объекты и по необходимости мы их будет оттуда получать.

Контейнер читает конфиг файл и создает объекты которые описаны в конфиг файле.

###Основные функции Spring Container:
Видео 01.04
1. IoC - инверсия управления или Аутсортинг создания и управления объектами. Т.е. передача программистом прав на создание и управление обьктами Spring-у.
2. DI - Dependency Injection Внедрение зависимостей

Спринг контейнер будет создавать объекты. Какие именно описано в конфигурационом файле.

###Способы конфигурации Spring Container:
- XML file
- Annotations + XML file
- Java code


###applicationContext.xml
Cоздаем applicationContext.xml начало заполняю его из файла в курсе. Это наймспейсы. Это будет наш `конфигурационный файл`. 

Создаем в нем бин bean. В данном случае должен быть или конструктор по умолчанию без параметров или не каких конструкторов.
````
    <bean id="myPet"
          class="org.sevod.spring_introducion.Dog">        
    </bean>
````

###Spring Bean (Bean)
Это объект который создается и управляется Spring Container

Создаем `спринг контейнер` в файле test.java и получаем нашего пета который и есть Bean

````
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("aplicationContext.xml");
    Pet pet = context.getBean("myPet", Pet.class);
````

В этот раз мы получили собаку, но мы можем получить и кошку. Для этого нужно будет изменить настройки в конфигурационном файле.

Обязательно! Закрываем контекст

`context.close();`

#Dependency Injection (внедрение зависимости)

В конфиг файле мы описываем зависимости между объектами (бинами) и на основании этого спринг сам внедряет эти зависимости.

DI - это аутсорсинг добавления/внедрения зависимостей. Например, к хозяину добавит его питомца как в файле Test3.java. DI делает объекты нашего приложения `слабо зависимыми` друг от друга.

###Способы внедрения зависимостей (DI)
1. Конструктор
2. Сетторы
3. Autowiring

###DI через конструктор
В конфиг файле спринга создаем новый бин и указываем параметры конструктора куда передаем ранее созданый бин пета. `<constructor-arg ref="myPet"/>`

    <bean id="myPerson" class="org.sevod.spring_introducion.Person">
        <constructor-arg ref="myPet"/>
    </bean>

###DI через setter
В Person создаем setter для Pet и конструктор без аргументов.

Изменяем бин myPerson в конфиг файле. Добавляем `<property name="pet" ref="myPet"/>`. pet в данном случае означает что надо использовать setPet в Person и передать туда пета (myPet).

    <bean id="myPerson" class="org.sevod.spring_introducion.Person">
        <property name="pet" ref="myPet"/>
    </bean>

###Внедрение строк и других значений.

В класс Person добавляем String surname и int age и делаем гетер и сетор. В конфиг файле добавляем:

    <property name="surname" value="Ivan"/>
    <property name="age" value="33"/>

###Внедрение строк и других значений из properties файла

Создаем в ресурсах файл `myApp.properties` и заполяем его. 

В конфиг файле указываем какой properties файл ему читать. 

    <context:property-placeholder location="classpath:myApp.properties"/>

classpath - это где находится resources.

изменяем конфиг файл

    <property name="surname" value="${person.surname}"/>
    <property name="age" value="${person.age}"/>

Теперь данные читаются из файла `myApp.properties`

Писать `person.surname` не обязаетльно, можно в одно слова например `surname`. Но используем класс, что бы не запутаться.


##Bean scope 
Scope (область видимости) определяет:
- Жизненный цикл бина
- возможное количество создаваемых бинов

####Виды bean scope:
- singleton 
- prototype
- request
- session
- global-session

####singleton 

создается по умолчанию. 

Создается сразу после прочтения Spring Container-ом конфиг файла. 

`scope="singleton"`. stateless объект.

    <bean id="myPerson" class="org.sevod.spring_introducion.Person" scope="singleton">

####prototype

Создается только после обращения к Spring Container-у с помощью метода getBean.

При каждом обращении создается новый Bean в Spring Container-е

Подходит для stateful объектов.

    <bean id="myPerson" class="org.sevod.spring_introducion.Person" scope="prototype">

####init-method

Вызывается после создания бина и внедрения зависимостей.

####destroy-method

Вызывается перед остановкой приложения.

####Создание init и destroy методов

- Модификатор доступа (access modifier) может быть любым. 
- Ретерн тайп тоже может быть любым (обычно void). 
- Методы должны быть без параметров.
- Для scope="prototype" метод инит вызывается для каждого обьекта, метод дестрой не вызывается. Программист делает это самостоятельно.

В самом классе создаем два метода, можем дать им любые названия. Далее в конфиге созадем `init-method="init" destroy-method="destroy"`

    <bean id="myPet"
          class="org.sevod.spring_introducion.Cat"
          init-method="init"
          destroy-method="destroy">
    </bean>    

#Конфигурации с помощью аннотаций @
Что бы спринг понимал где искать наши аннотации, в конфиг файл добавляем строку

        <context:component-scan base-package="org.sevod.spring_introducion"/>
        
####@Component

`@Component` - ищется первой и создается(регистрируется) бин в Спринг контейнере.

Теперь на нужные нам классы навешиваем аннотации, в кавычках id бина (не обязательный, будет id "cat").  

    @Component("catBean")
    public class Cat implements Pet{
    
Если две заглавных буквы в назавнии класса будет id с большой буквы. id = MCat
        
    @Component
    public class MCat implements Pet{   
    
#DI(внедрение зависимостей) с помощью аннотаций    
    
####@Autowired

Автосвязывание. Используется для внеднения зависимостей (DI). 

Используется в:
- Конструктор.
- Сеттер.
- Поле.

####@Autowired для конструктора (Constructor injection)

    @Autowired
    public Person(Pet pet) {
        System.out.println("Person Bean is created");
        this.pet = pet;
    }
    
Поиск бина для связывания идет по типу.   

Если в классе только один конструктор, то со Спринг 4.3  @Autowired сработает, автоматически.    

####@Autowired для сеттеров (Setter injection)

    @Autowired
    public void setPet(Pet pet) {
    
####@Autowired для поля (Field injection)

    @Autowired
    private Pet pet;
    
Сеттеры при этом не используются.        

####@Qualifier

Используем в паре с @Autowired, когда несколько бинов, что бы указать какой бин использовать.

Пример с полем

    @Autowired
    @Qualifier("dog")
    private Pet pet;

Пример с полем    
    
    @Autowired
    @Qualifier("dog")
    public void setPet(Pet pet) {        
    
Пример для конструктора

    @Autowired
    public Person(@Qualifier("dog") Pet pet) {    
    
####@Value
Используем что бы заполнить значение полей. При этом нет необходимость в сетерах.

    @Value("Ivanov")
    private String surname;
    @Value("33")
    private int age;
    
Добавляем проперти в конфиг файл

    <context:property-placeholder location="myApp.properties"/>   

Меняем Value
    
    @Value("${person.surname}")
    private String surname;
    @Value("${person.age}")
    private int age;    
    
####@Scope

- @Scope("singleton")
- @Scope("prototype")


    @Component("personBean")
    @Scope("prototype")

####@PostConstruct (init method) 
Эта и следующая анотация, требует установки через мавен, поскольку в Java9 стала устаревшей, а в Java11 удалена.

Устанавливаем с мавен Javax Annotation API.

####@PreDestroy (destroy method)

#Конфигурация Spring Containera-a с помощью Java кода.
###Первый способ
Для создания конфигурации используем класс MyConfig (можно назвать по другому) с аннотациями

    @Configuration
    @ComponentScan("org.sevod.spring_introduction")
    public class MyConfig {
    
Для получения контекста (Spring Containera):

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
####@Configuration
`@Configuration` Означает что данный класс является конфигурацией.
####@ComponentScan
`@ComponentScan("org.sevod.spring_introducion")` мы показываем какой пакет нужно сканировать на наличие бинов и разных аннотаций.

###Второй способ
Сканирование не используем и аннотацию @Component не используем

    @Configuration
    public class MyConfig {

Все бины и DI описываем внутри конфигурации (класс MyConfig в этом случае). Получение контекста:

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

####@Bean
Создаем бин cat. Бин id для нового бина в данном случае будет catBean. При необходимости указываем Scope

    @Bean
    @Scope("singleton")
    public Pet catBean(){
        return new Cat();
    }
    
А так мы создаем Person и делаем DI в Person (засунули туда кота).    
    
    @Bean
    public Person personBean(){
        return new Person(catBean());
    }    

####@PropertySource
Используется для того что бы указать расположение файла properties.

    @Configuration
    @PropertySource("classpath:myApp.properties")
    public class MyConfig {


#Aspect Oriented Programming (Аспектно ориентированное программирование)

`AOP` - парадигма, основанная на идее разделения основного и служебного функционала. Служебный функционал записывается в Aspect-классы.

К сквозному функционалу относят:
- Логирование
- Проверка прав
- Обработка транзакций
- Обработка исключений
- Кэширование
- И т.д.

Будем создавать `Aspect-классы`

`AOP Proxy` - это промежуточное звено. При обращении к какому ту методу все идет через прокси.

AOP frameworks:
- Spring AOP по умолчанию поддерживает AOP. Но только самую распространенную и необходимую функциональность.
- AspectJ AOP фреймворк. Предоставляет всю функциональность в отличии от спринга.

##Создаем приложение AOP
все будем делать в пакете aop

####@EnableAspectJAutoProxy - позволяет использовать Spring AOP Proxy. Навешиваем на класс MyConfig.

####@Aspect - навешивается на класс-аспект.
Что бы заработал надо установить пакет мавен "aspectj weaver" и почему то мне еще понадобилось "aspectjrt"

    @Component
    @Aspect
    public class LoggingAspect {

####Advice
Это термин из AOP означающий что должно произойти при вызове метода (в нашем случае getBook).

####Advice Типы
- Before выполняется до метода с основной логикой.
- After returning выполнятся после нормального окончания метода с основной логикой.
- After throwing выполняется после основного метода если было исключение.
- After / After finally выполняется после окончания метода с основной логикой.
- Around выполняется до и после метода с основной логикой.

####@Before (Advice)

    @Before("execution(public void getBook())")
    public void beforeGetBookAdvice(){
    
Строка  `@Before("execution(public void getBook())")` называется `Pointcut`. Читается как перед выполнением(execution) метода void getBook().

####Pointcut 
- это выражение, описывающее где должен быть применён Advice.

####AspectJ Pointcut expression language
Используется в Spring AOP. Это определенные правила написания выражений для создания Pointcut.

####шаблон для написания pointcut
часть элементов шаблона не обязательна, после этих элементов стоит "?"

    execution(modifiers-pattern? return-type-puttern declaring-type-pattern? method-name-pattern(parameters-pattern)throws-pattern?)
    
Если метод подходит под шаблон, то при вызове этого метода будет вызываться и Advice. Под шаблон может подходит один или несколько методов.

`declaring-type-pattern` это класс в котором указан метод.
    
        @Before("execution(public void org.sevod.aop.UniLibrary.getBook())")
        public void beforeGetBookAdvice(){

`throws-pattern` это исключения которые выбрасывает метод.

####whildecut
мы можем использовать "*" для более широкого охвата названий.

    @Before("execution(public void get*())")
    public void beforeGetBookAdvice(){    
    
####return-type-puttern

    public String returnBook(){
        System.out.println("мы возвращаем книгу в UniLibrary");
            
не сработает для, поскольку ожидает void, а не String

    @Before("execution(public void returnBook())")
    
сработает @Before("execution(public * returnBook())")    

####pointcut и параметры метода
Пример:

    public void getBook(String bookName){
    
    @Before("execution(public void get*(String))")
    public void beforeGetBookAdvice(){          
    
Или

    @Before("execution(public void get*(*))")
    public void beforeGetBookAdvice(){            
    
Если нам нужно что бы подходило под любое количество параметров (ноль или больше) любого типа используем (..)      

    @Before("execution(public void get*(..))")
    public void beforeGetBookAdvice(){          
    
Если в параметрах класс

    public void getBook(Book book){

    @Before("execution(public void getBook(org.sevod.aop.Book))")
    public void beforeGetBookAdvice(){        

####@Pointcut - Объявление pointcut    
Один раз объявляем и используем сколько угодно раз. Если сделать public можно использовать в других классах-аспектах

    @Pointcut("execution(* get*())")
    private void allGetMethods(){}

    @Before("allGetMethods()")
    public void beforeGetLoggingAdvice(){

####Комбинирование pointcut
Это объединение Poitcut-ов с помощью && || !    

    @Pointcut("execution(* org.sevod.aop.UniLibrary.get* ())")
    private void allGetMethodsFromUniLibrary(){}

    @Pointcut("execution(* org.sevod.aop.UniLibrary.return* ())")
    private void allReturnMethodsFromUniLibrary(){}

    @Pointcut("allGetMethodsFromUniLibrary() || allReturnMethodsFromUniLibrary()")
    private void allGetAndReturnMethodsFromUniLibrary(){}
    
Еще пример && и !

    @Pointcut("execution(* org.sevod.aop.UniLibrary.*())")
    private void allMethodsFromUniLibrary(){}

    @Pointcut("execution(public void org.sevod.aop.UniLibrary.returnMagazine()))")
    private void returnMagazineFromUniLibrary(){}

    @Pointcut("allMethodsFromUniLibrary() && !returnMagazineFromUniLibrary()")
    private void allMethodsExceptReturnMagazineFromUniLibrary(){}        
    
###Порядок выполнения Aspect-ов
Если мы хотим контролировать порядок выполнения, мы должны разместить методы в разные Aspect-классы. И использовать аннотацию @Order
####@Order

    @Component
    @Aspect
    @Order(1)
    public class LoggingAspect {
    
    @Component
    @Aspect
    @Order(2)
    public class SecurityAspect {    
    
###Join Point
Это точка/момент времени когда применяется Advice. Прописывается в параметрах метода Advice `JoinPoint joinPoint`. Получаем информацию о сигнатуре и параметрах этого метода.

#####MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
    @Before("MyPointCuts.allAddMethods()")
    public void beforeAddLoggingAdvice(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        System.out.println("methodSignature = " + methodSignature);
        System.out.println("methodSignature.getMethod = " + methodSignature.getMethod());
        System.out.println("methodSignature.getReturnType = " + methodSignature.getReturnType());
        System.out.println("methodSignature.getName = " + methodSignature.getName());    
        
#####Object[] arguments = joinPoint.getArgs();

        if (methodSignature.getName().equals("addBook")){
            Object[] arguments = joinPoint.getArgs();
            for(Object obj: arguments){
                if(obj instanceof Book){
                    Book myBook = (Book) obj;
                    System.out.println("Информация о книге: название - " + myBook.getName() +
                            "; автор - " + myBook.getAuthor() +
                            "; год издания - " + myBook.getYearOfPublication());
                }
                else if (obj instanceof String){
                    System.out.println("Книгу в библиотеку добавил " + obj);
                }
            }
        }    
        
####@AfterReturning (Advice)
Выполняется только после нормального окончания метода с основной логикой, но до присвоения результата этого метода какой либо переменной.

    @AfterReturning("execution(* getStudents())")
    public void afterReturningStudentsLoggingAdvice(){

Метод AfterReturning может перехватывать результат таргет метода и делать с ним определенную работу. Что бы перехватить результат используем `returning = "students"` и `List<Student> students`

    @AfterReturning(pointcut = "execution(* getStudents())", returning = "students")
    public void afterReturningStudentsLoggingAdvice(List<Student> students){
        Student firstStudent = students.get(0);
        String nameSurname = firstStudent.getNameSurname();
        nameSurname = "Mr. " + nameSurname;
        firstStudent.setNameSurname(nameSurname);
        double avgGrade = firstStudent.getAvgGrade();
        avgGrade++;
        firstStudent.setAvgGrade(avgGrade);
        
Поскольку тут у нас уже несколько элементов `@AfterReturning(pointcut = "execution(* getStudents())", returning = "students")` мы пишем уже их названия в параметрах Advice. `pointcut` и `returning`

####@AfterThroeing (Advice)        
Выполняется после окончания работы метода, если было выброшено исключение.

    @AfterThrowing("execution (* getStudents())")
    public void afterThrowingGetStudentsAdvice(){       
    
Можно получить параметры исключения

    @AfterThrowing(pointcut = "execution (* getStudents())", throwing = "exeption")
    public void afterThrowingGetStudentsAdvice(Throwable exeption){    
    
####@After
Выполняется после завершения метода. Вне зависимости от исключения. Нет доступа к результатам метода.   

    @After("execution (* getStudents())")
    public void afterGetStudentsAdvice(){
    
###@Around (Advice)
Выполняется до и после основной логики, можно получить/изменить результаты работы таргет метода, предпринять какие-либо действия если есть исключение

Around метод по умолчанию перехватывает таргет метод и он не выполняется.    
    
####ProceedingJoinPoint
Это то, что Around Advice принимает на вход от таргет метода.

Так мы получаем результат выполнения нашего таргет метода.

    Object targetMethodResult = proceedingJoinPoint.proceed();          
    
Пример:

    @Around("execution(public String returnBook())")
    public Object aroundReturnBookAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("aroundReturnBookAdvice: В библиотеку пытаются вернуть книгу.");
        long begin = System.currentTimeMillis();
        Object targetMethodResult = proceedingJoinPoint.proceed();
        targetMethodResult = "Маугли";
        long end = System.currentTimeMillis();
        System.out.println("aroundReturnBookAdvice: В библиотеку успешно вернули книгу.");
        System.out.println("aroundReturnBookAdvice: метод returnBook выполнил работу за " + (end - begin) + " миллисекунд");
        return targetMethodResult;
    }    
    
####@Around (Advice) работа с исключениями
Варианты
- ничего не делать
- обрабатывать исключения в Advice
- пробросить исключение дальше

----------------------------------------------------------

#3. Hibernate
    
###CRUD
В SQL
- CREATE команда INSERT
- READ команда SELECT
- UPDATE команда UPDATE
- DELETE команда DELETE

#### Настройки MySQL

---------------------------------
Root pwd: springcourse (а вот и нет :)

Connection: my_connection

User: spec Pwd: Spec123456

DB: my_db

####Настройка JDBC

Подключаем спринг к проекту. ПКМ на верхней папке проекта и "Add Framework support". 

Если через мавен "Hibernate Core Relocation 5.4.21", "Hibernate Commons Annotations"

В мавен добавляю `MySQL Connector/J » 8.0.23`

Заполняем файл hibernate.cfg.xml из прикрепленного файла(копи паст). Перенес его в папку resources

#####Связать класс и таблицу в БД можно 2 способами
1. С помощью XML файла
2. С помощью Java аннотаций

####Entity класс
Java-класс который отображает информацию определенной таблицы в БД.

####POJO класс (Plain Old Java Object)
Это класс удовлетворяющий нескольким условиям. private fields, setters, конструкторы без аргументов и т.д.

####@Entity
Означает что данный класс будет иметь отображение в БД. Обязательно должен быть конструктор без аргументов.
#####@Table
к какой именно таблице БД мы привязываем класс.

`name = "employees"` это имя таблицы в БД. Если название класса и таблицы в БД совпадает, то можно это не писать.

    @Entity
    @Table(name = "employees")
    public class Employee {
    
#####@Column
К какому именно из столбцов в таблице БД мы привязываем поле класса.  `name="id"` это имя столбца (В данном случае можно не писать, поскольку имена совпадают)

    @Id
    @Column(name="id")
    private int id;
          
    
#####@Id
Означает что данные столбец Primary Key

    @Id
    @Column(name="id")
    private int id;
    
#####@JPA(Java Persistence API)    

##SessionFactory
Фабрика по производству сессий. SessionFactory читает конфигурационный файл hibernate.

    import org.hibernate.cfg.Configuration; //именно этот пакет для Configuration
    
    SessionFactory factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Employee.class)
                    .buildSessionFactory();

#####Session
Это обертка вокруг подключения к базе с помощью JDBC. Через сессию мы будем работать с БД.                    
                    
    Session session = factory.getCurrentSession();     
    
#####Отправка данных в БД
    session.beginTransaction();
    session.save(emp);
    session.getTransaction().commit();
#####Закрываем SessionFactory
    factory.close();                   
    
###Полный пример SessionFactory Session Отправка данных в БД

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        try {
            Session session = factory.getCurrentSession();
            Employee emp = new Employee("Ivan", "Ivanov", "IT", 700);
            session.beginTransaction();
            session.save(emp);
            session.getTransaction().commit();
        } finally {
            factory.close();
        }    
        
#####@GeneratedValue
Стратегия для генерации Primary Key
      
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;  
    
- GenerationType.IDENTITY автоматическое увеличение столбца по правилам прописанным в БД.
- GenerationType.SEQUENCE полагается на работу Sequence.
- GenerationType.TABLE устаревший тип, используется с теми БД которые слабофункциональные.
- GenerationType.AUTO тип по умолчанию. Зависит о т типа БД с которой мы работаем.        

####Получение данных из hibernate по Id (get).

    session.beginTransaction();
    Employee employee = session.get(Employee.class, myId);
    session.getTransaction().commit();         
    
####HQL Получение данных из hibernate по произвольному полю

    session.beginTransaction();
    List<Employee> emps = session.createQuery("from Employee").getResultList();
    for (Employee e: emps) 
        System.out.println(e);
    session.getTransaction().commit();    
    
Еще примеры 
    
    List<Employee> emps = session.createQuery("from Employee where name LIKE 'Petr'").getResultList();  
    
    List<Employee> emps = session.createQuery("from Employee where name = 'Petr' and salary > 100").getResultList();
Employee и name в этих примерах, это класс и поле из Java, а не из SQL.   

####Изменение данных в БД через hibernate

    Employee employee = session.get(Employee.class, 2);
    employee.setName("Sidr");
    session.getTransaction().commit();
    
    session.beginTransaction();
    session.createQuery("update Employee set salary=1000 where name = 'Elena' ").executeUpdate();
    session.getTransaction().commit();    
     
#####Удаление объектов в БД через hibernate

    session.beginTransaction();
    Employee employee = session.get(Employee.class, 5);
    session.delete(employee);
    session.getTransaction().commit();    
    
    session.beginTransaction();
    session.createQuery("delete Employee where name = 'Elena'").executeUpdate();
    session.getTransaction().commit();
    
######Foreign Key внешний ключ        

##One-to-One

####Uni-directional 
Это однонаправленные отношения, когда одна сторона о них не знает.

####Bi-directional 
Это двуноправленные отношеия, когда обе стороны знают друг о друге.

#####@OneToOne
`cascade = CascadeType.ALL` - означает что операции выполняются не только на текущую энтити, но и на связанные энтити тоже. Например, удаление будет в обоих таблицах.
#####@JoinColumn(name = "details_id")
JoinColumn - указывает на столбец, который осуществляет связь с другим объектом.

Данная запись означет, что будет связь один к одному и поле `"details_id"` Foreign Key

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "details_id")
    private Detail empDetail;
    
#####CascadeType
- ALL для всех
- PERSIST когда сохраняем объект
- MERGE объединение
- REMOVE удаление
- REFRESH обновление состояния объекта
- DETACH 

#####Изменяем SessionFactory
Добавляем .addAnnotatedClass(Detail.class)

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Detail.class)
                .buildSessionFactory();       
                
#####Добавляем 2 зависимых энтити

    Session session = factory.getCurrentSession();
    Employee employee = new Employee("Ivan", "Ivanov", "IT", 1000);
    Detail detail = new Detail("Baku", "123456", "email@gmail.com");
    employee.setEmpDetail(detail);
    session.beginTransaction();
    session.save(employee);
    session.getTransaction().commit();    
    
##### session.close(); перенесли в секцию finally на случай исключений    
    
    finally {
        session.close();
        factory.close();
    }                
    
##One-to-One Bi-directional    
Во втором классе, в дополнение к тому что было сделано ранее в первом классе добавляем поле с первым классом. 
`mappedBy = "empDetail"` означает что связь уже налажена и ее нужно искать в классе "Employee" в поле "empDetail"
    
    @OneToOne(mappedBy = "empDetail", cascade = CascadeType.ALL)
    private Employee employee;    
    
#####Создание данных:

    Employee employee = new Employee("Nikolay", "Ivanov", "HR", 500);
    Detail detail = new Detail("New-York", "42978922", "Nikolay@gmail.com");
    employee.setEmpDetail(detail);
    detail.setEmployee(employee);
    session.beginTransaction();
    session.save(detail);
    session.getTransaction().commit();       

#####Получения данных.

    session.beginTransaction();
    Detail detail = session.get(Detail.class, 4);
    System.out.println(detail.getEmployee());
    session.getTransaction().commit();
    
#####Удаление данных

    session.beginTransaction();
    Detail detail = session.get(Detail.class, 4);
    session.delete(detail);
    session.getTransaction().commit();    
    
######Что бы не удалять все, можно поменять CascadeType

    //@OneToOne(mappedBy = "empDetail", cascade = CascadeType.ALL)
    @OneToOne(mappedBy = "empDetail", cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private Employee employee;    

При этом при удалении предварительно нужно удалить вторичный ключ

    session.beginTransaction();
    Detail detail = session.get(Detail.class, 1);
    detail.getEmployee().setEmpDetail(null); //зануляем ссылку на detail перед удалением
    session.delete(detail);
    session.getTransaction().commit();
    
##One-to-Many (Bi-directional)
настройка связи:

в первом классе

`name = "department_id"` форен кей, находится в табице которая отвечает за Many

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private Department department;    
    
во втором классе

`mappedBy = "department"` department, это поле в первой таблице

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private List<Employee> emps;        
   
так же во втором классе создаем метод для заполнения поля employee

    public void addEmployeeToDepartment(Employee employee){
        if (emps == null)
            emps = new ArrayList<>();
        emps.add(employee);
        employee.setDepartment(this); //тут мы employee присваеваем департамент
    }   
    
#####Заполенение данных

    session = factory.getCurrentSession();
    Department dep = new Department("IT", 1200, 300);
    Employee emp1 = new Employee("Zaur", "Tregulov", 800);
    Employee emp2 = new Employee("Elena", "Smirnova", 1000);
    dep.addEmployeeToDepartment(emp1);
    dep.addEmployeeToDepartment(emp2);
    session.beginTransaction();
    session.save(dep);
    session.getTransaction().commit();       
    
#####Настройка CascadeType
Что бы при удалении работника не удалялся департамент и наоборот, настраиваем CascadeType. Убираем оттуда DELETE.

    //@ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "department_id")
    private Department department;    
    
    
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE}, mappedBy = "department")
    private List<Employee> emps;    
    
###One-to-Many (Uni-directional)

Удаляем в классе Employee все поля ссылающие на Department, а в Department переписываем нотации

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private List<Employee> emps;
    
Здесь поле `department_id` Foreign Key находится в таблице Employee, а не в Department 

##Loading types
1. Eager (нетерпеливая) загрузка
2. Lazy (ленивая) загрузка (обычно используют при большом количестве данных)

####Fetch type по умолчанию 

- One-to-one   Eager
- One-to-Many  Lazy
- Many-to-One  Eager
- Many-to-Many Lazy

####Fetch type (тип выборки)

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department", fetch = FetchType.EAGER)
    private List<Employee> emps;

##Many-to-Many

####Join Table
Таблица связей, для обслуживания взаимоотношений Many-to-Many. Служебная таблица.

`Столбцы Join Table` это Foreign Keys, которые ссылаются на Primary Key связываемых таблиц.

#####@JoinTable подключаем Entity Child
- `name = "child_section"` - имя служебной таблицы в БД (Join Table)
- `joinColumns = @JoinColumn(name = "child_id")` - имя колонки в Join Table которая будет Foreign Keys для Child
- `inverseJoinColumns = @JoinColumn(name = "section_id")` - имя колонки в Join Table для другой таблицы, с которой мы налаживаем связь


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "child_section",
            joinColumns = @JoinColumn(name = "child_id"),
            inverseJoinColumns = @JoinColumn(name = "section_id")
    )

#####Все тоже самое и для второго Entity Section

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "child_section",
            joinColumns = @JoinColumn(name = "section_id"),
            inverseJoinColumns = @JoinColumn(name = "child_id")
    )
    private List<Child> children;
    
    
#####Добавляем детей в секцию (полный пример в файле Test)
 
    Section section1 = new Section("Football");
    
    Child child1 = new Child("Zaur", 5);
    Child child2 = new Child("Masha", 7);
    Child child3 = new Child("Vasya", 6);
    
    section1.addChildToSection(child1);
    section1.addChildToSection(child2);
    section1.addChildToSection(child3);
    
    session.beginTransaction();    
    session.save(section1);    
    session.getTransaction().commit();
  
#####Добавляем секции в ребенка (полный пример в файле Test2)  
    
    Section section1 = new Section("Volleyball");
    Section section2 = new Section("Chess");
    Section section3 = new Section("Math");
    
    Child child1 = new Child("Igor", 10);
    
    child1.addSectionToChild(section1);
    child1.addSectionToChild(section2);
    child1.addSectionToChild(section3);
    
    session.beginTransaction();
    session.save(child1);
    session.getTransaction().commit();
    
#####Удаление section

    Section section = session.get(Section.class, 3);
    session.delete(section);    
  
Для корректного удаления нужно корректно настроить cascade в обоих классах

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE})  
    
Но появляется пробема с сохранением.    
    
#####CascadeType.PERSIST из пакета javax.persistence
это не совсем то же самое что и save. В пакете `org.hibernate.annotations` есть отдельный метод save. PERSIST и SAVE разные вещи. SAVE есть в ALL.

#####persist решение этой проблемы (файл Test6)

    //session.save(section);    
    session.persist(section);    

-------------------------------------------------------------------------

#Spring MVC
Фреймворк для создания web приложений в основе которого лежит шаблон проектирования MVC.

#####MVC
- Model - отвечает за работу с данными. Контейнер для храненения данных.
- View - отвечает за представление данных
- Controller - отвечает за связь между View и Model. Именно здесь сосредоточена логика работы приложения.

#####Front Controller (DispatcherServlet)
Является частью спринг. Перенаправляет вохдящие http запросы, другим контролерам.

#####Spring MVC состоит
- Конфигурация Spring
- Описание Spring бинов
- Web страницы

#####JSP Java Server Page

#####JSTL Java Server Pages Standart Tag Library

##Настрока Spring MVC
1. Создаем maven-archetype-webapp 
2. Добавляем зависимости в maven: "spring-context" "spring-core" "spring-beans" "spring-webmvc" "jstl"
3. Настрока Tomcat  видео 56
4. Создаем папку java и помечаем ее как "Sources Root"
5. Конфигурируем web.xml (копипастим с курса). В нем конфигурируем DispatcherServlet.
6. Конфигурируем applicationContext.xml (копипастим с курса).

####@Controller
Это специальный компонент и анотация @Component не нужна.

#####@RequestMapping
@RequestMapping("/") анотация используемая для мапинга вэб страниц. 
В данном случае все запросы по адрессу "/" будут перенаправлены на first-view.jsp (префикс и постфикс прописаны в web.xml)

    @Controller
    public class MyController {
        @RequestMapping("/")
        public String showFirstView(){
            return "first-view";
        }
    }
    
#####Изменение URL адреса в настройках Томкат
Видео 57 8.00 "Edit configuration" -> "Deployment"

#####Работа с методом get
${param.emloyeeName} - emloyeeName получаем из get запроса

###Model
Устанавливаем в maven "JavaServlet(TM) Specification"
Model - это контейнер для хранения данных. Находясь в Controller, мы можем добавлять данные в Model и затем эти данные использовать во View.

#####HttpServletRequest параметр контролера
используем это в параметрах контролера (методе), для получения данных пришедших с http запроса.
        
#####Model -параметр в контролере, данные которые мы будем использовать далее

    @RequestMapping("/showDetails")
    public String showEmployeeDetails(HttpServletRequest request, Model model){
        String empName = request.getParameter("emloyeeName"); //emloyeeName это get параметр кторой прийдет к нам в http запросе
        empName = "Mr. " + empName;
        model.addAttribute("nameAttribute", empName);      
        
#####${nameAttribute}
А так мы потом читаем данные с модели во view (jsp файле).
    
    Yor name: ${nameAttribute}        
    
#####@RequestParam
Данная анотация позволяет читать данные непосредственно с http запроса

    @RequestMapping("/showDetails")
    public String showEmployeeDetails(@RequestParam("emloyeeName") String empName, Model model){
        empName = "Mr. " + empName;      
        
`@RequestParam("emloyeeName") String empName` - читаем данные с поля  "emloyeeName"  и помещаем в переменную "empName" 

#####@RequestMapping для Controller
@RequestMapping - может быть как для класса (контролер мэпинг), так и для метода (метод мэпинг).

Над классом, это для всех методов класса. Над методом, только для него, но адрес с класса добавляется в начало адреса для контролера.      

    @Controller
    @RequestMapping("/employee")
    public class MyController {
    
#####Ambiguous mapping    
Это ошибка. Возникает когда один и тот же url прописан в разные методы.

##Формы Spring MVC

####MVC форма input

В метод вызывающий вью добавляем в параметры модель, а внутри метода в модель добавляем класс Employee. Так мы связали модель и класс. 

    @RequestMapping("/askDetails")
    public String askEmployeeDetails(Model model){
        model.addAttribute("employee", new Employee());
        return "ask-emp-details-view";
    }
    
А вот так мы можем предзаполнить employee

    Employee emp = new Employee();
    emp.setName("Ivan");
    emp.setSurname("Ivanov");
    emp.setSalary(1000);
    model.addAttribute("employee", emp);
    return "ask-emp-details-view";    
    
#####form:form 
основная Spring форма, которая содержит в себе другие формы (форма контейнер).

#####form:input 
форма предназначенная для текста, можно использовать только одну строку.     

######Первый вью, который собирает данные

    <form:form action="showDetails" modelAttribute="employee">
        Name: <form:input path="name"/>
        Surname: <form:input path="surname"/>
        Salary: <form:input path="salary"/>
        <input type="submit" value="OK">
    </form:form>

#####<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
Строка необходимая в заголовке страницы что бы работали формы MVC.

#####@ModelAttribute
`@ModelAttribute("employee") Employee emp` - означает что метод может использовать атрибут "employee". Он автоматически будет дступен во вью.

    @RequestMapping("/showDetails")
    public String showEmployeeDetails(@ModelAttribute("employee") Employee emp){
        return "show-emp-details-view";
    }
    
А вот так мы можем менять пришедшие данные
    
        String name = emp.getName();
        emp.setName("Mr. " + name);
######Второй вью, которые отображает данные

    Your name: ${employee.name}
    Your surname: ${employee.surname}
    Your salary: ${employee.salary}    
    
#####form:select
Выпадающих список

    <form:select path="department">
            <form:option value="Information Technology" label="IT"/>
            <form:option value="Human Resources" label="HR"/>
            <form:option value="Sales" label="Sales"/>    
    </form:select>

#####form:option
     <form:option value="Information Technology" label="IT"/>
     
`label="IT"` - это то что видеть пользователь     
`value="Information` Technology" - а это значение этого поля, которое попадет в `path="department"`.  

Выводим это на экран конструкцией

    Your department: ${employee.department}
    
#####Что бы жестко не прописывать в коде form:select
Правим класс Employee. Создаем там 
    
    private Map<String, String> departments;        

И заполняем это в конструкторе

    public Employee() {
        departments = new HashMap<>();
        departments.put("Information Technology", "IT");
        departments.put( "Human Resources", "HR");
        departments.put("Sales", "Sales");
    }
    
И меняем форму

    Department: <form:select path="department">
        <form:options items="${employee.departments}"/>
    </form:select>    
    
####form:radiobutton
Добавляем в Employee 
    
    private String carBrand;         
    
В форме ввода данных

    BMW <form:radiobutton path="carBrand" value="BMW"/>
    Audi <form:radiobutton path="carBrand" value="Audi"/>
    Mercedes-Benz <form:radiobutton path="carBrand" value="Mercedes-Benz"/>
    
В форме вывода данных

    Your car: ${employee.carBrand}         
    
######Для предварительно задания данных в form:radiobutton

Изменяем конструктор Employee

    public Employee() {
        carBrands = new HashMap<>();
        carBrands.put("BMW", "BMW");
        carBrands.put("Audi", "Audi");
        carBrands.put("Mercedes-Benz", "Mercedes-Benz");
    }    
    
В форму ввода 

    <form:radiobuttons path="carBrand" items="${employee.carBrands}"/>    
    
####form:checkbox
В Employee добавляем `private String[] languages;`

В форму выбора добавляем 

    EN <form:checkbox path="languages" value="English"/>
    EN <form:checkbox path="languages" value="Deutch"/>
    EN <form:checkbox path="languages" value="French"/>
    
В форму вывода данных

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    Language (s):
    <ul>
        <c:forEach var="lang" items="${employee.languages}">
            <li>${lang}</li>
        </c:forEach>
    </ul>

######для преварительно заданных данных в Employee

    private Map<String, String > languageList;

В конструктор
    
    languageList = new HashMap<>();
    languageList.put("English", "EN");
    languageList.put("Deutch", "DE");
    languageList.put("French", "FR");      
    
В форму отображения

    <form:checkboxes path="languages" items="${employee.languageList}"/>    
    
##Валидация форм Spring MVC
####Java Standard Bean Validation API - Это спецификация
####Hibernate Validator - Это реализация JSBV API
Добавляем в maven "hibernate-validator"

#####@Size длина поля
    @Size(min = 2, message = "name must be min 2 symbols") //минимум 2 символа, и выдается сообщение
    private String name;
    
#####form:errors
В форму вывода добавляем строку. Она будет выводить ошибки.

    <form:errors path="name"/>    
#####@Valid
означает что в контролере используется валидация. В нашем случае, атрибут employee должен пройти валидацию. 
#####BindingResult
Параметр BindingResult содержит в себе возможные ошибки валидации. Этот параметр должен идти сразу после атрибута нашей модели.
    
    @RequestMapping("/showDetails")
    public String showEmployeeDetails(@Valid @ModelAttribute("employee") Employee emp, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "ask-emp-details-view";
        else
            return "show-emp-details-view";
    }    
    
#####@NotNull
Означает что данное поле обязательно к заполнению. В данном случае не сработает, потому что будет не null.
     
    @NotNull(message = "surname is required field")
    private String surname;

#####@NotEmpty
Означает что данное поле обязательно к заполнению. Не null и не пустое. Но можно обмануть и использовать пробелы.

    @NotEmpty(message = "surname is required field")
    private String surname;

#####@NotBlank
Аналог @NotEmpty, но проверяет и на пробелы.

    @NotBlank(message = "surname is required field")
    private String surname;
    
#####@Min
#####@Max
Минимальное и максимальное значение поля.

    @Min(value = 500, message = "mast be greater than 499") 
    @Max(value = 5000, message = "mast be less than 50001")
    private int salary;        
    
####@Pattern 
поле проверяется на соответствие определенному регулярному выражению.  

####Собственные анотации для валидации
видео 68
#####Создаем свою анотация CheckEmail.java и класс для валидации CheckEmailValidator.java

----------------------------------------------------------------------------------------------------------------------------

#Spring MVC + Hibernate + AOP
Все настраиваем как раньше (видео 69)

Зависимости в maven: spring-webmvc, jstl, hibernate-core, mysql-connector-java, c3p0, spring-orm

c3p0 - коннекшен пул для связи с БД. 

Конфигурируем web.xml и applicationContext.xml копипастом с папки курса.

В файле  applicationContext.xml мы сразу создаем бины `sessionFactory` и `transactionManager` что бы не создавать их потом.

###DAO data access object
Вспомогательный компонент для работы с БД.

####@Repository
это специлизированный @Component. Используется для DAO. При поиски компонентов Спринг будет регистирировать все DAO в Spring Container

#####SessionFactory + @Autowired
Можно подключать автомотически, предварительно настроив это в applicationContext.xml (конфиг файл)

    @Autowired
    private SessionFactory sessionFactory;

#####@Transactional
Можно использовать эту анотацию над методом, что бы писать меньше кода. Что бы работало надо правильно настроить applicationContext.xml (конфиг файл)

    <bean id="transactionManager"
          class="org.springframework.orm.hibernate5.HibernateTransactionManager">
        <property name="sessionFactory" ref="sessionFactory"/>
    </bean>
    
    <tx:annotation-driven transaction-manager="transactionManager" />
    
####Настройка Spring MVC + Hibernate (Описание видео 70)
1. Создаем @Entity Employee, где прописываем все связи с БД.


    @Entity
    @Table(name = "employees")
    public class Employee {    
    
2. Создаем DAO 

    
    public interface EmpolyeeDAO {
    public List<Employee> getAllEmployees();
    
3. Создаем имплементацию DAO EmployeeDAOImpl. Автоматически подключаем SessionFactory и используем @Transactional.


    @Repository
    public class EmployeeDAOImpl implements EmpolyeeDAO {
    
        @Autowired
        private SessionFactory sessionFactory;
    
        @Override
        @Transactional
        public List<Employee> getAllEmployees() {    
        
4. Создаем контролер MyController. С помощью @Autowired подключаем к нашему DAO. Получаем данные с БД, помещаем в модель и вызываем вью all-employess.


    @Controller
    public class MyController {
    
        @Autowired
        private EmpolyeeDAO empolyeeDAO;
    
        @RequestMapping("/")
        public String showAllEmployees(Model model){
            List<Employee> allEmployees = empolyeeDAO.getAllEmployees();
            model.addAttribute("allEmps", allEmployees);
            return "all-employess";        

####Service    
Контролер должен работать с Service, а не напрямую с DAO, поскольку DAO может быть несколько.
#####@Service
Отмечает класс который содержит бизнес логику. Является соединительным звеном между Controller and DAO. 
@Service это специальных @Component который при сканировании Spring окажется в Spring Container. 
@Transactional c DAO переносим в Service.

    @Service
    public class EmloyeeServiceImpl implements EmployeeService {

    @Autowired
    private EmpolyeeDAO empolyeeDAO;

    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        return empolyeeDAO.getAllEmployees();
        
        
###addNewEmployee        
#####<input type="button" value="Add" onclick="window.location.href = 'addNewEmployee'">
Во вью добавляем кнопку. Ее перехватит метод контролера который обслуживает этот путь.

    @RequestMapping("/addNewEmployee")
        public String addNewEmployee(Model model){
            Employee employee = new Employee();
            model.addAttribute("employee", employee);
    
            return "employee-info";        
            
#####новое вью

    <form:form action="saveEmployye" modelAttribute="employee">
        Name <form:input path="name"/>
        <br><br>
        Surname <form:input path="surname"/>
        <br><br>
        Department <form:input path="department"/>
        <br><br>
        Salary <form:input path="salary"/>
        <input type="submit" value="OK"/>
    </form:form>
    
вызовет контролеер, который сохранит сотрудника и редиректнет на "/" страницу. 
Что бы работало  `employeeService.saveEmployee(employee);` делаем цепочку действий добавляя этот методв Servic и EmloyeeDAO              

    @RequestMapping("/saveEmployye")
    public String saveEmloyee(@ModelAttribute("employee") Employee employee){
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }
    
###updateEmployee

####Ссылка в JSP
Ее не будет видно, мы ее используем при создании кнопки.
    
    <c:url var = "updateButton" value="/updateInfo">
        <c:param name="empId" value="${emp.id}"/>
    </c:url>

    <td>
        <input type="button" value="Update" onclick="window.location.href = '${updateButton}'">
    </td>
    
    
####<form:hidden path="id" />
скрытое поле, которе может хранить в себе информацию.  

####saveOrUpdate
метод для работы с hibernate. Если id равен нулю, это сохранение, а если не нуль, это обновление.
    
    session.saveOrUpdate(employee);  
    
####Обновление информации о сотруднике, описание процесса.
1. Во вью добавляем кнопку которая вызывает контролер "updateEmployee" и передает туда id сотрудника.
2. Создаем цепочку действий в Service и DAO для получения данных по Employee из БД. Метод getEmployee.
3. Во втором вью, созадем скрытое поле которе будет содержать id    
4. В цепочке методов в DAO меняем save на saveOrUpdate

###deleteEmployee
1. Создаем кнопку во вью аналогичную предыдущему уроку.

3. По окончанию редиректим на страницу с пользователями `return "redirect:/";`

#####id =:employeeId это параметр в запросе

    Query query = session.createQuery("delete from Employee where id =:employeeId"); // в конце, это параметр
    query.setParameter("employeeId", id);
    query.executeUpdate();
    
###Добавляем AOP
1. В maven добавляем "aspectjweaver"
2. Редактируем applicationContext.xml.   
3. Создаем пакет aspect и в нем MyLoggingAspect

#Spring REST

#####REST REpresentational State Transfer

#####@GetMapping и PostMapping
Аналоги @RequestMapping под соответствующий протокол

#Методы REST
- GET
- POST
- PUT
- DELETE

###Создаем новый проект
1. Добавляем зависимости spring-webmvc, hibernate-core, mysql-connector-java, c3p0, spring-orm, javax.servlet-api, jackson-databind (для конвертации в JSON)
2. Конфигурировать будем в MyConfig.java. Повторяем настройки из предыдущего проекта (там это все было в xml файле).
3. Для настройки Dispatcher Servlet используем класс MyWebInitializer.java
 
 
####@RestController
Это специальный Controller который управляет REST запросами и ответами

    @RestController
    @RequestMapping("/api")
    public class MyRESTController {

######Из пердыдущего проекта копируем 3 пакета.

#####/api/employees
получаем список всех сотрудников. Возврат будет в формате JSON за счет использованного нами "jackson-databind".

    @GetMapping("/employees")
    public List<Employee> showAllEmployees(){
        List<Employee> allEmployee = employeeService.getAllEmployees();
        return allEmployee;
    }

