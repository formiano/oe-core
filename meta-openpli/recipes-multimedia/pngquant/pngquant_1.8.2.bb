DESCRIPTION = "pngquant converts 24/32-bit RGBA PNGs to 8-bit"
MAINTAINER = "Persian Professionals <persianpros@yahoo.com>"
SECTION = "console/graphics"
HOMEPAGE = "http://pngquant.org"
require conf/license/license-gplv2.inc

inherit autotools pkgconfig	

PR = "r1"

SRC_URI = "http://pngquant.org/pngquant-${PV}-src.tar.bz2"

S = "${WORKDIR}/pngquant-1.8.2"

do_install () {
        install -d ${D}${bindir}
        install -m 755 ${BPN} ${D}${bindir}
}
