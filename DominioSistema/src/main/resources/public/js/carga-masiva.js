function checkFile() {
    var fileInput = document.getElementById('archivo');
    var submitButton = document.getElementById('submitButton');

    if (fileInput.files.length > 0) {
        submitButton.disabled = false;
    } else {
        submitButton.disabled = true;
    }
}

function validateForm() {
    var fileInput = document.getElementById('archivo');

    if (fileInput.files.length === 0) {
        alert("Por favor, seleccione un archivo.");
        return false;
    }

    // Additional validation logic can be added here

    return true;
}