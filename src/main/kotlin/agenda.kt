/**
 * La clase Agenda contiene los metodos: filtrar, esTelefono, validarTelefono, anadirContactoNuevo, validarContacto
 * y listar.
 *
 * @property contactos mapa mutable que contiene los nombres de los contactos juntos a sus telefonos
 * correspondientes.
 *
 * @constructor que contiene los contactos.
 *
 */
class Agenda(
    val contactos: MutableMap<String,String> = mutableMapOf()
) {

    /**
     * El metodo filtrar lo que hace es crear un mapa mutable, despues recorre en un bucle las claves del
     * mapa de contactos y mira si el texto que se le pasa como parametro se encuentra en alguna de las
     * claves y por ultimo introduce en el mapa creado anteriormente la clave que coincida y como valor
     * el que corresponda a esa clave.
     *
     *
     * @param entrada de tipo String que introduce el usuario y de ahi se saca el texto a buscar en el mapa.
     *
     * @return MutableMap con las claves y valores que coinciden con el texto que se pasa como parametro.
     */
    fun filtrar(entrada:String): MutableMap<String, String> {
        print("A continuacion se filtrara el diccionario por la informacion dada: ")
        val dictResultante : MutableMap<String,String> =  mutableMapOf()
        val separacion = entrada.split(" ")
        val textoBuscar = separacion[1]
        for(nombre in contactos.keys) {
            if(textoBuscar in nombre) {
                val valor = contactos[nombre]
                if (valor != null) {
                    dictResultante[nombre] = valor
                }
            }
        }

        return dictResultante

    }


    /**
     * El metodo esTelefono se crea una variable inmutable 'numeros' para comprobar si el parametro de entrada
     * el primer caracter se encuentra en la lista y el siguiente caracter es tambien un numero o si
     * el primer caracter es un signo '+' para indicar que es un prefijo de telefono.
     *
     * @param entrada de tipo String que indica el usuario por pantalla.
     *
     * @return Boolean devuelve verdadero o falso segun las condiciones anteriores.
     */
    fun esTelefono(entrada: String): Boolean {
        val numeros = listOf('0','1','2','3','4','5','6','7','8','9')
        return (entrada[0] == '+') || ((entrada[0] in numeros) && (entrada[1] !in 'a'..'z'))
    }


    /**
     * El metodo validarTelefono se le pasa como parametro un telefono. Crea una variable nueva para eliminar
     * los espacios en blanco del telefono. Despues se crea la expresion regular para validar que el telefono
     * tenga o no prefijo con minimo 1 y maximo 3 digitos de prefijo y despues 9 digitos mas. Despues crea
     * otra variable donde guarda el resultado de si es valido o no.
     *
     * @param telefono de tipo String para comprobar si es valido o no.
     *
     * @return Boolean resultado devuelve true si es valido, en caso contrario false.
     */
    fun validarTelefono(telefono: String): Boolean {
        val telefonoSinEspacios = telefono.replace(" ","")
        //val r1 = Regex("[0-9]+")
        // Validar expresion del telefono
        val r1 = Regex("(\\+[\\d]{1,3})?\\s?[\\d]{9}+")

        val resultado = r1.matches(telefonoSinEspacios)
        if(resultado) {
            return true
        }
        return resultado
    }


    /**
     * Metodo anadirContactoNuevo. Se crea una variable para quitarle los espacios en blanco al telefono.
     * Despues se introduce en el mapa de contactos como clave el nombre del contacto y como valor
     * el telefono que ambos se le pasan como parametros.
     *
     * @param telefono de tipo String. Telefono del contacto nuevo.
     * @param nombreContacto de tipo String. Nombre del contacto nuevo.
     *
     * @return MutableMap devuelve el mapa de contactos una vez introducido el nuevo contacto.
     */
    fun anadirContactoNuevo(telefono: String, nombreContacto: String) : MutableMap<String,String> {
        val telefonoSinEspacios = telefono.replace(" ","")
        contactos[nombreContacto] = telefonoSinEspacios
        return contactos
    }


    /**
     * Metodo de validarContacto. Se crea una variable para quitar los espacios al nombre del contacto.
     * Despues en otra variable se guarda la expresion regular para comprobar que pueda introducir tambien
     * caracteres imprimibles. En otra variable mas se guarda el resultado de si es valido o no.
     *
     * @param nombreContacto de tipo String que se le pasa para validarlo.
     *
     * @return Boolean devuelve true si el contacto es valido y en caso contrario false.
     */
    fun validarContacto(nombreContacto: String): Boolean {
        val contactoSinEspacios = nombreContacto.replace(" ","")
        //val r2 = Regex("^[a-zA-ZÑñÁáÉéÍíÓóÚúÜü\s]+([a-zA-ZÑñÁáÉéÍíÓóÚúÜü0123456789!#$%&-/@{}ªº|'+?():;,.~_^<>`\s]?)+([a-zA-ZÑñÁáÉéÍíÓóÚúÜü]?)+$")
        val r2 = Regex("^[a-zA-ZÑñÁáÉéÍíÓóÚúÜü]+([a-zA-ZÑñÁáÉéÍíÓóÚúÜü0123456789!#$%&-/@{}ªº|'+?():;,.~_^<>`]?)+([a-zA-ZÑñÁáÉéÍíÓóÚúÜü]?)+$")

        val resultado = r2.matches(contactoSinEspacios)
        if(resultado) {
            return true
        }
        return resultado
    }


    /**
     * Metodo listar. En primer lugar, se crea una variable para meter las claves del mapa ordenadas
     * de forma alfabetica. Despues otra variable para ir metiendo cada una de las claves ordenadas
     * con su valor correspondiente.
     *
     * @return MutableMap con los contactos ordenados por el nombre alfabeticamente.
     */
    fun listar(): MutableMap<String, String> {
        println("A continuacion se ordenara el diccionario por nombres: ")
        val clavesOrdenadas = (contactos.keys).sorted()
        val contactosOrdenados : MutableMap<String,String> =  mutableMapOf()
        for(key in clavesOrdenadas) {
            val valor = contactos[key]
            if (valor != null) {
                contactosOrdenados[key] = valor
            }
        }

        return contactosOrdenados


    }



}




