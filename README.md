# CONTINUACION DEL CURSO COMPLETO DE SPRING/SPRING BOOT

# 3ra Seccion - Inyeccion de Dependencias

## 1) Creando un nuevo proyecto (spring-boot-di) para Inyeccion de Dependencias

    - start.spring.io -> creamos el proyecto con las dependencias anterior 
        -> Spring Dev Tools, Spring Web, Thymeleaf
    - Creamos dos packages como el anterior proyecto
        -> Controllers para las request por parte de los usuarios
        -> Models para la clase que representa DATOS y LOGICA DE NEGOCIO
    - Creamos el controlador para mostrar la vista del 'index'
    - Todavia no crearemos la vista solo dejaremos el controlador.

## 2) Adding the SERVICE class

    - Clase SERVICIO: se encarga de trabajar con los datos y operar
    todo lo relacionado a la logica de negocio. Una fachada a los objetos datos
    de acceso dato, ejemplo, consulta y operaciones a la base de datos!

    - Ejemplo simple para aplicar la INYECCION DE DEPENDENCIA
    - Pero en este caso no trabajaremos con INYECCION DE DEPENDENCIA, SINO
    PARA VER LA DIFERENCIA Y COMO SE ACOPLA AL CONTROLADOR CUANDO NO USAMOS IdeD o INTERFACES

    - Creamos otro package, servicio (service) con un simple metodo de operacion
    - Por lo general, los SERVICEs interactuan con el DAO (que acceden a los datos de la base de datos)
    - Tambien comunicarse con API Rest por ejemplo, para obtener de otro servicio.

    - INSTANCIAMOS EL SERVICIO EN EL INDEX CONTROLLER -> private MiServicio miServicio = new MiServicio();
    - Y lo llamamos dentro del index.html

## 3) Implementando inyeccion de dependencia con la anotacion @AutoWired

    - @Component: Componente -> Registrar en el contenedor, queda registrado como un Bean en Spring
    - Se puede Inyectar en otros componentes de nuestra aplicacion
    - LO IMPORTANTE -> QUE ES LA LOGICA DE NEGOCIOS? DATOS, TRAER DATOS MEDIANTE API REST, USANDO ALGUN CLIENTE HTTP.. ETC
        -> UNA 'FACHADA'
    - Ver comentarios en MiServicio e IndexController.

