# Swing-Bancario  
Simulador bancario desarrollado en **Java Swing**, que permite realizar operaciones básicas como inicio de sesión, depósitos, retiros, transferencias y visualización de historial de transacciones.

---

## Características principales

### 🔐 Inicio de Sesión
El sistema valida credenciales predefinidas:
- **Usuario:** `cliente123`
- **Contraseña:** `clave456`

Si los datos son correctos, se abre la ventana principal del banco.

---

## Funcionalidades Bancarias

### Depósito
- Solicita un monto a depositar.
- Suma el monto al saldo actual.
- Agrega la operación al historial.

### Retiro
- Solicita un monto a retirar.
- Verifica si hay saldo suficiente.
- Si es válido, descuenta el monto.
- Registra la operación en el historial.

### Transferencia
- Solicita nombre del destinatario y monto.
- Verifica saldo disponible.
- Registra la transferencia con detalles.

### Historial de Transacciones
Cada operación realizada se muestra en un `JTextArea`, permitiendo llevar un seguimiento claro de la actividad del usuario.

---

## Tecnologías utilizadas
- **Java 8+**
- **Swing**
- **IntelliJ IDEA** (o cualquier IDE compatible)

---

## Capturas de pantalla
<img width="342" height="189" alt="image" src="https://github.com/user-attachments/assets/08016efa-c423-4d09-b142-c682ac6e9bbf" />
<img width="580" height="409" alt="image" src="https://github.com/user-attachments/assets/7c23de71-2ae5-4bf2-9c31-525b97da466a" />
<img width="583" height="408" alt="image" src="https://github.com/user-attachments/assets/2f909879-498a-4fb9-b57a-82cef0d0500f" />
<img width="583" height="412" alt="image" src="https://github.com/user-attachments/assets/a2ab11db-867d-4b8a-a3b0-d9d21b2d483f" />
<img width="583" height="408" alt="image" src="https://github.com/user-attachments/assets/9c9127fe-72d9-4ff9-80c7-227b78a50aea" />
<img width="583" height="412" alt="image" src="https://github.com/user-attachments/assets/ea1025bb-76c9-435b-8e45-e6aef05e4626" />
<img width="583" height="411" alt="image" src="https://github.com/user-attachments/assets/779c7005-1e35-45e1-b5b6-580f5e900610" />





---

## Cómo ejecutar el proyecto

1. Clonar este repositorio:
   ```bash
   git clone https://github.com/J3r3my1209/Swing-Bancario.git
2. Ejecutar la clase:
   LoginForm.java
3. Iniciar sesión con las credenciales:
   Usuario: cliente123
   Contraseña: clave456


