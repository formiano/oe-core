DESCRIPTION = "create Azboxhd patch.e2 images"
SECTION = "console/utils"
require conf/license/license-gplv2.inc
PROVIDES = "azbox-hd-buildimage"

PV = "${AZVERSION}"
PR = "${AZREVISION}"

SRC_URI = "file://pack_e2.c \
	   file://Makefile.am \
	   file://configure.ac"

S = "${WORKDIR}/pack_e2"

inherit autotools native

do_configure_prepend() {
	install -m 0644 ${WORKDIR}/pack_e2.c ${S}
	install -m 0644 ${WORKDIR}/configure.ac ${S}
	install -m 0644 ${WORKDIR}/Makefile.am ${S}
}

BBCLASSEXTEND = "native nativesdk"