## 4) Inyectando mediante la interface (interface)

    - DESACOPLAR completamente una implementacion en concreta como una clase
    - MiServicio -> acoplado ya es una clase (?
    - Creamos una interface para nuestro servicio -> IServicio
    - TODOS LOS COMPONENTES DE SPRING TIENEN QUE TENER UN CONTENEDOR VACIO! SIEMPRE!

## 5) INYECCION de dependencias via constructor y setters

    - Inyectar mediante el atributo
    - Ver debajo del index de IndexController.
    - EN CONSTRUCTORES podemos quitar el @AutoWired. Lo inyecta automaticamente.

## 6) Anotacion @Primary y @Qualifier

    - Creamos otra clase MiServicio pero esta vez con otro nombre.. MiServicioComplejo.. todo igual que MiServicio..
    - CLARO AHORA TENEMOS DOS METODOS operacion AL INYECTAR IServicio...
        -> QUEDA AMBIGUO..
            Error: El controlador requiere un 'single bean' aparecen dos
        COMO PROCEDEMOS?

        - 1er Metodo: MARCAMOS LA CLASE QUE QUEREMOS QUE SEA PRIMERA CON @Primary
            - Lo podemos poner el cualquiera que queramos que sea el 'MAIN'

        - 2do Metodo: @Qualifier -> Que sucede si quiero usar un poco de la PRIMARIA
        Pero en otra parte del codigo quiero usar la implementacion de la NO Primaria?
        Como hago para inyectar ---> CALIFICADORES!
            -> PODEMOS INYECTAR MEDIANTE EL NOMBRE! (miServicioComplejo -> ejemplo)
            -> @QUALIFIER TIENE PRIORIDAD OJO!
            
    - UNO U OTRO, NO USEMOS LOS DOS JUNTOS, NO TIENE SENTIDO.

## 7) Registrando componentes usando la clase configuracion y la anotacion @Bean

    - Otra alternativa de REGISTRAR componentes en SPRING.
    - COMENTAMOS LAS ANOTACIONES (@Primary, @Component..) 
    - AHORA SON SIMPLES CLASES, NO SON MANEJADAS POR EL CONTENEDOR DE SPRING, NO SE PUEDEN INYECTAR
    
    -> AQUI TENEMOS @Configuration

        - Creamos dentro de la BASE(Raiz) del PROYECTO una clase -> AppConfig
        - Aplicamos la anotacion @Configuration para determinar bien que es una config de la app.
        - METODOS QUE CREAN LOS COMPONENTES y retornan la instancia, el objeto!

            -> Ejemplo con IServicio.. Vamos a registrar con BEAN el IServicio para poder inyectarlo.

            -> Lo hacemos igual para miServicioSimple y Complejo: Nos genera nuevamente el error de ambiguedad 
                -> PODEMOS MARCAR EL QUE QUERAMOS CON PRIMARY, IGUAL CON QUALIFIER!

        CUAL USAR? Si las clases son nuestras -> @Component, @Primary a nuestras propias clases.
                   Si son de 3ros, API's de Spring ej -> @Configuration, @Bean..

    
## 8) Ejemplo Factura y relaciones entre sus componentes.

    -> PONIENDO EN PRACTICA lo aprendido

    -> Implementacion de relacion de objetos en un ejemplo de FACTURAS
        A CONSIDERAR:
            - Clase CLIENTE, Clase FACTURA, Clase ITEM_FACTURA, Clase PRODUCTO
            Y RELACIONARLAS USANDO INYECCION DE DEPENDENCIAS! NICEEEE!

    - ENTONCES: 

        -> Creamos un nuevo PACKAGE 'domain' 
        -> Creamos las clases : Cliente, Factura, ItemFactura, Producto
                - Una FACTURA tiene un cliente, tiene muchos ITEMS o LINEAS
        -> Atributos y @Component a Cliente y Factura
            - PORQUE NO @Component a ITEMFACTURA?: Porque lo que necesitamos es una LISTA de item, la LISTA va a ser componente
            Va a ir a CONFIGURATION, Porque un LIST de algo no podemos anotar como un COMPONENT.
            - PORQUE NO @Component a PRODUCTO?: Lo vamos a relacionar con cada LINEA, VAMOS A CREAR Producto
            y dentro del metodo anotado con @Bean se lo asignamos a la LINEA.

## 9) Registrando componente ItemFactura

    -> CREAMOS EL COMPONENTE DE LISTADO DE ITEM FACTURA PARA INYECTAR EN LIST DE FACTURA (ITEMS)

    - CREAMOS CONSTRUCTORES en ITEMFACTURA y en PRODUCTO  
    - Vamos a APPCONFIG y creamos el BEAN para itemFactura.

## 10) Creando el controlador FacturaController y su vista

## 11) Registramos otras lineas para la factura
    
    - itemFacturaOficina con @Primary o @Qualifier

## 12) Ciclo de vida del Component

    - POST, CONSTRUCT y PRE destroy  
    - Metodo para inicializar un componente justo despues de que el contenedor de spring cree e instancie este objeto. (POST)
    - Que ejecute alguna tarea antes de que el contenedor de Spring cree o instancie el objeto (PRE)
    - En FACTURA metodo inicializar()
    - En FACTURA metodo destruir()

## 13) Contextos (Scope) de los componentes

    - SINGLETON. Se mantendra en el contenedor de SPRING, una sola instancia.
    - CONTROLLER -> SINGLETON TAMBIEN! 
    - Se puede cambiar el contexto. Ejemplo, context de request o de sesion.
    - CUIDADO! HAY QUE TENER PRESENTE
    
        -> CUANDO ES SINGLETON, NO PODEMOS TENER ATRIBUTOS DENTRO DEL CONTROLADOR QUE SEAN PROPIOS DEL USUARIO
        -> LA IDEA ES QUE TODOS ESTOS OBJETOS QUE INYECTAMOS 


                SEAN 'STATELESS'  -> IMPORTANTE!!!!!!!!!!!!!!
                QUE NO MANTENGA VALORES DE USUARIOS, COMO SESION, CARROS DE COMPRAS
                CONTEXTO DE REQUEST, SESSION.

    - @RequestScope

        -> VER EN FACTURA!
        -> Si agregamos @RequestScope en FACTURA, vamos a ver que se va agregar mas y mas el nombre de inicializar()
        -> AHORA, AGREGAMOS A CLIENTE Y YA NO SE AGREGA MAS AL NOMBRE, SE DESTRUYE Y SE GENERA EN OTRA REQUEST SIN MODIFICAR EL NOMBRE!
        -> UNO X CADA REQUEST!

    - @SessionScope

        -> Para marcar nuestro objeto como de SESION, EJEMPLO un carrito de compra o con un sistema de autenticacion
        -> ES LO QUE DURA UNA SESION, SE INICIA CUANDO SE INSTANCIA UN OBJETO DE ESTE COMPONENTE 
        Y SE TERMINA CUANDO CERREMOS EL NAVEGADOR
        PERO TAMBIEN CUANDO SE PRODUCE UN TIMEOUT O CUANDO SE INVALIDA LA SESION
        
        -> implement SERIALIZABLE (VER MAS DE ESTO -> IMPORTANTE)
        
    - @ApplicationSession 

        -> Ver mas despues!






        



