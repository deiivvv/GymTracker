
function seleccionable(){
	
	document.querySelectorAll("[id^='idCard']")
			.forEach(function(element) {
				if(element.id!="idCardundefined"){
  					element.addEventListener('click',  function() {
                        crearEjercicio(element.id);
                    });
                }
	});
}

function crearEjercicio(id){
    let ejercicio=document.getElementById(id.replace("idCard",""));
    
    let listaEjercicios=document.getElementById("idlistaEjercicios");
    listaEjercicios.appendChild(crearCardEjercicio(ejercicio));

    $('#idModalEjercicios').modal('hide');
    $('#idModalSeriesCrear').modal('show');
}

let idEjercicio=0;
function crearCardEjercicio(ejercicio) {
    let nombre=ejercicio.value;
    let id=ejercicio.id;

    idEjercicio++;
    document.getElementById("idHidden").value=idEjercicio

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
    rowDiv2.classList.add('row', 'd-flex' , 'align-items-center');

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
    buttonColDiv.classList.add('col-md-3', 'mb-3');
    let buttonAddSerie = document.createElement('button');
    buttonAddSerie.type = 'button';
    buttonAddSerie.classList.add('btn', 'btn-success', 'mr-1');

    buttonAddSerie.setAttribute('data-bs-toggle', 'modal');
    buttonAddSerie.setAttribute('data-bs-target', '#idModalSeriesCrear');

    let inputHidden=document.createElement("input")
    inputHidden.value= idEjercicio;
    inputHidden.type="hidden"

    buttonAddSerie.addEventListener('click', function (){
        ejercicioSelecionado(inputHidden)
    });

    let svgAdd = document.createElementNS("http://www.w3.org/2000/svg", "svg");
    svgAdd.setAttribute("xmlns", "http://www.w3.org/2000/svg");
    svgAdd.setAttribute("width", "16");
    svgAdd.setAttribute("height", "16");
    svgAdd.setAttribute("fill", "currentColor");
    svgAdd.setAttribute("class", "bi bi-plus-lg");
    svgAdd.setAttribute("viewBox", "0 0 16 16");

    let path1Add = document.createElementNS("http://www.w3.org/2000/svg", "path");
    path1Add.setAttribute("d", "M8 2a.5.5 0 0 1 .5.5v5h5a.5.5 0 0 1 0 1h-5v5a.5.5 0 0 1-1 0v-5h-5a.5.5 0 0 1 0-1h5v-5A.5.5 0 0 1 8 2");
    svgAdd.appendChild(path1Add);
    buttonAddSerie.appendChild(svgAdd);

    let buttonEliminarEjercicio = document.createElement('button');
    buttonEliminarEjercicio.type = 'button';
    buttonEliminarEjercicio.classList.add("btn", "btn-danger");
    buttonEliminarEjercicio.addEventListener('click', function (){
       container.remove();
    });

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
    buttonEliminarEjercicio.appendChild(svgEliminar);

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
    buttonColDiv.appendChild(buttonEliminarEjercicio);

    let cardBodyDiv = document.createElement('div');
    cardBodyDiv.classList.add('card-body');

    let rowDiv3 = document.createElement('div');
    rowDiv3.classList.add('row');

    let colDiv3 = document.createElement('div');
    colDiv3.classList.add('col-md-12', 'table-responsive');

    let tableSeries = document.createElement('table');
    tableSeries.classList.add('table', 'table-hover');
    tableSeries.setAttribute('id', 'idTablaSeries' + idEjercicio);

    let tableSeriesHead = document.createElement('thead');
    tableSeriesHead.setAttribute('id', 'idTablaSeriesHead');
    tableSeriesHead.setAttribute("style", "color:#fff; background-color:#000")

    let tableSeriesBody = document.createElement('tbody');
    tableSeriesBody.setAttribute('id', 'idTablaSeriesBody' + idEjercicio);

    let tr=document.createElement("tr");
    let tdPeso=document.createElement("th");
    let tdRepes=document.createElement("th");
    let tdActions=document.createElement("th");

    tdPeso.innerHTML="Peso(kg)"
    tdRepes.innerHTML="Repeticiones"
    tdActions.innerHTML="Acciones"

    let hideElement = document.createElement("input");
    hideElement.type = "hidden";
    hideElement.value = id+"@"+nombre;
    hideElement.id="IdEjercicio"+id;


    card.appendChild(cardBodyDiv);
    cardBodyDiv.appendChild(rowDiv3);
    cardBodyDiv.appendChild(hideElement);
    rowDiv3.appendChild(colDiv3);
    colDiv3.appendChild(tableSeries);
    tableSeries.appendChild(tableSeriesHead);
    tableSeriesHead.appendChild(tr);
    tableSeries.appendChild(tableSeriesBody);
    tr.appendChild(tdPeso);
    tr.appendChild(tdRepes);
    tr.appendChild(tdActions);

    return container;

}

