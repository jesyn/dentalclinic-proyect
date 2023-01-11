window.addEventListener('load', function () {
    window.deleteBy = (id) => {
      if (window.confirm('Desea eliminar este paciente?')) {
        const url = '/pacientes/'+ id;
        const settings = {
          method: 'DELETE',
        };

        fetch(url, settings).then((response) => {
          alert('paciente eliminado correctamente');
          location.reload();
        });
      }
    };
})