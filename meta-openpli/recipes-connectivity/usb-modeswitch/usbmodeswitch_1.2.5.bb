DESCRIPTION = "tool to switch multidevice usb modes"
require conf/license/license-gplv2.inc

LICENSE = "GPL"
DEPENDS = "libusb"

SRC_URI[md5sum] = "c393603908eceab95444c5bde790f6f0"
SRC_URI[sha256sum] = "ce47a3dec3e4c93e0a2fcea64278d0e289e6e78d8e1381c54f03986e443ab90f "

PV = "1.2.5"

SRC_URI = "http://www.draisberghof.de/usb_modeswitch/usb-modeswitch-${PV}.tar.bz2"

S = "${WORKDIR}/usb-modeswitch-${PV}"

do_install() {
        install -d ${D}${bindir}
        install -m 0755 ${S}/usb_modeswitch ${D}${bindir}
}