let idSerie=0;
function crearSerie(peso, repes, id) {
	
	if(validacionSeries("Crear")){
    idSerie++;
    let tableSeries = document.getElementById("idTablaSeriesBody" + id);

    let serie = document.createElement("tr");
    serie.setAttribute('id', 'idSerie' + idSerie);

    let tdPeso = document.createElement("td");
    let tdRepes = document.createElement("td");
    let tdActions = document.createElement("td");

    let inputPeso = document.createElement("input");
    inputPeso.setAttribute('id', 'idPeso');
    inputPeso.setAttribute("readonly", "");
    inputPeso.type="hidden"
    inputPeso.value = peso;
    tdPeso.innerHTML = peso;

    let inputRepes = document.createElement("input");
    inputRepes.setAttribute('id', 'idRepes');
    inputRepes.setAttribute("readonly", "");
    inputRepes.type="hidden"
    inputRepes.value = repes;
    tdRepes.innerHTML = repes

    let buttonEditarSerie = document.createElement('button');
    buttonEditarSerie.type = 'button';
    buttonEditarSerie.classList.add("btn", "btn-outline-success", 'mr-1');
    buttonEditarSerie.setAttribute('data-bs-toggle', 'modal');
    buttonEditarSerie.setAttribute('data-bs-target', '#idModalSeriesEditar');
    buttonEditarSerie.addEventListener('click', function () {
        document.getElementById("idSerieHidden").value=serie.id.replace("idSerie","");
        document.getElementById("idRepesModalEditar").value=repes;
        document.getElementById("idPesoModalEditar").value=peso;
    });

    let svgEditar = document.createElementNS("http://www.w3.org/2000/svg", "svg");
    svgEditar.setAttribute("xmlns", "http://www.w3.org/2000/svg");
    svgEditar.setAttribute("width", "16");
    svgEditar.setAttribute("height", "16");
    svgEditar.setAttribute("fill", "currentColor");
    svgEditar.setAttribute("class", "bi bi-pencil-fill");
    svgEditar.setAttribute("viewBox", "0 0 16 16");

    let path1Editar = document.createElementNS("http://www.w3.org/2000/svg", "path");
    path1Editar.setAttribute("d", "M12.854.146a.5.5 0 0 0-.707 0L10.5 1.793 14.207 5.5l1.647-1.646a.5.5 0 0 0 0-.708zm.646 6.061L9.793 2.5 3.293 9H3.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.207zm-7.468 7.468A.5.5 0 0 1 6 13.5V13h-.5a.5.5 0 0 1-.5-.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.5-.5V10h-.5a.5.5 0 0 1-.175-.032l-.179.178a.5.5 0 0 0-.11.168l-2 5a.5.5 0 0 0 .65.65l5-2a.5.5 0 0 0 .168-.11z");

    svgEditar.appendChild(path1Editar);
    buttonEditarSerie.appendChild(svgEditar);
    tdActions.appendChild(buttonEditarSerie);

    let buttonEliminarSerie = document.createElement('button');
    buttonEliminarSerie.type = 'button';
    buttonEliminarSerie.classList.add("btn", "btn-outline-danger");
    buttonEliminarSerie.addEventListener('click', function () {
        serie.remove();
    });

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
    tdActions.appendChild(buttonEditarSerie);
    tdActions.appendChild(buttonEliminarSerie);

    tableSeries.appendChild(serie);
    serie.appendChild(tdPeso);
    serie.appendChild(tdRepes);
    serie.appendChild(tdActions);
    serie.appendChild(inputPeso);
    serie.appendChild(inputRepes);

    $('#idModalSeriesCrear').modal('hide');
    }
}

function editarSerie(peso, repes, id){
	if(validacionSeries("Editar")){
    	let serie=document.getElementById("idSerie" + id);
    	let tds=serie.querySelectorAll("td");
    	tds[0].innerHTML=peso;
    	tds[1].innerHTML=repes;
    	let inputs= serie.querySelectorAll("input");
    	inputs[0].value=peso;
    	inputs[1].value=repes;

    	$('#idModalSeriesEditar').modal('hide');
    }
}

function ejercicioSelecionado(inputHidden){
    document.getElementById("idHidden").value=inputHidden.value;
}

function validacionSeries(tipo){
	let valido=true;
	let alerts=["idAlertPesoCrear", "idAlertRepesCrear", "idAlertPesoEditar", "idAlertRepesEditar"];
	alerts.forEach((a)=>{
		document.getElementById(a).style="display:none";
	});
	
	let alertPeso=document.getElementById("idAlertPeso" + tipo);
	let alertRepes=document.getElementById("idAlertRepes" + tipo);
	
	let inputPeso= document.getElementById("idPesoModal" + tipo).value;
	let inputRepes= document.getElementById("idRepesModal" + tipo).value;
	
	if(inputPeso<0 || inputPeso===""){
		alertPeso.style="display: block";
		valido=false;
	}
	if(inputRepes<1 || inputRepes===""){
		alertRepes.style="display: block";
		valido= false;
	}
	return valido;
}