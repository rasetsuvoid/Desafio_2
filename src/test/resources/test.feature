Feature: Formulario de contacto en Caso 1

  Background:
    Given El usuario está en la página de contacto de Caso 1

  Scenario: Envío exitoso del formulario con todos los datos válidos
    When El usuario ingresa "Juan Pérez" en el campo "Tu Nombre"
    And ingresa "juan.perez@example.com" en el campo "Tu Email"
    And ingresa "Chapinero" en el campo "Varrio"
    And ingresa "Consulta general" en el campo "Asunto"
    And escribe "Me gustaría saber más sobre sus servicios." en el campo "Mensaje"
    And presiona el botón "Enviar"
    Then El formulario se envía correctamente

  Scenario: Campo 'Tu Nombre' vacío
    When El usuario deja vacío el campo "Tu Nombre"
    And ingresa "juan.perez@example.com" en el campo "Tu Email"
    And ingresa "Chapinero" en el campo "Varrio"
    And ingresa "Consulta general" en el campo "Asunto"
    And escribe "Mensaje de prueba" en el campo "Mensaje"
    And presiona el botón "Enviar"
    Then Se muestra un mensaje de error indicando que el nombre es obligatorio

  Scenario: Email inválido
    When El usuario ingresa "Juan Pérez" en el campo "Tu Nombre"
    And ingresa "correo-no-valido" en el campo "Tu Email"
    And ingresa "Chapinero" en el campo "Varrio"
    And ingresa "Consulta general" en el campo "Asunto"
    And escribe "Mensaje de prueba" en el campo "Mensaje"
    And presiona el botón "Enviar"
    Then Se muestra un mensaje de error indicando que el email no es válido

  Scenario: Todos los campos vacíos
    When El usuario presiona el botón "Enviar"
    Then Se muestran mensajes de error en todos los campos obligatorios

  Scenario: Campo 'Mensaje' excede el límite de caracteres
    When El usuario completa el formulario con datos válidos
    And escribe un mensaje con más de 1000 caracteres en el campo "Mensaje"
    And presiona el botón "Enviar"
    Then Se muestra un mensaje de error indicando que el mensaje es demasiado largo

  Scenario: Envío del formulario con caracteres especiales
    When El usuario ingresa "J@an P#rez!" en el campo "Tu Nombre"
    And ingresa "correo+prueba@example.com" en el campo "Tu Email"
    And ingresa "San Andrés <3" en el campo "Varrio"
    And ingresa "¿Qué servicios ofrecen?" en el campo "Asunto"
    And escribe "¡Hola! ¿Puedo obtener info?" en el campo "Mensaje"
    And presiona el botón "Enviar"
    Then El formulario se envía correctamente

  Scenario: Campos con espacios en blanco solamente
    When El usuario ingresa solo espacios en todos los campos
    And presiona el botón "Enviar"
    Then Se muestran mensajes de error indicando que los campos no pueden estar vacíos

  Scenario: Recarga de la página después del envío exitoso
    Given El usuario ha enviado el formulario exitosamente
    When El usuario recarga la página
    Then El formulario aparece vacío
