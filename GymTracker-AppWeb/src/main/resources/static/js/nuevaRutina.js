function seleccionable(){
	
	document.querySelectorAll("[id^='idCard']")
			.forEach(function(element) {
  					element.addEventListener('click', crearEjercicio(this.id));
			});
}

function crearEjercicio(id){
    let ejercicio=document.getElementById(id.replace("idCard",""));
    
    let listaEjercicios=document.getElementById("idlistaEjercicios");
    let li=document.createElement("li");
    li.classList.add("list-group-item");
    li.innerHTML=ejercicio.value;
    listaEjercicios.appendChild(li);

    let hideElement = document.createElement("input");
    hideElement.type = "hidden";
    hideElement.value = ejercicio.id;
    listaEjercicios.appendChild(hideElement);
    
    $('#idModalEjercicios').modal('hide');
}