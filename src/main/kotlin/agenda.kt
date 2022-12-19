// Falta + en prefijo movil controlar en la expresion
class Agenda(
    val contactos: MutableMap<String,String> = mutableMapOf()
) {

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

    fun esTelefono(entrada: String): Boolean {
        val numeros = listOf('0','1','2','3','4','5','6','7','8','9')
        return (entrada[0] == '+') || ((entrada[0] in numeros) && (entrada[1] !in 'a'..'z'))
    }


    fun validarTelefono(telefono: String): Boolean {
        //val r1 = Regex("(\+[0-9]{2})?\s?[0-9]{9}+")
        val telefonoSinEspacios = telefono.replace(" ","")
        val r1 = Regex("[0-9]+")

        val resultado = r1.matches(telefonoSinEspacios)
        if(resultado) {
            return true
        }
        return resultado
    }

    fun anadirContactoNuevo(telefono: String, nombreContacto: String) : MutableMap<String,String> {
        val telefonoSinEspacios = telefono.replace(" ","")
        contactos[nombreContacto] = telefonoSinEspacios
        return contactos
    }


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


