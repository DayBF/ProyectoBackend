window.addEventListener('load', function () {
    (function(){

      //con fetch invocamos a la API de odontólogos con el método GET
      //nos devolverá un JSON con una colección de odontólogos
      const url = '/pacientes';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {
      //recorremos la colección de odontólogos del JSON
         for(dentist of data){
            //por cada odontólogo armaremos una fila de la tabla
            //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos el odontólogo
            var table = document.getElementById("pacientesTable");
            var dentistRow =table.insertRow();
            let tr_id = 'tr_' + dentist.id;
            dentistRow.id = tr_id;

            dentistRow.innerHTML =
                    '<td class=\"td_id\">' + dentist.id + '</td>' +
                    '<td class=\"td_nombre\">' + dentist.nombre.toUpperCase() + '</td>' +
                    '<td class=\"td_apellido\">' + dentist.apellido.toUpperCase() + '</td>' +
                    '<td class=\"td_dni\">' + dentist.dni + '</td>' +
                    '<td class=\"td_fechaIngreso\">' + dentist.fechaIngreso + '</td>'
                    ;
        };

    })
    })

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/pacientesLista.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })


    })