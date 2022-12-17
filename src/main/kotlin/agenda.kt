class Agenda(
    val contactos: MutableMap<String,String> = mutableMapOf()
) {


    fun validarTelefono(telefono: String) {
        val regex = r"^(\+[0-9]{2})?\s?[0-9]{9}$"

        val resultado = re.match(regex,telefono)
        if(resultado is Nothing) {
            return False
        }return True

    }


}




fun main() {
    val numeros = listOf('0','1','2','3','4','5','6','7','8','9')
    print("Introduce algo:")
    var entrada = readln()

    while (entrada != "adios") {
        if (entrada[0] == '+' || entrada[0] in numeros) {

        }


    }



}