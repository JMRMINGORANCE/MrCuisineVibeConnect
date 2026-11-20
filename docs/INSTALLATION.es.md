# Instalar Toque en la Monsieur Cuisine Connect

🇬🇧 **[English version](INSTALLATION.md)** · 🇫🇷 **[Version française](INSTALLATION.fr.md)**

Esta guía empieza desde cero y no hace falta saber programar. Necesitas un ordenador, un cable, un
destornillador y entre 1 y 2 horas (casi todo es esperar). **Léela entera una vez antes de empezar.**

- **Mac**: partes 1 a 7.
- **PC con Linux**: lee primero la [parte 8](#8-desde-un-pc-con-linux), que sustituye a la parte 2.
- **PC con Windows**: partes 1, 4, 5 y 6, y la [parte 9](#9-desde-un-pc-con-windows) en lugar de
  las partes 2 y 3.

Los nombres de los menús son los de Toque en español (*Ajustes › Langue · Language › Español*).
Esta copia instala la versión en español de Toque, que se mantiene al día con el
[proyecto original](https://github.com/Wuki60/MrCuisineVibeConnect).

## Contenido

1. [Antes de empezar](#1-antes-de-empezar): el robot, el material, los riesgos, las 3 reglas
2. [Preparar el Mac](#2-preparar-el-mac-una-sola-vez) (una vez, 15 minutos)
3. [Instalar con Claude](#3a-instalar-con-claude-recomendado) (recomendado) **o**
   [instalarlo tú mismo](#3b-instalarlo-tú-mismo-paso-a-paso), paso a paso
4. [Después de la instalación](#4-después-de-la-instalación): primera prueba, recetas, wifi
5. [Si algo sale mal](#5-si-algo-sale-mal) · [Deshacer](#6-deshacer) ·
   [Detalles técnicos](#7-detalles-técnicos)
6. [Desde un PC con Linux](#8-desde-un-pc-con-linux) · [Desde un PC con Windows](#9-desde-un-pc-con-windows)

## 1. Antes de empezar

### Tu robot

- **Monsieur Cuisine Connect**, modelo **SKMC 1200** (viene en la etiqueta del robot).
- Si es una **Monsieur Cuisine Smart** (SKMS 1200) u otro modelo: **para aquí**, esta guía no sirve.

### Material

| | Necesitas | Nota |
|---|---|---|
| ☐ | Un **Mac** (Apple silicon o Intel) con Internet | Te pedirá la contraseña del Mac |
| ☐ | Un cable de datos **USB-A macho ↔ USB-A macho** | Los dos extremos son el USB grande rectangular. Un cable USB-C ↔ USB-A **no** sirve |
| ☐ | Si el Mac solo tiene USB-C: un **adaptador USB-C → USB-A** (hembra) o un hub | El cable A-A se enchufa ahí |
| ☐ | Un **destornillador Torx pequeño** | Para la tapa de mantenimiento de debajo del robot |
| ☐ | La **contraseña del wifi** de casa | Para el final |
| ☐ | El robot **cerca del Mac**, enchufado a la corriente, **con el vaso vacío** | Sigue enchufado a la corriente de principio a fin |

### Riesgos

- Pierdes la garantía del robot.
- Un error puede dejar inservible la tablet del robot. El script lo comprueba todo antes de escribir
  nada, y la **copia de seguridad** que se hace al principio es tu forma de volver atrás: no te la
  saltes nunca y guarda una copia.
- Toque funciona a diario en dos robots: uno instalado desde Windows (octubre de 2026) y otro desde
  un Mac (noviembre de 2026). No son muchos: bajo tu responsabilidad.

### Las 3 reglas que hay que recordar

Debajo del robot, detrás de la tapa, hay dos puertos USB:

<img src="https://raw.githubusercontent.com/EliasKotlyar/Monsieur-Cuisine-Connect-Hack/master/informations/usbports.jpg" alt="Los dos puertos USB bajo la tapa: 'Power PCB USB' a la izquierda, 'Android USB' a la derecha" width="360">

1. **Siempre el puerto de la derecha, el que pone «Android USB».** Nunca el que pone
   «Power PCB USB»: puede destruir la placa del robot.
2. **Enciende el robot con el cable desenchufado** y enchufa el cable cuando la pantalla ya esté
   encendida. Si lo enciendes con el cable puesto, se queda en una pantalla de batería: apaga,
   desenchufa, enciende y enchufa. **Única excepción**: los pasos `backup` y `flash-twrp`, en los
   que enchufas el cable con el robot apagado y lo enciendes cuando te lo pida el Mac.
3. **No apagues nunca el robot ni desenchufes el cable mientras un paso está en marcha.** Espera a
   la línea `OK: ... done`.

El interruptor del robot está en la parte de atrás.

## 2. Preparar el Mac (una sola vez)

Todo se hace en el **Terminal**: ábrelo con `Cmd + Espacio`, escribe `Terminal` y pulsa `Intro`.
Para cada orden de abajo: cópiala, pégala en el Terminal (`Cmd + V`), pulsa `Intro` y espera a que
termine (vuelve a aparecer el cursor).

1. **Instala Homebrew** (la herramienta que instala todo lo demás):
   ```
   /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
   ```
   Te pide la contraseña del Mac: escríbela (**no se ve nada mientras escribes, es normal**) y pulsa
   `Intro`. También instala las herramientas de línea de órdenes de Apple (entre ellas git): de 5 a
   15 minutos. Al final, si aparece **Next steps** con órdenes que ejecutar, cópialas y ejecútalas;
   luego **cierra el Terminal y vuelve a abrirlo**.
2. **Instala Python y libusb**:
   ```
   brew install python libusb
   ```
3. **Descarga este repositorio** en una carpeta `MrCuisineVibeConnect` dentro de tu carpeta personal:
   ```
   git clone https://github.com/JMRMINGORANCE/MrCuisineVibeConnect.git ~/MrCuisineVibeConnect
   ```
4. **Entra en esa carpeta**:
   ```
   cd ~/MrCuisineVibeConnect
   ```
   Cada vez que vuelvas a abrir el Terminal, escribe esta orden antes que las demás.

Después elige: **3a** (Claude escribe las órdenes por ti) o **3b** (las escribes tú). En los dos
casos, el robot solo cambia en los pasos que lo dicen.

## 3a. Instalar con Claude (recomendado)

[Claude Code](https://code.claude.com/docs/en/quickstart) es un asistente de IA que escribe las
órdenes en el Terminal, lee los resultados y te dice lo que tienes que hacer con las manos. Necesita
un plan de pago de Claude (Pro o superior): el plan gratuito no basta.

1. Instálalo:
   ```
   curl -fsSL https://claude.ai/install.sh | bash
   ```
   y luego cierra el Terminal y vuelve a abrirlo. `claude --version` tiene que mostrar un número de
   versión.
2. Arráncalo en la carpeta del repositorio:
   ```
   cd ~/MrCuisineVibeConnect
   claude
   ```
   La primera vez abre el navegador para que inicies sesión en tu cuenta de Claude.
3. Pega este mensaje:
   > Instala Toque en mi Monsieur Cuisine Connect siguiendo AGENTS.md. Háblame en español.

A partir de ahí:

- Claude te explica los riesgos y te pide un **sí** antes de empezar.
- Antes de cada orden, Claude Code te pide permiso: léela y acéptala.
- Antes de cada paso que escribe en el robot, te dice lo que hace y espera tu **sí**.
- Cuando hace falta tu mano (tapa, cable, interruptor, tocar la pantalla del robot), te lo dice:
  hazlo y contesta «hecho».

Ten esta guía abierta al lado: la parte 3b explica qué esperar en cada paso.

## 3b. Instalarlo tú mismo, paso a paso

Un solo script lo hace todo: `tools/setup/toque-setup.sh`. Cada paso se ejecuta con
`bash tools/setup/toque-setup.sh <paso>`, desde la carpeta del repositorio. Lo que muestra:

- `OK:` hecho y comprobado;
- `HUMAN:` algo que tienes que hacer tú (cable, interruptor, pantalla del robot). Hazlo y, si el
  paso se ha parado, vuelve a ejecutar la misma orden;
- `STOP:` un problema, **no se ha hecho nada irreversible**. La línea dice qué hacer; consulta
  también [Si algo sale mal](#5-si-algo-sale-mal).

¿Te has perdido? Esta orden dice qué está hecho y cuál es el siguiente paso:

```
bash tools/setup/toque-setup.sh status
```

Todos los pasos se pueden repetir sin peligro.

### Paso 1 · Descargar las herramientas — `prepare`

El robot no interviene.

```
bash tools/setup/toque-setup.sh prepare
```

Descarga en la carpeta `~/Toque`: adb, Magisk, TWRP, la última versión de Toque y mtkclient, y
comprueba cada archivo. Si aparece `MISSING:`, instala lo que diga y vuelve a ejecutarlo.

✅ Al final: `OK: prepare done. Next: backup`

### Paso 2 · Copia de seguridad del robot — `backup`

Solo se lee el robot, no se escribe nada.

1. **Apaga el robot** (interruptor de atrás).
2. Saca el vaso y desatornilla la **tapa de mantenimiento** de debajo del robot.
3. Enchufa el cable A-A al puerto de la derecha, **«Android USB»**, y al Mac.
4. Ejecuta:
   ```
   bash tools/setup/toque-setup.sh backup
   ```
5. Cuando el Terminal muestre *Waiting for PreLoader VCOM* (y luego *Handshake failed, retrying...*
   cada 10 segundos, es normal), **enciende el robot**, una sola vez. La pantalla del robot se queda
   negra: también es normal.
6. Si el Mac pide **permitir que se conecte el accesorio**: pulsa **Permitir** y luego apaga y
   enciende el robot mientras el paso espera.

✅ Al final: `OK: backup done. Next: flash-twrp`

La carpeta `~/Toque/backup` contiene ahora tus dos copias. **Cópiala a un pendrive o a la nube**:
es tu única forma de volver atrás. Después **apaga el robot** (pantalla negra: se ha quedado en modo
descarga).

### Paso 3 · Poner TWRP en el robot — `flash-twrp`

Escribe en el robot: TWRP, un sistema de mantenimiento, sustituye temporalmente al arranque normal.

1. Robot **apagado**, cable **enchufado**.
2. Ejecuta:
   ```
   bash tools/setup/toque-setup.sh flash-twrp
   ```
3. Igual que en la copia: enciende el robot cuando el Terminal espere al dispositivo.

✅ Al final: `OK: flash-twrp done`

4. **Apaga** el robot, **desenchufa** el cable y **enciéndelo**: aparece la pantalla de **TWRP**. La
   primera vez, TWRP pregunta *Keep System Read only?*: toca **Keep Read Only** (no deslices).
   **Vuelve a enchufar** el cable.

### Paso 4 · Preparar el root — `root`

Robot en la pantalla de TWRP, cable enchufado.

```
bash tools/setup/toque-setup.sh root
```

A partir de tu copia, prepara la versión «rooteada» del arranque del robot (con
[Magisk](https://github.com/topjohnwu/Magisk)) y activa en Android la conexión con el Mac.

✅ Al final: `OK: Magisk boot image ready ... Next: flash-root`

### Paso 5 · Escribir el root — `flash-root`

Escribe en el robot. Sigue en TWRP, con el cable enchufado.

```
bash tools/setup/toque-setup.sh flash-root
```

**No toques el robot** hasta que termine. El script comprueba primero lo que hay en el robot (y no
escribe nada si no lo reconoce), escribe y luego vuelve a leer para comprobarlo.

✅ Al final: `OK: flash-root done. Next: android`

Después **apaga** el robot, **desenchufa** el cable y **enciéndelo**: arranca la app de Monsieur
Cuisine de siempre. **Vuelve a enchufar** el cable.

### Paso 6 · Comprobar el root — `android`

```
bash tools/setup/toque-setup.sh android
```

- Si el robot pide **permitir la depuración USB**: marca *Permitir siempre*, toca **Aceptar** y
  vuelve a ejecutar el paso.
- Cuando Magisk pida acceso de superusuario para *Shell*: toca **Grant** en la pantalla del robot
  **antes de 10 segundos**. ¿Se te ha pasado? Vuelve a ejecutar el paso.

✅ Al final: `OK: Root works (uid 0). Next: install`

### Paso 7 · Instalar Toque — `install`

```
bash tools/setup/toque-setup.sh install
```

✅ Al final: `OK: Toque installed` y Toque se abre en el robot. Elige el español en *Ajustes ›
Langue · Language*, haz la primera prueba ([parte 4](#4-después-de-la-instalación)) y vuelve al
paso 8.

### Paso 8 · Sin cable — `wifi`

El robot tiene que estar en el wifi de casa. Si no lo está: en Toque, toca **el reloj 5 veces
seguidas** arriba a la derecha (ajustes de Android), conéctate al wifi y vuelve con el botón
**«Volver a Toque»**.

```
bash tools/setup/toque-setup.sh wifi
```

✅ Al final: `OK: ADB over Wi-Fi: <dirección IP>:5555`. Desenchufa el cable y **vuelve a atornillar
la tapa**. Listo.

## 4. Después de la instalación

### Primera prueba — vaso VACÍO

1. En Toque: **Ajustes › Máquina › Máquina real › Aplicar**. Toque desactiva el servicio del motor
   de la app original (es reversible) y abre el puerto serie.
2. La etiqueta de arriba a la derecha tiene que pasar a **«Lista»**. Si no: Ajustes › Herramientas ›
   Diagnóstico de la base del motor (¿llegan tramas?) y Registro.
3. Vaso vacío, tapa cerrada y **la mano cerca del interruptor**:
   - Cocción manual: 10 s, velocidad 2 → el motor gira y se para solo.
   - Abre la tapa durante una prueba → parada inmediata, «En pausa: tapa abierta».
   - 30 s a 37 °C, velocidad 1 → la temperatura mostrada sube.
   - Báscula: pon encima un objeto de peso conocido, Tara.
   - Diagnóstico: quita el vaso y mira «Vaso detectado». Si cambia bien, puedes activar
     Ajustes › Seguridad › «Exigir que se detecte el vaso».
4. Ante el más mínimo comportamiento raro: **interruptor** y vuelve a la app original
   ([parte 6](#6-deshacer)).

### Recetas de Lidl

**Ajustes › Recetas de Lidl › Sincronizar** (robot con wifi). Toque descarga las recetas oficiales
de monsieur-cuisine.com sin necesidad de cuenta: unos 15 minutos la primera vez y después solo las
nuevas, cada noche a las 3:30. Vienen en el idioma de la interfaz: con Toque en español, se descarga
el catálogo español (unas 880 recetas) y las opiniones en español.

Otra fuente: **«Importar de la app original»** copia las recetas que ya tiene la app original
(sustituyen a las de la web). Si aún no tiene ninguna, **«Abrir la app original»** deja que se
sincronice con los servidores de Lidl; vuelve con el botón **«Volver a Toque»**. Si esa importación
falla, el archivo `Android/data/app.toque/files/diagnostic-recettes.txt` describe la base de datos
original.

### Cada día

- Toque se abre solo al encender el robot.
- El robot no tiene botón de Inicio ni de Atrás: cuando hay otra app abierta (la app original, los
  ajustes de Android), el botón **«Volver a Toque»** se queda encima (puedes moverlo).
- Ajustes de Android: toca 5 veces seguidas el reloj de arriba a la derecha.
- Actualizaciones: **Ajustes › Actualizaciones** instala las versiones nuevas de esta copia en
  español, que se publican solas cuando el proyecto original saca una versión.
- Desde el Mac, por wifi: `~/Toque/platform-tools/adb connect <IP del robot>:5555` y luego
  `~/Toque/platform-tools/adb shell am start -n app.toque/.ui.MainActivity` abre Toque.

## 5. Si algo sale mal

| Lo que ves | Por qué | Qué hacer |
|---|---|---|
| El robot muestra una **batería** en lugar de arrancar | Lo has encendido con el cable enchufado | Apaga, desenchufa, enciende y enchufa cuando la pantalla esté encendida |
| `backup` / `flash-twrp` esperan sin fin (*Waiting for device*) | Cable, puerto o USB-C sin adaptador incorrectos, o robot ya encendido | Cable A-A, puerto «Android USB», adaptador USB-C → USB-A. Robot apagado, repite el paso y enciende cuando lo pida |
| *Handshake failed, retrying...* cada 10 s | Normal: el Mac espera al robot | Enciende el robot, una vez |
| *Sync error* o *Failed to upload da* | El robot ya estaba en modo descarga, o falta `prepare` | `prepare`, apaga el robot y repite el paso |
| Pantalla del robot negra tras `backup` o `flash-twrp` | Se queda en modo descarga | Apágalo |
| El Mac pide **permitir el accesorio** | Seguridad de los Mac con Apple silicon | **Permitir** y luego apaga y enciende el robot |
| `MISSING: Python` o `MISSING: libusb` | Faltan paquetes | `brew install python libusb`. ¿No encuentra `brew`? Repite los *Next steps* de Homebrew ([parte 2](#2-preparar-el-mac-una-sola-vez)) |
| El Mac no ve el robot en TWRP | Cable enchufado antes de que TWRP estuviera en pantalla | Desenchufa y vuelve a enchufar |
| Pantalla de batería entre `root` y `flash-root` | El robot se ha reiniciado con el cable puesto | Apaga, desenchufa, enciende: vuelve TWRP. Enchufa y sigue |
| `flash-root`: *write failed* o *read-back differs* | Error de escritura | **No apagues el robot** (TWRP sigue en marcha). Repite `flash-root` |
| `flash-root`: *holds something unexpected* | Contenido inesperado en el robot | No se ha escrito nada. No sigas: `status` y pide ayuda |
| `android`: se te ha pasado tocar **Grant** | El aviso de Magisk se cierra a los 10 s | Repite `android` y toca rápido. Si quedó denegado: app Magisk › Superusuario › Shell › permitir |
| adb: *more than one device/emulator* | Tras `wifi`, con el cable aún puesto, adb ve el robot dos veces | Desenchufa el cable o ejecuta `adb disconnect <IP del robot>:5555` |
| Toque muestra «Sin base» (sin enlace con el motor) | Falta el busybox de Magisk | Repite `install` |
| Magisk dice que se ha denegado el superusuario a Toque | Ajuste de Magisk | Repite `install` |
| Atascado en la app original | Normal, este robot no tiene botón Atrás | Botón **«Volver a Toque»** |

¿Sigues atascado? Envía `~/Toque/setup-log.txt` (y `~/Toque/mtk-<paso>-out.txt` para `backup` o
`flash-twrp`) a quien te ayude, o abre una
[incidencia](https://github.com/JMRMINGORANCE/MrCuisineVibeConnect/issues).

### Si el Mac nunca llega al robot en modo descarga

No se escribe nada mientras el Mac no llegue al robot. Por orden:

1. Justo después de encender el robot, `system_profiler SPUSBDataType` tiene que mostrar un
   **MT65xx Preloader** (vendor ID `0x0e8d`). Si no, es el cable, el puerto o el adaptador.
2. Si se ve el robot pero el paso se para en *Sync error*: `status` tiene que mostrar
   *mtkclient: v2.1.4.1 + macOS serial fixes* (si no, ejecuta `prepare`). Apaga el robot y repite el
   paso. `--libusb` prueba otra forma de conectar (no funcionó en el robot en que se probó).
3. Si no, haz **solo** `backup` y `flash-twrp` en un PC con Windows ([parte 9](#9-desde-un-pc-con-windows),
   SP Flash Tool), copia las dos copias en `~/Toque/backup/` del Mac, ejecuta `backup` (entonces solo
   las comprueba) y sigue con `root` en el Mac: todo lo demás va por adb.

## 6. Deshacer

- **Volver a la app original** (manteniendo el root): en Toque, Ajustes › Herramientas › «Volver a
  la app original», o desde el Mac `bash tools/restore-robot.sh --uninstall` (en Windows:
  `powershell -ExecutionPolicy Bypass -File tools\restore-robot.ps1 -Uninstall`). Toque no cambia ni
  el sistema ni la app original: solo desactiva el servicio del motor original, y las dos formas lo
  deshacen.
- **Quitar también el root y TWRP** (después de lo anterior): `bash tools/setup/toque-setup.sh unroot`
  vuelve a poner en el robot tus copias `boot-original.img` y `recovery-original.img`, desde TWRP.
  Si el robot ya no arranca: `unroot --download-mode` (robot apagado, cable puesto, como en
  `backup`). Lo que queda después en el robot: adb sigue activado (USB, y el puerto 5555 si hiciste
  `wifi`), sin root, y la app Magisk sigue instalada, sin efecto.

## 7. Detalles técnicos

Para curiosos, o para hacerlo todo sin el script. Los archivos están en `~/Toque` (carpeta de
trabajo, se cambia con `--work`) y el registro en `~/Toque/setup-log.txt`.

**Lo que se descarga** (y se comprueba con SHA-256): adb (Google), el APK oficial de
[Magisk](https://github.com/topjohnwu/Magisk/releases) v31.0, el recovery TWRP `hacks/recovery.img`
y el archivo de particiones `l706_dfbh_v_695scatter.txt` de la
[guía de referencia](https://github.com/EliasKotlyar/Monsieur-Cuisine-Connect-Hack), el último APK de
Toque de esta copia y [mtkclient](https://github.com/bkerler/mtkclient) v2.1.4.1. mtkclient sustituye
a SP Flash Tool y a su driver de Windows para las dos operaciones que necesitan el modo descarga
MediaTek del robot (la copia y la primera escritura de TWRP). mtkclient necesitó tres arreglos para
el driver serie de macOS: [tools/setup/mtkclient-macos-serial.patch](../tools/setup/mtkclient-macos-serial.patch),
que aplica `prepare`.

**`backup`** lee `boot` (inicio `0x1d20000`, longitud `0x1000000`, región `EMMC_USER`) y `recovery`
(inicio `0x2d20000`, longitud `0x1000000`) en `~/Toque/backup/boot-original.img` y
`recovery-original.img` (16 MiB cada una) y comprueba que son imágenes de arranque de Android. A
mano, desde `~/Toque/mtkclient/run`:

```
DYLD_FALLBACK_LIBRARY_PATH="$(brew --prefix)/lib" ../venv/bin/python ../src/mtk.py --serialport DETECT multi "ro 0x1d20000 0x1000000 boot-original.img --parttype user;ro 0x2d20000 0x1000000 recovery-original.img --parttype user"
```

(la ruta de libusb hace falta en Apple silicon; `--serialport` solo funciona con los arreglos que
aplica `prepare`).

**`flash-twrp`** escribe TWRP (`hacks/recovery.img`) solo en `boot` y lo vuelve a leer para
comprobarlo.

**`root`**, desde TWRP, equivale a:

```
adb push ~/Toque/downloads/Magisk-v31.0.apk /tmp/magisk.apk
adb push ~/Toque/backup/boot-original.img /tmp/boot.img
adb push ~/.android/adbkey.pub /tmp/adbkey.pub
adb push tools/robot/twrp-root.sh /tmp/
adb shell sh /tmp/twrp-root.sh
adb pull /tmp/magisk/new-boot.img ~/Toque/boot-magisk.img
```

[`twrp-root.sh`](../tools/robot/twrp-root.sh) parchea tu `boot` original con Magisk
(`boot-magisk.img`), activa ADB en Android (`/system/build.prop`), borra
`/system/recovery-from-boot.p` (si no, Android vuelve a poner el recovery original encima de TWRP) y
autoriza la clave ADB de tu Mac (no hay que confirmar nada en la pantalla del robot).

**`flash-root`** escribe TWRP en `recovery` y `boot-magisk.img` en `boot` desde TWRP, con
[`twrp-flash.sh`](../tools/robot/twrp-flash.sh): primero comprueba que `recovery` contiene tu
recovery original (o TWRP) y `boot` contiene TWRP (o el boot de Magisk), no escribe nada en caso
contrario, y luego vuelve a leer cada partición.

**`android`** instala la app Magisk y comprueba el root: a mano,
`adb install ~/Toque/downloads/Magisk-v31.0.apk` y luego `adb shell su -c id`, que tiene que mostrar
`uid=0`.

**`install`** ejecuta [`tools/install-robot.sh`](../tools/install-robot.sh) (por wifi:
`--ip <IP del robot>`; otro APK: `--apk <archivo>`). También autoriza a Toque en Magisk (sin aviso en
el robot: el de Magisk se cierra solo a los 10 s y después deniega para siempre) y coloca el
**busybox** de Magisk (`/data/adb/magisk/busybox`), que Toque necesita para el puerto serie.

**`wifi`** equivale a:

```
adb shell su -c "setprop persist.adb.tcp.port 5555"
adb reboot
```

Solo en la red de casa: cualquier dispositivo conectado a ella podría llegar al robot.

## 8. Desde un PC con Linux

El mismo script y los mismos pasos funcionan desde Linux (x86_64 o ARM). **Probado una vez en un
robot real**, desde Arch Linux en un portátil x86_64: de `prepare` a `wifi` y `unroot` funcionaron
todos, por libusb; `backup` y `flash-twrp` también funcionaron con `--serial`. Aún sin probar desde
Linux: `unroot --download-mode` y ARM.

En lugar de la parte 2:

1. Instala Python 3.10+, libusb, git, curl y unzip con la herramienta de tu distribución:
   - Arch: `sudo pacman -S python libusb git curl unzip`
   - Debian/Ubuntu: `sudo apt install python3 python3-venv libusb-1.0-0 git curl unzip`
   - Fedora: `sudo dnf install python3 libusb1 git curl unzip`
2. Descarga este repositorio y entra en su carpeta (pasos 3 y 4 de la parte 2).
3. Da a tu usuario acceso al puerto USB del robot con la
   [regla udev](../tools/setup/51-toque-robot.rules) de este repositorio (te pide la contraseña):
   ```
   sudo install -m 644 tools/setup/51-toque-robot.rules /etc/udev/rules.d/
   sudo udevadm control --reload-rules
   ```
   La regla también aparta a ModemManager del robot. Da acceso a quien tenga la sesión abierta en
   el ordenador, no a una sesión SSH. Si gestionas el acceso USB de otra forma, añade
   `--no-udev-check` a todas las órdenes.

Después sigue la parte 3a o 3b tal cual, leyendo «ordenador» donde pone «Mac». Lo que cambia:

- No aparece el aviso de *permitir el accesorio*.
- mtkclient habla con el robot por **libusb** por defecto (en un Mac: el driver serie). Si `backup`
  se para en un handshake o en *Sync error*, apaga el robot y vuelve a probar con `--serial` (usa
  `/dev/ttyACM0` y los mismos arreglos que en un Mac).
- Para ver si el ordenador ve el robot en modo descarga: `lsusb` justo después de encenderlo tiene
  que mostrar `0e8d:2000` (MediaTek). Si no, es el cable, el puerto o el adaptador. En TWRP el robot
  aparece como `18d1:4ee2`, y en Android con adb como `0e8d:201d` (`0e8d:2008` antes del root).
- `sudo dmesg` repite *new low-speed USB device* y *device descriptor read/64, error -71*: el cable
  no está bien metido. Desenchúfalo y vuelve a enchufarlo.
- `adb` dice *no permissions*: falta la regla udev; instálala y desenchufa y vuelve a enchufar el
  cable.
- En Linux ARM, Google no publica adb: instala el de tu distribución (`adb` o `android-tools`).

## 9. Desde un PC con Windows

> **Con Claude Code** (u otro agente de IA que maneje tu PC): dale este repositorio y pídele que
> siga [AGENTS.md](../AGENTS.md) usando el script de Windows
> [`tools/setup/toque-setup.ps1`](../tools/setup/toque-setup.ps1), que automatiza los pasos de abajo.
> También puedes ejecutarlo tú (`-Step status` te dice por dónde vas).

Lee antes la [parte 1](#1-antes-de-empezar): las reglas y los riesgos son los mismos. Los pasos 1
y 2 (root) son los delicados.

### Lo que necesitas

- Un PC con Windows, con Android Studio (para ADB) y [SP Flash Tool](https://spflashtool.com/)
  (probada la v5.1924).
- Un cable de datos **USB-A macho ↔ USB-A macho** y un destornillador Torx pequeño. Un cable
  USB-A ↔ USB-C **no** sirve: el puerto del robot es de dispositivo, así que el USB-C del PC nunca
  lo detecta.
- El driver **MediaTek PreLoader USB VCOM**: búscalo en el
  [Catálogo de Microsoft Update](https://www.catalog.update.microsoft.com/), extrae el `.cab` (quédate
  con la carpeta `x64`) e instálalo como administrador: `pnputil /add-driver cdc-acm.inf /install`.
  En Windows 11, si Windows dice *«No se puede cargar un controlador en este dispositivo: usb2ser.sys»*,
  desactiva la **Integridad de memoria** (Seguridad de Windows › Seguridad del dispositivo ›
  Aislamiento del núcleo) y reinicia. Vuelve a activarla al terminar (quita antes el driver:
  `pnputil /enum-drivers` para encontrar su `oemXX.inf` y luego
  `pnputil /delete-driver oemXX.inf /uninstall`).
- La guía de referencia: <https://github.com/EliasKotlyar/Monsieur-Cuisine-Connect-Hack> (archivo
  de particiones `l706_dfbh_v_695scatter.txt` y recovery TWRP `hacks/recovery.img`).
- El APK oficial de [Magisk](https://github.com/topjohnwu/Magisk/releases) (probada la v31.0;
  Android 6 es compatible).
- Este repositorio, descargado con `git clone https://github.com/JMRMINGORANCE/MrCuisineVibeConnect.git`
  o como ZIP desde GitHub (botón *Code › Download ZIP*).

> **Enciende el robot con el cable desenchufado.** Si lo enciendes con el cable puesto, se queda en
> una pantalla de batería (carga). Enchufa el cable cuando haya arrancado. SP Flash Tool es la
> excepción: ahí el robot tiene que encenderse *con* el cable enchufado.

### Paso 1 · Copia de seguridad y TWRP

1. Robot apagado (interruptor de atrás). Desatornilla la tapa de mantenimiento de debajo del robot.
2. Conecta el PC **solo al puerto de la derecha, el que pone «Android USB»** (el otro puede destruir
   la placa).
3. SP Flash Tool, con el archivo scatter cargado, pestaña **Readback**: añade `boot` (inicio
   `0x1d20000`, longitud `0x1000000`, región `EMMC_USER`) y `recovery` (inicio `0x2d20000`,
   longitud `0x1000000`), pulsa **Read Back** y enciende el robot. Obtienes `boot-original.img` y
   `recovery-original.img` (16 MB cada una): **guárdalas bien, son tu única forma de volver atrás.**
4. Pestaña **Download**, *Download Only*: marca solo `boot`, con `hacks/recovery.img` (TWRP). Pulsa
   **Download** y apaga y enciende el robot.
5. Apaga el robot, desenchufa el cable y enciéndelo: arranca **TWRP**. Vuelve a enchufar el cable:
   `adb devices` muestra el robot en modo `recovery`.

### Paso 2 · ADB y root (Magisk)

1. Desde la carpeta del repositorio, con TWRP en marcha:
   ```
   adb push Magisk-v31.0.apk /tmp/magisk.apk
   adb push boot-original.img /tmp/boot.img
   adb push %USERPROFILE%\.android\adbkey.pub /tmp/adbkey.pub
   adb push tools\robot\twrp-root.sh /tmp/
   adb shell sh /tmp/twrp-root.sh
   adb pull /tmp/magisk/new-boot.img boot-magisk.img
   ```
   (Lo que hace `twrp-root.sh` está en la [parte 7](#7-detalles-técnicos).)
2. SP Flash Tool, *Download Only*: marca `boot` = `boot-magisk.img` y `recovery` =
   `hacks/recovery.img`, pulsa **Download** y apaga y enciende el robot.
3. Apaga el robot, desenchufa el cable y enciéndelo: arranca la app original. Enchufa el cable.
4. `adb install Magisk-v31.0.apk` y luego `adb shell su -c id` y toca **Grant** en el robot: tiene
   que mostrar `uid=0`.

Para deshacer el root: escribe `boot-original.img` en `boot` con SP Flash Tool (y
`recovery-original.img` en `recovery` para quitar TWRP).

Opcional, para no necesitar más el cable, ADB por wifi (solo en la red de casa):

```
adb shell su -c "setprop persist.adb.tcp.port 5555"
adb reboot
```

### Paso 3 · Instalar Toque

1. Descarga el último `Toque-X.Y.Z.apk` de la
   [página de versiones](https://github.com/JMRMINGORANCE/MrCuisineVibeConnect/releases).
2. `powershell -ExecutionPolicy Bypass -File tools\install-robot.ps1 -Apk <ruta del APK>`
   (por wifi: añade `-Ip <IP del robot>`).
3. El script también autoriza a Toque en Magisk y comprueba que está el busybox de Magisk.

Después sigue con la [parte 4](#4-después-de-la-instalación).
