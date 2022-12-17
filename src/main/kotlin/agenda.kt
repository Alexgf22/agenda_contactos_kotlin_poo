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
    }

    fun anadirTelefono(telefono: String, nombreContacto: String) : MutableMap<String,String> {
        contactos[nombreContacto] = telefono
        return contactos
    }


}




fun main(): Any {
    val agenda1 = Agenda(mutableMapOf("alex" to "667275980","jose" to "668876657"))
    val numeros = listOf('0','1','2','3','4','5','6','7','8','9')
    print("Introduce algo:")
    var entrada = readln()

    while (entrada != "adios") {

        if (entrada[0] == '+' || entrada[0] in numeros) {
            val telefono: String = entrada
            if(!agenda1.validarTelefono(telefono)) {
                error("El telefono no es valido")
            }
            else {
                // Hacer que me saque el nombre del contacto con ese teléfono
                if(telefono in agenda1.contactos.values) {
                    return listOf<String>(agenda1.contactos.keys)[listOf<>(agenda1.contactos.values).indexOf(telefono)]
                }

                else if(telefono !in agenda1.contactos) {
                    nombreContacto = input("Introduce el nombre del contacto: ")
                    agenda1.contactos[nombreContacto] = telefono
                    return agenda1.contactos
                }
            }
        }


    }



}