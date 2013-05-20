<<<<<<< HEAD
DESCRIPTION = "USB DVB driver for Afatech 9035 chipset"

require conf/license/license-gplv2.inc
=======
DESCRIPTION = "USB DVB driver for af9035 devices"

require conf/license/openpli-gplv2.inc
>>>>>>> openpli3/master

DVBPROVIDER ?= "kernel"

RDEPENDS_${PN} = " \
	${DVBPROVIDER}-module-dvb-usb-af9035 \
<<<<<<< HEAD
	firmware-dvb-usb-af9035-01 \
=======
	firmware-dvb-usb-af9035-02 \
	firmware-dvb-usb-it913x \
>>>>>>> openpli3/master
	"

PV = "1.0"
PR = "r0"

ALLOW_EMPTY_${PN} = "1"