fun main() {
    val agenda1 = Agenda(mutableMapOf("alex" to "667275980","jose" to "668876657"))
    val numeros = listOf('0','1','2','3','4','5','6','7','8','9')
    print("Introduce algo: ")
    var entrada = readln()

    while (entrada != "adios") {


        // Con el numero de telefono sacar nombre de contacto si el telefono esta o introducir en el map
        if (agenda1.esTelefono(entrada)) {
            val telefono: String = entrada
            if(!agenda1.validarTelefono(telefono)) {
                println("El telefono no es valido")
            }
            else {
                // Hacer que me saque el nombre del contacto con ese teléfono
                if(telefono in agenda1.contactos.values) {
                    val claves = agenda1.contactos.keys.toMutableList()
                    val valores = agenda1.contactos.values.toMutableList()
                    println("El telefono: $telefono pertenece a: ${claves[valores.indexOf(telefono)]}")
                }
                // Introducir nuevo telefono junto al nombre del contacto en el map
                else if(telefono !in agenda1.contactos) {
                    println("Introduce el nombre del contacto: ")
                    val nombreContacto = readln()
                    println(agenda1.anadirContactoNuevo(telefono,nombreContacto))
                }
            }
        }


        // Nombre de contacto
        else if(entrada != "listado" && "filtra" !in entrada) {
            val nombreContacto: String = entrada
            if (!agenda1.validarContacto(nombreContacto) || nombreContacto[0] in numeros) {
                println("El nombre del contacto no es valido")
            } else {
                // Si el contacto esta en el map saca el telefono
                if(nombreContacto in agenda1.contactos) {
                    println("El telefono de: $nombreContacto es ${agenda1.contactos[nombreContacto]}")
                }
                // Si el contacto no esta en el map
                else if(nombreContacto !in agenda1.contactos) {
                    println("Introduce el numero de telefono: ")
                    val numeroTelefono = readln()
                    if (!agenda1.validarTelefono(numeroTelefono)) {
                        println("El numero de telefono no es valido")
                    } else {  // Si el contacto no esta en el map lo introduce junto al nuevo telefono
                        println(agenda1.anadirContactoNuevo(numeroTelefono,nombreContacto))
                    }
                }

            }

        }


        else if(entrada == "listado") {
            println(agenda1.listar())
        }


        else if("filtra" in entrada) {
            println(agenda1.filtrar(entrada))
        }


        print("Introduce algo: ")
        entrada = readln()


    }



}


