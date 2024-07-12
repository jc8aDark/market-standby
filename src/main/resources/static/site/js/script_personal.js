
    var totalPrecio = 0; // Variable para almacenar la suma total de los subtotales

    function removeProduct(link) {
        console.log("Remove product function called");
        var row = link.closest("tr");
        var subtotal = parseFloat(row.children[3].textContent);
        totalPrecio -= subtotal; // Restar el subtotal de la fila eliminada al total
        row.remove();
        actualizarTotal();
        // Puedes realizar cualquier otra lógica que necesites, como actualizar el modelo 'order'
    }

    function addProduct() {
        var productInput = document.getElementById("productInput");
        var quantityInput = document.getElementById("quantityInput");
        var userInput = document.getElementById("userInput");

        var productName = productInput.value;
        var quantity = quantityInput.value;
        var userName = userInput.value;

        // Obtener todas las opciones del datalist de productos
        var datalistOptions = document.getElementById("products").getElementsByTagName("option");

        // Buscar la opción correspondiente al nombre del producto
        var selectedProductOption = null;
        for (var i = 0; i < datalistOptions.length; i++) {
            if (datalistOptions[i].text === productName) {
                selectedProductOption = datalistOptions[i];
                break;
            }
        }

        if (selectedProductOption) {
            var product = {
                name: selectedProductOption.text,
                quantity: quantity,
                price: selectedProductOption.getAttribute('data-price') || 0,
                user: userName  // Agregar el nombre del usuario al objeto product
            };

            // Calcular el subtotal multiplicando precio por cantidad
            var subtotal = product.price * product.quantity;
            totalPrecio += subtotal; // Agregar el subtotal al total

            // Crear una nueva fila en la tabla
            var table = document.querySelector("#addedProductsContainer table tbody");
            var newRow = table.insertRow();

            // Insertar celdas para nombre del producto, cantidad, precio, subtotal, nombre del usuario y acción
            var cellProductName = newRow.insertCell(0);
            var cellQuantity = newRow.insertCell(1);
            var cellPrice = newRow.insertCell(2);
            var cellSubtotal = newRow.insertCell(3);
            var cellUser = newRow.insertCell(4);
            var cellAction = newRow.insertCell(5);

            // Establecer los valores en las celdas
            cellProductName.innerHTML = product.name;
            cellQuantity.innerHTML = product.quantity;
            cellPrice.innerHTML = product.price;
            cellSubtotal.innerHTML = subtotal;  // Mostrar el subtotal en lugar de product.price
            cellUser.innerHTML = product.user;
            cellAction.innerHTML = '<a href="#" onclick="removeProduct(this)">Eliminar</a>'; // O puedes usar un botón

            // Limpiar los valores de los campos de entrada
            productInput.value = '';
            quantityInput.value = '';
            userInput.value = ''; // Limpiar el campo de usuario

            actualizarTotal();
        } else {
            console.error("No se pudo encontrar la opción seleccionada en el datalist.");
        }
    }

    function updateProductName() {
        var input = document.getElementById("productInput");
        var selectedProductId = input.value;

        // Busca la opción con el valor seleccionado en el datalist de productos
        var selectedProductOption = document.querySelector('#products option[value="' + selectedProductId + '"]');

        if (selectedProductOption) {
            // Se establece el valor del campo como el texto de la opción seleccionada
            input.value = selectedProductOption.text;
        }
    }

    function actualizarTotal() {
        // Actualizar el total en la sección <tfoot>
        var totalElemento = document.getElementById('total'); // Supongamos que hay un elemento con id 'total' en tu HTML
        if (totalElemento) {
            totalElemento.textContent = totalPrecio.toFixed(2); // Mostrar el total con dos decimales
        }
    }

    function updateUserName() {
        var input = document.getElementById("userInput");
        var selectedUserId = input.value;

        // Busca la opción con el valor seleccionado en el datalist de usuarios
        var selectedUserOption = document.querySelector('#users option[value="' + selectedUserId + '"]');

        if (selectedUserOption) {
            // Limpiar la lista actual del datalist de usuarios
            var usersDatalist = document.getElementById("users");
            usersDatalist.innerHTML = "";

            // Agregar la opción de usuario al datalist
            usersDatalist.appendChild(selectedUserOption.cloneNode(true));

            // Actualizar el valor del campo con el texto de la opción seleccionada
            input.value = selectedUserOption.text;
        }
    }