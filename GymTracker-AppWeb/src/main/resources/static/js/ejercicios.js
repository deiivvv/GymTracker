function cargarEjercicios(url, callback) {
	axios.get(url)
		.then(function(response) {
			const listaEjercicios = document.getElementById('lista-ejercicios');
			listaEjercicios.innerHTML = ''; // Limpiar la lista antes de agregar los nuevos ejercicios
			if (response.data.length === 0) {
				listaEjercicios.appendChild(crearCard("", ""));
			} else {
				response.data.forEach((e) => {
					listaEjercicios.appendChild(crearCard(e.nombre,e.descripcion, e.id));
				});
			}

            if(Boolean(document.getElementById("idCrearEjercicio").value)){
                seleccionable();
            }
            
            if(callback!=null){
				callback();	
			}
            
		})
		.catch(function(error) {
			console.error('Error al obtener los ejercicios:', error);
		});
		

}

function cargarEjerciciosSegunInputs(callback) {
    const musculo = document.getElementById('idMusculoEjercicio').value;
    const equipamiento = document.getElementById('idEquipamientoEjercicio').value;
    const nombre = document.getElementById('idNombreEjercicio').value.toLowerCase();

    // const url = `https://gymtracker.duckdns.org:8081/ejercicios/buscar?nombre=${nombre}&musculo=${musculo}&equipamiento=${equipamiento}`;
    const url = `https://localhost:8081/ejercicios/buscar?nombre=${nombre}&musculo=${musculo}&equipamiento=${equipamiento}`;
    cargarEjercicios(url, callback);    
}

function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}

function crearCard(nombre, descripcion, id) {
	let  RUTAIMAGENES="/images/ejercicios/"
    let colDiv =document.createElement("div");
    colDiv.classList.add("col-md-4");

    let cardDiv = document.createElement("div");
    cardDiv.classList.add("card", "mt-2", "mb-2", "text-bg-light");
    cardDiv.id="idCard" + id;

    let inputHidden = document.createElement("input");
    inputHidden.type="hidden"
    inputHidden.id=id
    inputHidden.value=nombre

    let imgDiv=document.createElement("div");
    imgDiv.classList.add("d-flex", "justify-content-center");

    let imgElement = document.createElement("img");
    imgElement.classList.add("card-img-top", "mt-3");

    let cardBodyDiv = document.createElement("div");
    cardBodyDiv.classList.add("card-body");

    let textElement = document.createElement("p");
    textElement.classList.add("card-text");

    let titleElement = document.createElement("h5");
    titleElement.classList.add("card-title");

    if (nombre === "" && descripcion === "") {
        descripcion = "No se ha encontrado ningún ejercicio bajo tu búsqueda";
        RUTAIMAGENES="/images/error/"
        nombre="error";
    }
    titleElement.textContent = capitalizeFirstLetter(nombre);
    textElement.textContent = descripcion;
    imgElement.loading="lazy";
    imgElement.src = RUTAIMAGENES + nombre + ".png";
    imgElement.alt = nombre;

    colDiv.appendChild(cardDiv);
    cardDiv.appendChild(inputHidden);
    cardDiv.appendChild(imgDiv);
    imgDiv.appendChild(imgElement);
    cardDiv.appendChild(cardBodyDiv);
    cardBodyDiv.appendChild(titleElement);
    cardBodyDiv.appendChild(textElement);

    return colDiv;
}

