IBUS_adapter board
==================

The IBUS_adapter board is designed to adapt the I-Bus signals to
TTL serial at 9600 baud 8E1. The serial pinout is compatible with
the Virtuabotix [BT2S] Bluetooth Serial Slave device.

I stuffed this board, the BT2S, and the board from a cheap USB phone
car charger into a small project box and connected it to the 3-pin
plug formerly connected to the Sirius satellite radio module in the
trunk of my BMW.

In future revisions I may integrate the 5 V power supply into this board.

Configuring the BT2S
--------------------

The bluetooth module on the BT2S seems to be a Guangzhou HC
Information Technology Co. [HC-06]. The full command set can be
found in the linked PDF. The module defaults to 9600 8N1 and in order
to change this to 8E1 AT-commands need to be sent to it over the serial
interface. I used an FT232R breakout board and minicom to communicate
with the module. The commands are sent without any pauses between bytes
and are terminated with a pause. They are *not* terminated with a carriage
return or line feed. The easiest way to achieve this is to type the command
out and copy it to the clipboard without a terminating newline, then paste
it into your terminal program. The commands are also not echoed, only the
response will appear in the terminal. The command for changing the parity
to even is `AT+PE`. This does not change the parity for the command
interface, only bluetooth passthrough, so the terminal parity should be left
at none.

Other useful commands:
* `AT+NAMExxxx` will change the name of the device for bluethooth
  discovery to xxxx.
* `AT+PIN0000` will change the pairing code to 0000 (must be 4 digits).

  [bt2s]: https://www.virtuabotix.com/?page_id=3117&productid=609224531705
  [hc-06]: http://www.mcu-turkey.com/wp-content/uploads/2013/01/HC-Serial-Bluetooth-Products-201104.pdf
