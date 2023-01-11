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

  const formulario = document.querySelector('#add_new_turno');
  console.log("form" + formulario);
  (formulario.addEventListener('submit', function (event) {
          event.preventDefault();
          console.log("entra al event");

//agrego un turno
        let select_paciente = document.querySelector('#listadoPaciente');
        console.log("paciente" + select_paciente);
        let paciente_appointment = select_paciente.options[select_paciente.selectedIndex].value;
        let select_odontologo = document.querySelector('#listadoOdontologos');
        let odontologo_appointment = select_odontologo.options[select_odontologo.selectedIndex].value;
        console.log("id "+ paciente_appointment,odontologo_appointment);

        const formData = {
            odontologo: {
                        id:odontologo_appointment
            },
            paciente: {
                      id:paciente_appointment
            },
            fecha: document.querySelector('#fecha').value
        };

        const url = '/turnos';
        const settings = {
            method: 'POST',
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
                    '<strong></strong> Turno registrado </div>'

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";

            })
            .catch(error => {
            console.log("entro al catch");
                    let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                    '<strong> Error intente nuevamente</strong> </div>'

                      document.querySelector('#response').innerHTML = errorAlert;
                      document.querySelector('#response').style.display = "block";

            })

  }),

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "turno/turnoList.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })
  )
})