DESCRIPTION = "PersianEmpireHD1 Skin By ramiMAHER"
MAINTAINER = "ramiMAHER"
require conf/license/openpli-gplv2.inc
SRCREV_pn-${PN} ?= "${AUTOREV}"
inherit gitpkgv

PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"
PR = "${AZVERSION}.${AZREVISION}"

PACKAGES = "${PN}"

SRC_URI = "git://github.com/persianpros/persianempirehd1-skin.git;protocol=git"

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
