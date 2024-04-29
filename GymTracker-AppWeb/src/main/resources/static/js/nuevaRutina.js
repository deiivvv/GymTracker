function seleccionable(){
	
	document.querySelectorAll("[id^='idCard']")
			.forEach(function(element) {
  					element.addEventListener('click',  function() {
                        crearEjercicio(element.id);
                    });
			});
}

function crearEjercicio(id){
    let ejercicio=document.getElementById(id.replace("idCard",""));
    
    let listaEjercicios=document.getElementById("idlistaEjercicios");
    listaEjercicios.appendChild(crearCardEjercicio(ejercicio.value));

    let hideElement = document.createElement("input");
    hideElement.type = "hidden";
    hideElement.value = ejercicio.id;
    listaEjercicios.appendChild(hideElement);
    
    $('#idModalEjercicios').modal('hide');
}

function crearCardEjercicio(nombre) {
    let container = document.createElement('div');
    container.classList.add("container");
    container.classList.add("mt-5");

    let rowDiv1 = document.createElement('div');
    rowDiv1.classList.add('row');

    let colDiv1 = document.createElement('div');
    colDiv1.classList.add("col-xl")

    let card = document.createElement('div');
    card.classList.add("card")

    let cardHeaderDiv = document.createElement('div');
    cardHeaderDiv.classList.add('card-header');

    let rowDiv2 = document.createElement('div');
    rowDiv2.classList.add('row');

    let imageColDiv = document.createElement('div');
    imageColDiv.classList.add('col-md-3');

    let image = document.createElement('img');
    image.src = nombre + '.jpg';
    image.classList.add('img-fluid');
    image.alt = nombre;

    let titleColDiv = document.createElement('div');
    titleColDiv.classList.add('col-md-6', 'd-flex', 'justify-content-center', 'align-items-center');

    let title = document.createElement('h5');
    title.classList.add('card-title');
    title.textContent = capitalizeFirstLetter(nombre);

    titleColDiv.appendChild(title);

    let buttonColDiv = document.createElement('div');
    buttonColDiv.classList.add('col-md-3');
    let buttonAddSerie = document.createElement('button');
    buttonAddSerie.type = 'button';
    buttonAddSerie.classList.add('btn', 'btn-success');
    let svgAdd = document.createElementNS("http://www.w3.org/2000/svg", "svg");
    svgAdd.setAttribute("xmlns", "http://www.w3.org/2000/svg");
    svgAdd.setAttribute("width", "16");
    svgAdd.setAttribute("height", "16");
    svgAdd.setAttribute("fill", "currentColor");
    svgAdd.setAttribute("class", "bi bi-plus-square-fill");
    svgAdd.setAttribute("viewBox", "0 0 16 16");

    let path1Add = document.createElementNS("http://www.w3.org/2000/svg", "path");
    path1Add.setAttribute("d", "M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4");
    /*buttonAddSerie.textContent = 'Añadir Serie';*/

    svgAdd.appendChild(path1Add);
    buttonAddSerie.appendChild(svgAdd);

    let buttonEliminarSerie = document.createElement('button');
    buttonEliminarSerie.type = 'button';
    buttonEliminarSerie.classList.add( "btn", "btn-danger");

    let svgEliminar = document.createElementNS("http://www.w3.org/2000/svg", "svg");
    svgEliminar.setAttribute("xmlns", "http://www.w3.org/2000/svg");
    svgEliminar.setAttribute("width", "16");
    svgEliminar.setAttribute("height", "16");
    svgEliminar.setAttribute("fill", "currentColor");
    svgEliminar.setAttribute("class", "bi bi-trash");
    svgEliminar.setAttribute("viewBox", "0 0 16 16");

    let path1Eliminar = document.createElementNS("http://www.w3.org/2000/svg", "path");
    path1Eliminar.setAttribute("d", "M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0z");

    let path2Eliminar = document.createElementNS("http://www.w3.org/2000/svg", "path");
    path2Eliminar.setAttribute("d", "M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4zM2.5 3h11V2h-11z");

    svgEliminar.appendChild(path1Eliminar);
    svgEliminar.appendChild(path2Eliminar);
    buttonEliminarSerie.appendChild(svgEliminar);

    container.appendChild(rowDiv1)
    rowDiv1.appendChild(colDiv1)
    colDiv1.appendChild(card);
    card.appendChild(cardHeaderDiv);
    cardHeaderDiv.appendChild(rowDiv2)

    rowDiv2.appendChild(imageColDiv);
    rowDiv2.appendChild(titleColDiv);
    rowDiv2.appendChild(buttonColDiv);

    imageColDiv.appendChild(image);
    titleColDiv.appendChild(title);
    buttonColDiv.appendChild(buttonAddSerie);
    buttonColDiv.appendChild(buttonEliminarSerie);

    let cardBodyDiv = document.createElement('div');
    cardBodyDiv.classList.add('card-body');

    let rowDiv3 = document.createElement('div');
    rowDiv3.classList.add('row');

    let colDiv3 = document.createElement('div');
    colDiv3.classList.add('col-md-4');

    card.appendChild(cardBodyDiv);
    cardBodyDiv.appendChild(rowDiv3);
    rowDiv3.appendChild(colDiv3);

    return container;

}