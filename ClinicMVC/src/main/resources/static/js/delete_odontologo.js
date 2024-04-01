$(document).ready(function () {
    // Función para eliminar un odontólogo
    function eliminarOdontologo(id) {
        const url = '/odontologos/' + id;
        const settings = {
            method: 'DELETE'
        };
        fetch(url, settings)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al eliminar el odontólogo');
                }
                // Eliminar la fila correspondiente en la tabla
                document.getElementById('tr_' + id).remove();
                $('#delete_response').html('<div class="alert alert-success">Odontólogo eliminado correctamente</div>');
            })
            .catch(error => {
                console.error('Error al eliminar el odontólogo:', error);
                $('#delete_response').html('<div class="alert alert-danger">Error al eliminar el odontólogo</div>');
            });
    }

    // Manejar el envío del formulario
    $('#delete_dentist_form').submit(function (event) {
        event.preventDefault();
        var id = $('#id').val();
        eliminarOdontologo(id);
    });

});