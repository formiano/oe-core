DEPENDS += "enigma2"

SRC_URI = "git://github.com/oe-alliance/e2openplugin-OpenWebif.git"

S="${WORKDIR}/git"

SRCREV_pn-${PN} ?= "${AUTOREV}"

PACKAGES =+ "${PN}-src"

inherit autotools pkgconfig

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
