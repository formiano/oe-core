DESCRIPTION = "MyMetrixHD Skin - Based On VTi Version"
require conf/license/openpli-gplv2.inc
SRCREV_pn-${PN} ?= "${AUTOREV}"
inherit gitpkgv

PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"
PR = "r1"

PACKAGES = "${PN}"

SRC_URI = "git://github.com/Misenkooo/e2-openpli-plugin-MyMetrixHD.git;protocol=git"

FILES_${PN} = "\
	/usr \
	/etc \
"

S = "${WORKDIR}/git/MyMetrixHD"

do_compile() {
	python -O -m compileall ${S}
}

do_install() {
	install -d ${D}/usr
	cp -rp ${S}/usr/* ${D}/usr/
	install -d ${D}/etc
	cp -rp ${S}/etc/* ${D}/etc/
}

PACKAGES =+ "${PN}-src"

inherit pkgconfig

FILES_${PN}-dbg += "\
	/usr/lib/enigma2/python/Plugins/*/*/.debug \
	/usr/lib/enigma2/python/Plugins/*/*/*/.debug \
	/usr/lib/enigma2/python/Plugins/*/*/*/*/.debug \
	/usr/lib/enigma2/python/Plugins/*/*/*/*/*/.debug \
	/usr/lib/enigma2/python/Plugins/*/*/*/*/*/*/.debug \
	"

FILES_${PN}-src = "\
	/usr/lib/enigma2/python/*/*.py \
	/usr/lib/enigma2/python/*/*/*.py \
	/usr/lib/enigma2/python/*/*/*/*.py \
	/usr/lib/enigma2/python/*/*/*/*/*.py \
	/usr/lib/enigma2/python/*/*/*/*/*/*.py \
	/usr/lib/enigma2/python/*/*/*/*/*/*/*.py \
	/usr/lib/enigma2/python/*/*/*/*/*/*/*/*.py \
	/usr/lib/enigma2/python/*/*/*/*/*/*/*/*/*.py \
	/usr/lib/enigma2/python/*/*/*/*/*/*/*/*/*/*.py \
	/usr/lib/enigma2/python/*/*/*/*/*/*/*/*/*/*/*.py \
	"
