window.addEventListener('load', function () {
  
//busco pacientes
(function () {
      const url = '/pacientes';
      const settings = {
        method: 'GET',
      };
console.log("get pacientes");
      fetch(url, settings)
        .then((response) => response.json())
        .then((data) => {
          for (paciente of data) {
            var select = document.getElementById('listadoPaciente');

            select.innerHTML +=
              '<option value="'+paciente.id+'">' + paciente.nombre.toUpperCase() + " " + paciente.apellido.toUpperCase() + '</option>';


          }
        });
    })(function () {
            let pathname = window.location.pathname;

            if (pathname == 'paciente/pacienteList.html') {
              document.querySelector('.nav .nav-item a:last').addClass('active');
            }
          });

//busco odontologos
(function () {
      const url = '/odontologos';
      const settings = {
        method: 'GET',
      };
console.log("get odontologos");
      fetch(url, settings)
        .then((response) => response.json())
        .then((data) => {
          for (odontologo of data) {
          console.log(data);
            var select = document.getElementById('listadoOdontologos');

            select.innerHTML +=

              '<option value="'+odontologo.id+'">' + odontologo.nombre.toUpperCase() + " " + odontologo.apellido.toUpperCase() + '</option>';

          }
        });
    })(function () {
            let pathname = window.location.pathname;

            if (pathname == 'odontologo/odontologoList.html') {
              document.querySelector('.nav .nav-item a:last').addClass('active');
            }
          });

  const formulario = document.querySelector('#update_turno_form');
  console.log("form" + formulario);

  (formulario.addEventListener('submit', function (event) {
          event.preventDefault();
          console.log("entra al event");


//actualizo un turno
        let turnoId = document.querySelector('#turno_id_update').value
        let select_paciente = document.querySelector('#listadoPaciente');
        let paciente_appointment = select_paciente.options[select_paciente.selectedIndex].value;
        let select_odontologo = document.querySelector('#listadoOdontologos');
        let odontologo_appointment = select_odontologo.options[select_odontologo.selectedIndex].value;

        const formData = {
            id: document.querySelector('#turno_id_update').value,
            odontologo: {
                        id:odontologo_appointment
            },
            paciente: {
                      id:paciente_appointment
            },
            fecha: document.querySelector('#fecha-update').value
        };

        const url = '/turnos';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
            console.log("entro bien");
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong></strong> Turno actualizado </div>'

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";

            })
            .then(() => location.reload())
            .catch(error => {
            console.log("entro al catch");
                    let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                    '<strong> Error intente nuevamente</strong> </div>'

                      document.querySelector('#response').innerHTML = errorAlert;
                      document.querySelector('#response').style.display = "block";


            })

          })
  )
})
  function findBy(id) {

          const url = '/turnos'+"/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {

              let turno = data;

              document.querySelector('#turno_id_update').value = turno.id;
              let select_paciente = document.querySelector('#listadoPaciente');

              for(paciente of select_paciente){
                 if(paciente.value == turno.paciente.id){
                    paciente.setAttribute("selected", "selected");
                 }
              }
              let select_odontologo = document.querySelector('#listadoOdontologos');
              for(odontologo of select_odontologo){
                 if(odontologo.value == turno.odontologo.id){
                    odontologo.setAttribute("selected", "selected");
                 }
              }

              document.querySelector('#fecha-update').value = turno.fecha;

              document.querySelector('#div_turno_updating').style.display = "block";

          }).catch(error => {
              alert("Error: " + error);
          })

  }
