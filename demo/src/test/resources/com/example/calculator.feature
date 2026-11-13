# language: es
Característica: Operaciones de la calculadora
  Para obtener resultados aritméticos fiables
  Como usuario
  Quiero sumar y restar dos operandos

  Escenario: Sumar dos enteros
    Dado que ingreso los operandos 3 y 4
    Cuando solicito sumarlos
    Entonces el resultado debe ser 7

  Escenario: Restar dos enteros
    Dado que ingreso los operandos 10 y 4
    Cuando solicito restarlos
    Entonces el resultado debe ser 6
