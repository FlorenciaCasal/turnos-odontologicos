window.addEventListener('load', function () {
      (function(){
        const url = '/turnos';
        const settings = {
          method: 'GET'
        }

        fetch(url,settings)
        .then(response => response.json())
        .then(data => {
              for(turno of data){
              var table = document.getElementById("turnoTable");
              var turnoRow =table.insertRow();
              let tr_id = 'tr_' + turno.id;
              turnoRow.id = tr_id;
              let deleteButton = '<button' +
                                        ' id=' + '\"' + 'btn_delete_' + turno.id + '\"' +
                                        ' type="button" onclick="deleteBy('+turno.id+')" class="btn rounded-circle btn-outline-danger btn_delete">' +
                                        '&times' +
                                        '</button>';
              let updateButton = '<button' +
                                        ' id=' + '\"' + 'btn_id_' + turno.id + '\"' +
                                        ' type="button" onclick="findBy('+turno.id+')" class="btn  rounded-circle  btn-outline-success btn_id">' +
                                        turno.id +
                                        '</button>';

              turnoRow.innerHTML =
                      '<td >' + turno.id + '</td>' +
                      '<td >' + turno.pacienteId + '</td>' +
                      '<td >' + turno.odontologoId + '</td>' +
                      '<td >' + turno.fecha + '</td>' +
                      '<td >' + turno.hora + '</td>' +
                      '<td>' + deleteButton + '</td>'+
                      '<table class="table table-dark table-striped-columns">'+ '<td >' + updateButton + '</td>';
                   };
                })
              })
      (function(){
        let pathname = window.location.pathname;
        if (pathname == "/turnoList.html") {
        document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })
})