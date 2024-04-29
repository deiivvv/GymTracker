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

function crearCardEjercicio(nombre){
    let container= document.createElement('div');
    container.classList.add("container");
    container.classList.add("mt-5");

    let rowDiv1 = document.createElement('div');
    rowDiv1.classList.add('row');

    let colDiv1 =document.createElement('div');
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
    let buttonSerie = document.createElement('button');
    buttonSerie.type = 'button';
    buttonSerie.classList.add('btn', 'btn-success');
    buttonSerie.textContent = 'Añadir Serie';

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
    buttonColDiv.appendChild(buttonSerie);

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