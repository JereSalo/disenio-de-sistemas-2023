// Obtén el elemento select por su ID
var selectRol = document.getElementById('rol');

selectRol.addEventListener('change', function() {
    
    httpRequest = new XMLHttpRequest();

    if (document.getElementById("comunidad-label")) document.getElementById("comunidad-label").remove();
    if (document.getElementById("prestadora-de-servicio-label")) document.getElementById("prestadora-de-servicio-label").remove();
    if (document.getElementById("organismo-de-control-label")) document.getElementById("organismo-de-control-label").remove();

    if (document.getElementById("comunidad")) document.getElementById("comunidad").remove();

    if (document.getElementById("organismo-de-control")) document.getElementById("organismo-de-control").remove();

    if (document.getElementById("prestadora-de-servicio")) document.getElementById("prestadora-de-servicio").remove();

    if (selectRol.value == '1'){

        document.getElementById("submit-button").remove();

        httpRequest.onreadystatechange = desplegarComunidades;
        httpRequest.open("GET", "/comunidades", true);
        httpRequest.send();
    }

    else if (selectRol.value == '2'){
        
        document.getElementById("submit-button").remove();

        httpRequest.onreadystatechange = desplegarOrganismosDeControlLuegoPrestadoras;
        httpRequest.open("GET", "/organismos-de-control", true);
        httpRequest.send();
    }

    else if(selectRol.value == '5'){

        document.getElementById("submit-button").remove();

        httpRequest.onreadystatechange = desplegarOrganismosDeControl;
        httpRequest.open("GET", "/organismos-de-control", true);
        httpRequest.send();

    }

    else if(selectRol.value == '6'){

        document.getElementById("submit-button").remove();

        httpRequest.onreadystatechange = desplegarPrestadorasDeServicios;
        httpRequest.open("GET", "/prestadoras-de-servicios", true);
        httpRequest.send();

    }

});

function desplegarComunidades(){
    if (httpRequest.readyState === XMLHttpRequest.DONE) {
        if (httpRequest.status === 200) {
            const response = JSON.parse(httpRequest.responseText);

            const formUsuario = document.getElementById('form-usuario');

            const label = document.createElement('label');

            label.innerHTML = 'Comunidad';
            label.id = "comunidad-label";

            formUsuario.appendChild(label);

            const select = document.createElement('select');

            select.name="comunidad"
            select.id="comunidad"

            const option1 = document.createElement('option');

            option1.text = "Seleccionar";
            option1.selected = true;
            option1.disabled = true;

            select.appendChild(option1);


            // Itera sobre la lista de elementos JSON y agrega opciones al select
            response.forEach(element => {
                const option = document.createElement('option');
                option.value = element.id;
                option.text = element.nombre;
                select.appendChild(option);
            })
            formUsuario.appendChild(select);

            const submit = document.createElement('input');
            submit.type="submit";
            submit.value="Actualizar";
            submit.id="submit-button";

            formUsuario.appendChild(submit);
        } else {
            alert("There was a problem with the request.");
        }
    }
}

function desplegarOrganismosDeControl(){

if (httpRequest.readyState === XMLHttpRequest.DONE) {
        if (httpRequest.status === 200) {
            const response = JSON.parse(httpRequest.responseText);

            const formUsuario = document.getElementById('form-usuario');

            const label = document.createElement('label');

            label.innerHTML = 'Organismo de Control';
            label.id = "organismo-de-control-label";

            formUsuario.appendChild(label);

            const select = document.createElement('select');

            select.name="organismo-de-control"
            select.id="organismo-de-control"

            const option1 = document.createElement('option');

            option1.text = "Seleccionar";
            option1.selected = true;
            option1.disabled = true;

            select.appendChild(option1);


            // Itera sobre la lista de elementos JSON y agrega opciones al select
            response.forEach(element => {
                const option = document.createElement('option');
                option.value = element.id;
                option.text = element.nombre;
                select.appendChild(option);
            })

            formUsuario.appendChild(select);

            const submit = document.createElement('input');
            submit.type="submit";
            submit.value="Actualizar";
            submit.id="submit-button";

            formUsuario.appendChild(submit);


        } else {
            alert("There was a problem with the request.");
        }
    }
}

function desplegarOrganismosDeControlLuegoPrestadoras() {

    if (httpRequest.readyState === XMLHttpRequest.DONE) {
        if (httpRequest.status === 200) {
            const response = JSON.parse(httpRequest.responseText);

            const formUsuario = document.getElementById('form-usuario');

            const label = document.createElement('label');

            label.innerHTML = 'Organismo de Control';
            label.id = "organismo-de-control-label";

            formUsuario.appendChild(label);

            const select = document.createElement('select');

            select.name="organismo-de-control"
            select.id="organismo-de-control"

            const option1 = document.createElement('option');

            option1.text = "Seleccionar";
            option1.selected = true;
            option1.disabled = true;

            select.appendChild(option1);


            // Itera sobre la lista de elementos JSON y agrega opciones al select
            response.forEach(element => {
                const option = document.createElement('option');
                option.value = element.id;
                option.text = element.nombre;
                select.appendChild(option);
            })

            formUsuario.appendChild(select);

            httpRequest = new XMLHttpRequest();

            httpRequest.onreadystatechange = desplegarPrestadorasDeServicios;
            httpRequest.open("GET", "/prestadoras-de-servicios", true);
            httpRequest.send();

        } else {
            alert("There was a problem with the request.");
        }
  }

}

function desplegarPrestadorasDeServicios() {
    if (httpRequest.readyState === XMLHttpRequest.DONE) {

        if (httpRequest.status === 200) {
            const response = JSON.parse(httpRequest.responseText);

            const formUsuario = document.getElementById('form-usuario');

            const label = document.createElement('label');

            label.innerHTML = 'Prestadora de Servicio';
            label.id = "prestadora-de-servicio-label";

            formUsuario.appendChild(label);

            const select = document.createElement('select');

            select.name="prestadora-de-servicio"
            select.id="prestadora-de-servicio"

            const option1 = document.createElement('option');

            option1.text = "Seleccionar";
            option1.selected = true;
            option1.disabled = true;

            select.appendChild(option1);

            // Itera sobre la lista de elementos JSON y agrega opciones al select
            response.forEach(element => {
                const option = document.createElement('option');
                option.value = element.id;
                option.text = element.nombre;
                select.appendChild(option);
            })

            formUsuario.appendChild(select);

            const submit = document.createElement('input');
            submit.type="submit";
            submit.value="Actualizar";
            submit.id="submit-button";

            formUsuario.appendChild(submit);
        } else {
            alert("There was a problem with the request.");
        }
   }

}

