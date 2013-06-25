DESCRIPTION = "enigma2 compatibility links"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"

PV = "${AZVERSION}"
PR = "${AZREVISION}"

inherit allarch

do_install() {
	install -d ${D}/lib
	ln -sf libcrypto.so.0.9.8 ${D}/lib/libcrypto.so.0.9.7
	ln -sf libgcc_s.so.1 ${D}/lib/libgcc_s_nof.so.1
	install -d ${D}/usr/lib
        ln -sf libdvbsi++.so.1 ${D}/usr/lib/libdvbsi++.so.0
        ln -sf libgif.so.4 ${D}/usr/lib/libungif.so.4
	ln -sf libjpeg.so.8 ${D}${libdir}/libjpeg.so.62
	ln -sf libssl.so.0.9.8 ${D}/usr/lib/libssl.so.0.9.7
	ln -sf libpython2.7.so.1.0 ${D}/usr/lib/libpython2.6.so.1.0
}

PACKAGES = "${PN}"
