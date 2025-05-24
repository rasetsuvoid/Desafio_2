Feature: Formulario de contacto en Caso 1

  Background:
    Given El usuario está en la página de contacto de Caso 1

  Scenario: Envío exitoso del formulario con todos los datos válidos
    When El usuario ingresa "Juan Perez" en el campo "Tu Nombre"
    And ingresa "juan.perez@example.com" en el campo "Tu Email"
    And ingresa "Chapinero" en el campo "Varrio"
    And ingresa "Consulta general" en el campo "Asunto"
    And escribe "Me gustaría saber más sobre sus servicios." en el campo "Mensaje"
    And presiona el botón "Enviar"
    Then El formulario se envía correctamente
