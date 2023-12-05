function eliminarComunidad(idComunidad) {
    Swal.fire({
        title: '¿Estás seguro?',
        text: 'No podrás revertir esta decisión',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Eliminar Comunidad',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.isConfirmed) {
            const httpRequest = new XMLHttpRequest();
            httpRequest.open('DELETE', '/administracion/comunidades/' + idComunidad, true);
            httpRequest.send();

            httpRequest.onreadystatechange = function() {
                if (httpRequest.readyState === XMLHttpRequest.DONE) {
                    if (httpRequest.status === 200) {
                        Swal.fire(
                            'Eliminado',
                            'La comunidad ha sido eliminada',
                            'success'
                        ).then((result) => {
                            if (result.isConfirmed) {
                                window.location.href = '/administracion/comunidades';
                            }
                        });
                    } else {
                        Swal.fire(
                            'Ups!',
                            'Ocurrió un error al eliminar la comunidad.',
                            'error'
                        );
                    }
                }
            };
        }
    });
}