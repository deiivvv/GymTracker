   function cargarCalendario(mes, ano) {

    let calendario = document.getElementById('idCalendario');
    let fechas = document.getElementById('idFechas').value.split(",");

    let hoy = new Date();
    let diaHoy = hoy.getDate();
    let mesHoy = hoy.getMonth() + 1;
    let anoHoy = hoy.getFullYear();

    let numDiasMes = new Date(ano, mes + 1, 0).getDate();
    let primerDiaMes = new Date(ano, mes, 1).getDay();
    let diaComienzo = (primerDiaMes === 0) ? 6 : primerDiaMes - 1; // Lunes es 0, Martes es 1, ..., Domingo es 6

    let meses = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];
    
    let html =  '<div class="col-3"></div>';
    html += '<div class="col-6 mb-3 d-flex justify-content-center align-items-center text-center">';
    html += '<select id="idMes" class="form-control mb-1">';
    for (let i = 0; i < 12; i++) {
      html += '<option value="' + i + '"' + (i === mes ? ' selected' : '') + '>' + meses[i] + '</option>';
    }
    html += '</select>';

    html += '<select id="idAno" class="form-control mb-1">';
    for (let i = ano - 10; i <= ano + 10; i++) {
      html += '<option value="' + i + '"' + (i === ano ? ' selected' : '') + '>' + i + '</option>';
    }
    html += '</select>';
    html += '</div>'
    html +=  '<div class="col-3"></div>';

    html += '<div class="col-12 mb-3 d-flex justify-content-center align-items-center text-center">';
    html += '<div class="table-responsive">';
    html += '<table class="table table-hover">';
    html += '<thead><tr><th colspan="7">' + meses[mes] + ' ' + ano + '</th></tr>';
    html += '<tr><th>Lunes</th><th>Martes</th><th>Miércoles</th><th>Jueves</th><th>Viernes</th><th>Sábado</th><th>Domingo</th></tr></thead>';
    html += '<tbody>';

    let dia = 1;
    mes++;
    for (let i = 0; i < 6; i++) {
      html += '<tr>';
      for (let j = 0; j < 7; j++) {
        if (i === 0 && j < diaComienzo) {
          html += '<td></td>';
        } else if (dia > numDiasMes) {
          break;
        } else {
			if(anoHoy=== ano && (mesHoy=== "0"+mes || mesHoy === mes) &&  (diaHoy=== "0"+dia || diaHoy === dia)){
            html += '<td class="bg-secondary text-white">' + dia + '</td>';
          }else if(fechas.includes(ano+"-"+mes+"-"+dia) || fechas.includes(ano+"-0"+mes+"-0"+dia) || fechas.includes(ano+"-0"+mes+"-"+dia) || fechas.includes(ano+"-"+mes+"-0"+dia)){
            html += '<td class="bg-success text-white">' + dia + '</td>';
          }else{
            html += '<td>' + dia + '</td>';
          }
          dia++;
        }
      }
      html += '</tr>';
    }

    html += '</tbody></table></div></div>';
    calendario.innerHTML = html;

    document.getElementById('idMes').addEventListener('change', function() {
      cargarCalendario(parseInt(this.value), parseInt(document.getElementById('idAno').value));
    });

    document.getElementById('idAno').addEventListener('change', function() {
      cargarCalendario(parseInt(document.getElementById('idMes').value), parseInt(this.value));
    });
  }
  
  function onloadCargarCalendario(){
	
    let hoy = new Date();
    cargarCalendario(hoy.getMonth(),  hoy.getFullYear())
  }
  