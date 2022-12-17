class Agenda(
    val contactos: MutableMap<String,String> = mutableMapOf()
) {


    fun validarTelefono(telefono: String): Boolean {
        //val r1 = Regex("(\+[0-9]{2})?\s?[0-9]{9}+")
        val r1 = Regex("[0-9]+")

        val resultado = r1.matches(telefono)
        if(resultado) {
            return true
        }
        return resultado
    }

    /*fun anadirTelefono(telefono: String, nombreContacto: String) : MutableMap<String,String> {
        contactos[nombreContacto] = telefono
        return contactos
    }*/


    fun validarContacto(nombreContacto: String): Boolean {
        //val r2 = Regex("^[a-zA-ZÑñÁáÉéÍíÓóÚúÜü\s]+([a-zA-ZÑñÁáÉéÍíÓóÚúÜü0123456789!#$%&-/@{}ªº|'+?():;,.~_^<>`\s]?)+([a-zA-ZÑñÁáÉéÍíÓóÚúÜü]?)+$")
        val r2 = Regex("^[a-zA-ZÑñÁáÉéÍíÓóÚúÜü]+([a-zA-ZÑñÁáÉéÍíÓóÚúÜü0123456789!#$%&-/@{}ªº|'+?():;,.~_^<>`]?)+([a-zA-ZÑñÁáÉéÍíÓóÚúÜü]?)+$")

        val resultado = r2.matches(nombreContacto)
        if(resultado) {
            return true
        }
        return resultado
    }


}




fun main() {
    val agenda1 = Agenda(mutableMapOf("alex" to "667275980","jose" to "668876657"))
    val numeros = listOf('0','1','2','3','4','5','6','7','8','9')
    print("Introduce algo:")
    val entrada = readln()

    while (entrada != "adios") {

        // Comprobar numero de telefono
        if (entrada[0] == '+' || entrada[0] in numeros) {
            val telefono: String = entrada
            if(!agenda1.validarTelefono(telefono)) {
                error("El telefono no es valido")
            }
            else {
                // Hacer que me saque el nombre del contacto con ese teléfono
                if(telefono in agenda1.contactos.values) {
                    val claves = agenda1.contactos.keys.toMutableList()
                    val valores = agenda1.contactos.values.toMutableList()
                    print(claves[valores.indexOf(telefono)])
                }

                else if(telefono !in agenda1.contactos) {
                    print("Introduce el nombre del contacto: ")
                    val nombreContacto = readln()
                    agenda1.contactos[nombreContacto] = telefono
                    print(agenda1.contactos)
                }
            }
        }


        // Comprobar nombre de contacto
        else if(entrada != "listado" && entrada != "filtra") {
            val nombreContacto: String = entrada
            if (!agenda1.validarContacto(nombreContacto)) {
                error("El nombre del contacto no es valido")
            } else {

                if(nombreContacto in agenda1.contactos) {
                    print(agenda1.contactos[nombreContacto])
                }

                else if(nombreContacto !in agenda1.contactos) {
                    print("Introduce el número de teléfono: ")
                    val telefonoNum = readln()
                    if (!agenda1.validarTelefono(telefonoNum)) {
                        error("El numero de telefono no es valido")
                    } else {
                        agenda1.contactos[nombreContacto] = telefonoNum
                        print(agenda1.contactos)
                    }
                }

            }

        }


    }



}


