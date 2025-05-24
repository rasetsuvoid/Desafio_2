Feature: Formulario de contacto en Caso 1

  Background:
    Given El usuario está en la página de contacto de Caso 1

  Scenario: Envío exitoso del formulario con todos los datos válidos
    When El usuario ingresa "Juan Perez" en el campo "Tu Nombre"
    And ingresa "juan.perez@example.com" en el campo "Tu Email"
    And ingresa "Chapinero" en el campo "Tu Barrio"
    And ingresa "Consulta general" en el campo "Asunto"
    And escribe "Me gustaría saber más sobre sus servicios." en el campo "Mensaje"
    And presiona el botón "Enviar"
    Then El formulario se envía correctamente

  Scenario: Envío fallido cuando se dejan todos los campos vacíos
    When presiona el botón "Enviar"
    Then Se muestran mensajes de error indicando que los campos son obligatorios

  Scenario: Validación del campo correo con formato inválido
    When El usuario ingresa "Juan Perez" en el campo "Tu Nombre"
    And ingresa "correomalo" en el campo "Tu Email"
    Then Se muestra un mensaje de error indicando que el "Tu Email" no es válido

Scenario: Validación del campo nombre con formato inválido
  When El usuario ingresa "Diego Rincón" en el campo "Tu Nombre"
  Then Se muestra un mensaje de error indicando que el "Tu Nombre" no es válido

Scenario: El nombre válido con más de 12 caracteres no debe generar error
  When El usuario ingresa "JuanPerezzzZz" en el campo "Tu Nombre"
  Then No se muestra un mensaje de error para el campo "Tu Nombre"

  Scenario: El barrio válido con más de 4 caracteres no debe generar error
  When El usuario ingresa "Engativa" en el campo "Tu Barrio"
  Then No se muestra un mensaje de error para el campo "Tu Barrio"
