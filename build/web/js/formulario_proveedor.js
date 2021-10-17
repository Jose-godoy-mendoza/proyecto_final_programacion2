/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


const formulario = document.getElementById('formulario');
const inputs = document.querySelectorAll('#formulario input');

function Limpiar() {
    $("#txt_id").val(0);
    $("#txt_proo").val('');
    $("#txt_nit").val('');
    $("#txt_direccion").val('');
    $("#txt_telefono").val('');

}

$('#tbl_proveedores').on('click', 'tr td', function (evt) {
    var target, id, proveedor, direccion, telefono, nit;
    target = $(event.target);
    id = target.parent().data('id');
    proveedor = target.parent("tr").find("td").eq(0).html();
    nit = target.parent("tr").find("td").eq(1).html();
    direccion = target.parent("tr").find("td").eq(2).html();
    telefono = target.parent("tr").find("td").eq(3).html();


    $("#txt_id").val(id);
    $("#txt_proo").val(proveedor);
    $("#txt_nit").val(nit);
    $("#txt_direccion").val(direccion);
    $("#txt_telefono").val(telefono);
    $("#modal_proveedor").modal('show');
});


const expresiones = {

    proveedor: /^[A-Z]{1}[a-zA-ZÀ-ÿ\s]{4,40}$/, // Letras y espacios, pueden llevar acentos.
    apellido: /^[A-Z]{1}[a-zA-ZÀ-ÿ\s]{4,40}[ ][A-Z]{1}[a-zA-ZÀ-ÿ\s]{4,40}$/,
    correo: /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/,
    telefono: /^[+]{1}[0-9]{1,4}[ ][0-9]{7,14}$/, // 7 a 14 numeros.
     nit: /^[0-9]{6}[-][0-9]{1}$/,
   direccion: /^[A-Z]{1}[a-zA-ZÀ-ÿ\s]{4,40}[ ][A-Z]{1}[a-zA-ZÀ-ÿ\s]{4,40}$/,
    dpi: /^[0-9]{4}[ ][0-9]{5}[ ][0-9]{4}$/
};

const campos = {
    proveedor: false,
    apellido: false,
    correo: false,
    telefono: false,
    nit: false,
    direccion: false,
    dpi: false
};

const validarFormulario = (e) => {
    switch (e.target.name) {
        case "txt_proo":
            validarCampo(expresiones.proveedor, e.target, 'txt_proo');
            break;
        case "txt_nit":
            validarCampo(expresiones.nit, e.target, 'txt_nit');
            break;
        case "txt_direccion":
            validarCampo(expresiones.direccion, e.target, 'txt_direccion');
            break;
        case "txt_telefono":
            validarCampo(expresiones.telefono, e.target, 'txt_telefono');
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
        swal({
            title: "Error!!",
            text: "Llene los campos correctamente!!",
            icon: "error",
            button: "Aceptar",

            showConfirmButton: false
        });


    } else {
        swal({
            title: "Excelente!!",
            text: "Registro Agregado Correctamente!!",

            icon: "success",
            timer: 10000,

            showConfirmButton: false
        });
    }
};

let obtener_datos2 = () => {
    let error2 = validar2();
    if (error2) {
        swal("Error!!", "Llene los campos correctamente", "error");
    } else {
        swal({
            title: "Excelente!!",
            text: "Registro Agregado Correctamente!!",
            icon: "success",
            timer: 90000,
            showConfirmButton: false
        });
    }
};     
btn_agregar.addEventListener('click', obtener_datos);
btn_modificar.addEventListener('click', obtener_datos2);