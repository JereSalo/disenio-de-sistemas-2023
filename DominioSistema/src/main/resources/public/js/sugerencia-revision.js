function agradecer() {
    alert("Gracias por su respuesta!");

    window.location.href = "/";
}

function enviarFormulario(){
    var formulario = document.getElementById("form-respuesta");

    formulario.submit();
}
