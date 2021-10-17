/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


const formulario = document.getElementById('formulario');
const inputs = document.querySelectorAll('#formulario input');
function Limpiar() {
    $("#txt_id").val(0);
    $("#txt_puesto").val('');
}


$('#tbl_puestos').on('click', 'tr td', function (evt) {
    var target, id, puesto;
    target = $(event.target);
    id = target.parent().data('id');
    puesto = target.parent("tr").find("td").eq(0).html();
    $("#txt_id").val(id);
    $("#txt_puesto").val(puesto);
    $("#modal_puesto").modal('show');
});
const expresiones = {

    nombre: /^[A-Z]{1}[a-zA-ZÀ-ÿ\s]{3,40}$/ // Letras y espacios, pueden llevar acentos.

};
const campos = {
    nombre: false
};
const validarFormulario = (e) => {
    switch (e.target.name) {
        case "txt_puesto":
            validarCampo(expresiones.nombre, e.target, 'txt_puesto');
            break;
    }
};
const validarCampo = (expresion, input, campo) => {
    if (expresion.test(input.value)) {
        document.getElementById(`grupo__${campo}`).classList.remove('formulario__grupo-incorrecto');
        document.getElementById(`grupo__${campo}`).classList.add('formulario__grupo-correcto');
        document.querySelector(`#grupo__${campo} i`).classList.add('fa-check-circle');
        document.querySelector(`#grupo__${campo} i`).classList.remove('fa-times-circle');
        document.querySelector(`#grupo__${campo} .formulario__input-error`).classList.remove('formulario__input-error-activo');
        campos[campo] = true;
    } else {
        document.getElementById(`grupo__${campo}`).classList.add('formulario__grupo-incorrecto');
        document.getElementById(`grupo__${campo}`).classList.remove('formulario__grupo-correcto');
        document.querySelector(`#grupo__${campo} i`).classList.add('fa-times-circle');
        document.querySelector(`#grupo__${campo} i`).classList.remove('fa-check-circle');
        document.querySelector(`#grupo__${campo} .formulario__input-error`).classList.add('formulario__input-error-activo');
        campos[campo] = false;
    }
};
inputs.forEach((input) => {
    input.addEventListener('keyup', validarFormulario);
    input.addEventListener('blur', validarFormulario);
});
function recargar() {
    document.location.reload();
}

'use strict';
const btn_agregar = document.querySelector('#btn_agregar');
const btn_modificar = document.querySelector('#btn_modificar');
let validar = () => {
    let inputs_requerido = document.querySelectorAll('#formulario [required]');
    let error = false;
    for (let i = 0; i < inputs_requerido.length; i++) {
        if (inputs_requerido[i].value === '') {
            error = true;
        } else {
            inputs_requerido[i].classList.remove('input_error2');
        }
    }
    return error;
};
let validar2 = () => {
    let inputs_requerido2 = document.querySelectorAll('#formulario [required]');
    let error2 = false;
    for (let i = 0; i < inputs_requerido2.length; i++) {
        if (inputs_requerido2[i].value === '') {
            error2 = true;
        } else {
            inputs_requerido2[i].classList.remove('input_error2');
        }
    }
    return error2;
};
let obtener_datos = () => {
    let error = validar();
    if (error) {
        error44();
    } else {

        correcto();
    }
};
let obtener_datos2 = () => {
    let error2 = validar2();
    if (error2) {
        error44();
    } else {
        correcto();
    }
};
function error44() {
    Swal.fire({
        icon: 'error',
        title: 'Error!!',
       html: '<h5 style=color:red><br><b>Debe Llenar los campos correctamente</b></h5>',
       // confirmButtonColor: '#a52a2a'
       showConfirmButton: false 
    });
}

function correcto() {
    Swal.fire({
        icon: 'success',
        title: 'Excelente!!',
        html: '<h5 style=color:lime><br><b>Datos Ingresados Correctamente</b></h5>',
      //   background: "#1e2122",
        showConfirmButton: false 
      //  confirmButtonColor: '#00ff00'
    });
}
btn_agregar.addEventListener('click', obtener_datos);
btn_modificar.addEventListener('click', obtener_datos2);
//btn_eliminar.addEventListener('click', obtener_datos2);
function doss() {
    //   var mensaje;
    var opcion = confirm("Desea Eliminar");
    if (opcion === true) {
        //     mensaje = "Eliminado";
        //     alert(mensaje);
        return true;
    } else {
        //     mensaje = "No Eliminado";
        //     alert(mensaje);

        return false;
    }
}

//$('#confirm').click(function () {
function confirmar2(evt) {
    var r;
  
    Swal.fire({
        title: 'Eliminar',
        text: "Desea Eliminar el registro?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'si, eliminar',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.value === true) {
            Swal.fire(
                    'Eliminado',
                    'Datos Eliminados Correctamente',
                    'success'
                  );
  
$("#btn_eliminar").click();
        } else {
 }      
    });
    return false;
}
//});
function tres() {
alertify.confirm("Desea Eliminar?", function(e){
 if(e){
return true;
alertify.alert("Eliminado");

 }else{
alertify.alert("Cancelado");    
return false;
 }
  
});
}



//if (confirm('Are you sure you want to delete?')) form.action='/Config?pg=FIBiller&amp;cmd=delete'; else confirmar2();