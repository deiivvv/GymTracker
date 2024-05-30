 const musculos=[
        'cuadriceps',
        'gluteos',
        'pectorales',
        'triceps',
        'espalda',
        'biceps',
        'espalda baja',
        'isquiotibiales',
        'deltoides' ,
        'abdominales',
        'gemelos',
        'multiples',
        'antebrazo',
        'trapecio']

// Array de colores de fondo
const backgroundColors = [
    'rgba(255, 99, 132, 0.2)',   // cuadriceps
    'rgba(54, 162, 235, 0.2)',   // gluteos
    'rgba(255, 206, 86, 0.2)',   // pectorales
    'rgba(75, 192, 192, 0.2)',   // triceps
    'rgba(153, 102, 255, 0.2)',  // espalda
    'rgba(255, 159, 64, 0.2)',   // biceps
    'rgba(255, 99, 132, 0.2)',   // espalda baja
    'rgba(54, 162, 235, 0.2)',   // isquiotibiales
    'rgba(255, 206, 86, 0.2)',   // deltoides
    'rgba(75, 192, 192, 0.2)',   // abdominales
    'rgba(153, 102, 255, 0.2)',  // gemelos
    'rgba(255, 159, 64, 0.2)',   // multiples
    'rgba(255, 99, 132, 0.2)',   // antebrazo
    'rgba(54, 162, 235, 0.2)'    // trapecio
];

// Array de colores de bordes
const borderColors = [
    'rgba(255, 99, 132, 1)',     // cuadriceps
    'rgba(54, 162, 235, 1)',     // gluteos
    'rgba(255, 206, 86, 1)',     // pectorales
    'rgba(75, 192, 192, 1)',     // triceps
    'rgba(153, 102, 255, 1)',    // espalda
    'rgba(255, 159, 64, 1)',     // biceps
    'rgba(255, 99, 132, 1)',     // espalda baja
    'rgba(54, 162, 235, 1)',     // isquiotibiales
    'rgba(255, 206, 86, 1)',     // deltoides
    'rgba(75, 192, 192, 1)',     // abdominales
    'rgba(153, 102, 255, 1)',    // gemelos
    'rgba(255, 159, 64, 1)',     // multiples
    'rgba(255, 99, 132, 1)',     // antebrazo
    'rgba(54, 162, 235, 1)'      // trapecio
];



var chart;

$(document).ready(function(){
    var ctx = document.getElementById('myChart').getContext('2d');
    chart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: [],
            datasets: [{
                label: 'Músculos',
                data: [],
                backgroundColor: [],
                borderColor: [],
                borderWidth: 1
            }]
        },
        options: {
            animation: {
                duration: 1000,
                easing: 'easeInOutQuart'
            },
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });

});

function cargarEstadisticas(){
       musculos.forEach((m)=>{
        let url=`http://localhost:8081/ejercicios/buscar?musculo=${m}`;
        //let url=`http://gymtracker.duckdns.org:8081/ejercicios/buscar?musculo=${m}`;
        axios.get(url)
		.then(function(response) {
              let ejercicios = response.data;
              let ids = ejercicios.map(ejercicio => ejercicio.id);
              let idsString = ids.join(',');
			  contarEjercicios(m, idsString);
            })
            .catch(function(error) {
                console.error('Error al obtener los ejercicios:', error);
            });
        }
    )
}

function contarEjercicios(musculo, ids) {
	let url=`https://localhost/estadisticas/contar?ids=${ids}`
	axios.get(url)
		.then(function(response) {
            crearBarra(musculo, response.data);
            })
            .catch(function(error) {
                 console.error('Error al enviar los IDs a estadísticas:', error);
            })
        
 }
  
 function crearBarra(label, value){
	if(label && value){
		let index = musculos.indexOf(label);
                chart.data.labels.push(label);
                chart.data.datasets[0].data.push(value);
                chart.data.datasets[0].backgroundColor.push(backgroundColors[index]);
                chart.data.datasets[0].borderColor.push(borderColors[index]);
                chart.update();

            $('#myChart').addClass('fade-in');
            setTimeout(() => $('#myChart').removeClass('fade-in'), 1000);
            
                var legendItem = $("<button class='btn mb-2 mr-2'>")
                    .text(label)
                    .css('background-color', backgroundColors[index])
                    .css('border-color', borderColors[index])
                    .click(function() {
                        $(this).toggleClass('fade-outB');
                        setTimeout(() => $(this).toggleClass('fade-inB'), 500);

                        var index = chart.data.labels.indexOf(label);
                        chart.data.datasets[0].data[index] = chart.data.datasets[0].data[index] ? 0 : value;
                        chart.update();       
                    });
                $('#legend-container').append(legendItem);
            }
        }
