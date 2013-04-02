DESCRIPTION = "tool to switch multidevice usb modes"
require conf/license/license-gplv2.inc

PV="20121109"

SRC_URI[md5sum] = "a7d23a03157871013a0d708ab2b1b6df"
SRC_URI[sha256sum] = "a74346a471d540ba9da7d7b332ad35ea05ff7375297c0da87da5be675293d5a5"

SRC_URI +="http://www.draisberghof.de/usb_modeswitch/usb-modeswitch-data-${PV}.tar.bz2"

S = "${WORKDIR}/usb-modeswitch-data-${PV}"

do_compile() {
}

do_install() {
	install -d ${D}/usr/share/usb_modeswitch
	install -d ${D}/etc/usb_modeswitch.d
	install -m 0755 ${S}/usb_modeswitch.d/* ${D}/usr/share/usb_modeswitch/
	DESTDIR=${D} make install
}

PACKAGES =+ "usbmodeswitch-data"
FILES_${PN} += "/etc /lib /usr"
