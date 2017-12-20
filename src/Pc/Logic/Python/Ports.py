import serial.tools.list_ports
import time


arduino_ports = [
    p.device
    for p in serial.tools.list_ports.comports()
    if 'Arduino' in p.description
]
time.sleep(1)
print(arduino_ports)