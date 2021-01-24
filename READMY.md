#Курс Spring для начинающих от Заур Трегулов.

Используем комьюнити идею. Будем использовать maven. 

Создаем новый maven проект с архитипом maven-archetype-quickstart и добавляем с сайта maven три зависимости   

`spring-context spring-core spring-beans`

#Inversion of Control (инверсия управления)

###Основное назначение Spring Container:
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

Создаем в нем бин bean
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