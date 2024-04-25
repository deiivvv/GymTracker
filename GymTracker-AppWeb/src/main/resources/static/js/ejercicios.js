function cargarEjercicios(url) {
	axios.get(url)
		.then(function(response) {
			const listaEjercicios = document.getElementById('lista-ejercicios');
			listaEjercicios.innerHTML = ''; // Limpiar la lista antes de agregar los nuevos ejercicios
			if (response.data.length === 0) {
				const li = document.createElement('li');
				li.innerHTML = "No se ha encontrado ningún ejercicio bajo tu búsqueda";
				listaEjercicios.appendChild(li);
			} else {
				response.data.forEach((e) => {
					const li = document.createElement('li');
					li.innerHTML = capitalizeFirstLetter(e.nombre);
					listaEjercicios.appendChild(li);
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
