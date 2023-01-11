window.addEventListener('load', function () {

   const formulario = document.querySelector('#update_paciente_form');

   formulario.addEventListener('submit', function (event) {
        event.preventDefault();

       let pacienteId = document.querySelector('#paciente_id_update').value;

       const formData = {
           id: document.querySelector('#paciente_id_update').value,
           nombre: document.querySelector('#nombre-update').value,
           apellido: document.querySelector('#apellido-update').value,
           dni: document.querySelector('#dni-update').value,
       };

       const url = '/pacientes';
       const settings = {
           method: 'PUT',
           headers: {
               'Content-Type': 'application/json',
           },
        body: JSON.stringify(formData)
       }

        fetch(url,settings)
        .then(response => response.json())
        location.reload()
   })

})

function findBy(id) {

        const url = '/pacientes'+"/"+id;
        const settings = {
            method: 'GET'
        }
        fetch(url,settings)
        .then(response => response.json())
        .then(data => {

            let paciente = data;

            document.querySelector('#paciente_id_update').value = paciente.id;
            document.querySelector('#nombre-update').value = paciente.nombre;
            document.querySelector('#apellido-update').value = paciente.apellido;
            document.querySelector('#dni-update').value = paciente.dni;

            document.querySelector('#div_paciente_updating').style.display = "block";

        }).catch(error => {
            alert("Error: " + error);
 })
}
