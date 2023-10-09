// Obtén el elemento select por su ID
var selectEntidad = document.getElementById('entidad');

var selectEstablecimiento = document.getElementById('establecimiento');

selectEntidad.addEventListener('change', function() {

    httpRequest = new XMLHttpRequest();

    httpRequest.onreadystatechange = cambiarOpcionesEstablecimiento;
    httpRequest.open("GET", "/establecimientos/" + selectEntidad.value, true);
    httpRequest.send();

});

selectEstablecimiento.addEventListener('change', function() {

    httpRequest = new XMLHttpRequest();

    httpRequest.onreadystatechange = cambiarOpcionesServicio;
    httpRequest.open("GET", "/servicios/" + selectEstablecimiento.value, true);
    httpRequest.send();

});

function cambiarOpcionesEstablecimiento() {

    if (httpRequest.readyState === XMLHttpRequest.DONE) {
        if (httpRequest.status === 200) {
            const response = JSON.parse(httpRequest.responseText);

            // Obtén el elemento select
            const selectElement = document.getElementById('establecimiento');

            // Limpia las opciones existentes en el select
            selectElement.innerHTML = '';

            const option = document.createElement('option');
            option.text = "Seleccionar";
            option.selected = true;
            option.disabled = true;

            selectElement.appendChild(option);


            // Itera sobre la lista de elementos JSON y agrega opciones al select
            response.forEach(element => {
                const option = document.createElement('option');
                option.value = element.id;
                option.text = element.nombre;
                selectElement.appendChild(option);
            })
        } else {
            alert("There was a problem with the request.");
        }
  }

}

function cambiarOpcionesServicio() {
    if (httpRequest.readyState === XMLHttpRequest.DONE) {

        if (httpRequest.status === 200) {
            const response = JSON.parse(httpRequest.responseText);

            // Obtén el elemento select
            const selectElement = document.getElementById('servicio');

            // Limpia las opciones existentes en el select
            selectElement.innerHTML = '';

            const option = document.createElement('option');
            option.text = "Seleccionar";
            option.selected = true;
            option.disabled = true;

            selectElement.appendChild(option);

            // Itera sobre la lista de elementos JSON y agrega opciones al select
            response.forEach(element => {
                const option = document.createElement('option');
                option.value = element.id;
                option.text = element.nombre;
                selectElement.appendChild(option);
            })
        } else {
            alert("There was a problem with the request.");
        }
   }

}

