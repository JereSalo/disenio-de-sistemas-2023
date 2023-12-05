// Crear funcion con alert basico

function eliminarComunidad(idComunidad) {
    // alert("prueba " + idComunidad);

    const httpRequest = new XMLHttpRequest();
    httpRequest.open('DELETE', '/administracion/comunidades/' + idComunidad, true);
    httpRequest.send();

    httpRequest.onreadystatechange = function() {
        if (httpRequest.readyState === XMLHttpRequest.DONE) {
            if (httpRequest.status === 200) {
                // alert("Comunidad eliminada");

                mostrarMensaje("<h1>¡Comunidad eliminada exitosamente!</h1>");


                // redirigir a la pagina de administracion de comunidades luego de 3 segundos
                setTimeout(function(){ window.location.href = "/administracion/comunidades"; }, 3000);
            } else {
                alert("No se pudo eliminar la comunidad");
            }
        }
    };


}


function mostrarMensaje(mensaje) {
    // Obtén el elemento con la clase "main-container"
    var mainContainer = document.getElementsByClassName("main-container")[0];

    // Crea un nuevo elemento div para el mensaje
    var mensajeDiv = document.createElement("div");

    // Agrega contenido HTML al nuevo elemento
    mensajeDiv.innerHTML = mensaje;

    // Aplica estilos CSS al nuevo elemento
    mensajeDiv.style.backgroundColor = "#4CAF50"; // Fondo verde
    mensajeDiv.style.opacity = "0.8";
    mensajeDiv.style.color = "white"; // Texto blanco
    mensajeDiv.style.padding = "20px"; // Relleno interno
    mensajeDiv.style.borderRadius = "10px"; // Bordes redondeados
    mensajeDiv.style.textAlign = "center"; // Texto centrado

    // Estilos para centrar vertical y horizontalmente
    mensajeDiv.style.position = "absolute";
    mensajeDiv.style.top = "50%";
    mensajeDiv.style.left = "50%";
    mensajeDiv.style.transform = "translate(-50%, -50%)";

    // Estilos específicos para el elemento h1 dentro del mensaje
    mensajeDiv.querySelector('h1').style.fontSize = "2em"; // Ajusta el tamaño de la fuente

    // Limpia el contenido existente dentro del contenedor principal
    mainContainer.innerHTML = "";

    // Agrega el nuevo elemento al contenedor principal
    mainContainer.appendChild(mensajeDiv);
}
