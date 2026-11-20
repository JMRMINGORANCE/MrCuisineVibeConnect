# MrCuisineVibeConnect — en español

🇬🇧 **[English version](README.md)** · 🇫🇷 **[Version française](README.fr.md)**

> **Copia en español** de [Wuki60/MrCuisineVibeConnect](https://github.com/Wuki60/MrCuisineVibeConnect)
> (la app) y de [flexbox/MrCuisineVibeConnect](https://github.com/flexbox/MrCuisineVibeConnect) (la
> instalación desde Mac y Linux). Añade la interfaz en español, un buscador que entiende los plurales
> en español y esta documentación. Se **sincroniza sola cada día** con los dos originales y publica
> una versión nueva cuando ellos lo hacen, así que *Ajustes › Actualizaciones* en el robot recibe
> las novedades del original sin perder el español.

**Toque** es una interfaz rápida y moderna que sustituye a la de serie en el robot de cocina
**Monsieur Cuisine Connect** de Lidl (SKMC 1200). Funciona en la propia tablet del robot, en lugar
de la app original, y sigue usando **las recetas oficiales de Lidl**.

> Proyecto independiente, sin relación con Lidl, SilverCrest ni Hoyer. El control del motor se basa
> en un análisis público del protocolo serie; funciona a diario en un robot (SKMC 1200, Android 6)
> desde octubre de 2026 y en un segundo desde noviembre de 2026, pero no se ha probado en otros.
> Úsalo bajo tu responsabilidad y sigue el procedimiento de la primera prueba.

## Instalación

**Empieza aquí: [docs/INSTALLATION.es.md](docs/INSTALLATION.es.md)**, una guía paso a paso desde
cero para **Mac**, **Linux** y **Windows**.

En resumen: necesitas un ordenador, un cable **USB-A macho ↔ USB-A macho**, un destornillador Torx
pequeño y de 1 a 2 horas. Preparas el ordenador y una copia de este repositorio, y luego dejas que
[Claude Code](https://code.claude.com/docs/en/quickstart) haga la instalación siguiendo
[AGENTS.md](AGENTS.md), o ejecutas tú mismo
[`tools/setup/toque-setup.sh`](tools/setup/toque-setup.sh) (Windows:
[`toque-setup.ps1`](tools/setup/toque-setup.ps1)) paso a paso. Primero hace una copia de seguridad
del robot, lo rootea con Magisk, instala Toque y activa ADB por wifi. Tú solo te encargas de la
tapa, el cable, el interruptor y de tocar la pantalla del robot.

## Funciones

- **Interfaz oscura** pensada para la pantalla de 7" y el mando giratorio del robot, y fluida en su
  tablet original (1 GB de RAM, Android 6).
- **Recetas de Lidl, directamente de monsieur-cuisine.com.** Con la interfaz en español, Toque
  descarga el catálogo oficial español de Monsieur Cuisine connect (unas 880 recetas, con los
  ajustes de máquina de cada paso) desde la API pública de la web, cada noche o cuando quieras: sin
  cuenta, sin servidor propio y sin la app original. También se pueden importar las recetas que ya
  tiene la app original.
- **Autor, valoraciones y opiniones** de cada receta, en el idioma de la interfaz.
- **Un buscador que funciona**: ignora tildes, plurales («limones», «nueces», «tomates») y erratas
  («calabasa»). Búsqueda por ingredientes (p. ej. «pollo, curry»), filtros y favoritas.
- **Cocina guiada**: cada paso arranca con un toque, el siguiente aparece solo, báscula integrada y
  reanudación tras un corte de luz. Los ajustes seguidos sin nada que hacer entre ellos arrancan
  juntos con un solo toque.
- **Cocción manual**, programas **Amasar / Vapor / Rehogar**, **Turbo** y **báscula**.
- **Sin conexión**: todo funciona sin red.
- **Siempre en hora**: la zona horaria sale de la conexión a Internet y la hora de la red (el robot
  no tiene pila para el reloj).
- **Pensado para un robot sin botones de Android**: Toque se abre al encender y un botón **Volver a
  Toque** se queda encima de la app original cuando la abres.
- **Actualizaciones desde GitHub**: *Ajustes › Actualizaciones* consulta las
  [versiones](https://github.com/JMRMINGORANCE/MrCuisineVibeConnect/releases) de esta copia y las
  instala (sin preguntar en un robot rooteado; luego Toque se reinicia). No lo permite mientras hay
  un programa en marcha.

## Seguridad: qué cambia respecto a la app original

Se mantienen todos los límites originales (velocidad 3 como máximo con calor y con giro inverso,
tiempos máximos, condiciones del Turbo…) y se corrigen varios fallos encontrados al analizar el
protocolo:

| Fallo de la app original | Toque |
|---|---|
| Pedía arrancar antes de comprobar la tapa | Tapa, errores y lecturas recientes se comprueban **antes** de arrancar |
| No detectaba una placa del motor muda | Sin respuesta en 1,5 s: todo se para y aparece una alerta |
| Ignoraba los errores de escritura serie | Parada y alerta |
| Un STOP seguido de un START podía no enviar nunca el stop | Todo rearranque o cambio de sentido espera una lectura que confirme el **motor parado** |
| Lectura serie sin resincronización | Se gestionan tramas fragmentadas, unidas o corruptas |
| La cuchilla giraba a veces en modo vapor | Vapor a velocidad 0 |

Además: confirmación (y subida progresiva) antes de triturar contenido a 60 °C o más, límite de
velocidad con el accesorio para remover, alertas que se quedan hasta que las aceptas y nada que
arranque solo tras una pausa o una alerta. El control del motor va en **su propio proceso**: si la
interfaz se cuelga, la cocción no se corta.

## Cómo se mantiene al día esta copia

- [`.github/workflows/sync-upstream.yml`](.github/workflows/sync-upstream.yml) fusiona cada día la
  rama `main` de Wuki60 y de flexbox en la nuestra. Si hay cambios, los sube y lanza la publicación.
  Si una fusión choca con los cambios de esta copia, no toca nada y abre una incidencia.
- [`.github/workflows/release.yml`](.github/workflows/release.yml) publica
  `<versión del original>.<forkRevision>`, por ejemplo `0.5.1.1`, con el APK firmado con la misma
  clave pública del proyecto (se instala encima de la versión original sin desinstalar).
- Para publicar un cambio propio sin esperar al original (por ejemplo, una corrección de la
  traducción), sube en 1 `forkRevision` en [`fork.properties`](fork.properties).
- La traducción está en [`app/src/main/assets/lang/es.json`](app/src/main/assets/lang/es.json).
  Las claves son los textos en francés; respeta los `%s` / `%d`. Si el original añade textos nuevos,
  aparecen en francés hasta que se traduzcan, y `./gradlew :core:test` avisa de los que sobran.

## Compilar

Abre la carpeta en **Android Studio**, espera a la sincronización de Gradle y luego
**Build › Build App Bundle(s) / APK(s) › Build APK(s)**. El APK queda en
`app/build/outputs/apk/debug/`.

Desde la línea de órdenes: `./gradlew assembleDebug` (y `./gradlew :core:test` para las pruebas).
Gradle necesita JDK 17 o 21.

## Probar sin el robot

1. **Modo demo** (por defecto): instala el APK en cualquier dispositivo Android o en el emulador de
   Android Studio (en horizontal). La placa del motor está simulada; *Ajustes › Herramientas ›
   Diagnóstico de la base del motor* permite abrir la tapa o provocar un error para ver la
   seguridad. *Ajustes › Recetas de Lidl › Sincronizar* también descarga las recetas en cualquier
   dispositivo.
2. **Gemelo digital**: el gemelo del
   [Monsieur Cuisine Developer SDK](https://github.com/antrea/monsieur-cuisine-developer-sdk)
   reproduce el protocolo real. Ejecuta `python3 tools/twin.py --sdk <carpeta del SDK>` y en Toque
   elige *Ajustes › Máquina › Gemelo (PC)*.

## Créditos

- App Toque: [Wuki60/MrCuisineVibeConnect](https://github.com/Wuki60/MrCuisineVibeConnect).
- Instalación desde Mac y Linux: [flexbox/MrCuisineVibeConnect](https://github.com/flexbox/MrCuisineVibeConnect).
- Protocolo: [Monsieur Cuisine Developer SDK](https://github.com/antrea/monsieur-cuisine-developer-sdk) (MIT).
- Acceso al robot: [Monsieur-Cuisine-Connect-Hack](https://github.com/EliasKotlyar/Monsieur-Cuisine-Connect-Hack).
- Modo descarga MediaTek en macOS: [mtkclient](https://github.com/bkerler/mtkclient) (GPLv3, se descarga en `prepare`, no se incluye).

Licencia MIT. Este repositorio no contiene recetas, imágenes ni código de Lidl: cada robot descarga
las recetas por sí mismo de monsieur-cuisine.com, para el uso personal de su dueño.
