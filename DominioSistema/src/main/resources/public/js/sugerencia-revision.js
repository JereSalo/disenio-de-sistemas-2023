// function agradecer() {
//     // alert("Gracias por su respuesta!");
//
//     window.location.href = "/";
// }

function agradecer() {
    var todoElResto = document.getElementById("todo-el-resto");
    var mensajeAgradecimiento = document.getElementById("agradecimiento");
    mensajeAgradecimiento.style.display = "block";
    todoElResto.style.display = "none";

    // 5 Seconds sleep
    setTimeout(function(){
        window.location.href = "/";
    }
    , 5000);
}


function enviarFormulario(){
    let formulario = document.getElementById("form-respuesta");

    formulario.submit();
}


const yes_button = document.getElementById("yes-button");
const no_button = document.getElementById("no-button");

const popup = document.getElementById("popup");
const cancelButton = document.getElementById("cancelButton");
const proceedButton = document.getElementById("proceedButton");
let currentButton; // Variable para rastrear el botón que inició el popup

function mostrarPopup(button) {
    currentButton = button; // Almacenar el botón actual
    popup.style.display = "block";
}

function ocultarPopup() {
    popup.style.display = "none";
}

yes_button.addEventListener("click", () => {
    mostrarPopup(yes_button);
});

no_button.addEventListener("click", () => {
    mostrarPopup(no_button);
});

cancelButton.addEventListener("click", () => {
    ocultarPopup();
});

proceedButton.addEventListener("click", () => {
    if (currentButton === no_button) {
        agradecer();
    } else if (currentButton === yes_button) {
        enviarFormulario();
    }
    ocultarPopup();
});