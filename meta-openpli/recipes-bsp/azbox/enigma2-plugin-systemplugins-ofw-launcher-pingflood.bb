DESCRIPTION = "OFW Launcher by Ping Flood"
RDEPENDS = "enigma2"
DEPENDS = "python-native"
PACKAGE_ARCH = "${MACHINE_ARCH}"
LICENSE = "CLOSED"

SRCREV_pn-${PN} ?= "${AUTOREV}"

inherit gitpkgv pkgconfig

PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"
PR = "r1"

SRC_URI = "git://github.com/OpenAZBox/ofwlauncher-plugin.git;protocol=git"

S = "${WORKDIR}/git"

do_compile() {
	python -O -m compileall ${S}
}

python populate_packages_prepend () {
	enigma2_plugindir = bb.data.expand('${libdir}/enigma2/python/Plugins', d)

	do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/[a-zA-Z0-9_]+.*$', 'enigma2-plugin-%s', 'Enigma2 Plugin: %s', recursive=True, match_path=True, prepend=True)
	do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\.py$', 'enigma2-plugin-%s-src', 'Enigma2 Plugin: %s', recursive=True, match_path=True, prepend=True)
	do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/(.*/)?\.debug/.*$', 'enigma2-plugin-%s-dbg', 'Enigma2 Plugin: %s', recursive=True, match_path=True, prepend=True)
}

do_install() {
	install -d  ${D}/usr/lib/enigma2/python/Plugins/SystemPlugins/OFW_Launcher
	
	install -m 0644 ${S}/*.pyo \
	${D}/usr/lib/enigma2/python/Plugins/SystemPlugins/OFW_Launcher
}

FILES_enigma2-plugin-systemplugins-ofw-launcher-pingflood = "/usr/lib/enigma2/python/Plugins/SystemPlugins/OFW_Launcher"

PACKAGES = "enigma2-plugin-systemplugins-ofw-launcher-pingflood"

PROVIDES="${PACKAGES}"
