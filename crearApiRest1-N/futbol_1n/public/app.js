addEventListener("load", (event) => {
    const btnSubmitEquipo = document.querySelector('.submit.equipo');
    const btnSubmitJugador = document.querySelector('.submit.jugador');
    const tableEquipos = document.querySelector('.lista-equipos');
    const tableJugadores = document.querySelector('.lista-jugadores');
    const equipoSelect = document.querySelector('#equipoId');

    const urlEquipos = "http://localhost:8080/equipos";
    const urlJugadores = "http://localhost:8080/jugadores";

    // Cargar equipos y jugadores
    loadEquipos();
    loadJugadores();

    // Cargar equipos en el desplegable
    function loadEquiposDropdown() {
        fetch(urlEquipos)
            .then(response => response.json())
            .then(response => {
                empty(equipoSelect);
                const defaultOption = document.createElement('option');
                defaultOption.value = '';
                defaultOption.textContent = '--Seleccione un Equipo--';
                equipoSelect.appendChild(defaultOption);
                
                response.forEach(element => {
                    const option = document.createElement('option');
                    option.value = element.idequipo;
                    option.textContent = element.nombre;
                    equipoSelect.appendChild(option);
                });
            })
            .catch(err => console.error(err));
    }

    // Cargar equipos al inicio
    loadEquiposDropdown();

    btnSubmitEquipo.addEventListener('click', e => {
        e.preventDefault();

        const formData = new FormData(document.forms.createEquipo);
        const data = Object.fromEntries(formData.entries());

        fetch(urlEquipos, {
            method: "POST",
            body: JSON.stringify({ nombre: data.nombreEquipo }),
            headers: {
                'Content-Type': 'application/json; charset=utf-8'
            }
        })
        .then(response => {
            loadEquipos();
            loadEquiposDropdown();
        })
        .catch(err => console.error(err));
    });

    btnSubmitJugador.addEventListener('click', e => {
        e.preventDefault();
    
        let formData = new FormData(document.forms.createJugador);
        let entries = formData.entries();
        let data = Object.fromEntries(entries);
    
        fetch(urlJugadores, {
            method: "POST",
            body: JSON.stringify({
                nombre: data.nombreJugador,
                edad: parseInt(data.edadJugador),
                equipo: {
                    idequipo: parseInt(data.equipoId)
                }
            }),
            headers: {
                'Content-Type': 'application/json; charset=utf-8'
            }
        })
        .then(() => {
            loadJugadores();
            loadEquipos();
        })
        .catch(err => console.error(err));
    });

    function loadEquipos() {
        fetch(urlEquipos)
            .then(response => response.json())
            .then(response => {
                empty(tableEquipos);
                let rowHeaders = tableEquipos.insertRow();
                createHeader(rowHeaders, "Nombre del Equipo");
                createHeader(rowHeaders, "Jugadores");
                let thOpciones = document.createElement("th");
                rowHeaders.appendChild(thOpciones);

                response.forEach(element => {
                    let row = tableEquipos.insertRow();
                    createCell(row, element.nombre);
                    createCell(row, element.jugadores.map(j => j.nombre).join(", "));
                    createDeleteButton(row, element.idequipo);
                });
            })
            .catch(err => console.error(err));
    }

    function loadJugadores() {
        fetch(urlJugadores)
            .then(response => response.json())
            .then(response => {
                empty(tableJugadores);
                let rowHeaders = tableJugadores.insertRow();
                createHeader(rowHeaders, "Nombre");
                createHeader(rowHeaders, "Edad");
                createHeader(rowHeaders, "Equipo");
                let thOpciones = document.createElement("th");
                rowHeaders.appendChild(thOpciones);
    
                response.forEach(element => {
                    let row = tableJugadores.insertRow();
                    createCell(row, element.nombre); // Nombre del jugador
                    createCell(row, element.edad);    // Edad del jugador
                    
                    // Accede al nombre del equipo del jugador
                    createCell(row, element.equipoNombre ? element.equipoNombre : "No Inscrito"); 
    
                    createDeleteButtonJugador(row, element.idjugador); // Botón de eliminar
                });
            })
            .catch(err => console.error(err));
    }
    

    function createHeader(row, headerName) {
        let th = document.createElement("th");
        th.appendChild(document.createTextNode(headerName));
        row.appendChild(th);
    }

    function createCell(row, textContent) {
        let cell = row.insertCell();
        cell.appendChild(document.createTextNode(textContent));
    }

    function createDeleteButton(row, equipoId) {
        let cell = row.insertCell();
        let btn = document.createElement("button");
        btn.textContent = "Eliminar Equipo";
        btn.addEventListener('click', function() {
            fetch(urlEquipos + '/' + equipoId, {
                method: "DELETE"
            })
            .then(() => loadEquipos())
            .catch(err => console.error(err));
        });
        cell.appendChild(btn);
    }

    function createDeleteButtonJugador(row, jugadorId) {
        let cell = row.insertCell();
        let btn = document.createElement("button");
        btn.textContent = "Eliminar Jugador";
        btn.addEventListener('click', function() {
            fetch(urlJugadores + '/' + jugadorId, {
                method: "DELETE"
            })
            .then(() => {
                loadJugadores();
                loadEquipos();
                loadEquiposDropdown();
            })
            .catch(err => console.error(err));
        });
        cell.appendChild(btn);
    }

    function empty(element) {
        while (element.firstChild) {
            element.firstChild.remove();
        }
    }
});