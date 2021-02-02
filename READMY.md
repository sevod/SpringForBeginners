#Курс Spring для начинающих от Заур Трегулов.

Используем комьюнити идею. Будем использовать maven. 

Создаем новый maven проект с архитипом maven-archetype-quickstart и добавляем с сайта maven три зависимости   

`spring-context spring-core spring-beans`

#Inversion of Control (инверсия управления) и Dependency Injection (внедрение зависимости)

#Inversion of Control (инверсия управления)

###Spring Container
Является ответственным за создание и управление объектами.

В этом контейнере будут находится созданные одъекты и по необходимости мы их будет оттуда получать.

Контейнер читет конфиг файл и создает объекты которые описаны в конфиг файле.

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

В конфиг файле мы описываем зависимости между обьектами (бинами) и на основании этого српинг сам внедряет эти зависимости.

DI - это аутсорсинг добавления/внедрения зависимостей. Например, к хозяину добавит его питомца как в файле Test3.java. DI делает объекты нашего приложения `слабо зависимыми` друг от друга.

###Способы внедрения зависимостей
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
- Spring AOP по умолчанию поддерживает AOP. Но только саму распространенную и необходимую.
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

####@Before

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

                 