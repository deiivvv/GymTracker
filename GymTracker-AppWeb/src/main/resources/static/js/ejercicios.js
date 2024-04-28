function cargarEjercicios(url) {
	axios.get(url)
		.then(function(response) {
			const listaEjercicios = document.getElementById('lista-ejercicios');
			listaEjercicios.innerHTML = ''; // Limpiar la lista antes de agregar los nuevos ejercicios
			if (response.data.length === 0) {
				listaEjercicios.appendChild(crearCard("", ""));
			} else {
				response.data.forEach((e) => {
					/*const li = document.createElement('li');
					li.innerHTML = e.nombre;*/
					listaEjercicios.appendChild(crearCard(e.nombre,e.descripcion));
				});
			}
		})
		.catch(function(error) {
			console.error('Error al obtener los ejercicios:', error);
		});
};

function cargarEjerciciosSegunInputs() {
	const musculo = document.getElementById('idMusculoEjercicio').value;
	const equipamiento = document.getElementById('idEquipamientoEjercicio').value;
	const nombre = document.getElementById('idNombreEjercicio').value.toLowerCase();

	const url = `http://localhost:8081/ejercicios/buscar?nombre=${nombre}&musculo=${musculo}&equipamiento=${equipamiento}`;
	cargarEjercicios(url);
}

function capitalizeFirstLetter(string) {
	return string.charAt(0).toUpperCase() + string.slice(1);
}

function crearCard(nombre, descripcion) {
    let cardDiv = document.createElement("div");
    cardDiv.classList.add("card", "mt-3");

    let cardBodyDiv = document.createElement("div");
    cardBodyDiv.classList.add("card-body");

    if (nombre !== "" && descripcion !== "") {
        let titleElement = document.createElement("h5");
        titleElement.classList.add("card-title");
        titleElement.textContent = capitalizeFirstLetter(nombre);

        let rowDiv = document.createElement("div");
        rowDiv.classList.add("row");

        let col8Div = document.createElement("div");
        col8Div.classList.add("col-md-8");

        let textElement = document.createElement("p");
        textElement.classList.add("card-text");
        textElement.textContent = descripcion;

        let col4Div = document.createElement("div");
        col4Div.classList.add("col-md-4");

        let imgElement = document.createElement("img");
        imgElement.classList.add("img-fluid");
        imgElement.src = nombre + ".png";
        imgElement.alt = nombre;

        col8Div.appendChild(textElement);
        col4Div.appendChild(imgElement);
        rowDiv.appendChild(col8Div);
        rowDiv.appendChild(col4Div);
        cardBodyDiv.appendChild(titleElement);
        cardBodyDiv.appendChild(rowDiv);
    } else {
        let textElement = document.createElement("p");
        textElement.classList.add("card-text");
        textElement.textContent = "No se ha encontrado ningún ejercicio bajo tu búsqueda";

        cardBodyDiv.appendChild(textElement);
    }

    cardDiv.appendChild(cardBodyDiv);

    return cardDiv;
}

