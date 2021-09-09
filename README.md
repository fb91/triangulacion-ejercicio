# Challenge: Operación Fuego de Quasar
Documento con el enunciado del desafío: [enunciado_desafio.pdf](https://github.com/fb91/triangulacion-ejercicio/tree/master/doc/enunciado_desafio.pdf)

## Ejecución
Según se solicita en el enunciado, el proyecto exponen 2 endpoints que se detallan a continuación:

### Endpoint: topsecret
Este endpoint recibe datos de 3 satélites conocidos y retorna: la **ubicación** de la nave utilizando un algoritmo de triangulación y el **mensaje** decodificado
* Request URL *(servicio actualmente inactivo)*: https://challengemercadolibre.rj.r.appspot.com/topsecret
* Método: POST
* Ejemplo body del request:
```json
{
  "satellites": [
    {
      "name": "kenobi",
      "distance": 100.0,
      "message": ["este", "", "", "mensaje", ""]
    }, {
      "name": "skywalker",
      "distance": 115.5,
      "message": ["", "es", "", "", "secreto"]
    }, {
      "name": "sato",
      "distance": 142.7,
      "message": ["este", "", "un", "", ""]
    }
  ]
}
```
* Ejemplo response:
```json
{
  "position": {
    "x": -58.3,
    "y": -69.6
  },
  "message": "este es un mensaje secreto"
}
```

### Endpoint: topsecret_split
Este endpoint recibe el dato de un satelite. Si previamente ha recolectado datos suficientes como para triangular la posición y decodificar el mensaje, entonces lo retorna.
* Request URL *(servicio actualmente inactivo)*: https://challengemercadolibre.rj.r.appspot.com/topsecret_split/{nombre_del_satelite}
* Método: GET
* Ejemplos de URLs *(servicio actualmente inactivo)*:
* [https://challengemercadolibre.rj.r.appspot.com/topsecret_split/kenobi?distance=100.0&message=este;;;mensaje;](https://challengemercadolibre.rj.r.appspot.com/topsecret_split/kenobi?distance=100.0&message=este;;;mensaje;)
* [https://challengemercadolibre.rj.r.appspot.com/topsecret_split/skywalker?distance=115.5&message=;es;;;secreto](https://challengemercadolibre.rj.r.appspot.com/topsecret_split/skywalker?distance=115.5&message=;es;;;secreto)
* [https://challengemercadolibre.rj.r.appspot.com/topsecret_split/sato?distance=142.7&message=este;;un;;](https://challengemercadolibre.rj.r.appspot.com/topsecret_split/sato?distance=142.7&message=este;;un;;)
* Ejemplo de response (éxito):
```json
{
    "position": {
        "x": -58.3,
        "y": -69.6
    },
    "message": "este es un mensaje secreto"
}
```
* Ejemplo de response (aún no se cuenta con datos suficientes):
```json
{
    "timestamp": "2021-09-02T03:06:36.140+00:00",
    "status": 404,
    "error": "Not Found",
    "message": "404 NOT_FOUND \"Se precisa al menos 3 satélites para determinar con precisión la ubicación\"; nested exception is java.lang.Exception: Se precisa al menos 3 satélites para determinar con precisión la ubicación",
    "path": "/topsecret_split/kenobi"
}
```
