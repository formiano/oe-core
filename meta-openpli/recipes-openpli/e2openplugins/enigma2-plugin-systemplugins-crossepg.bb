DESCRIPTION = "Handle your EPG on enigma2 from various sources (opentv, mhw, xmltv, custom sources)"
HOMEPAGE = "https://github.com/E2OpenPlugins/e2openplugin-CrossEPG"
MODULE = "CrossEPG"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://LICENSE.TXT;md5=4fbd65380cdd255951079008b364516c"

DEPENDS += "libxml2 zlib python python-native"

# it sometime fails to build in parallel
PARALLEL_MAKE = ""

inherit gitpkgv
SRCREV = ""
PV = "0.6.2+git${SRCPV}"
PKGV = "0.6.2+git${GITPKGV}"
PR = "r2"

inherit python-dir autotools pkgconfig

require alliance-crossepg.inc

FILES_${PN} = "/usr/*"
FILES_${PN}-dbg += "/usr/crossepg/scripts/mhw2epgdownloader/.debug"

CFLAGS_append = " -I${STAGING_INCDIR}/libxml2/ -I${STAGING_INCDIR}/${PYTHON_DIR}/"

do_compile() {
	echo ${PKGV} > ${S}/VERSION
	oe_runmake SWIG="swig"
	python -O -m compileall ${S}
}

do_install() {
	oe_runmake 'D=${D}' install
}

pkg_postrm() {
rm -fr /usr/lib/enigma2/python/Plugins/SystemPlugins/CrossEPG > /dev/null 2>&1
}

python populate_packages_prepend() {
	enigma2_plugindir = bb.data.expand('${libdir}/enigma2/python/Plugins', d)
	do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/[a-zA-Z0-9_]+.*$', 'enigma2-plugin-%s', 'Enigma2 Plugin: %s', recursive=True, match_path=True, prepend=True)
	do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\.py$', 'enigma2-plugin-%s-src', 'Enigma2 Plugin: %s', recursive=True, match_path=True, prepend=True)
	do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/(.*/)?\.debug/.*$', 'enigma2-plugin-%s-dbg', 'Enigma2 Plugin: %s', recursive=True, match_path=True, prepend=True)
}
