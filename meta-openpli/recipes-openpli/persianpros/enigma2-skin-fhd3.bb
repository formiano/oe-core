DESCRIPTION = "FHD3 Skin By Naser (Eminem) - Based On ABC HD Skin By CooST"
MAINTAINER = "Naser (Eminem)"
require conf/license/openpli-gplv2.inc
SRCREV_pn-${PN} ?= "${AUTOREV}"
inherit gitpkgv

PV = "rc3+git${SRCPV}"
PKGV = "rc3+git${GITPKGV}"
PR = "r3"

PACKAGES = "${PN}"

SRC_URI = "git://github.com/persianpros/fhd3-skin.git;protocol=git"

FILES_${PN} = "/usr/"

S = "${WORKDIR}/git"

do_compile() {
	python -O -m compileall ${S}
}

do_install() {
	install -d ${D}/usr
	cp -rp ${S}/usr/* ${D}/usr/
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
